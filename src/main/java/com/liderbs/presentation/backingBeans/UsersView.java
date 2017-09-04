package com.liderbs.presentation.backingBeans;

import com.liderbs.exceptions.*;

import com.liderbs.modelo.*;
import com.liderbs.modelo.dto.PaisDTO;
import com.liderbs.modelo.dto.UsersDTO;

import com.liderbs.presentation.businessDelegate.*;

import com.liderbs.utilities.*;

import org.hibernate.Hibernate;
import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;


/**
 * @author Zathura Code Generator http://code.google.com/p/zathura
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class UsersView implements Serializable {
	
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(UsersView.class);
    private InputText txtCellphone;
    private InputText txtFixedphone;
    private InputText txtPassword;
    private InputText txtSaldo;
    private InputText txtStatus;
    private InputText txtUserid;
    private InputText txtUsername;
    private InputText txtIdusers;
    private Calendar txtCreated;
    private Calendar txtLastlogin;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<UsersDTO> data;
    private List<UsersDTO> dataTrainer;
    private UsersDTO selectedUsers;
    private Users entity;
    private Users usuarioapp;
    private Integer idAccount = 0;
    private boolean showDialog;
    private boolean showSearchDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;
    
    //Perfil virfit
    private InputText namesProfile;
    
    //Personal data
    private InputText txtName;
    private InputText txtLastname;
    private SelectOneMenu selectIdentificacion;
    private InputText txtNumeroIdentificacion;
    private InputText txtLugarNacimiento;
    private Date txtFechaNacimiento;
    private SelectOneMenu selectPais;
    private List<SelectItem> listPaises;
    private boolean lockDepto = true;
    private SelectOneMenu selectDepto;
    private List<SelectItem> listDeptos;
    private InputText txtCiudad;
    private InputText txtDireccion;
    private UploadedFile trainerPic;
    
    //Profesional data
    private SelectOneMenu selectTipoEntrenador;
    private InputTextarea txtDescripcionPerfil;
    
   //Academic data
    private SelectOneMenu selectNivelEstudio;
    private InputText txtAreaEstudio;
    private InputText txtCertificacion;
    private InputTextarea txtCertificacionDesc;
    
    //Profesional profile
    private InputTextarea txtProfesionalProfile;
    
    //Profesional Experience
    private InputText txtEmpresa;
    private SelectOneMenu selectPaisExp;
    private List<SelectItem> listPaisesExp;
    private SelectOneMenu selectDeptoExp;
    private List<SelectItem> listDeptosExp;
    private InputText txtCiudadExp;
    private boolean lockDeptoExp = true;
    private InputText txtCargo;
    private Date txtPeriodoInicio;
    private Date txtPeriodoFin;
    private InputTextarea txtFuncionCargo;
    
    //Staff search
    
    private SelectOneMenu selectCategory;
    private List<SelectItem> listCategory;
    private List<UsersDTO> listTrainers;
    
    

    public UsersView() {
        super();
    }
    
    @PostConstruct
    public void init(){
    try{
    	
    
    	Users user = getUsuarioapp();
		Set<Account> list = user.getAccounts();
		
		 for (Iterator<Account> it = list.iterator(); it.hasNext();) {
 		 	Account act = it.next();
 	        
 		 	//Si tiene cuenta activa
 		 	if(act.getAccountStatus() == 1){
 		 		if(act.getAccountDefault() == 1){
 		 			idAccount = act.getIdAccount();
 		 		}
 		 	}
 	    }//end for 
		 
    	}catch(Exception e){
    		log.info(e.toString());
    	}	 
    }
    
    public Users getUsuarioapp() {
		if(usuarioapp==null) {				
			  usuarioapp = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			  //FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("rolsaldosefa_session", usuarioapp.get);				
		}
		return usuarioapp;
	}
    
    
    public void search(){
    
    try{
    	
    	int idCategoria = 0;
    	
    	if(this.listTrainers == null){
    		this.listTrainers = new ArrayList<UsersDTO>();
    	}
    	
    	this.listTrainers.clear();
    	
    	if(FacesUtils.checkInteger(selectCategory) != null){
    		idCategoria = FacesUtils.checkInteger(selectCategory);
    	}
    	
    	List<Category> list = new ArrayList<Category>();
    	
    	list = businessDelegatorView.findByCriteriaInCategory(new Object[]{"idcategory",false, idCategoria, "="},
																									null,
																									null);
    	
    	
    	for(Category cat: list){
    		
    		Hibernate.initialize(cat.getUserses());
    		Set<Users> userses = cat.getUserses();
    		
    		for (Iterator<Users> it = userses.iterator(); it.hasNext(); ) {			
    			Users user = it.next();	
    			this.listTrainers.add(new UsersDTO(user.getIdusers(), user.getName()));
    		}
    		
    		
    	}

    	}catch(Exception e){
    		log.info(e.toString());
    	}
    }
    
    public void addStaff(){
    	setShowSearchDialog(true);
    }
    
    public void action_closeSearch(){
    	setShowSearchDialog(false);
    }
    
    public void populateData(){
    	
    	 try{	
    		 
    		 if(usuarioapp == null){
     	 		FacesUtils.addErrorMessage("Error, no se pudo identificar el usuario, por favor utilice la opcion ayuda");
     	 	}else{
     	 		
     	 		int idUser = usuarioapp.getIdusers();
     	 		
     	 		List<Trainer> list = businessDelegatorView.findByCriteriaInTrainer(new Object[]{"usersIdusers",false, idUser, "="},
     	 																		   null,
     	 																		   null);
     	 		
     	 		int idTrainer = 0;
     	 		
     	 		for(Trainer trainer: list){
     	 			idTrainer = trainer.getIdtrainer();
     	 		}
     	 		
     	 		if(idTrainer == 0){
     	 			FacesUtils.addErrorMessage("No se pudo identificar su perfil de entrenador, por favor contacte con la opcion Ayuda");
     	 		}else{
     	 			
     	 			Trainer trainer = businessDelegatorView.getTrainer(idTrainer);
     	 			Identification identi = businessDelegatorView.getIdentification(trainer.getIdentification().getIdidentification());
     	 			
     	 			txtName.setValue(trainer.getName());
     	 			txtLastname.setValue(trainer.getLastname());
     	 			selectIdentificacion.setValue(identi.getIdidentification());
     	 			txtLugarNacimiento.setValue(trainer.getLugar_nacimiento());
     	 			txtFechaNacimiento = trainer.getBorndate();
     	 			selectPais.setValue(trainer.getCountry());
     	 			selectDepto.setValue(trainer.getRegion());
     	 			txtCiudad.setValue(trainer.getCity());
     	 			txtDireccion.setValue(trainer.getAddress());
     	 			
     	 		}//end if-else
     	 	}//end if-else
    		 
    	 }catch(Exception e){
     		log.info(e.toString());
     	 }
    	
    }
    
    public void savePersonalData(){
    try{	
    	 	if(usuarioapp == null){
    	 		FacesUtils.addErrorMessage("Error, no se pudo identificar el usuario, por favor utilice la opcion ayuda");
    	 	}else{
    	 		
    	 		int idUser = usuarioapp.getIdusers();
    	 		
    	 		List<Trainer> list = businessDelegatorView.findByCriteriaInTrainer(new Object[]{"usersIdusers",false, idUser, "="},
    	 																		   null,
    	 																		   null);
    	 		
    	 		int idTrainer = 0;
    	 		
    	 		for(Trainer trainer: list){
    	 			idTrainer = trainer.getIdtrainer();
    	 		}
    	 		
    	 		if(idTrainer == 0){
    	 			FacesUtils.addErrorMessage("No se pudo identificar su perfil de entrenador, por favor contacte con la opcion Ayuda");
    	 		}else{
    	 			
    	 			Trainer trainer = businessDelegatorView.getTrainer(idTrainer);
    	 			Identification identi = businessDelegatorView.getIdentification(FacesUtils.checkInteger(selectIdentificacion));
    	 			
    	 			trainer.setName(FacesUtils.checkString(txtName));
    	 			trainer.setLastname(FacesUtils.checkString(txtLastname));
    	 			trainer.setIdentification(identi);
    	 			trainer.setLugar_nacimiento(FacesUtils.checkString(txtLugarNacimiento));
    	 			trainer.setBorndate(txtFechaNacimiento);
    	 			trainer.setCountry(FacesUtils.checkInteger(selectPais));
    	 			trainer.setRegion(FacesUtils.checkInteger(selectDepto));
    	 			trainer.setCity(FacesUtils.checkString(txtCiudad).toUpperCase());
    	 			trainer.setAddress(FacesUtils.checkString(txtDireccion));
    	 			
    	 			if(trainerPic != null) {
    	 				
    	 				InputStream filecontent = null;
    	 				String picName = "";
    	 				OutputStream out = null;
    	 			
    	 				try {
    	 				
    	 				final String path = "C:\\desarrollo\\workspaces\\trainerpics\\";
    	 	            
    	 				picName = trainerPic.getFileName();
    	 				filecontent = trainerPic.getInputstream();
    	 				
    	 				out = new FileOutputStream(new File(path + picName));
    	 				
    	 				int read = 0;
    	 		        final byte[] bytes = new byte[1024];
    	 				
    	 				while ((read = filecontent.read(bytes)) != -1) {
    	 		            out.write(bytes, 0, read);
    	 		        }
    	 				
    	 		        log.info("New file " + picName + " created at " + path);
    	 		        
    	 				 } catch (FileNotFoundException fne) {
    	 					FacesUtils.addErrorMessage("Archivo seleccionado invalido o protegido");
    	 					log.info(fne.toString());
    	 			    } finally {
    	 			        if (out != null) {
    	 			            out.close();
    	 			        }
    	 			        if (filecontent != null) {
    	 			            filecontent.close();
    	 			        }
    	 			    }//end try catch  		
    	 	        }//end picture upload
    	 			
    	 			businessDelegatorView.updateTrainer(trainer);
    	 			FacesUtils.addInfoMessage("El entrenador ha sido actualizado satisfactoriamente");
    	 			
    	 		}//end if-else
    	 	}//end user app detect
    	 	
    	}catch(Exception e){
    		log.info(e.toString());
    	}
    	 	
    }
    
    public void saveDatosProf(){
	 	
    }
    
    public void saveAcademic(){
	 	
    }
    
    public void saveCertificacion(){
	 	
    }
    
    public void saveProfProfile(){
	 	
    }
    
    public void saveProfExp(){
	 	
    }
    
    
    
    public void buscarDeptoExp(){
    	
    	if(FacesUtils.checkLong(selectPaisExp) != null){
    		
    	this.listDeptosExp = new ArrayList<SelectItem>();	
    	
    	try{
    		long idPais = FacesUtils.checkLong(selectPaisExp);
    		
    		this.listDeptosExp.clear();
    		
    		List<Estado> list = businessDelegatorView.findByCriteriaInEstado(new Object[]{"pais.idpais",false, idPais, "="},
    																		 null,
    																		 null);
    		
    		if(list.size() > 0){
    			
    			for(Estado estado:list){
    				this.listDeptosExp.add(new SelectItem(estado.getIdestado(), estado.getEstadonombre()));
    			}
    			
    			setLockDeptoExp(false);
    			
    		}
    		
    	}catch(Exception e){
    		log.info(e.toString());
    	}
    	}
    }
    
    public void buscarDepto(){
    	if(FacesUtils.checkLong(selectPais) != null){
    		
    	this.listDeptos = new ArrayList<SelectItem>();	
    	
    	try{
    		long idPais = FacesUtils.checkLong(selectPais);
    		
    		this.listDeptos.clear();
    		
    		List<Estado> list = businessDelegatorView.findByCriteriaInEstado(new Object[]{"pais.idpais",false, idPais, "="},
    																		 null,
    																		 null);
    		
    		if(list.size() > 0){
    			
    			for(Estado estado:list){
    				this.listDeptos.add(new SelectItem(estado.getIdestado(), estado.getEstadonombre()));
    			}
    			
    			setLockDepto(false);
    			
    		}
    		
    	}catch(Exception e){
    		log.info(e.toString());
    	}
    	}
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            UsersDTO usersDTO = (UsersDTO) e.getObject();

            if (txtCellphone == null) {
                txtCellphone = new InputText();
            }

            txtCellphone.setValue(usersDTO.getCellphone());

            if (txtFixedphone == null) {
                txtFixedphone = new InputText();
            }

            txtFixedphone.setValue(usersDTO.getFixedphone());

            if (txtLastname == null) {
                txtLastname = new InputText();
            }

            txtLastname.setValue(usersDTO.getEmail());

            if (txtName == null) {
                txtName = new InputText();
            }

            txtName.setValue(usersDTO.getName());

            if (txtPassword == null) {
                txtPassword = new InputText();
            }

            txtPassword.setValue(usersDTO.getPassword());

            if (txtSaldo == null) {
                txtSaldo = new InputText();
            }

            txtSaldo.setValue(usersDTO.getSaldo());

            if (txtStatus == null) {
                txtStatus = new InputText();
            }

            txtStatus.setValue(usersDTO.getStatus());

            if (txtUserid == null) {
                txtUserid = new InputText();
            }

            txtUserid.setValue(usersDTO.getUserid());

            if (txtUsername == null) {
                txtUsername = new InputText();
            }

            txtUsername.setValue(usersDTO.getUsername());

            if (txtIdusers == null) {
                txtIdusers = new InputText();
            }

            txtIdusers.setValue(usersDTO.getIdusers());

            if (txtCreated == null) {
                txtCreated = new Calendar();
            }

            txtCreated.setValue(usersDTO.getCreated());

            if (txtLastlogin == null) {
                txtLastlogin = new Calendar();
            }

            txtLastlogin.setValue(usersDTO.getLastlogin());

            Integer idusers = FacesUtils.checkInteger(txtIdusers);
            entity = businessDelegatorView.getUsers(idusers);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedUsers = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
    	
        entity = null;
        selectedUsers = null;

        if (txtCellphone != null) {
            txtCellphone.setValue(null);
            txtCellphone.setDisabled(false);
        }

        if (txtFixedphone != null) {
            txtFixedphone.setValue(null);
            txtFixedphone.setDisabled(false);
        }

        if (txtLastname != null) {
            txtLastname.setValue(null);
            txtLastname.setDisabled(false);
        }

        if (txtName != null) {
            txtName.setValue(null);
            txtName.setDisabled(false);
        }

        if (txtPassword != null) {
            txtPassword.setValue(null);
            txtPassword.setDisabled(false);
        }

        if (txtSaldo != null) {
            txtSaldo.setValue(null);
            txtSaldo.setDisabled(false);
        }

        if (txtStatus != null) {
            txtStatus.setValue(null);
            txtStatus.setDisabled(false);
        }

        if (txtUserid != null) {
            txtUserid.setValue(null);
            txtUserid.setDisabled(false);
        }

        if (txtUsername != null) {
            txtUsername.setValue(null);
            txtUsername.setDisabled(false);
        }

        if (txtCreated != null) {
            txtCreated.setValue(null);
            txtCreated.setDisabled(false);
        }

        if (txtLastlogin != null) {
            txtLastlogin.setValue(null);
            txtLastlogin.setDisabled(false);
        }

        if (txtIdusers != null) {
            txtIdusers.setValue(null);
            txtIdusers.setDisabled(false);
        }

        if (btnSave != null) {
            btnSave.setDisabled(false);
        }

        if (btnDelete != null) {
            btnDelete.setDisabled(false);
        }

        return "";
    }

    public void listener_txtCreated() {
        Date inputDate = (Date) txtCreated.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtLastlogin() {
        Date inputDate = (Date) txtLastlogin.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtId() {
        try {
            Integer idusers = FacesUtils.checkInteger(txtIdusers);
            entity = (idusers != null)
                ? businessDelegatorView.getUsers(idusers) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtCellphone.setDisabled(false);
            txtFixedphone.setDisabled(false);
            txtLastname.setDisabled(false);
            txtName.setDisabled(false);
            txtPassword.setDisabled(false);
            txtSaldo.setDisabled(false);
            txtStatus.setDisabled(false);
            txtUserid.setDisabled(false);
            txtUsername.setDisabled(false);
            txtCreated.setDisabled(false);
            txtLastlogin.setDisabled(false);
            txtIdusers.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtCellphone.setValue(entity.getCellphone());
            txtCellphone.setDisabled(false);
            txtCreated.setValue(entity.getCreated());
            txtCreated.setDisabled(false);
            txtFixedphone.setValue(entity.getFixedphone());
            txtFixedphone.setDisabled(false);
            txtLastlogin.setValue(entity.getLastlogin());
            txtLastlogin.setDisabled(false);
            txtLastname.setValue(entity.getEmail());
            txtLastname.setDisabled(false);
            txtName.setValue(entity.getName());
            txtName.setDisabled(false);
            txtPassword.setValue(entity.getPassword());
            txtPassword.setDisabled(false);
            txtSaldo.setValue(entity.getSaldo());
            txtSaldo.setDisabled(false);
            txtStatus.setValue(entity.getStatus());
            txtStatus.setDisabled(false);
            txtUserid.setValue(entity.getUserid());
            txtUserid.setDisabled(false);
            txtUsername.setValue(entity.getUsername());
            txtUsername.setDisabled(false);
            txtIdusers.setValue(entity.getIdusers());
            txtIdusers.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
    	
        selectedUsers = (UsersDTO) (evt.getComponent().getAttributes()
                                       .get("selectedUsers"));
        
        if (txtCellphone != null) {
        	txtCellphone.setValue(selectedUsers.getCellphone());
        	txtCellphone.setDisabled(false);
        }
        
        if (txtFixedphone != null) {
        	txtFixedphone.setValue(selectedUsers.getFixedphone());
            txtFixedphone.setDisabled(false);
        }
        
        if (txtLastname != null) {
        	if(selectedUsers.getEmail() != null){
        		txtLastname.setValue(selectedUsers.getEmail().toUpperCase());
                txtLastname.setDisabled(false);
        	}
        }
        
        if (txtName != null) {
        	txtName.setValue(selectedUsers.getName());
            txtName.setDisabled(false);
        }
        
        if (txtPassword != null) {
        	txtPassword.setValue(selectedUsers.getPassword());
            txtPassword.setDisabled(false);
        }
        
        if (txtUsername != null) {
        	txtUsername.setValue(selectedUsers.getUsername().toUpperCase());
            txtUsername.setDisabled(false);
        }
        
        
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedUsers == null) && (entity == null)) {
                action_create();
            } else {
                action_modify();
            }

            data = null;
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_create() {
        try {
        	
        	Date today = new Date();
        	
            entity = new Users();

            entity.setCellphone(FacesUtils.checkString(txtCellphone));
            entity.setCreated(today);
            entity.setFixedphone(FacesUtils.checkString(txtFixedphone));
            entity.setLastlogin(FacesUtils.checkDate(txtLastlogin));
            entity.setEmail(FacesUtils.checkString(txtLastname));
            entity.setName(FacesUtils.checkString(txtName));
            entity.setPassword(FacesUtils.checkString(txtPassword));
            entity.setSaldo(FacesUtils.checkFloat(txtSaldo));
            entity.setStatus(FacesUtils.checkInteger(txtStatus));
            entity.setUserid(FacesUtils.checkString(txtUserid));
            entity.setUsername(FacesUtils.checkString(txtUsername).toUpperCase());
            
            //entity.setAccounts(FacesUtils.checkAccount(txtAccounts));
            businessDelegatorView.saveUsers(entity);
            FacesUtils.addInfoMessage("Usuario creado satisfactoriamente");
            action_clear();
        } catch (Exception e) {
            entity = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modify() {
        try {
            if (entity == null) {
                Integer idusers = new Integer(selectedUsers.getIdusers());
                entity = businessDelegatorView.getUsers(idusers);
            }

            entity.setCellphone(FacesUtils.checkString(txtCellphone));
            entity.setFixedphone(FacesUtils.checkString(txtFixedphone));
            entity.setEmail(FacesUtils.checkString(txtLastname));
            entity.setName(FacesUtils.checkString(txtName));
            entity.setPassword(FacesUtils.checkString(txtPassword));
            entity.setSaldo(FacesUtils.checkFloat(txtSaldo));
            entity.setStatus(FacesUtils.checkInteger(txtStatus));
            entity.setUsername(FacesUtils.checkString(txtUsername).toUpperCase());
            
            businessDelegatorView.updateUsers(entity);
            FacesUtils.addInfoMessage("Usuario modificado satisfactoriamente");
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedUsers = (UsersDTO) (evt.getComponent().getAttributes()
                                           .get("selectedUsers"));

            Integer idusers = new Integer(selectedUsers.getIdusers());
            entity = businessDelegatorView.getUsers(idusers);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer idusers = FacesUtils.checkInteger(txtIdusers);
            entity = businessDelegatorView.getUsers(idusers);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteUsers(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
            data = null;
        } catch (Exception e) {
            throw e;
        }
    }

    public String action_closeDialog() {
        setShowDialog(false);
        action_clear();

        return "";
    }

    public String actionDeleteDataTableEditable(ActionEvent evt) {
        try {
            selectedUsers = (UsersDTO) (evt.getComponent().getAttributes()
                                           .get("selectedUsers"));

            Integer idusers = new Integer(selectedUsers.getIdusers());
            entity = businessDelegatorView.getUsers(idusers);
            businessDelegatorView.deleteUsers(entity);
            data.remove(selectedUsers);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String cellphone, Date created,
        String fixedphone, Integer idusers, Date lastlogin, String lastname,
        String name, String password, Float saldo, Integer status,
        String userid, String username) throws Exception {
        try {
            entity.setCellphone(FacesUtils.checkString(cellphone));
            entity.setCreated(FacesUtils.checkDate(created));
            entity.setFixedphone(FacesUtils.checkString(fixedphone));
            entity.setLastlogin(FacesUtils.checkDate(lastlogin));
            entity.setEmail(FacesUtils.checkString(lastname));
            entity.setName(FacesUtils.checkString(name));
            String hash = texMD5(FacesUtils.checkString(password));
            entity.setPassword(hash);
            entity.setSaldo(FacesUtils.checkFloat(saldo));
            entity.setStatus(FacesUtils.checkInteger(status));
            entity.setUserid(FacesUtils.checkString(userid));
            entity.setUsername(FacesUtils.checkString(username));
            businessDelegatorView.updateUsers(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("UsersView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }
    
	public String texMD5(String cadena) {
		String hash=cadena;
       	 byte[] defaultBytes =cadena.getBytes();	        	
       	 MessageDigest algorithm;
		try {
			algorithm = MessageDigest.getInstance("MD5");		
			algorithm.reset();
			algorithm.update(defaultBytes);
			byte messageDigest[] = algorithm.digest();	        		            
			StringBuffer hexString = new StringBuffer();
			for (int i=0;i<messageDigest.length;i++) {
				 int val = 0xff &  messageDigest[i];
				  if (val < 16)
                     hexString.append("0");
                 hexString.append(Integer.toHexString(val));
                 //hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
			}	        			        			
			hash=hexString+"";	
		return hash;
		} catch (NoSuchAlgorithmException e) {
			return null;
		}	
	}

    public InputText getTxtCellphone() {
        return txtCellphone;
    }

    public void setTxtCellphone(InputText txtCellphone) {
        this.txtCellphone = txtCellphone;
    }

    public InputText getTxtFixedphone() {
        return txtFixedphone;
    }

    public void setTxtFixedphone(InputText txtFixedphone) {
        this.txtFixedphone = txtFixedphone;
    }

    public InputText getTxtLastname() {
        return txtLastname;
    }

    public void setTxtLastname(InputText txtLastname) {
        this.txtLastname = txtLastname;
    }

    public InputText getTxtName() {
        return txtName;
    }

    public void setTxtName(InputText txtName) {
        this.txtName = txtName;
    }

    public InputText getTxtPassword() {
        return txtPassword;
    }

    public void setTxtPassword(InputText txtPassword) {
        this.txtPassword = txtPassword;
    }

    public InputText getTxtSaldo() {
        return txtSaldo;
    }

    public void setTxtSaldo(InputText txtSaldo) {
        this.txtSaldo = txtSaldo;
    }

    public InputText getTxtStatus() {
        return txtStatus;
    }

    public void setTxtStatus(InputText txtStatus) {
        this.txtStatus = txtStatus;
    }

    public InputText getTxtUserid() {
        return txtUserid;
    }

    public void setTxtUserid(InputText txtUserid) {
        this.txtUserid = txtUserid;
    }

    public InputText getTxtUsername() {
        return txtUsername;
    }

    public void setTxtUsername(InputText txtUsername) {
        this.txtUsername = txtUsername;
    }

    public Calendar getTxtCreated() {
        return txtCreated;
    }

    public void setTxtCreated(Calendar txtCreated) {
        this.txtCreated = txtCreated;
    }

    public Calendar getTxtLastlogin() {
        return txtLastlogin;
    }

    public void setTxtLastlogin(Calendar txtLastlogin) {
        this.txtLastlogin = txtLastlogin;
    }

    public InputText getTxtIdusers() {
        return txtIdusers;
    }

    public void setTxtIdusers(InputText txtIdusers) {
        this.txtIdusers = txtIdusers;
    }

    public List<UsersDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataUsers();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<UsersDTO> usersDTO) {
        this.data = usersDTO;
    }

    public UsersDTO getSelectedUsers() {
        return selectedUsers;
    }

    public void setSelectedUsers(UsersDTO users) {
        this.selectedUsers = users;
    }

    public CommandButton getBtnSave() {
        return btnSave;
    }

    public void setBtnSave(CommandButton btnSave) {
        this.btnSave = btnSave;
    }

    public CommandButton getBtnModify() {
        return btnModify;
    }

    public void setBtnModify(CommandButton btnModify) {
        this.btnModify = btnModify;
    }

    public CommandButton getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(CommandButton btnDelete) {
        this.btnDelete = btnDelete;
    }

    public CommandButton getBtnClear() {
        return btnClear;
    }

    public void setBtnClear(CommandButton btnClear) {
        this.btnClear = btnClear;
    }

    public TimeZone getTimeZone() {
        return java.util.TimeZone.getDefault();
    }

    public IBusinessDelegatorView getBusinessDelegatorView() {
        return businessDelegatorView;
    }

    public void setBusinessDelegatorView(
        IBusinessDelegatorView businessDelegatorView) {
        this.businessDelegatorView = businessDelegatorView;
    }

    public boolean isShowDialog() {
        return showDialog;
    }

    public void setShowDialog(boolean showDialog) {
        this.showDialog = showDialog;
    }

	public InputText getNamesProfile() {
		return namesProfile;
	}

	public void setNamesProfile(InputText namesProfile) {
		this.namesProfile = namesProfile;
	}

	public SelectOneMenu getSelectIdentificacion() {
		return selectIdentificacion;
	}

	public void setSelectIdentificacion(SelectOneMenu selectIdentificacion) {
		this.selectIdentificacion = selectIdentificacion;
	}

	public InputText getTxtNumeroIdentificacion() {
		return txtNumeroIdentificacion;
	}

	public void setTxtNumeroIdentificacion(InputText txtNumeroIdentificacion) {
		this.txtNumeroIdentificacion = txtNumeroIdentificacion;
	}

	
	public InputText getTxtLugarNacimiento() {
		return txtLugarNacimiento;
	}

	public void setTxtLugarNacimiento(InputText txtLugarNacimiento) {
		this.txtLugarNacimiento = txtLugarNacimiento;
	}

	public Date getTxtFechaNacimiento() {
		return txtFechaNacimiento;
	}

	public void setTxtFechaNacimiento(Date txtFechaNacimiento) {
		this.txtFechaNacimiento = txtFechaNacimiento;
	}

	public SelectOneMenu getSelectPais() {
		return selectPais;
	}

	public void setSelectPais(SelectOneMenu selectPais) {
		this.selectPais = selectPais;
	}

	public SelectOneMenu getSelectDepto() {
		return selectDepto;
	}

	public void setSelectDepto(SelectOneMenu selectDepto) {
		this.selectDepto = selectDepto;
	}


	public InputText getTxtDireccion() {
		return txtDireccion;
	}

	public void setTxtDireccion(InputText txtDireccion) {
		this.txtDireccion = txtDireccion;
	}

	public SelectOneMenu getSelectTipoEntrenador() {
		return selectTipoEntrenador;
	}

	public void setSelectTipoEntrenador(SelectOneMenu selectTipoEntrenador) {
		this.selectTipoEntrenador = selectTipoEntrenador;
	}

	public InputTextarea getTxtDescripcionPerfil() {
		return txtDescripcionPerfil;
	}

	public void setTxtDescripcionPerfil(InputTextarea txtDescripcionPerfil) {
		this.txtDescripcionPerfil = txtDescripcionPerfil;
	}

	public SelectOneMenu getSelectNivelEstudio() {
		return selectNivelEstudio;
	}

	public void setSelectNivelEstudio(SelectOneMenu selectNivelEstudio) {
		this.selectNivelEstudio = selectNivelEstudio;
	}

	public InputText getTxtAreaEstudio() {
		return txtAreaEstudio;
	}

	public void setTxtAreaEstudio(InputText txtAreaEstudio) {
		this.txtAreaEstudio = txtAreaEstudio;
	}

	public InputText getTxtCertificacion() {
		return txtCertificacion;
	}

	public void setTxtCertificacion(InputText txtCertificacion) {
		this.txtCertificacion = txtCertificacion;
	}

	public InputTextarea getTxtCertificacionDesc() {
		return txtCertificacionDesc;
	}

	public void setTxtCertificacionDesc(InputTextarea txtCertificacionDesc) {
		this.txtCertificacionDesc = txtCertificacionDesc;
	}

	public InputTextarea getTxtProfesionalProfile() {
		return txtProfesionalProfile;
	}

	public void setTxtProfesionalProfile(InputTextarea txtProfesionalProfile) {
		this.txtProfesionalProfile = txtProfesionalProfile;
	}

	public InputText getTxtEmpresa() {
		return txtEmpresa;
	}

	public void setTxtEmpresa(InputText txtEmpresa) {
		this.txtEmpresa = txtEmpresa;
	}

	public SelectOneMenu getSelectPaisExp() {
		return selectPaisExp;
	}

	public void setSelectPaisExp(SelectOneMenu selectPaisExp) {
		this.selectPaisExp = selectPaisExp;
	}

	public SelectOneMenu getSelectDeptoExp() {
		return selectDeptoExp;
	}

	public void setSelectDeptoExp(SelectOneMenu selectDeptoExp) {
		this.selectDeptoExp = selectDeptoExp;
	}

	public InputText getTxtCiudadExp() {
		return txtCiudadExp;
	}

	public void setTxtCiudadExp(InputText txtCiudadExp) {
		this.txtCiudadExp = txtCiudadExp;
	}

	public InputText getTxtCargo() {
		return txtCargo;
	}

	public void setTxtCargo(InputText txtCargo) {
		this.txtCargo = txtCargo;
	}

	public Date getTxtPeriodoInicio() {
		return txtPeriodoInicio;
	}

	public void setTxtPeriodoInicio(Date txtPeriodoInicio) {
		this.txtPeriodoInicio = txtPeriodoInicio;
	}

	public Date getTxtPeriodoFin() {
		return txtPeriodoFin;
	}

	public void setTxtPeriodoFin(Date txtPeriodoFin) {
		this.txtPeriodoFin = txtPeriodoFin;
	}

	public InputTextarea getTxtFuncionCargo() {
		return txtFuncionCargo;
	}

	public void setTxtFuncionCargo(InputTextarea txtFuncionCargo) {
		this.txtFuncionCargo = txtFuncionCargo;
	}

	public List<SelectItem> getListPaises() {
		
		if(this.listPaises == null){
			
			try{
			
			this.listPaises = new ArrayList<SelectItem>();
			
			List<PaisDTO> list = businessDelegatorView.getDataPais();
			
			for(PaisDTO pais: list){
				this.listPaises.add(new SelectItem(pais.getIdpais(), pais.getPaisnombre()));
			}
			
			
			}catch(Exception e){
				log.info(e.toString());
			}
			
		}
		
		return listPaises;
	}

	public void setListPaises(List<SelectItem> listPaises) {
		this.listPaises = listPaises;
	}

	public List<SelectItem> getListDeptos() {
		return listDeptos;
	}

	public void setListDeptos(List<SelectItem> listDeptos) {
		this.listDeptos = listDeptos;
	}

	public InputText getTxtCiudad() {
		return txtCiudad;
	}

	public void setTxtCiudad(InputText txtCiudad) {
		this.txtCiudad = txtCiudad;
	}

	public boolean isLockDepto() {
		return lockDepto;
	}

	public void setLockDepto(boolean lockDepto) {
		this.lockDepto = lockDepto;
	}

	public List<SelectItem> getListPaisesExp() {
		
		if(this.listPaisesExp == null){
			
			try{
			
			this.listPaisesExp = new ArrayList<SelectItem>();
			
			List<PaisDTO> list = businessDelegatorView.getDataPais();
			
			for(PaisDTO pais: list){
				this.listPaisesExp.add(new SelectItem(pais.getIdpais(), pais.getPaisnombre()));
			}
			
			
			}catch(Exception e){
				log.info(e.toString());
			}
			
		}
		
		return listPaisesExp;
	}

	public void setListPaisesExp(List<SelectItem> listPaisesExp) {
		this.listPaisesExp = listPaisesExp;
	}

	public List<SelectItem> getListDeptosExp() {
		return listDeptosExp;
	}

	public void setListDeptosExp(List<SelectItem> listDeptosExp) {
		this.listDeptosExp = listDeptosExp;
	}

	public boolean isLockDeptoExp() {
		return lockDeptoExp;
	}

	public void setLockDeptoExp(boolean lockDeptoExp) {
		this.lockDeptoExp = lockDeptoExp;
	}

	public boolean isShowSearchDialog() {
		return showSearchDialog;
	}

	public void setShowSearchDialog(boolean showSearchDialog) {
		this.showSearchDialog = showSearchDialog;
	}

	public SelectOneMenu getSelectCategory() {
		return selectCategory;
	}

	public void setSelectCategory(SelectOneMenu selectCategory) {
		this.selectCategory = selectCategory;
	}

	public List<SelectItem> getListCategory() {
		
		if(this.listCategory == null){
			
		try{
			
			List<Category> list = businessDelegatorView.findByCriteriaInCategory(new Object[]{"level.idlevel",false, 1, "="},
																				 null,
																				 null);
			
			if(list.size() > 0){
				
				this.listCategory = new ArrayList<SelectItem>();
				
				for(Category cat:list){
					this.listCategory.add(new SelectItem(cat.getIdcategory(), cat.getDescription()));
				}
				
			}
			
		}catch(Exception e){
			log.info(e.toString());
		}
			
		}
		
		return listCategory;
	}

	public void setListCategory(List<SelectItem> listCategory) {
		this.listCategory = listCategory;
	}

	public List<UsersDTO> getListTrainers() {
		
		return listTrainers;
	}

	public void setListTrainers(List<UsersDTO> listTrainers) {
		this.listTrainers = listTrainers;
	}

	public List<UsersDTO> getDataTrainer() {
		populateData();
		return dataTrainer;
	}

	public void setDataTrainer(List<UsersDTO> dataTrainer) {
		this.dataTrainer = dataTrainer;
	}

	public UploadedFile getTrainerPic() {
		return trainerPic;
	}

	public void setTrainerPic(UploadedFile trainerPic) {
		this.trainerPic = trainerPic;
	}
	 
}
