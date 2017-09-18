package com.liderbs.presentation.backingBeans;

import com.liderbs.exceptions.*;

import com.liderbs.modelo.*;
import com.liderbs.modelo.dto.CategoryDTO;
import com.liderbs.modelo.dto.PaisDTO;
import com.liderbs.modelo.dto.UsersDTO;
import com.liderbs.modelo.dto.UsuariosCategoriasDTO;
import com.liderbs.presentation.businessDelegate.*;

import com.liderbs.utilities.*;

import org.hibernate.Hibernate;
import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.selectcheckboxmenu.SelectCheckboxMenu;
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
import java.io.IOException;
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
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
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
    private List<UsuariosCategoriasDTO> dataCategory;
    private UsersDTO selectedUsers;
    private CategoryDTO selectedCat;
    private Users entity;
    private Users usuarioapp;
    private Integer idAccount = 0;
    private boolean showDialog;
    private boolean showSearchDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;
    
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat formatImage = new SimpleDateFormat("ddMMyyyy");
    
    
    //Perfil virfit
    private InputText namesProfile;
    private List<Category> listCategoriesTrainer;
    private List<Courses> listCoursesTrainer;
    private List<Experience> listExperienceTrainer;
    private List<Courses> dataCoursesTrainer;
    private List<Experience> dataExperienceTrainer;
    private Courses selectedCertification;
    private Experience selectedExperience;
    
    //Personal data
    private String txtName;
    private String txtLastname;
    private Integer selectIdentificacion;
    private String txtNumeroIdentificacion;
    private String txtLugarNacimiento;
    private Date txtFechaNacimiento;
    private Integer selectPais;
    private List<SelectItem> listPaises;
    private boolean lockDepto = true;
    private Integer selectDepto;
    private List<SelectItem> listDeptos;
    private String txtCiudad;
    private String txtDireccion;
    private UploadedFile trainerPic;
    
    //Profesional data
    private Integer[] selectTipoEntrenador;
    private InputTextarea txtDescripcionPerfil;
    
   //Academic data
    private SelectOneMenu selectNivelEstudio;
    private InputText txtAreaEstudio;
    private InputText txtCertificacion;
    private Date txtFechaCertificacion;
    private InputText txtEnteCertificador;
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
    private Map<String,Integer> listCategory;
    private List<UsersDTO> listTrainers;
    
    //Config
    private Date minDate;
    
    //Profile first page
    private String firstNameDesc;
    private String lastNameDesc;
    private String identiDesc;
    private String identiNumberDesc;
    private String bornPlaceDesc;
    private String bornDateDesc;
    private String trainerPicDesc;  
    private String countryResDesc;
    private String stateResDesc;
    private String cityResDesc;
    private String addressResDesc;
    
    private String nivelAcademicoDesc;
    private String areaEstudioDesc;
    
    //Rating
    private Integer rating = 0;
    
    //Render tabs
    private boolean renderTabsEdit;
    private Integer recruitmentProcessStatus;

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
		 
		 java.util.Calendar fechaInicio = java.util.Calendar.getInstance();
		 fechaInicio.set(java.util.Calendar.YEAR, 1950);
		 
		 minDate = fechaInicio.getTime();
		 
		 int idUser = user.getIdusers();
	 		
	 	 List<Trainer> listTrainer = businessDelegatorView.findByCriteriaInTrainer(new Object[]{"usersIdusers",false, idUser, "="},
	 																		   null,
	 																		   null);
	 		
	 		int idTrainer = 0;
	 		
	 		for(Trainer trainer: listTrainer){
	 			idTrainer = trainer.getIdtrainer();
	 		}
	 		
	 		if(idTrainer == 0){
	 			FacesUtils.addErrorMessage("No se pudo identificar su perfil de entrenador, por favor contacte con la opcion Ayuda");
	 		}else{
	 			
	 			Trainer trainer = businessDelegatorView.getTrainer(idTrainer);
	 			
	 			recruitmentProcessStatus = trainer.getTrainerProfStatus();
	 			
	 			if(trainer.getTrainerProfStatus() == 0){
	 				renderTabsEdit = true;
	 			}else{
	 				renderTabsEdit = false;
	 			}
	 			
	 			
	 		}//end if
		 
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
    	
    	rating = 0;
    	
    	 try{	
    		 
    		 if(usuarioapp == null){
     	 		FacesUtils.addErrorMessage("Error, no se pudo identificar el usuario, por favor utilice la opcion ayuda");
     	 	}else{
     	 		
     	 		int idUser = usuarioapp.getIdusers();
     	 		
     	 		Users usuario = businessDelegatorView.getUsers(idUser);
     	 		
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
     	 			
     	 			txtName = trainer.getName();
     	 			txtLastname = trainer.getLastname();
     	 			selectIdentificacion = identi.getIdidentification();
     	 			txtNumeroIdentificacion = trainer.getTrainer_id_number();
     	 			txtLugarNacimiento = trainer.getLugar_nacimiento();
     	 			txtFechaNacimiento = trainer.getBorndate();
     	 			selectPais = trainer.getCountry();
     	 			buscarDepto();
     	 			selectDepto = trainer.getRegion();
     	 			txtCiudad = trainer.getCity();
     	 			txtDireccion = trainer.getAddress();
     	 			
     	 			if(trainer.getCountry() != null){
     	 				Pais country = businessDelegatorView.getPais(trainer.getCountry());
     	 				countryResDesc = country.getPaisnombre().toUpperCase();
     	 			}
     	 			
     	 			if(trainer.getRegion() != null){
     	 				Estado state = businessDelegatorView.getEstado(trainer.getRegion());
         	 			stateResDesc = state.getEstadonombre().toUpperCase();
     	 			}
     	 			
     	 		 	firstNameDesc = ((trainer.getName() != null) ? trainer.getName().toUpperCase() : null);
     	 			lastNameDesc = ((trainer.getLastname() != null) ? trainer.getLastname().toUpperCase():null);
     	 			identiDesc = ((identi.getName() != null) ? identi.getName().toUpperCase():null);
     	 			identiNumberDesc = ((trainer.getTrainer_id_number() != null) ? trainer.getTrainer_id_number():null);
     	 			bornPlaceDesc = (( trainer.getLugar_nacimiento() != null) ? trainer.getLugar_nacimiento().toUpperCase():null);
     	 			Date fechaNac = ((trainer.getBorndate() != null) ? trainer.getBorndate():null);
     	 			
     	 			
     	 			if(firstNameDesc != null && identiNumberDesc != null && bornPlaceDesc != null){
     	 				rating++;
     	 			}
     	 			
     	 			if(fechaNac != null){
     	 				bornDateDesc = ((format.format(fechaNac) != null) ? format.format(fechaNac):null);
     	 			}
     	 			
     	 			trainerPicDesc = ((trainer.getTrainer_picture() != null) ? trainer.getTrainer_picture():null);
     	 			cityResDesc = ((trainer.getCity() != null) ? trainer.getCity().toUpperCase():null);
     	 		    addressResDesc = ((trainer.getAddress() != null) ? trainer.getAddress().toUpperCase():null);
     	 			
     	 		    
     	 		    if(trainerPicDesc != null){
     	 		    	rating++;
   	 				}else{
   	 					trainerPicDesc = "user-icon.png";
   	 				}
     	 		    
     	 		    //Fill groupal class
     	 		    
     	 		    Hibernate.initialize(usuario.getCategories());
     	 	        Set<Category> categories = usuario.getCategories();
     	 	        
     	 	        selectTipoEntrenador = new Integer[categories.size()];
     	 	        
     	 	        
     	 	        if(categories.size() > 0){
   	 					rating++;
   	 				}
     	 	        
     	 	        int i = 0;
     	 	        
     	 	        for (Iterator<Category> it = categories.iterator(); it.hasNext(); ) {
     	 	   		 
     	 			 	Category opt = it.next();
     	 			 	selectTipoEntrenador[i] = opt.getIdcategory();
     	 			 	i++;
     	 	        }
     	 	        
     	 	        //Fill academic level
     	 	        
     	 	        Academiclevel academic = trainer.getAcademiclevel();
     	 	        String academicArea = trainer.getAcademicArea();
     	 	       
     	 	        if(academic != null){
     	 	        	selectNivelEstudio.setValue(academic.getIdacademiclevel());
         	 	        nivelAcademicoDesc = academic.getName().toUpperCase();
     	 	        }
     	 	        
     	 	        if(academicArea != null){
     	 	        	txtAreaEstudio.setValue(academicArea);
     	 	        	areaEstudioDesc = trainer.getAcademicArea().toUpperCase();
     	 	        }
     	 	        
     	 	      if(academic != null){
 	 					rating++;
 	 				}
     	 	       
     	 		}//end if-else
     	 	}//end if-else
    		 
    	 }catch(Exception e){
     		log.info(e.toString());
     	 }
    	
    }
    
    public void sendToValidate(){
    	try{
    		
    		if(usuarioapp == null){
    	 		FacesUtils.addErrorMessage("Error, no se pudo identificar el usuario, por favor utilice la opcion ayuda");
    		}else if(rating < 3){
    			FacesUtils.addErrorMessage("El perfil no tiene la calidad suficiente para ser evaluado, es necesario que complete mas informacion");
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
    	 			trainer.setTrainerProfStatus(1);
    	 			businessDelegatorView.updateTrainer(trainer);
    	 			FacesUtils.addInfoMessage("Su perfil se encuentra en evaluacion, en los siguientes 5 dias habiles recibira una respuesta por correo electronico.");
    	 		
    	 			String url = ("profile.xhtml");
        	    	FacesContext fc = FacesContext.getCurrentInstance();
        	    	ExternalContext ec = fc.getExternalContext();
        	    	
        	    	try {
        	    			ec.redirect(url);
        	    	} catch (IOException e) {
        	    		e.printStackTrace();
        	    	}
        	    	
    	 			
    	 		}//end validation trainer
    	 		
    	 	}//end validation user
    		
    	}catch(Exception e){
    		log.info(e.toString());
    	}
    }
    
    public void savePersonalData(){
    
    try{	
    	
    		if(txtName == null){
    			FacesUtils.addErrorMessage("Por favor ingrese su nombre");
    		}else if(txtLastname == null){
    			FacesUtils.addErrorMessage("Por favor ingrese sus apellidos");
    		}else if(selectIdentificacion == null){
    			FacesUtils.addErrorMessage("Por favor seleccione un tipo de identificacion");
    		}else if(txtNumeroIdentificacion == null){
    			FacesUtils.addErrorMessage("Por favor ingrese su numero de identificacion");
    		}else if(txtLugarNacimiento == null){
    			FacesUtils.addErrorMessage("Por favor ingrese su lugar de nacimiento");
    		}else if(txtFechaNacimiento == null){
    			FacesUtils.addErrorMessage("Por favor seleccione su fecha de nacimiento");
    		}else if(selectPais == null){
    			FacesUtils.addErrorMessage("Por favor seleccione su pais de residencia");
    		}else if(selectDepto == null){
    			FacesUtils.addErrorMessage("Por favor seleccione su departamento de residencia");
    		}else if(txtCiudad == null){
    			FacesUtils.addErrorMessage("Por favor ingrese su ciudad de residencia");
    		}else if(txtDireccion == null){
    			FacesUtils.addErrorMessage("Por favor ingrese una direccion valida");
    		}else if(trainerPic == null){
    			FacesUtils.addErrorMessage("Por favor adjunte una foto");
    		}else{
    				
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
        	 			Identification identi = businessDelegatorView.getIdentification(selectIdentificacion);
        	 			
        	 			trainer.setName(txtName);
        	 			trainer.setLastname(txtLastname);
        	 			trainer.setIdentification(identi);
        	 			trainer.setTrainer_id_number(txtNumeroIdentificacion);
        	 			trainer.setLugar_nacimiento(txtLugarNacimiento);
        	 			trainer.setBorndate(txtFechaNacimiento);
        	 			trainer.setCountry(selectPais);
        	 			trainer.setRegion(selectDepto);
        	 			trainer.setCity(txtCiudad.toUpperCase());
        	 			trainer.setAddress(txtDireccion.toUpperCase());
        	 			
        	 			if(trainerPic != null) {
        	 				
        	 				InputStream filecontent = null;
        	 				String picName = "";
        	 				OutputStream out = null;
        	 			
        	 				try {
        	 				
        	 				final String path = "C:\\desarrollo\\workspaces\\trainerpics\\";
        	 				
        	 				Date currentDate = new Date();
        	 				String formatedDate = formatImage.format(currentDate);
        	 	            
        	 				//picName = trainerPic.getFileName();
        	 				picName = ""+trainer.getIdtrainer()+""+formatedDate+".jpg";
        	 				filecontent = trainerPic.getInputstream();
        	 				
        	 				out = new FileOutputStream(new File(path + picName));
        	 				
        	 				int read = 0;
        	 		        final byte[] bytes = new byte[1024];
        	 				
        	 				while ((read = filecontent.read(bytes)) != -1) {
        	 		            out.write(bytes, 0, read);
        	 		        }
        	 				
        	 		        //log.info("New file " + picName + " created at " + path);
        	 				trainer.setTrainer_picture(picName);
        	 		        
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
        	 			FacesUtils.addInfoMessage("Su perfil ha sido actualizado satisfactoriamente");
        	 			
        	 		}//end if-else
        	 	}//end user app detect		
    		}//end validations
    	
    	}catch(Exception e){
    		log.info(e.toString());
    	}
    }
    
    public void saveDatosProf(){
    try{	
    	if(usuarioapp == null){
	 		FacesUtils.addErrorMessage("Error, no se pudo identificar el usuario, por favor utilice la opcion ayuda");
	 	}else{
	 	
			int idUser = usuarioapp.getIdusers();
			
			Users usuario = businessDelegatorView.getUsers(idUser);
	    	
	        if(selectTipoEntrenador != null && selectTipoEntrenador.length>0){
	        	
	        	Set<Category> categories = new HashSet();
	        	
	        	for (int i = 0; i < selectTipoEntrenador.length; i++) {
	            	 
	        		Category opc = businessDelegatorView.getCategory(selectTipoEntrenador[i]);
	        		categories.add(opc);
	            }
	        	
	        	usuario.setCategories(categories);
	        	businessDelegatorView.updateUsers(usuario);
	        	FacesUtils.addInfoMessage("Modalidades asignadas satisfactoriamente");
	        	
	        }//end if
	 		
	 	}//end if-else
    }catch(Exception e){
    	log.info(e.toString());
    }
    }
    
    public void saveAcademic(){
    	
    	try{
    		
	 		if(FacesUtils.checkInteger(selectNivelEstudio) == null){
	 			FacesUtils.addErrorMessage("Por favor seleccione un nivel de estudio");
	 		}else if(FacesUtils.checkString(txtAreaEstudio) == null){
	 			FacesUtils.addErrorMessage("Por escriba un area de estudio");
	 		}else{
	 			
	 			if(usuarioapp == null){
	 		 		FacesUtils.addErrorMessage("Error, no se pudo identificar el usuario, por favor utilice la opcion ayuda");
	 		 	}else{
	 		 		
	 		 		int academicId = FacesUtils.checkInteger(selectNivelEstudio);
		 			String areaStudio = FacesUtils.checkString(txtAreaEstudio);
		 			Academiclevel academic = businessDelegatorView.getAcademiclevel(academicId);
		 		
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
	    	 			
	 		 			trainer.setAcademiclevel(academic);
	 		 			trainer.setAcademicArea(areaStudio);
	 		 			
	 		 			businessDelegatorView.updateTrainer(trainer);
	 		 			FacesUtils.addInfoMessage("Nivel de estudios guardado satisfactoriamente");
	
	 		 		}//end if-else
	 		 		
	 		 	}//end if
	 		}//end validations
    	}catch(Exception e){
    		log.info(e.toString());
    	}
    }
    
    public void saveCertificacion(){
    	try{
    		
	 		if(FacesUtils.checkString(txtCertificacion) == null){
	 			FacesUtils.addErrorMessage("Por favor escriba el nombre de la certificacion");
	 		}else if(txtFechaCertificacion == null){
	 			FacesUtils.addErrorMessage("Seleccione la fecha de la certificacion");
	 		}else if(FacesUtils.checkString(txtEnteCertificador) == null){
	 			FacesUtils.addErrorMessage("Escriba el instituto o empresa que lo certifico");
	 		}else if(FacesUtils.checkString(txtCertificacionDesc) == null){
	 			FacesUtils.addErrorMessage("Por favor ingrese una descripcion de la certificacion");
	 		}else{
	 			
	 			if(usuarioapp == null){
	 		 		FacesUtils.addErrorMessage("Error, no se pudo identificar el usuario, por favor utilice la opcion ayuda");
	 		 	}else{
	 		 		
	 		 		String certificacion = FacesUtils.checkString(txtCertificacion);
		 			String descripcionCertificacion = FacesUtils.checkString(txtCertificacionDesc);
		 			Date fechaCertificacion = txtFechaCertificacion;
		 			String certificador = FacesUtils.checkString(txtEnteCertificador);
		 			
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
	 		 			
	 		 			Courses course = new Courses();
	 		 			course.setName(certificacion);
	 		 			course.setDatecertified(fechaCertificacion);
	 		 			course.setCertificator(certificador);
	 		 			course.setDescription(descripcionCertificacion);
	 		 			
	 		 			businessDelegatorView.saveCourses(course);
	 		 			
	 		 			Set<Courses> courses = new HashSet();
	 		 			Hibernate.initialize(trainer.getCourseses());
		     	 		courses = trainer.getCourseses();
	 		 			courses.add(course);
	 		        	
	 		 			trainer.setCourseses(courses);
	 		 			
	 		 			businessDelegatorView.updateTrainer(trainer);
	 		 			
	 		 			txtCertificacion.setValue(null);
	 		 			txtFechaCertificacion = null;
	 		 			txtEnteCertificador.setValue(null);
	 		 			txtCertificacionDesc.setValue(null);
	 		 			dataCoursesTrainer = null;
	 		 			
	 		 			FacesUtils.addInfoMessage("Certificacion guardada satisfactoriamente");
	
	 		 		}//end if-else
	 		 		
	 		 	}//end if
	 		}//end validations
    	}catch(Exception e){
    		log.info(e.toString());
    	}
    }
    
    
    public void saveProfExp(){
    	try{
    		
	 		if(FacesUtils.checkString(txtEmpresa) == null){
	 			FacesUtils.addErrorMessage("Por favor ingrese el nombre de la empresa o institucion");
	 		}else if(FacesUtils.checkString(txtCiudadExp) == null){
	 			FacesUtils.addErrorMessage("Escriba la ciudad en la cual ejercio el cargo");
	 		}else if(FacesUtils.checkString(txtCargo) == null){
	 			FacesUtils.addErrorMessage("Por favor ingrese el cargo");
	 		}else if(txtPeriodoInicio == null){
	 			FacesUtils.addErrorMessage("Ingrese un periodo de inicio");
	 		}else if(txtPeriodoFin == null){
	 			FacesUtils.addErrorMessage("Ingrese un periodo de fin");
	 		}else if(FacesUtils.checkString(txtFuncionCargo) == null){
	 			FacesUtils.addErrorMessage("Por favor ingrese una descripcion del cargo");	
	 		}else{
	 			
	 			if(usuarioapp == null){
	 		 		FacesUtils.addErrorMessage("Error, no se pudo identificar el usuario, por favor utilice la opcion ayuda");
	 		 	}else{
	 		 		
	 		 		String empresa = FacesUtils.checkString(txtEmpresa);
		 			String ciudad = FacesUtils.checkString(txtCiudadExp);
		 			String cargo = FacesUtils.checkString(txtCargo);
		 			Date periodoInicio = txtPeriodoInicio;
		 			Date periodoFin = txtPeriodoFin;
		 			String descripcion = FacesUtils.checkString(txtFuncionCargo);
		 			
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
	 		 			
	 		 			Job trabajo = new Job();
	 		 			trabajo.setName(cargo);
	 		 			businessDelegatorView.saveJob(trabajo);
	 		 			
	 		 			Experience experience = new Experience();
	 		 			experience.setCompany(empresa.toUpperCase());
	 		 			experience.setCity(ciudad.toUpperCase());
	 		 			experience.setStartdate(periodoInicio);
	 		 			experience.setEnddate(periodoFin);
	 		 			experience.setJob(trabajo);
	 		 			experience.setFunction(descripcion.toUpperCase());
	 		 			
	 		 			businessDelegatorView.saveExperience(experience);
	 		 			
	 		 			Set<Experience> experiences = new HashSet();
	 		 			Hibernate.initialize(trainer.getExperiences());
	 		 			experiences = trainer.getExperiences();
	 		 			experiences.add(experience);
	 		        	
	 		 			trainer.setExperiences(experiences);
	 		 			
	 		 			businessDelegatorView.updateTrainer(trainer);
	 		 			
	 		 			txtEmpresa.setValue(null);
	 		 			txtCiudadExp.setValue(null);
	 		 			txtCargo.setValue(null);
	 		 			txtPeriodoInicio = null;
	 		 			txtPeriodoFin = null;
	 		 			
	 		 			dataExperienceTrainer = null;
	 		 			
	 		 			FacesUtils.addInfoMessage("Experiencia laboral guardada satisfactoriamente");
	
	 		 		}//end if-else
	 		 		
	 		 	}//end if
	 		}//end validations
    	}catch(Exception e){
    		log.info(e.toString());
    	}
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
    	if(selectPais != null){
    		
    	this.listDeptos = new ArrayList<SelectItem>();	
    	
    	try{
    		int idPais = selectPais;
    		
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

	public Integer getSelectIdentificacion() {
		return selectIdentificacion;
	}

	public void setSelectIdentificacion(Integer selectIdentificacion) {
		this.selectIdentificacion = selectIdentificacion;
	}


	public Date getTxtFechaNacimiento() {
		return txtFechaNacimiento;
	}

	public void setTxtFechaNacimiento(Date txtFechaNacimiento) {
		this.txtFechaNacimiento = txtFechaNacimiento;
	}

	public Integer getSelectPais() {
		return selectPais;
	}

	public void setSelectPais(Integer selectPais) {
		this.selectPais = selectPais;
	}

	public Integer getSelectDepto() {
		return selectDepto;
	}

	public void setSelectDepto(Integer selectDepto) {
		this.selectDepto = selectDepto;
	}


	public String getTxtDireccion() {
		return txtDireccion;
	}

	public void setTxtDireccion(String txtDireccion) {
		this.txtDireccion = txtDireccion;
	}

	public Integer[] getSelectTipoEntrenador() {
		return selectTipoEntrenador;
	}

	public void setSelectTipoEntrenador(Integer[] selectTipoEntrenador) {
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

	public String getTxtCiudad() {
		return txtCiudad;
	}

	public void setTxtCiudad(String txtCiudad) {
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

	public Map<String,Integer> getListCategory() {
		
		if(this.listCategory == null){
			
		try{
			
			List<Category> list = businessDelegatorView.findByCriteriaInCategory(new Object[]{"level.idlevel",false, 1, "="},
																				 null,
																				 null);
			
			if(list.size() > 0){
				
				this.listCategory  = new LinkedHashMap<String, Integer>();
				
				for(Category cat:list){
					listCategory.put(cat.getDescription(), cat.getIdcategory());
					//this.listCategory.add(new SelectItem(cat.getIdcategory(), cat.getDescription()));
				}
				
			}
			
		}catch(Exception e){
			log.info(e.toString());
		}
			
		}
		
		return listCategory;
	}

	public void setListCategory(Map<String,Integer> listCategory) {
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

	public String getTxtName() {
		return txtName;
	}

	public void setTxtName(String txtName) {
		this.txtName = txtName;
	}

	public String getTxtLastname() {
		return txtLastname;
	}

	public void setTxtLastname(String txtLastname) {
		this.txtLastname = txtLastname;
	}

	public String getTxtNumeroIdentificacion() {
		return txtNumeroIdentificacion;
	}

	public void setTxtNumeroIdentificacion(String txtNumeroIdentificacion) {
		this.txtNumeroIdentificacion = txtNumeroIdentificacion;
	}

	public String getTxtLugarNacimiento() {
		return txtLugarNacimiento;
	}

	public void setTxtLugarNacimiento(String txtLugarNacimiento) {
		this.txtLugarNacimiento = txtLugarNacimiento;
	}

	public String getFirstNameDesc() {
		return firstNameDesc;
	}

	public void setFirstNameDesc(String firstNameDesc) {
		this.firstNameDesc = firstNameDesc;
	}

	public String getLastNameDesc() {
		return lastNameDesc;
	}

	public void setLastNameDesc(String lastNameDesc) {
		this.lastNameDesc = lastNameDesc;
	}

	public String getIdentiDesc() {
		return identiDesc;
	}

	public void setIdentiDesc(String identiDesc) {
		this.identiDesc = identiDesc;
	}

	public String getIdentiNumberDesc() {
		return identiNumberDesc;
	}

	public void setIdentiNumberDesc(String identiNumberDesc) {
		this.identiNumberDesc = identiNumberDesc;
	}

	public String getBornPlaceDesc() {
		return bornPlaceDesc;
	}

	public void setBornPlaceDesc(String bornPlaceDesc) {
		this.bornPlaceDesc = bornPlaceDesc;
	}

	public String getBornDateDesc() {
		return bornDateDesc;
	}

	public void setBornDateDesc(String bornDateDesc) {
		this.bornDateDesc = bornDateDesc;
	}

	public String getTrainerPicDesc() {
		return trainerPicDesc;
	}

	public void setTrainerPicDesc(String trainerPicDesc) {
		this.trainerPicDesc = trainerPicDesc;
	}

	public String getCountryResDesc() {
		return countryResDesc;
	}

	public void setCountryResDesc(String countryResDesc) {
		this.countryResDesc = countryResDesc;
	}

	public String getStateResDesc() {
		return stateResDesc;
	}

	public void setStateResDesc(String stateResDesc) {
		this.stateResDesc = stateResDesc;
	}

	public String getCityResDesc() {
		return cityResDesc;
	}

	public void setCityResDesc(String cityResDesc) {
		this.cityResDesc = cityResDesc;
	}

	public String getAddressResDesc() {
		return addressResDesc;
	}

	public void setAddressResDesc(String addressResDesc) {
		this.addressResDesc = addressResDesc;
	}

	public Date getMinDate() {
		return minDate;
	}

	public void setMinDate(Date minDate) {
		this.minDate = minDate;
	}

	public List<UsuariosCategoriasDTO> getDataCategory() {
		
		if(dataCategory == null){
			try{
				 if(usuarioapp == null){
		     	 		FacesUtils.addErrorMessage("Error, no se pudo identificar el usuario, por favor utilice la opcion ayuda");
		     	 }else{
		     		 
		     		 	dataCategory = new ArrayList<UsuariosCategoriasDTO>();
		     	 		
		     	 		int idUser = usuarioapp.getIdusers();
		     	 		Users usuario = businessDelegatorView.getUsers(idUser);
		     	 		
		     	 		Hibernate.initialize(usuario.getCategories());
		     	 		Set<Category> categories = usuario.getCategories();
		     	 		
		     	 		 for (Iterator<Category> it = categories.iterator(); it.hasNext(); ) {
		            		 
		     	 			Category act = it.next();
		     	 			
		     	 			dataCategory.add(new UsuariosCategoriasDTO(act.getIdcategory(), act.getDescription()));
		            	        
		            	 }//end for
		     	 		
		     	 		
		     	 }//end if-else
			}catch(Exception e){
				log.info(e.toString());
			}
			
			
			
		}
		
		return dataCategory;
	}

	public void setDataCategory(List<UsuariosCategoriasDTO> dataCategory) {
		this.dataCategory = dataCategory;
	}
	
    public String action_delete_cat(ActionEvent evt) {
        try {
        	
            selectedCat = (CategoryDTO) (evt.getComponent().getAttributes()
                                           .get("selectedCats"));

            Integer idcat = new Integer(selectedCat.getIdcategory());
            
            UsuariosCategorias usucat = businessDelegatorView.getUsuariosCategorias(idcat);
            
            businessDelegatorView.deleteUsuariosCategorias(usucat);
            
            FacesUtils.addInfoMessage("Categoria eliminada satisfactoriamente");
            dataCategory = null;
            
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

	public Date getTxtFechaCertificacion() {
		return txtFechaCertificacion;
	}

	public void setTxtFechaCertificacion(Date txtFechaCertificacion) {
		this.txtFechaCertificacion = txtFechaCertificacion;
	}

	public InputText getTxtEnteCertificador() {
		return txtEnteCertificador;
	}

	public void setTxtEnteCertificador(InputText txtEnteCertificador) {
		this.txtEnteCertificador = txtEnteCertificador;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public List<Category> getListCategoriesTrainer() {
		
		if(listCategoriesTrainer == null){
			try{
				 if(usuarioapp == null){
		     	 		FacesUtils.addErrorMessage("Error, no se pudo identificar el usuario, por favor utilice la opcion ayuda");
		     	 }else{
		     		 
		     		 	listCategoriesTrainer = new ArrayList<Category>();
		     		 	
		     		 	dataCategory = new ArrayList<UsuariosCategoriasDTO>();
		     	 		
		     	 		int idUser = usuarioapp.getIdusers();
		     	 		Users usuario = businessDelegatorView.getUsers(idUser);
		     	 		
		     	 		Hibernate.initialize(usuario.getCategories());
		     	 		Set<Category> categories = usuario.getCategories();
		     	 		
		     	 		if(rating < 5 && categories.size() > 0){
		     	 			rating++;
		     	 		}
		     	 		
		     	 		 for (Iterator<Category> it = categories.iterator(); it.hasNext(); ) {
		            		 
		     	 			Category act = it.next();
		     	 			
		     	 			listCategoriesTrainer.add(act);
		            	        
		            	 }//end for
		     	 		
		     	 		
		     	 }//end if-else
			}catch(Exception e){
				log.info(e.toString());
			}
		}
		
		return listCategoriesTrainer;
	}

	public void setListCategoriesTrainer(List<Category> listCategoriesTrainer) {
		this.listCategoriesTrainer = listCategoriesTrainer;
	}

	public String getNivelAcademicoDesc() {
		return nivelAcademicoDesc;
	}

	public void setNivelAcademicoDesc(String nivelAcademicoDesc) {
		this.nivelAcademicoDesc = nivelAcademicoDesc;
	}

	public String getAreaEstudioDesc() {
		return areaEstudioDesc;
	}

	public void setAreaEstudioDesc(String areaEstudioDesc) {
		this.areaEstudioDesc = areaEstudioDesc;
	}

	public List<Courses> getListCoursesTrainer() {
		
		if(listCoursesTrainer == null){
			try{
				 if(usuarioapp == null){
		     	 		FacesUtils.addErrorMessage("Error, no se pudo identificar el usuario, por favor utilice la opcion ayuda");
		     	 }else{
		     		 
		     		 	listCoursesTrainer = new ArrayList<Courses>();
		     		 	
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
		 		 			
		 		 			Hibernate.initialize(trainer.getCourseses());
			     	 		Set<Courses> courses = trainer.getCourseses();
			     	 	
			     	 		if(rating < 5 && courses.size() > 0){
			     	 			rating++;
			     	 		}
			     	 		
			     	 		 for (Iterator<Courses> it = courses.iterator(); it.hasNext(); ) {
			     	 			Courses course = it.next();
			     	 			listCoursesTrainer.add(course);
			            	 }//end for
			     	 		
		 		 		}
		     	 		
		     	 }//end if-else
			}catch(Exception e){
				log.info(e.toString());
			}
		}//end if
		
		return listCoursesTrainer;
	}

	public void setListCoursesTrainer(List<Courses> listCoursesTrainer) {
		this.listCoursesTrainer = listCoursesTrainer;
	}

	public List<Experience> getListExperienceTrainer() {
		
		if(listExperienceTrainer == null){
			try{
				 if(usuarioapp == null){
		     	 		FacesUtils.addErrorMessage("Error, no se pudo identificar el usuario, por favor utilice la opcion ayuda");
		     	 }else{
		     		 
		     		 	listExperienceTrainer = new ArrayList<Experience>();
		     		 	
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
		 		 			
		 		 			Hibernate.initialize(trainer.getExperiences());
			     	 		Set<Experience> experiences = trainer.getExperiences();
			     	 		
			     	 		if(rating < 5 && experiences.size() > 0){
			     	 			rating++;
			     	 		}
			     	 		
			     	 		 for (Iterator<Experience> it = experiences.iterator(); it.hasNext(); ) {
			     	 			Experience experience = it.next();
			     	 			listExperienceTrainer.add(experience);
			            	 }//end for
			     	 		
		 		 		}
		     	 		
		     	 }//end if-else
			}catch(Exception e){
				log.info(e.toString());
			}
		}//end if
		
		return listExperienceTrainer;
	}

	public void setListExperienceTrainer(List<Experience> listExperienceTrainer) {
		this.listExperienceTrainer = listExperienceTrainer;
	}

	public List<Courses> getDataCoursesTrainer() {
		
		if(dataCoursesTrainer == null){
			try{
				 if(usuarioapp == null){
		     	 		FacesUtils.addErrorMessage("Error, no se pudo identificar el usuario, por favor utilice la opcion ayuda");
		     	 }else{
		     		 
		     		 	dataCoursesTrainer = new ArrayList<Courses>();
		     		 	
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
		 		 			
		 		 			Hibernate.initialize(trainer.getCourseses());
			     	 		Set<Courses> courses = trainer.getCourseses();
			     	 		
			     	 		 for (Iterator<Courses> it = courses.iterator(); it.hasNext(); ) {
			     	 			Courses course = it.next();
			     	 			dataCoursesTrainer.add(course);
			            	 }//end for
			     	 		
		 		 		}
		     	 		
		     	 }//end if-else
			}catch(Exception e){
				log.info(e.toString());
			}
		}//end if
		
		return dataCoursesTrainer;
	}
	
	public String action_delete_certification(ActionEvent evt) {
        try {
        	
            selectedCertification = (Courses) (evt.getComponent().getAttributes()
                                           .get("selectedCertificacion"));
            if(usuarioapp == null){
     	 		FacesUtils.addErrorMessage("Error, no se pudo identificar el usuario, por favor utilice la opcion ayuda");
            }else{
     		 
     	 		int idUser = usuarioapp.getIdusers();
     	 		int idCourse = selectedCertification.getIdcourses();
     	 		
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
		 			
		 			Hibernate.initialize(trainer.getCourseses());
	     	 		Set<Courses> courses = trainer.getCourseses();
	     	 		Set<Courses> coursesNew = new HashSet<Courses>();
	     	 		
	     	 		 for (Iterator<Courses> it = courses.iterator(); it.hasNext(); ) {
	     	 			Courses course = it.next();
	     	 			
	     	 			if(course.getIdcourses() != idCourse){
		     	 			coursesNew.add(course);
	     	 			}
	            	 }//end for
	     	 		 
	     	 		 trainer.setCourseses(coursesNew);
	     	 		 businessDelegatorView.updateTrainer(trainer);
	     	 		 
	     	 		 Courses course = businessDelegatorView.getCourses(idCourse);
	     	 		 businessDelegatorView.deleteCourses(course);
	     	 		 
	     	 		 dataCoursesTrainer = null;
	     	 		 FacesUtils.addErrorMessage("Curso eliminado satisfactoriamente");
	     	 		
		 		}
     	 }

        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

	public void setDataCoursesTrainer(List<Courses> dataCoursesTrainer) {
		this.dataCoursesTrainer = dataCoursesTrainer;
	}

	public List<Experience> getDataExperienceTrainer() {
		
		if(dataExperienceTrainer == null){
			try{
				 if(usuarioapp == null){
		     	 		FacesUtils.addErrorMessage("Error, no se pudo identificar el usuario, por favor utilice la opcion ayuda");
		     	 }else{
		     		 
		     		 	dataExperienceTrainer = new ArrayList<Experience>();
		     		 	
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
		 		 			
		 		 			Hibernate.initialize(trainer.getCourseses());
			     	 		Set<Experience> experience = trainer.getExperiences();
			     	 		
			     	 		 for (Iterator<Experience> it = experience.iterator(); it.hasNext(); ) {
			     	 			Experience exp = it.next();
			     	 			dataExperienceTrainer.add(exp);
			            	 }//end for
			     	 		
		 		 		}
		     	 		
		     	 }//end if-else
			}catch(Exception e){
				log.info(e.toString());
			}
		}//end if
		
		return dataExperienceTrainer;
	}

	public void setDataExperienceTrainer(List<Experience> dataExperienceTrainer) {
		this.dataExperienceTrainer = dataExperienceTrainer;
	}
	
	public String action_delete_experience(ActionEvent evt) {
        try {
        	
            selectedExperience = (Experience) (evt.getComponent().getAttributes()
                                           .get("selectedExperience"));
            if(usuarioapp == null){
     	 		FacesUtils.addErrorMessage("Error, no se pudo identificar el usuario, por favor utilice la opcion ayuda");
            }else{
     		 
     	 		int idUser = usuarioapp.getIdusers();
     	 		int idExperience = selectedExperience.getIdexperience();
     	 		
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
		 			
		 			Hibernate.initialize(trainer.getExperiences());
	     	 		Set<Experience> experiences = trainer.getExperiences();
	     	 		Set<Experience> experiencesNew = new HashSet<Experience>();
	     	 		
	     	 		 for (Iterator<Experience> it = experiences.iterator(); it.hasNext(); ) {
	     	 			Experience exp = it.next();
	     	 			
	     	 			if(exp.getIdexperience() != idExperience){
		     	 			experiencesNew.add(exp);
	     	 			}
	            	 }//end for
	     	 		 
	     	 		 trainer.setExperiences(experiencesNew);
	     	 		 businessDelegatorView.updateTrainer(trainer);
	     	 		 
	     	 		 Experience experience = businessDelegatorView.getExperience(idExperience);
	     	 		 businessDelegatorView.deleteExperience(experience);
	     	 		 
	     	 		 dataExperienceTrainer = null;
	     	 		 FacesUtils.addErrorMessage("Experiencia laboral eliminada satisfactoriamente");
	     	 		
		 		}
     	 }

        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

	public boolean isRenderTabsEdit() {
		return renderTabsEdit;
	}

	public void setRenderTabsEdit(boolean renderTabsEdit) {
		this.renderTabsEdit = renderTabsEdit;
	}

	public Integer getRecruitmentProcessStatus() {
		return recruitmentProcessStatus;
	}

	public void setRecruitmentProcessStatus(Integer recruitmentProcessStatus) {
		this.recruitmentProcessStatus = recruitmentProcessStatus;
	}
	
	
	
}
