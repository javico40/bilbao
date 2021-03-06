package com.liderbs.presentation.backingBeans;

import com.liderbs.exceptions.*;

import com.liderbs.modelo.*;
import com.liderbs.modelo.dto.PaisDTO;
import com.liderbs.modelo.dto.PlaceDTO;

import com.liderbs.presentation.businessDelegate.*;

import com.liderbs.utilities.*;

import org.hibernate.Hibernate;
import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.extensions.component.inputnumber.InputNumber;
import org.primefaces.model.UploadedFile;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.event.RowEditEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

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
public class PlaceView implements Serializable {
	
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PlaceView.class);
    
    private InputText txtPlaceNit;
    private InputText txtPlaceAddress;
    private InputText txtPlaceIsVzone;
    private InputText txtPlaceName;
    private InputText txtPlaceOwner;
    private InputText txtPlacePhone;
    private InputText txtPlacePhoneAlt;
    private InputText txtPlaceStatus;
    private InputText txtIdPlace;
    private Calendar txtPlaceCreated;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<PlaceDTO> data;
    private List<PlaceDTO> dataCenpro;
    private PlaceDTO selectedPlace;
    private Place entity;
    private SelectOneMenu selectPais;
    private List<SelectItem> listPaises;
    private boolean lockDepto = true;
    private Integer selectDepto;
    private List<SelectItem> listDeptos;
    private InputText txtCiudad;
    private InputText txtAssocAct;
    private InputText txtAssocActOwner;
    private Integer assocAct;
    private Users usuarioapp;
    private Integer idAccount = 0;
    private Integer[] selectTiposCentro;
    private Map<String,Integer> tiposCentro;
    private Integer[] selectServicios;
    private Map<String,Integer> tiposServicio;    
    private Date horaEntradaLunesViernes;
    private Date horaSalidaLunesViernes;
    private Date horaEntradaSabado;
    private Date horaSalidaSabado;
    private Date horaEntradaDomingo;
    private Date horaSalidaDomingo;
    private InputText dailyTrainCost;
    private InputText montlyTrainCost;
    private InputText txtlatitud;
    private InputText txtlongitud;
    private InputText txtcode;
    private InputText txtEmailGym;
    private InputText txtpaytoken;
    private String txtdescripcion;
    private String txtlogo;
    private String txtpic1;
    private String txtpic2;
    private String txtpic3;
    private UploadedFile txtLogoGym;
    private UploadedFile txtGymPicture1;
    private UploadedFile txtGymPicture2;
    private UploadedFile txtGymPicture3;
    private Integer status;
    SimpleDateFormat formatImage = new SimpleDateFormat("ddMMyyyyhhmmss");
    private InputText txtMonthlyCost;
    private InputText txtDailyCost;
     
    
    private Place currentCenpro;
    
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public PlaceView() {
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
    
    public void populateCenproData(){
    	
    	try{
    		
    		 Account account = businessDelegatorView.getAccount(idAccount);
    		 
    			//Get profile of the user and call configuration if is a centro deportivo
    			 
    			 if(account.getProfile().getProfileName().contains("CENTROS DEPORTIVOS")){
    				
    				   //Obtener la informacion de su centro deportivo
    				 
    				 List<Place> placeList = businessDelegatorView.findByCriteriaInPlace(new Object[]{"accountID",false, idAccount, "="},
    																					 null,
    																					 null);
    				 
    				 int idPlace = 0;
    				 
    				 for(Place place: placeList){
    					 idPlace = place.getIdPlace();
    				 }
    				 
    				 if(idPlace > 0){
    					 
    					 Place place = businessDelegatorView.getPlace(idPlace);
    					 
    					 currentCenpro = place;
    					 txtPlaceNit.setValue((place.getFiscalID() != null) ? place.getFiscalID() : null );
    					 txtPlaceName.setValue((place.getPlaceName() != null) ? place.getPlaceName() : null );
    				     txtPlaceName.setDisabled(false);
    				     txtPlaceAddress.setValue((place.getPlaceAddress() != null) ? place.getPlaceAddress() : null );
    				     txtPlaceAddress.setDisabled(false);
    				     txtPlacePhone.setValue((place.getPlacePhone() != null) ? place.getPlacePhone() : null);
    				     txtPlacePhone.setDisabled(false);
    				     txtPlacePhoneAlt.setValue((place.getPlacePhoneAlt() != null) ? place.getPlacePhoneAlt() : null);
    				     txtPlacePhoneAlt.setDisabled(false);
    				     selectPais.setValue((place.getCountry() != null) ? place.getCountry() : null);
    				     selectPais.setDisabled(false);
    				     
    				     if(place.getProvince() != null){
    				    	 selectDepto = place.getProvince();
    				    	 setLockDepto(false);
    				     }
    				     
    				     txtCiudad.setValue((place.getCity() != null) ? place.getCity() : null);
    				     txtCiudad.setDisabled(false);
    				     
    				     if(place.getInWeekly() != null){
    				    	 horaEntradaLunesViernes = place.getInWeekly();
    				     }
    				     
    				     if(place.getInSaturday() != null){
    				    	 horaEntradaSabado = place.getInSaturday();
    				     }
    				     
    				     if(place.getInSunday() != null){
    				    	 horaEntradaDomingo = place.getInSunday();
    				     }
    				     
    				     if(place.getOutWeekly() != null){
    				    	 horaSalidaLunesViernes = place.getOutWeekly();
    				     }
    				     
    				     if(place.getOutSaturday() != null){
    				    	 horaSalidaSabado = place.getOutSaturday();
    				     }
    				     
    				     if(place.getOutSunday() != null){
    				    	 horaSalidaDomingo = place.getOutSunday();
    				     }
    				     
    				     dailyTrainCost.setValue((place.getDailyCost() != null) ? place.getDailyCost() : null);
    				     dailyTrainCost.setDisabled(false);
    				     
    				     montlyTrainCost.setValue((place.getMonthlyCost() != null) ? place.getMonthlyCost() : null);
    				     montlyTrainCost.setDisabled(false);
    				     
    				     Hibernate.initialize(place.getPlacetypes());
    				     Set<Placetype> placeType = place.getPlacetypes();
    				        
    				        selectTiposCentro = new Integer[placeType.size()];
    				        
    				        int i = 0;
    				        
    				        for (Iterator<Placetype> it = placeType.iterator(); it.hasNext(); ) {
    				   		 
    						 	Placetype opt = it.next();
    						 	selectTiposCentro[i] = opt.getIdplacetype();
    						 	i++;
    				        }
    				        
    				        Hibernate.initialize(place.getPlaceserviceses());
    				        Set<Placeservices> placeServices = place.getPlaceserviceses();
    				        
    				        selectServicios = new Integer[placeServices.size()];
    				        
    				        i = 0;
    				        
    				        for (Iterator<Placeservices> it = placeServices.iterator(); it.hasNext(); ) {
    				   		 
    						 	Placeservices opt = it.next();
    						 	selectServicios[i] = opt.getIdplaceservices();
    						 	i++;
    				        }
    					 
    				 }//have Id Place
    			 }//isCentroDeportivo
    		
    		
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
    
    public void buscarAssocAct(){
    	
    	try{
    		
    	if(FacesUtils.checkString(txtAssocAct) == null){
    		FacesUtils.addErrorMessage("Escriba una cuenta asociada valida");
    	}else{
    		
    		String accountName = FacesUtils.checkString(txtAssocAct);
    		
    		List<Account> list = businessDelegatorView.findByCriteriaInAccount(new Object[]{"accountName",false, accountName, "="},
					   null,
					   null);	

    		if(list.size() == 0){
    			FacesUtils.addErrorMessage("No se encontraron cuentas asociadas al identificador ingresado");
    			txtAssocActOwner.setValue("");
    		}else{
    			
    			String accountDesc= "";
    			
    			for(Account act:list){
    				assocAct = act.getIdAccount();
    				accountDesc = act.getAccountDescription();
    			}
    			
    			txtAssocActOwner.setValue(accountDesc);
    			
    		}	
    	}
    	
    	}catch(Exception e){
    		log.info(e.toString());
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
            PlaceDTO placeDTO = (PlaceDTO) e.getObject();

            if (txtPlaceAddress == null) {
                txtPlaceAddress = new InputText();
            }

            txtPlaceAddress.setValue(placeDTO.getPlaceAddress());

            if (txtPlaceIsVzone == null) {
                txtPlaceIsVzone = new InputText();
            }

            txtPlaceIsVzone.setValue(placeDTO.getPlaceIsVzone());

            if (txtPlaceName == null) {
                txtPlaceName = new InputText();
            }

            txtPlaceName.setValue(placeDTO.getPlaceName());

            if (txtPlaceOwner == null) {
                txtPlaceOwner = new InputText();
            }

            txtPlaceOwner.setValue(placeDTO.getPlaceOwner());

            if (txtPlacePhone == null) {
                txtPlacePhone = new InputText();
            }

            txtPlacePhone.setValue(placeDTO.getPlacePhone());

            if (txtPlacePhoneAlt == null) {
                txtPlacePhoneAlt = new InputText();
            }

            txtPlacePhoneAlt.setValue(placeDTO.getPlacePhoneAlt());

            if (txtPlaceStatus == null) {
                txtPlaceStatus = new InputText();
            }

            txtPlaceStatus.setValue(placeDTO.getPlaceStatus());

            if (txtIdPlace == null) {
                txtIdPlace = new InputText();
            }

            txtIdPlace.setValue(placeDTO.getIdPlace());

            if (txtPlaceCreated == null) {
                txtPlaceCreated = new Calendar();
            }

            txtPlaceCreated.setValue(placeDTO.getPlaceCreated());

            Integer idPlace = FacesUtils.checkInteger(txtIdPlace);
            entity = businessDelegatorView.getPlace(idPlace);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedPlace = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedPlace = null;

        if (txtPlaceAddress != null) {
            txtPlaceAddress.setValue(null);
            txtPlaceAddress.setDisabled(false);
        }

        if (txtPlaceIsVzone != null) {
            txtPlaceIsVzone.setValue(null);
            txtPlaceIsVzone.setDisabled(false);
        }

        if (txtPlaceName != null) {
            txtPlaceName.setValue(null);
            txtPlaceName.setDisabled(false);
        }

        if (txtPlaceOwner != null) {
            txtPlaceOwner.setValue(null);
            txtPlaceOwner.setDisabled(false);
        }

        if (txtPlacePhone != null) {
            txtPlacePhone.setValue(null);
            txtPlacePhone.setDisabled(false);
        }

        if (txtPlacePhoneAlt != null) {
            txtPlacePhoneAlt.setValue(null);
            txtPlacePhoneAlt.setDisabled(false);
        }

        if (txtPlaceStatus != null) {
            txtPlaceStatus.setValue(null);
            txtPlaceStatus.setDisabled(false);
        }

        if (txtPlaceCreated != null) {
            txtPlaceCreated.setValue(null);
            txtPlaceCreated.setDisabled(false);
        }

        if (txtIdPlace != null) {
            txtIdPlace.setValue(null);
            txtIdPlace.setDisabled(false);
        }

        if (btnSave != null) {
            btnSave.setDisabled(false);
        }

        if (btnDelete != null) {
            btnDelete.setDisabled(true);
        }

        return "";
    }

    public void listener_txtPlaceCreated() {
        Date inputDate = (Date) txtPlaceCreated.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtId() {
        try {
            Integer idPlace = FacesUtils.checkInteger(txtIdPlace);
            entity = (idPlace != null)
                ? businessDelegatorView.getPlace(idPlace) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtPlaceAddress.setDisabled(false);
            txtPlaceIsVzone.setDisabled(false);
            txtPlaceName.setDisabled(false);
            txtPlaceOwner.setDisabled(false);
            txtPlacePhone.setDisabled(false);
            txtPlacePhoneAlt.setDisabled(false);
            txtPlaceStatus.setDisabled(false);
            txtPlaceCreated.setDisabled(false);
            txtIdPlace.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtPlaceAddress.setValue(entity.getPlaceAddress());
            txtPlaceAddress.setDisabled(false);
            txtPlaceCreated.setValue(entity.getPlaceCreated());
            txtPlaceCreated.setDisabled(false);
            txtPlaceIsVzone.setValue(entity.getPlaceIsVzone());
            txtPlaceIsVzone.setDisabled(false);
            txtPlaceName.setValue(entity.getPlaceName());
            txtPlaceName.setDisabled(false);
            txtPlaceOwner.setValue(entity.getPlaceOwner());
            txtPlaceOwner.setDisabled(false);
            txtPlacePhone.setValue(entity.getPlacePhone());
            txtPlacePhone.setDisabled(false);
            txtPlacePhoneAlt.setValue(entity.getPlacePhoneAlt());
            txtPlacePhoneAlt.setDisabled(false);
            txtPlaceStatus.setValue(entity.getPlaceStatus());
            txtPlaceStatus.setDisabled(false);
            txtIdPlace.setValue(entity.getIdPlace());
            txtIdPlace.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
    	
        selectedPlace = (PlaceDTO) (evt.getComponent().getAttributes()
                                       .get("selectedPlace"));
        
        txtPlaceAddress.setValue(selectedPlace.getPlaceAddress());
        txtPlaceAddress.setDisabled(false);
        txtPlaceName.setValue(selectedPlace.getPlaceName());
        txtPlaceName.setDisabled(false);
        txtPlacePhone.setValue(selectedPlace.getPlacePhone());
        txtPlacePhone.setDisabled(false);
        txtlatitud.setValue(selectedPlace.getLatitud());
        txtlongitud.setValue(selectedPlace.getLongitud());
        txtcode.setValue(selectedPlace.getCode());
        txtpaytoken.setValue(selectedPlace.getPaytoken());
        txtdescripcion = selectedPlace.getDescripcion();
        selectPais.setDisabled(false);
        getSelectPais();
        selectPais.setValue(selectedPlace.getCountry());
        buscarDepto();
        selectDepto = selectedPlace.getProvince();
        txtCiudad.setValue(selectedPlace.getCity());
        txtPlaceNit.setValue(selectedPlace.getFiscalID());
        txtlogo = selectedPlace.getLogo();
        txtpic1 = selectedPlace.getPic1();
        txtpic2 = selectedPlace.getPic2();
        txtpic3 = selectedPlace.getPic3();
        txtEmailGym.setValue(selectedPlace.getPlaceEmail());
        status = selectedPlace.getPlaceStatus();
        txtMonthlyCost.setDisabled(false);
        txtMonthlyCost.setValue(selectedPlace.getMonthlyCost());;
        txtDailyCost.setDisabled(false);
        txtDailyCost.setValue(selectedPlace.getDailyCost());
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }
    
    public String action_edit_cenpro(ActionEvent evt) {
        
    	selectedPlace = (PlaceDTO) (evt.getComponent().getAttributes()
                                       .get("selectedPlace"));
    	
    	txtPlaceNit.setValue(selectedPlace.getFiscalID());
    	txtPlaceName.setValue(selectedPlace.getPlaceName());
        txtPlaceName.setDisabled(false);
    	txtPlaceAddress.setValue(selectedPlace.getPlaceAddress());
        txtPlaceAddress.setDisabled(false);
        txtPlacePhone.setValue(selectedPlace.getPlacePhone());
        txtPlacePhone.setDisabled(false);
        
        Hibernate.initialize(selectedPlace.getPlacetypes());
        Set<Placetype> placeType = selectedPlace.getPlacetypes();
        
        selectTiposCentro = new Integer[placeType.size()];
        
        int i = 0;
        
        for (Iterator<Placetype> it = placeType.iterator(); it.hasNext(); ) {
   		 
		 	Placetype opt = it.next();
		 	selectTiposCentro[i] = opt.getIdplacetype();
		 	i++;
        }
        
        Hibernate.initialize(selectedPlace.getPlaceserviceses());
        Set<Placeservices> placeServices = selectedPlace.getPlaceserviceses();
        
        selectServicios = new Integer[placeServices.size()];
        
        i = 0;
        
        for (Iterator<Placeservices> it = placeServices.iterator(); it.hasNext(); ) {
   		 
		 	Placeservices opt = it.next();
		 	selectServicios[i] = opt.getIdplaceservices();
		 	i++;
        }
        
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedPlace == null) && (entity == null)) {
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
    
    public String action_save_cenpro() {
        try {
            if ((selectedPlace == null) && (entity == null)) {
                action_create_cenpro();
            } else {
                action_modify_cenpro();
            }

            data = null;
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }
    
    public void validarDepto(){
    	
    }
    
    public String action_create_cenpro() {
        try {
        	
            entity = new Place();
            Date today = new Date();
            
            entity.setAccountID(idAccount);
            entity.setFiscalID(FacesUtils.checkString(txtPlaceNit));
            entity.setPlaceName(FacesUtils.checkString(txtPlaceName));
            entity.setPlaceAddress(FacesUtils.checkString(txtPlaceAddress));
            entity.setCountry(FacesUtils.checkInteger(selectPais));
            //entity.setProvince(FacesUtils.checkInteger(selectDepto));
            entity.setProvince(1722);
            entity.setCity(FacesUtils.checkString(txtCiudad).toUpperCase());
            entity.setPlaceIsVzone(0);
            entity.setPlacePhone(FacesUtils.checkString(txtPlacePhone));
            entity.setPlacePhoneAlt(FacesUtils.checkString(txtPlacePhoneAlt));
            entity.setPlaceCreated(today);
            entity.setPlaceStatus(1);
            
            
            if(selectTiposCentro!=null && selectTiposCentro.length>0){
            	
            	Set<Placetype> placeType = new HashSet();
            	
            	for (int i = 0; i < selectTiposCentro.length; i++) {
	            	 
            		Placetype opc = businessDelegatorView.getPlacetype(selectTiposCentro[i]);
            		placeType.add(opc);
	            }
            	 entity.setPlacetypes(placeType);
            }
            
            if(selectServicios != null && selectServicios.length>0){
            	
            	Set<Placeservices> opciones = new HashSet();
            	
            	for (int i = 0; i < selectServicios.length; i++) {
	            	 
            		Placeservices opc = businessDelegatorView.getPlaceservices(selectServicios[i]);
            		opciones.add(opc);
	            }
            	 entity.setPlaceserviceses(opciones);
            }
            
            
            
            businessDelegatorView.savePlace(entity);
            
            FacesUtils.addInfoMessage("Centro deportivo registrado satisfactoriamente");
            action_clear();
        } catch (Exception e) {
            entity = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_create() {
        try {
        	
            entity = new Place();
            Date today = new Date();
            
            entity.setAccountID(1);
            entity.setPlaceName(FacesUtils.checkString(txtPlaceName));
            entity.setPlaceOwner(FacesUtils.checkInteger(txtPlaceOwner));
            entity.setPlacePhone(FacesUtils.checkString(txtPlacePhone));
            entity.setPlacePhoneAlt(FacesUtils.checkString(txtPlacePhoneAlt));
            entity.setPlaceAddress(FacesUtils.checkString(txtPlaceAddress));
            entity.setPlaceCreated(FacesUtils.checkDate(txtPlaceCreated));
            entity.setPlaceIsVzone(FacesUtils.checkInteger(txtPlaceIsVzone));
            entity.setPlaceStatus(FacesUtils.checkInteger(txtPlaceStatus));
            entity.setLatitud(FacesUtils.checkString(txtlatitud));
            entity.setLongitud(FacesUtils.checkString(txtlongitud));
            entity.setCode(FacesUtils.checkString(txtcode));
            entity.setPaytoken(FacesUtils.checkString(txtpaytoken));
            entity.setDescripcion(txtdescripcion);
            entity.setFiscalID(FacesUtils.checkString(txtPlaceNit));
            entity.setCountry(FacesUtils.checkInteger(selectPais));
            entity.setProvince(selectDepto);
            entity.setCity(FacesUtils.checkString(txtCiudad));
            entity.setPlaceStatus(status);
            entity.setPlaceEmail(FacesUtils.checkString(txtEmailGym));
            entity.setMonthlyCost(FacesUtils.checkDouble(txtMonthlyCost));
            entity.setDailyCost(FacesUtils.checkDouble(txtDailyCost));
            
            
            businessDelegatorView.savePlace(entity);
            
            //Seccion de fotos
            
            //Save picture
            
            InputStream filecontent = null;
			String picName = "";
			OutputStream out = null;
			//final String path = "C:\\desarrollo\\workspaces\\gympics\\";
 			final String path = "/var/www/html/govirfit/appimg/gyms/";
			
			long size = 0;
 				
			//Logo
            
            if(txtLogoGym != null) {
            	
            size =	txtLogoGym.getSize();
 				
            if(size > 0) {
            	
            	try {
     				
     				Date currentDate = new Date();
     				String formatedDate = formatImage.format(currentDate);
     	            
     				//picName = trainerPic.getFileName();
     				picName = ""+entity.getIdPlace()+""+formatedDate+".jpg";
     				filecontent = txtLogoGym.getInputstream();
     				
     				out = new FileOutputStream(new File(path + picName));
     				
     				int read = 0;
     		        final byte[] bytes = new byte[1024];
     				
     				while ((read = filecontent.read(bytes)) != -1) {
     		            out.write(bytes, 0, read);
     		        }
     				
     		        //log.info("New file " + picName + " created at " + path);
     				entity.setLogo(picName);
     		        
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
     			
     				//Check if an old image exist and remove it
     				if(txtlogo != null) {
     					File file = new File(path+txtlogo);
     					if(file.exists()){
     						file.delete();
     					}
     				}
     				
     				businessDelegatorView.updatePlace(entity);
            	
            }//end validador
  
 	        }
            
            //Imagen 1
            
            if(txtGymPicture1 != null) {
            	
            	size =	txtGymPicture1.getSize();
 				
                if(size > 0) {
                	
                	try {
         				
         				Date currentDate = new Date();
         				String formatedDate = formatImage.format(currentDate);
         	            
         				//picName = trainerPic.getFileName();
         				picName = ""+entity.getIdPlace()+""+formatedDate+".jpg";
         				filecontent = txtGymPicture1.getInputstream();
         				
         				out = new FileOutputStream(new File(path + picName));
         				
         				int read = 0;
         		        final byte[] bytes = new byte[1024];
         				
         				while ((read = filecontent.read(bytes)) != -1) {
         		            out.write(bytes, 0, read);
         		        }
         				
         		        //log.info("New file " + picName + " created at " + path);
         				entity.setPic1(picName);
         		        
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
         			
         				//Check if an old image exist and remove it
         				if(txtpic1 != null) {
         					File file = new File(path+txtpic1);
         					if(file.exists()){
         						file.delete();
         					}
         				}
         				
         				businessDelegatorView.updatePlace(entity);
                	
                }//end validador
					
     		
                    
     	        }
            
            //Secound picture
            
            if(txtGymPicture2 != null) {
            	
            	size =	txtGymPicture2.getSize();
 				
                if(size > 0) {
                	
            		try {
         				
         				Date currentDate = new Date();
         				String formatedDate = formatImage.format(currentDate);
         	            
         				//picName = trainerPic.getFileName();
         				picName = ""+entity.getIdPlace()+""+formatedDate+".jpg";
         				filecontent = txtGymPicture2.getInputstream();
         				
         				out = new FileOutputStream(new File(path + picName));
         				
         				int read = 0;
         		        final byte[] bytes = new byte[1024];
         				
         				while ((read = filecontent.read(bytes)) != -1) {
         		            out.write(bytes, 0, read);
         		        }
         				
         		        //log.info("New file " + picName + " created at " + path);
         				entity.setPic2(picName);
         		        
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
         			
         				//Check if an old image exist and remove it
         				if(txtpic2 != null) {
         					File file = new File(path+txtpic2);
         					if(file.exists()){
         						file.delete();
         					}
         				}
         				
         				businessDelegatorView.updatePlace(entity);
                	
                }//end validador
					
     	
                    
     	        }
            
            //third picture
            
            if(txtGymPicture3 != null) {
            	
            	size =	txtGymPicture3.getSize();
 				
                if(size > 0) {
                	
                	try {
         				
         				Date currentDate = new Date();
         				String formatedDate = formatImage.format(currentDate);
         	            
         				//picName = trainerPic.getFileName();
         				picName = ""+entity.getIdPlace()+""+formatedDate+".jpg";
         				filecontent = txtGymPicture3.getInputstream();
         				
         				out = new FileOutputStream(new File(path + picName));
         				
         				int read = 0;
         		        final byte[] bytes = new byte[1024];
         				
         				while ((read = filecontent.read(bytes)) != -1) {
         		            out.write(bytes, 0, read);
         		        }
         				
         		        //log.info("New file " + picName + " created at " + path);
         				entity.setPic3(picName);
         		        
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
         			
         				//Check if an old image exist and remove it
         				if(txtpic3 != null) {
         					File file = new File(path+txtpic3);
         					if(file.exists()){
         						file.delete();
         					}
         				}
         				
         				businessDelegatorView.updatePlace(entity);
                	
                }//end validador
			
     	      }
            
            //End seccion de fotos
            
            
            FacesUtils.addInfoMessage("Centro deportivo registrado satisfactoriamente");
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
                Integer idPlace = new Integer(selectedPlace.getIdPlace());
                entity = businessDelegatorView.getPlace(idPlace);
            }

            entity.setPlaceName(FacesUtils.checkString(txtPlaceName));
            entity.setPlaceOwner(FacesUtils.checkInteger(txtPlaceOwner));
            entity.setPlacePhone(FacesUtils.checkString(txtPlacePhone));
            entity.setPlacePhoneAlt(FacesUtils.checkString(txtPlacePhoneAlt));
            entity.setPlaceAddress(FacesUtils.checkString(txtPlaceAddress));
            entity.setPlaceCreated(FacesUtils.checkDate(txtPlaceCreated));
            entity.setPlaceIsVzone(FacesUtils.checkInteger(txtPlaceIsVzone));
            entity.setPlaceStatus(FacesUtils.checkInteger(txtPlaceStatus));
            entity.setLatitud(FacesUtils.checkString(txtlatitud));
            entity.setLongitud(FacesUtils.checkString(txtlongitud));
            entity.setCode(FacesUtils.checkString(txtcode));
            entity.setPaytoken(FacesUtils.checkString(txtpaytoken));
            entity.setDescripcion(txtdescripcion);
            entity.setFiscalID(FacesUtils.checkString(txtPlaceNit));
            entity.setCountry(FacesUtils.checkInteger(selectPais));
            entity.setProvince(selectDepto);
            entity.setCity(FacesUtils.checkString(txtCiudad));
            entity.setPlaceStatus(status);
            entity.setPlaceEmail(FacesUtils.checkString(txtEmailGym));
            entity.setMonthlyCost(FacesUtils.checkDouble(txtMonthlyCost));
            entity.setDailyCost(FacesUtils.checkDouble(txtDailyCost));
            
            
            businessDelegatorView.updatePlace(entity);
            
            //Seccion de fotos
            
            //Save picture
            
            InputStream filecontent = null;
			String picName = "";
			OutputStream out = null;
			
			//final String path = "C:\\desarrollo\\workspaces\\gympics\\";
			final String path = "/var/www/html/govirfit/appimg/gyms/";
			
			long size = 0;
 				
			//Logo
            
            if(txtLogoGym != null) {
            	
            size =	txtLogoGym.getSize();
 				
            if(size > 0) {
            	
            	try {
     				
     				Date currentDate = new Date();
     				String formatedDate = formatImage.format(currentDate);
     	            
     				//picName = trainerPic.getFileName();
     				picName = ""+entity.getIdPlace()+""+formatedDate+".jpg";
     				filecontent = txtLogoGym.getInputstream();
     				
     				out = new FileOutputStream(new File(path + picName));
     				
     				int read = 0;
     		        final byte[] bytes = new byte[1024];
     				
     				while ((read = filecontent.read(bytes)) != -1) {
     		            out.write(bytes, 0, read);
     		        }
     				
     		        //log.info("New file " + picName + " created at " + path);
     				entity.setLogo(picName);
     		        
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
     			
     				//Check if an old image exist and remove it
     				if(txtlogo != null) {
     					File file = new File(path+txtlogo);
     					if(file.exists()){
     						file.delete();
     					}
     				}
     				
     				businessDelegatorView.updatePlace(entity);
            	
            }//end validador
  
 	        }
            
            //Imagen 1
            
            if(txtGymPicture1 != null) {
            	
            	size =	txtGymPicture1.getSize();
 				
                if(size > 0) {
                	
                	try {
         				
         				Date currentDate = new Date();
         				String formatedDate = formatImage.format(currentDate);
         	            
         				//picName = trainerPic.getFileName();
         				picName = ""+entity.getIdPlace()+""+formatedDate+".jpg";
         				filecontent = txtGymPicture1.getInputstream();
         				
         				out = new FileOutputStream(new File(path + picName));
         				
         				int read = 0;
         		        final byte[] bytes = new byte[1024];
         				
         				while ((read = filecontent.read(bytes)) != -1) {
         		            out.write(bytes, 0, read);
         		        }
         				
         		        //log.info("New file " + picName + " created at " + path);
         				entity.setPic1(picName);
         		        
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
         			
         				//Check if an old image exist and remove it
         				if(txtpic1 != null) {
         					File file = new File(path+txtpic1);
         					if(file.exists()){
         						file.delete();
         					}
         				}
         				
         				businessDelegatorView.updatePlace(entity);
                	
                }//end validador
					
     		
                    
     	        }
            
            //Secound picture
            
            if(txtGymPicture2 != null) {
            	
            	size =	txtGymPicture2.getSize();
 				
                if(size > 0) {
                	
            		try {
         				
         				Date currentDate = new Date();
         				String formatedDate = formatImage.format(currentDate);
         	            
         				//picName = trainerPic.getFileName();
         				picName = ""+entity.getIdPlace()+""+formatedDate+".jpg";
         				filecontent = txtGymPicture2.getInputstream();
         				
         				out = new FileOutputStream(new File(path + picName));
         				
         				int read = 0;
         		        final byte[] bytes = new byte[1024];
         				
         				while ((read = filecontent.read(bytes)) != -1) {
         		            out.write(bytes, 0, read);
         		        }
         				
         		        //log.info("New file " + picName + " created at " + path);
         				entity.setPic2(picName);
         		        
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
         			
         				//Check if an old image exist and remove it
         				if(txtpic2 != null) {
         					File file = new File(path+txtpic2);
         					if(file.exists()){
         						file.delete();
         					}
         				}
         				
         				businessDelegatorView.updatePlace(entity);
                	
                }//end validador
					
     	
                    
     	        }
            
            //third picture
            
            if(txtGymPicture3 != null) {
            	
            	size =	txtGymPicture3.getSize();
 				
                if(size > 0) {
                	
                	try {
         				
         				Date currentDate = new Date();
         				String formatedDate = formatImage.format(currentDate);
         	            
         				//picName = trainerPic.getFileName();
         				picName = ""+entity.getIdPlace()+""+formatedDate+".jpg";
         				filecontent = txtGymPicture3.getInputstream();
         				
         				out = new FileOutputStream(new File(path + picName));
         				
         				int read = 0;
         		        final byte[] bytes = new byte[1024];
         				
         				while ((read = filecontent.read(bytes)) != -1) {
         		            out.write(bytes, 0, read);
         		        }
         				
         		        //log.info("New file " + picName + " created at " + path);
         				entity.setPic3(picName);
         		        
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
         			
         				//Check if an old image exist and remove it
         				if(txtpic3 != null) {
         					File file = new File(path+txtpic3);
         					if(file.exists()){
         						file.delete();
         					}
         				}
         				
         				businessDelegatorView.updatePlace(entity);
                	
                }//end validador
			
     	      }
            
            //End seccion de fotos
            
            FacesUtils.addInfoMessage("Centro deportivo actualizado satisfactoriamente");
            
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }
    
    public String action_modify_cenpro() {
    	
        try {
        	
        	if(currentCenpro == null){
        		FacesUtils.addErrorMessage("No se pudo identificar el centro deportivo asociado a la cuenta, contacto con el administrador por la opcion Ayuda");
        	}else{
        		
                entity = currentCenpro;
                entity.setFiscalID(FacesUtils.checkString(txtPlaceNit));
                entity.setPlaceName(FacesUtils.checkString(txtPlaceName));
                entity.setPlaceAddress(FacesUtils.checkString(txtPlaceAddress));
                entity.setPlacePhone(FacesUtils.checkString(txtPlacePhone));
                entity.setPlacePhoneAlt(FacesUtils.checkString(txtPlacePhoneAlt));
                entity.setCountry(FacesUtils.checkInteger(selectPais));
                entity.setProvince(selectDepto);
                entity.setCity(FacesUtils.checkString(txtCiudad).toUpperCase());     
                entity.setInWeekly(horaEntradaLunesViernes);    
                entity.setOutWeekly(horaSalidaLunesViernes);
                entity.setInSatuday(horaEntradaSabado);
                entity.setOutSaturday(horaSalidaSabado);
                entity.setInSunday(horaEntradaDomingo);
                entity.setOutSunday(horaSalidaDomingo);
                entity.setDailyCost(FacesUtils.checkDouble(dailyTrainCost));
                entity.setMonthlyCost(FacesUtils.checkDouble(montlyTrainCost));
                
                businessDelegatorView.updatePlace(entity);
                
                Set<Placetype> placeType = entity.getPlacetypes();   
                placeType.clear();
                
                for (int i = 0; i < selectTiposCentro.length; i++) {  	 
                	Placetype opc = businessDelegatorView.getPlacetype(selectTiposCentro[i]);
                	placeType.add(opc);
                }
                
                entity.setPlacetypes(placeType);
                
                
                Set<Placeservices> placeServices = entity.getPlaceserviceses();   
                placeServices.clear();
                
                for (int i = 0; i < selectServicios.length; i++) {  	 
                	Placeservices opc = businessDelegatorView.getPlaceservices(selectServicios[i]);
                	placeServices.add(opc);
                }
                
                entity.setPlaceserviceses(placeServices);
                
                businessDelegatorView.updatePlace(entity);
                
                FacesUtils.addInfoMessage("Centro deportivo modificado satisfactoriamente");
        	}//end if-else

        } catch (Exception e) {
            data = null;
            log.info(e.toString());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedPlace = (PlaceDTO) (evt.getComponent().getAttributes()
                                           .get("selectedPlace"));

            Integer idPlace = new Integer(selectedPlace.getIdPlace());
            entity = businessDelegatorView.getPlace(idPlace);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer idPlace = FacesUtils.checkInteger(txtIdPlace);
            entity = businessDelegatorView.getPlace(idPlace);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deletePlace(entity);
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
            selectedPlace = (PlaceDTO) (evt.getComponent().getAttributes()
                                           .get("selectedPlace"));

            Integer idPlace = new Integer(selectedPlace.getIdPlace());
            entity = businessDelegatorView.getPlace(idPlace);
            businessDelegatorView.deletePlace(entity);
            data.remove(selectedPlace);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Integer idPlace, String placeAddress,
        Date placeCreated, Integer placeIsVzone, String placeName,
        Integer placeOwner, String placePhone, String placePhoneAlt,
        Integer placeStatus) throws Exception {
        try {
            entity.setPlaceAddress(FacesUtils.checkString(placeAddress));
            entity.setPlaceCreated(FacesUtils.checkDate(placeCreated));
            entity.setPlaceIsVzone(FacesUtils.checkInteger(placeIsVzone));
            entity.setPlaceName(FacesUtils.checkString(placeName));
            entity.setPlaceOwner(FacesUtils.checkInteger(placeOwner));
            entity.setPlacePhone(FacesUtils.checkString(placePhone));
            entity.setPlacePhoneAlt(FacesUtils.checkString(placePhoneAlt));
            entity.setPlaceStatus(FacesUtils.checkInteger(placeStatus));
            businessDelegatorView.updatePlace(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("PlaceView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtPlaceAddress() {
        return txtPlaceAddress;
    }

    public void setTxtPlaceAddress(InputText txtPlaceAddress) {
        this.txtPlaceAddress = txtPlaceAddress;
    }

    public InputText getTxtPlaceIsVzone() {
        return txtPlaceIsVzone;
    }

    public void setTxtPlaceIsVzone(InputText txtPlaceIsVzone) {
        this.txtPlaceIsVzone = txtPlaceIsVzone;
    }

    public InputText getTxtPlaceName() {
        return txtPlaceName;
    }

    public void setTxtPlaceName(InputText txtPlaceName) {
        this.txtPlaceName = txtPlaceName;
    }

    public InputText getTxtPlaceOwner() {
        return txtPlaceOwner;
    }

    public void setTxtPlaceOwner(InputText txtPlaceOwner) {
        this.txtPlaceOwner = txtPlaceOwner;
    }

    public InputText getTxtPlacePhone() {
        return txtPlacePhone;
    }

    public void setTxtPlacePhone(InputText txtPlacePhone) {
        this.txtPlacePhone = txtPlacePhone;
    }

    public InputText getTxtPlacePhoneAlt() {
        return txtPlacePhoneAlt;
    }

    public void setTxtPlacePhoneAlt(InputText txtPlacePhoneAlt) {
        this.txtPlacePhoneAlt = txtPlacePhoneAlt;
    }

    public InputText getTxtPlaceStatus() {
        return txtPlaceStatus;
    }

    public void setTxtPlaceStatus(InputText txtPlaceStatus) {
        this.txtPlaceStatus = txtPlaceStatus;
    }

    public Calendar getTxtPlaceCreated() {
        return txtPlaceCreated;
    }

    public void setTxtPlaceCreated(Calendar txtPlaceCreated) {
        this.txtPlaceCreated = txtPlaceCreated;
    }

    public InputText getTxtIdPlace() {
        return txtIdPlace;
    }

    public void setTxtIdPlace(InputText txtIdPlace) {
        this.txtIdPlace = txtIdPlace;
    }

    public List<PlaceDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataPlace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<PlaceDTO> placeDTO) {
        this.data = placeDTO;
    }

    public PlaceDTO getSelectedPlace() {
        return selectedPlace;
    }

    public void setSelectedPlace(PlaceDTO place) {
        this.selectedPlace = place;
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

	public InputText getTxtPlaceNit() {
		return txtPlaceNit;
	}

	public void setTxtPlaceNit(InputText txtPlaceNit) {
		this.txtPlaceNit = txtPlaceNit;
	}

	public SelectOneMenu getSelectPais() {
		return selectPais;
	}

	public void setSelectPais(SelectOneMenu selectPais) {
		this.selectPais = selectPais;
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

	public boolean isLockDepto() {
		return lockDepto;
	}

	public void setLockDepto(boolean lockDepto) {
		this.lockDepto = lockDepto;
	}

	public Integer getSelectDepto() {
		return selectDepto;
	}

	public void setSelectDepto(Integer selectDepto) {
		this.selectDepto = selectDepto;
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

	public InputText getTxtAssocAct() {
		return txtAssocAct;
	}

	public void setTxtAssocAct(InputText txtAssocAct) {
		this.txtAssocAct = txtAssocAct;
	}

	public InputText getTxtAssocActOwner() {
		return txtAssocActOwner;
	}

	public void setTxtAssocActOwner(InputText txtAssocActOwner) {
		this.txtAssocActOwner = txtAssocActOwner;
	}

	public List<PlaceDTO> getDataCenpro() {
		try {
			
            if (dataCenpro == null) {
            	
            	if(idAccount == 0){
            		FacesUtils.addErrorMessage("Error al leer la cuenta, contacte con el administrador en Ayuda");
            	}else{
            		
            		dataCenpro = new ArrayList<PlaceDTO>();
                	
                	List<Place> list = businessDelegatorView.findByCriteriaInPlace(new Object[]{"accountID",false, idAccount, "="},
                			                                                       null,
                			                                                       null);
                	
                	for(Place place: list){
                		
                		dataCenpro.add(new PlaceDTO(place.getIdPlace(),
                				                    place.getFiscalID(),
                				                    place.getPlaceName(),
                				                    place.getPlaceAddress(),
                				                    place.getPlacePhone(),
                				                    place.getPlacetypes(),
                				                    place.getPlaceserviceses()));
                	}
                	
                	populateCenproData();
                	
            	}//end if-else

            }//end if
            
        } catch (Exception e) {
            log.info(e.toString());
        }
		return dataCenpro;
	}

	public void setDataCenpro(List<PlaceDTO> dataCenpro) {
		this.dataCenpro = dataCenpro;
	}

	public Integer[] getSelectTiposCentro() {
		return selectTiposCentro;
	}

	public void setSelectTiposCentro(Integer[] selectTiposCentro) {
		this.selectTiposCentro = selectTiposCentro;
	}

	public Map<String, Integer> getTiposCentro() {
		
		if(tiposCentro==null){
    		try {
    			
				List<Placetype> placeTypeList = businessDelegatorView.getPlacetype();
				
				tiposCentro = new LinkedHashMap<String, Integer>();
				
				for (Placetype placetypetmp : placeTypeList) {
					tiposCentro.put(placetypetmp.getName(), placetypetmp.getIdplacetype());
				}
			} catch (Exception e) {				
				e.printStackTrace();
			}
    	}
		
		return tiposCentro;
	}

	public void setTiposCentro(Map<String, Integer> tiposCentro) {
		this.tiposCentro = tiposCentro;
	}

	public Integer[] getSelectServicios() {	
		return selectServicios;
	}

	public void setSelectServicios(Integer[] selectServicios) {
		this.selectServicios = selectServicios;
	}

	public Map<String, Integer> getTiposServicio() {
		
		if(tiposServicio==null){
    		try {
				List<Placeservices> servicesList = businessDelegatorView.getPlaceservices();
				
				tiposServicio = new LinkedHashMap<String, Integer>();
				
				for (Placeservices servicestmp : servicesList) {
					tiposServicio.put(servicestmp.getNames(), servicestmp.getIdplaceservices());
				}
				
			} catch (Exception e) {				
				e.printStackTrace();
			}
    	}
		
		return tiposServicio;
	}

	public void setTiposServicio(Map<String, Integer> tiposServicio) {
		this.tiposServicio = tiposServicio;
	}

	public Date getHoraEntradaLunesViernes() {
		return horaEntradaLunesViernes;
	}

	public void setHoraEntradaLunesViernes(Date horaEntradaLunesViernes) {
		this.horaEntradaLunesViernes = horaEntradaLunesViernes;
	}

	public Date getHoraSalidaLunesViernes() {
		return horaSalidaLunesViernes;
	}

	public void setHoraSalidaLunesViernes(Date horaSalidaLunesViernes) {
		this.horaSalidaLunesViernes = horaSalidaLunesViernes;
	}

	public Date getHoraEntradaSabado() {
		return horaEntradaSabado;
	}

	public void setHoraEntradaSabado(Date horaEntradaSabado) {
		this.horaEntradaSabado = horaEntradaSabado;
	}

	public Date getHoraSalidaSabado() {
		return horaSalidaSabado;
	}

	public void setHoraSalidaSabado(Date horaSalidaSabado) {
		this.horaSalidaSabado = horaSalidaSabado;
	}

	public Date getHoraEntradaDomingo() {
		return horaEntradaDomingo;
	}

	public void setHoraEntradaDomingo(Date horaEntradaDomingo) {
		this.horaEntradaDomingo = horaEntradaDomingo;
	}

	public Date getHoraSalidaDomingo() {
		return horaSalidaDomingo;
	}

	public void setHoraSalidaDomingo(Date horaSalidaDomingo) {
		this.horaSalidaDomingo = horaSalidaDomingo;
	}

	public InputText getDailyTrainCost() {
		return dailyTrainCost;
	}

	public void setDailyTrainCost(InputText dailyTrainCost) {
		this.dailyTrainCost = dailyTrainCost;
	}

	public InputText getMontlyTrainCost() {
		return montlyTrainCost;
	}

	public void setMontlyTrainCost(InputText montlyTrainCost) {
		this.montlyTrainCost = montlyTrainCost;
	}

	public InputText getTxtlatitud() {
		return txtlatitud;
	}

	public void setTxtlatitud(InputText txtlatitud) {
		this.txtlatitud = txtlatitud;
	}

	public InputText getTxtlongitud() {
		return txtlongitud;
	}

	public void setTxtlongitud(InputText txtlongitud) {
		this.txtlongitud = txtlongitud;
	}

	public InputText getTxtcode() {
		return txtcode;
	}

	public void setTxtcode(InputText txtcode) {
		this.txtcode = txtcode;
	}

	public InputText getTxtpaytoken() {
		return txtpaytoken;
	}

	public void setTxtpaytoken(InputText txtpaytoken) {
		this.txtpaytoken = txtpaytoken;
	}


	public String getTxtdescripcion() {
		return txtdescripcion;
	}

	public void setTxtdescripcion(String txtdescripcion) {
		this.txtdescripcion = txtdescripcion;
	}

	public UploadedFile getTxtLogoGym() {
		return txtLogoGym;
	}

	public void setTxtLogoGym(UploadedFile txtLogoGym) {
		this.txtLogoGym = txtLogoGym;
	}

	public UploadedFile getTxtGymPicture1() {
		return txtGymPicture1;
	}

	public void setTxtGymPicture1(UploadedFile txtGymPicture1) {
		this.txtGymPicture1 = txtGymPicture1;
	}

	public UploadedFile getTxtGymPicture2() {
		return txtGymPicture2;
	}

	public void setTxtGymPicture2(UploadedFile txtGymPicture2) {
		this.txtGymPicture2 = txtGymPicture2;
	}

	public UploadedFile getTxtGymPicture3() {
		return txtGymPicture3;
	}

	public void setTxtGymPicture3(UploadedFile txtGymPicture3) {
		this.txtGymPicture3 = txtGymPicture3;
	}

	public String getTxtlogo() {
		return txtlogo;
	}

	public void setTxtlogo(String txtlogo) {
		this.txtlogo = txtlogo;
	}

	public String getTxtpic1() {
		return txtpic1;
	}

	public void setTxtpic1(String txtpic1) {
		this.txtpic1 = txtpic1;
	}

	public String getTxtpic2() {
		return txtpic2;
	}

	public void setTxtpic2(String txtpic2) {
		this.txtpic2 = txtpic2;
	}

	public String getTxtpic3() {
		return txtpic3;
	}

	public void setTxtpic3(String txtpic3) {
		this.txtpic3 = txtpic3;
	}

	public InputText getTxtEmailGym() {
		return txtEmailGym;
	}

	public void setTxtEmailGym(InputText txtEmailGym) {
		this.txtEmailGym = txtEmailGym;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public InputText getTxtMonthlyCost() {
		return txtMonthlyCost;
	}

	public void setTxtMonthlyCost(InputText txtMonthlyCost) {
		this.txtMonthlyCost = txtMonthlyCost;
	}

	public InputText getTxtDailyCost() {
		return txtDailyCost;
	}

	public void setTxtDailyCost(InputText txtDailyCost) {
		this.txtDailyCost = txtDailyCost;
	}
	
	

}
