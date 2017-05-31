package com.liderbs.presentation.backingBeans;

import com.liderbs.exceptions.*;

import com.liderbs.modelo.*;
import com.liderbs.modelo.dto.AutorizationDTO;
import com.liderbs.modelo.dto.MenuDTO;
import com.liderbs.modelo.dto.PlaceDTO;
import com.liderbs.modelo.dto.UsersDTO;
import com.liderbs.presentation.businessDelegate.*;

import com.liderbs.utilities.*;

import org.hibernate.Hibernate;
import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;

import org.primefaces.event.RowEditEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.Serializable;

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


/**
 * @author Zathura Code Generator http://code.google.com/p/zathura
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class AutorizationView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(AutorizationView.class);
    private InputText txtAutorizationCreator;
    private InputText txtAutorizationStatus;
    private InputText txtUsersIdusers;
    private InputText txtIdAutorizationtype_Autorizationtype;
    private InputText txtIdPlace_Place;
    private InputText txtIdAutorization;
    private Calendar txtAutorizationDate;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<AutorizationDTO> data;
    private AutorizationDTO selectedAutorization;
    private Autorization entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;
    private Users usuarioapp;
    // Cenpro
    private List<AutorizationDTO> dataAutorizedTrainers;
    private List<AutorizationDTO> dataInvitedTrainers;
    private List<AutorizationDTO> dataPostuledTrainers;
    //Trainer
    private List<AutorizationDTO> dataCenproInvitation;
    private List<AutorizationDTO> dataCenproRequest;
    private Integer idAccount = 0;
    private UsersDTO selectedUsers;
    private String userPlaceName;
    private AutorizationDTO selectedAuth;
    private List<PlaceDTO> dataPlaces;
    private PlaceDTO selectedPlace;
    private boolean showCenproDialog = false;

    public AutorizationView() {
        super();
    }
    
    @PostConstruct
    public void init(){
    	
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
    }
    
    public void postulateStaff(){
    	this.showCenproDialog = true;
    }
    
    public void action_closeDialogStaff(){
    	this.showCenproDialog = false;
    }
    
    public void action_accept_invitation(ActionEvent evt){
    	try{
    		
    		selectedAuth = (AutorizationDTO) (evt.getComponent().getAttributes().get("selectedAuth"));
    	
    		if(selectedAuth == null){
    			FacesUtils.addErrorMessage("Autorizacion invalida");
    		}else{
    		
    			int idAuth = selectedAuth.getIdAutorization();
    			Autorization auth = businessDelegatorView.getAutorization(idAuth);
    			auth.setAutorizationStatus(1);
    			businessDelegatorView.updateAutorization(auth);
    			this.dataCenproInvitation = null;
    			FacesUtils.addInfoMessage("Invitacion aceptada satisfactoriamente");
    		}
    	
    	}catch(Exception e){
    		log.info(e.toString());
    	}
    }
    
    public void action_accept_postuled(ActionEvent evt){
    	try{
    		
    		selectedAuth = (AutorizationDTO) (evt.getComponent().getAttributes().get("selectedAuth"));
    	
    		if(selectedAuth == null){
    			FacesUtils.addErrorMessage("Autorizacion invalida");
    		}else{
    		
    			int idAuth = selectedAuth.getIdAutorization();
    			Autorization auth = businessDelegatorView.getAutorization(idAuth);
    			auth.setAutorizationStatus(1);
    			businessDelegatorView.updateAutorization(auth);
    			this.dataPostuledTrainers = null;
    			this.dataAutorizedTrainers = null;
    			FacesUtils.addInfoMessage("Postulacion aceptada satisfactoriamente, puede ver el nuevo entrenador en Mi Staff");
    		}
    	
    	}catch(Exception e){
    		log.info(e.toString());
    	}
    }
    
    public void action_postulate(ActionEvent evt){
    	
    	try{
    		
    		selectedPlace = (PlaceDTO) (evt.getComponent().getAttributes().get("selectedCenpro"));
    		
    		if(selectedPlace == null){
    			FacesUtils.addErrorMessage("Centro deportivo seleccionado invalido");
    		}else{
    			
    			Users userLogged = getUsuarioapp();
    			int idUser = userLogged.getIdusers();
    			int idPlace = selectedPlace.getIdPlace();
    			
    			List<Autorization> list = businessDelegatorView.findByCriteriaInAutorization(new Object[]{"place.idPlace",false, idPlace, "=","usersIdusers",false, idUser, "="},
    																						 null,
    																						 null);
    			
    			if(list.size() == 0){
    			
    				Place place = businessDelegatorView.getPlace(idPlace);
    				Autorizationtype authtype = businessDelegatorView.getAutorizationtype(2);
    				
    				if(place == null){
    					FacesUtils.addErrorMessage("Centro deportivo invalido");
    				}else{
    					
    					Date today = new Date();
        				Autorization auth = new Autorization();
        				auth.setAutorizationDate(today);
        				auth.setAutorizationStatus(2);
        				auth.setPlace(place);
        				auth.setUsersIdusers(idUser);
        				auth.setAutorizationCreator(idUser);
        				auth.setAutorizationtype(authtype);
        				businessDelegatorView.saveAutorization(auth);
        				FacesUtils.addInfoMessage("Postulacion realizada satisfactoriamente");	
        				this.dataCenproRequest = null;
        				
    				}

    			}else{
    				
    				int alreadyPostuled = 0;
    				int statusPostuled = 0;
    				
    				for(Autorization auth:list){
    					if(auth.getUsersIdusers() == idUser){
    						alreadyPostuled = 1;
    						statusPostuled = auth.getAutorizationStatus();
    					}
    				}
    				
    				if(alreadyPostuled == 1){
    					if(statusPostuled == 2){
    						FacesUtils.addErrorMessage("Ya ha postulado a "+selectedPlace.getPlaceName()+" y su solicitud se encuentra en espera de aceptacion por el centro deportivo");
    					}else if(statusPostuled == 1){
    						FacesUtils.addErrorMessage("Ya ha postulado a "+selectedPlace.getPlaceName()+" y su solicitud fue aceptada, ya hace parte del staff");
    					}else{
    						FacesUtils.addErrorMessage("Ya ha postulado a "+selectedPlace.getPlaceName()+" y su solicitud fue rechazada");
    					}
    				}else{
    					if(statusPostuled == 2){
    						FacesUtils.addErrorMessage("El centro deportivo "+selectedPlace.getPlaceName()+" ya le ha enviado una invitacion y se encuentra en espera");
    					}else if(statusPostuled == 2){
    						FacesUtils.addErrorMessage("El centro deportivo "+selectedPlace.getPlaceName()+" ya le ha enviado una invitacion y su solicitud fue aceptada, ya hace parte del staff");
    					}else{
    						FacesUtils.addErrorMessage("El centro deportivo "+selectedPlace.getPlaceName()+" ya le ha enviado una invitacion y ha sido rechazada");
    					}
    				}
    				
    			}//end if-else
    		}//end if-else
    		
    	}catch(Exception e){
    		log.info(e.toString());
    	}
    }
    
    public String getUserPlaceName() {
    	
    try{	
    
    	if(userPlaceName == null){
    		
    	  	List<Place> placeList = businessDelegatorView.findByCriteriaInPlace(new Object[]{"accountID",false, idAccount, "="},
    				null,
    				null);
        	
        	        	
    		if(placeList.size() > 0){
    			for(Place place: placeList){
    				userPlaceName = place.getPlaceName();
    			}
    			 
    		}
    		
    	}
    	
    	}catch(Exception e){
    	log.info(e.toString());
    	}
    
		return userPlaceName;
	}

	public void setUserPlaceName(String userPlaceName) {
		this.userPlaceName = userPlaceName;
	}

	public void setUsuarioapp(Users usuarioapp) {
		this.usuarioapp = usuarioapp;
	}

	public String autorizeTrainer(ActionEvent evt){
    
    try{	
    	
    	Users userLogged = getUsuarioapp();
    
    	selectedUsers = (UsersDTO) (evt.getComponent().getAttributes().get("selectedTrainer"));	
    	
    	int idUser = selectedUsers.getIdusers();
    	
    	int idPlace = 0;
    	
    	//Buscar si el usuario tiene autorizaciones en algun gimnasio del cliente
    	
    	List<Place> placeList = businessDelegatorView.findByCriteriaInPlace(new Object[]{"accountID",false, idAccount, "="},
				null,
				null);
    	
    	int alreadyAuth = 0;

    	
		if(placeList.size() > 0){
			
			this.dataAutorizedTrainers = new ArrayList<AutorizationDTO>();
			
			
			for(Place place: placeList){
				
				idPlace = place.getIdPlace();
				
				List<Autorization> listAuth = businessDelegatorView.findByCriteriaInAutorization(new Object[]{"place.idPlace",false, idPlace, "=", "usersIdusers",false, idUser, "="},
																							 null,
																							 null);
				
				if(listAuth.size() > 0){
					alreadyAuth = 1;
				}
				
			}//end for
			
			if(alreadyAuth > 0){
				FacesUtils.addErrorMessage("El entrenador seleccionado ya hace parte de Mi Staff o tiene una solicitud en progreso");
			}else{

				Autorizationtype authtype = businessDelegatorView.getAutorizationtype(1);
				Place place = businessDelegatorView.getPlace(idPlace);
				
				Date today = new Date();
				
				Autorization auth = new Autorization();
				auth.setAutorizationDate(today);
				auth.setAutorizationStatus(2);
				auth.setPlace(place);
				auth.setUsersIdusers(idUser);
				auth.setAutorizationCreator(userLogged.getIdusers());
				auth.setAutorizationtype(authtype);
				businessDelegatorView.saveAutorization(auth);
				
				FacesUtils.addInfoMessage("La invitacion fue enviada satisfactoriamente, puedes visualizarla en solicitudes");
			}
			
		}else{
			FacesUtils.addErrorMessage("No tiene un centro deportivo al cual asignar el Staff");
		}
		
    }catch(Exception e){
    	log.info(e.toString());
    }
      return "";
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            AutorizationDTO autorizationDTO = (AutorizationDTO) e.getObject();

            if (txtAutorizationCreator == null) {
                txtAutorizationCreator = new InputText();
            }

            txtAutorizationCreator.setValue(autorizationDTO.getAutorizationCreator());

            if (txtAutorizationStatus == null) {
                txtAutorizationStatus = new InputText();
            }

            txtAutorizationStatus.setValue(autorizationDTO.getAutorizationStatus());

            if (txtUsersIdusers == null) {
                txtUsersIdusers = new InputText();
            }

            txtUsersIdusers.setValue(autorizationDTO.getUsersIdusers());

            if (txtIdAutorizationtype_Autorizationtype == null) {
                txtIdAutorizationtype_Autorizationtype = new InputText();
            }

            txtIdAutorizationtype_Autorizationtype.setValue(autorizationDTO.getIdAutorizationtype_Autorizationtype());

            if (txtIdPlace_Place == null) {
                txtIdPlace_Place = new InputText();
            }

            txtIdPlace_Place.setValue(autorizationDTO.getIdPlace_Place());

            if (txtIdAutorization == null) {
                txtIdAutorization = new InputText();
            }

            txtIdAutorization.setValue(autorizationDTO.getIdAutorization());

            if (txtAutorizationDate == null) {
                txtAutorizationDate = new Calendar();
            }

            txtAutorizationDate.setValue(autorizationDTO.getAutorizationDate());

            Integer idAutorization = FacesUtils.checkInteger(txtIdAutorization);
            entity = businessDelegatorView.getAutorization(idAutorization);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedAutorization = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedAutorization = null;

        if (txtAutorizationCreator != null) {
            txtAutorizationCreator.setValue(null);
            txtAutorizationCreator.setDisabled(true);
        }

        if (txtAutorizationStatus != null) {
            txtAutorizationStatus.setValue(null);
            txtAutorizationStatus.setDisabled(true);
        }

        if (txtUsersIdusers != null) {
            txtUsersIdusers.setValue(null);
            txtUsersIdusers.setDisabled(true);
        }

        if (txtIdAutorizationtype_Autorizationtype != null) {
            txtIdAutorizationtype_Autorizationtype.setValue(null);
            txtIdAutorizationtype_Autorizationtype.setDisabled(true);
        }

        if (txtIdPlace_Place != null) {
            txtIdPlace_Place.setValue(null);
            txtIdPlace_Place.setDisabled(true);
        }

        if (txtAutorizationDate != null) {
            txtAutorizationDate.setValue(null);
            txtAutorizationDate.setDisabled(true);
        }

        if (txtIdAutorization != null) {
            txtIdAutorization.setValue(null);
            txtIdAutorization.setDisabled(false);
        }

        if (btnSave != null) {
            btnSave.setDisabled(true);
        }

        if (btnDelete != null) {
            btnDelete.setDisabled(true);
        }

        return "";
    }

    public void listener_txtAutorizationDate() {
        Date inputDate = (Date) txtAutorizationDate.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtId() {
        try {
            Integer idAutorization = FacesUtils.checkInteger(txtIdAutorization);
            entity = (idAutorization != null)
                ? businessDelegatorView.getAutorization(idAutorization) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtAutorizationCreator.setDisabled(false);
            txtAutorizationStatus.setDisabled(false);
            txtUsersIdusers.setDisabled(false);
            txtIdAutorizationtype_Autorizationtype.setDisabled(false);
            txtIdPlace_Place.setDisabled(false);
            txtAutorizationDate.setDisabled(false);
            txtIdAutorization.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtAutorizationCreator.setValue(entity.getAutorizationCreator());
            txtAutorizationCreator.setDisabled(false);
            txtAutorizationDate.setValue(entity.getAutorizationDate());
            txtAutorizationDate.setDisabled(false);
            txtAutorizationStatus.setValue(entity.getAutorizationStatus());
            txtAutorizationStatus.setDisabled(false);
            txtUsersIdusers.setValue(entity.getUsersIdusers());
            txtUsersIdusers.setDisabled(false);
            txtIdAutorizationtype_Autorizationtype.setValue(entity.getAutorizationtype()
                                                                  .getIdAutorizationtype());
            txtIdAutorizationtype_Autorizationtype.setDisabled(false);
            txtIdPlace_Place.setValue(entity.getPlace().getIdPlace());
            txtIdPlace_Place.setDisabled(false);
            txtIdAutorization.setValue(entity.getIdAutorization());
            txtIdAutorization.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedAutorization = (AutorizationDTO) (evt.getComponent()
                                                     .getAttributes()
                                                     .get("selectedAutorization"));
        txtAutorizationCreator.setValue(selectedAutorization.getAutorizationCreator());
        txtAutorizationCreator.setDisabled(false);
        txtAutorizationDate.setValue(selectedAutorization.getAutorizationDate());
        txtAutorizationDate.setDisabled(false);
        txtAutorizationStatus.setValue(selectedAutorization.getAutorizationStatus());
        txtAutorizationStatus.setDisabled(false);
        txtUsersIdusers.setValue(selectedAutorization.getUsersIdusers());
        txtUsersIdusers.setDisabled(false);
        txtIdAutorizationtype_Autorizationtype.setValue(selectedAutorization.getIdAutorizationtype_Autorizationtype());
        txtIdAutorizationtype_Autorizationtype.setDisabled(false);
        txtIdPlace_Place.setValue(selectedAutorization.getIdPlace_Place());
        txtIdPlace_Place.setDisabled(false);
        txtIdAutorization.setValue(selectedAutorization.getIdAutorization());
        txtIdAutorization.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedAutorization == null) && (entity == null)) {
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
            entity = new Autorization();

            Integer idAutorization = FacesUtils.checkInteger(txtIdAutorization);

            entity.setAutorizationCreator(FacesUtils.checkInteger(
                    txtAutorizationCreator));
            entity.setAutorizationDate(FacesUtils.checkDate(txtAutorizationDate));
            entity.setAutorizationStatus(FacesUtils.checkInteger(
                    txtAutorizationStatus));
            entity.setIdAutorization(idAutorization);
            entity.setUsersIdusers(FacesUtils.checkInteger(txtUsersIdusers));
            entity.setAutorizationtype((FacesUtils.checkInteger(
                    txtIdAutorizationtype_Autorizationtype) != null)
                ? businessDelegatorView.getAutorizationtype(
                    FacesUtils.checkInteger(
                        txtIdAutorizationtype_Autorizationtype)) : null);
            entity.setPlace((FacesUtils.checkInteger(txtIdPlace_Place) != null)
                ? businessDelegatorView.getPlace(FacesUtils.checkInteger(
                        txtIdPlace_Place)) : null);
            businessDelegatorView.saveAutorization(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
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
                Integer idAutorization = new Integer(selectedAutorization.getIdAutorization());
                entity = businessDelegatorView.getAutorization(idAutorization);
            }

            entity.setAutorizationCreator(FacesUtils.checkInteger(
                    txtAutorizationCreator));
            entity.setAutorizationDate(FacesUtils.checkDate(txtAutorizationDate));
            entity.setAutorizationStatus(FacesUtils.checkInteger(
                    txtAutorizationStatus));
            entity.setUsersIdusers(FacesUtils.checkInteger(txtUsersIdusers));
            entity.setAutorizationtype((FacesUtils.checkInteger(
                    txtIdAutorizationtype_Autorizationtype) != null)
                ? businessDelegatorView.getAutorizationtype(
                    FacesUtils.checkInteger(
                        txtIdAutorizationtype_Autorizationtype)) : null);
            entity.setPlace((FacesUtils.checkInteger(txtIdPlace_Place) != null)
                ? businessDelegatorView.getPlace(FacesUtils.checkInteger(
                        txtIdPlace_Place)) : null);
            businessDelegatorView.updateAutorization(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedAutorization = (AutorizationDTO) (evt.getComponent()
                                                         .getAttributes()
                                                         .get("selectedAutorization"));

            Integer idAutorization = new Integer(selectedAutorization.getIdAutorization());
            entity = businessDelegatorView.getAutorization(idAutorization);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer idAutorization = FacesUtils.checkInteger(txtIdAutorization);
            entity = businessDelegatorView.getAutorization(idAutorization);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteAutorization(entity);
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
            selectedAutorization = (AutorizationDTO) (evt.getComponent()
                                                         .getAttributes()
                                                         .get("selectedAutorization"));

            Integer idAutorization = new Integer(selectedAutorization.getIdAutorization());
            entity = businessDelegatorView.getAutorization(idAutorization);
            businessDelegatorView.deleteAutorization(entity);
            data.remove(selectedAutorization);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Integer autorizationCreator,
        Date autorizationDate, Integer autorizationStatus,
        Integer idAutorization, Integer usersIdusers,
        Integer idAutorizationtype_Autorizationtype, Integer idPlace_Place)
        throws Exception {
        try {
            entity.setAutorizationCreator(FacesUtils.checkInteger(
                    autorizationCreator));
            entity.setAutorizationDate(FacesUtils.checkDate(autorizationDate));
            entity.setAutorizationStatus(FacesUtils.checkInteger(
                    autorizationStatus));
            entity.setUsersIdusers(FacesUtils.checkInteger(usersIdusers));
            businessDelegatorView.updateAutorization(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("AutorizationView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtAutorizationCreator() {
        return txtAutorizationCreator;
    }

    public void setTxtAutorizationCreator(InputText txtAutorizationCreator) {
        this.txtAutorizationCreator = txtAutorizationCreator;
    }

    public InputText getTxtAutorizationStatus() {
        return txtAutorizationStatus;
    }

    public void setTxtAutorizationStatus(InputText txtAutorizationStatus) {
        this.txtAutorizationStatus = txtAutorizationStatus;
    }

    public InputText getTxtUsersIdusers() {
        return txtUsersIdusers;
    }

    public void setTxtUsersIdusers(InputText txtUsersIdusers) {
        this.txtUsersIdusers = txtUsersIdusers;
    }

    public InputText getTxtIdAutorizationtype_Autorizationtype() {
        return txtIdAutorizationtype_Autorizationtype;
    }

    public void setTxtIdAutorizationtype_Autorizationtype(
        InputText txtIdAutorizationtype_Autorizationtype) {
        this.txtIdAutorizationtype_Autorizationtype = txtIdAutorizationtype_Autorizationtype;
    }

    public InputText getTxtIdPlace_Place() {
        return txtIdPlace_Place;
    }

    public void setTxtIdPlace_Place(InputText txtIdPlace_Place) {
        this.txtIdPlace_Place = txtIdPlace_Place;
    }

    public Calendar getTxtAutorizationDate() {
        return txtAutorizationDate;
    }

    public void setTxtAutorizationDate(Calendar txtAutorizationDate) {
        this.txtAutorizationDate = txtAutorizationDate;
    }

    public InputText getTxtIdAutorization() {
        return txtIdAutorization;
    }

    public void setTxtIdAutorization(InputText txtIdAutorization) {
        this.txtIdAutorization = txtIdAutorization;
    }

    public List<AutorizationDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataAutorization();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<AutorizationDTO> autorizationDTO) {
        this.data = autorizationDTO;
    }

    public AutorizationDTO getSelectedAutorization() {
        return selectedAutorization;
    }

    public void setSelectedAutorization(AutorizationDTO autorization) {
        this.selectedAutorization = autorization;
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
    
    public Users getUsuarioapp() {
		if(usuarioapp==null) {				
			  usuarioapp = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			  //FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("rolsaldosefa_session", usuarioapp.get);				
		}
		return usuarioapp;
	}
    
    public List<AutorizationDTO> getDataInvitedTrainers() {
    	
    	try{	
    		
    		if(this.dataInvitedTrainers == null){
    			
    				List<Place> placeList = businessDelegatorView.findByCriteriaInPlace(new Object[]{"accountID",false, idAccount, "="},
    																				null,
    																				null);
    			 
    			//Si tiene centro deportivos
    			
    			if(placeList.size() > 0){
    				
    				this.dataInvitedTrainers = new ArrayList<AutorizationDTO>();
    				
    				
    				for(Place place: placeList){
    					
    					int idPlace = place.getIdPlace();
    					
    					List<Autorization> listAuth = businessDelegatorView.findByCriteriaInAutorization(new Object[]{"place.idPlace",false, idPlace, "=","autorizationtype.idAutorizationtype",false, 1, "="},
    																								 null,
    																								 null);
    					boolean authVisible = false;
    					
    					for(Autorization auth: listAuth){
    						
    						Users trainer = businessDelegatorView.getUsers(auth.getUsersIdusers());
    						
    						String authStatus = "";
    						
    						if(auth.getAutorizationStatus() == 1){
    							authStatus = "Aceptada";
    							authVisible = false;
    						}else if(auth.getAutorizationStatus() == 2){
    							authStatus = "En espera de respuesta";
    							authVisible = true;
    						}else{
    							authStatus = "No aceptada";
    							authVisible = false;
    						}
    						
    						if(trainer != null){
    							this.dataInvitedTrainers.add(new AutorizationDTO(auth.getIdAutorization(),
    																			   auth.getUsersIdusers(),
    																			   auth.getAutorizationStatus(),
    																			   auth.getAutorizationCreator(),
    																			   auth.getAutorizationDate(),
    																			   trainer.getName(),
    																			   25,
    																			   "Funcionales",
    																			   place.getPlaceName(),
    																			   authStatus,
    																			   authVisible));
    						}//end if
    					}// end for
    					
    				}// end for place
    				
    		}
    	}// end is null
    			
    	}catch(Exception e){
    		log.info(e.toString());
    	}
    	
		return dataInvitedTrainers;
	}

	public void setDataInvitedTrainers(List<AutorizationDTO> dataInvitedTrainers) {
		this.dataInvitedTrainers = dataInvitedTrainers;
	}

	public List<AutorizationDTO> getDataAutorizedTrainers() {
		
	try{	
		
		if(this.dataAutorizedTrainers == null){
			
			
			//Busco los lugares asociados a la cuenta del usuario
			 
			List<Place> placeList = businessDelegatorView.findByCriteriaInPlace(new Object[]{"accountID",false, idAccount, "="},
																				null,
																				null);
			 
			//Si tiene centro deportivos
			
			if(placeList.size() > 0){
				
				this.dataAutorizedTrainers = new ArrayList<AutorizationDTO>();
				
				
				for(Place place: placeList){
					
					int idPlace = place.getIdPlace();
					
					List<Autorization> listAuth = businessDelegatorView.findByCriteriaInAutorization(new Object[]{"place.idPlace",false, idPlace, "=","autorizationStatus",false, 1L, "="},
																								 null,
																								 null);
					
					boolean authVisible = false;
					
					for(Autorization auth: listAuth){
						
						Users trainer = businessDelegatorView.getUsers(auth.getUsersIdusers());
						
						String authStatus = "";
						
						if(auth.getAutorizationStatus() == 1){
							authStatus = "Aceptada";
							authVisible = false;
						}else if(auth.getAutorizationStatus() == 2){
							authStatus = "En espera de respuesta";
							authVisible = true;
						}else{
							authStatus = "No aceptada";
							authVisible = false;
						}
						
						if(trainer != null){
							this.dataAutorizedTrainers.add(new AutorizationDTO(auth.getIdAutorization(),
																			   auth.getUsersIdusers(),
																			   auth.getAutorizationStatus(),
																			   auth.getAutorizationCreator(),
																			   auth.getAutorizationDate(),
																			   trainer.getName(),
																			   25,
																			   "Funcionales",
																			   place.getPlaceName(),
																			   authStatus,
																			   authVisible));
						}//end if
					}// end for
				}
				
				
				
				
			}//validador tiene centros deportivos
			
			
			
			
			
		}
	
	}catch(Exception e){
		log.info(e.toString());
	}
		
		return dataAutorizedTrainers;
	
	}

	public void setDataAutorizedTrainers(List<AutorizationDTO> dataAutorizedTrainers) {
		this.dataAutorizedTrainers = dataAutorizedTrainers;
	}

	public List<AutorizationDTO> getDataCenproInvitation() {
		
		try{	
			
			if(this.dataCenproInvitation == null){
			
				this.dataCenproInvitation = new ArrayList<AutorizationDTO>();
				
				Users userLogged = getUsuarioapp();
			    
		    	int idUser = userLogged.getIdusers();
				
				List<Autorization> listAuth = businessDelegatorView.findByCriteriaInAutorization(new Object[]{"usersIdusers",false, idUser, "=","autorizationCreator",false, idUser, "!="},
						 null,
						 null);
				
				boolean authVisible = false;
				
				for(Autorization auth: listAuth){
					
					Users trainer = businessDelegatorView.getUsers(auth.getUsersIdusers());
					
					String authStatus = "";
					
					if(auth.getAutorizationStatus() == 1){
						authStatus = "Aceptada";
						authVisible = false;
					}else if(auth.getAutorizationStatus() == 2){
						authStatus = "En espera de respuesta";
						authVisible = true;
					}else{
						authStatus = "No aceptada";
						authVisible = false;
					}
					
					
					Hibernate.initialize(auth.getPlace().getPlaceName());
					
					if(trainer != null){
						
						this.dataCenproInvitation.add(new AutorizationDTO(auth.getIdAutorization(),
																		   auth.getUsersIdusers(),
																		   auth.getAutorizationStatus(),
																		   auth.getAutorizationCreator(),
																		   auth.getAutorizationDate(),
																		   trainer.getName(),
																		   25,
																		   "Funcionales",
																		   auth.getPlace().getPlaceName(),
																		   authStatus,
																		   authVisible));
					}//end if
				}// end for
				
				
			}
			
		}catch(Exception e){
			log.info(e.toString());
		}
		
		return dataCenproInvitation;
	}

	public void setDataCenproInvitation(List<AutorizationDTO> dataCenproInvitation) {
		this.dataCenproInvitation = dataCenproInvitation;
	}

	public List<AutorizationDTO> getDataCenproRequest() {
		
		try{	
			
			if(this.dataCenproRequest == null){
			
				
				this.dataCenproRequest = new ArrayList<AutorizationDTO>();
				
				Users userLogged = getUsuarioapp();
			    
		    	int idUser = userLogged.getIdusers();
				
				
				List<Autorization> listAuth = businessDelegatorView.findByCriteriaInAutorization(new Object[]{"autorizationCreator",false, idUser, "="},
						 null,
						 null);
				
				boolean authVisible = false;
				
				for(Autorization auth: listAuth){
					
					Users trainer = businessDelegatorView.getUsers(auth.getUsersIdusers());
					
					String authStatus = "";
					
					if(auth.getAutorizationStatus() == 1){
						authStatus = "Aceptada";
						authVisible = false;
					}else if(auth.getAutorizationStatus() == 2){
						authStatus = "En espera de respuesta";
						authVisible = true;
					}else{
						authStatus = "No aceptada";
						authVisible = false;
					}
					
					Hibernate.initialize(auth.getPlace().getPlaceName());
					
					
					if(trainer != null){
						
						this.dataCenproRequest.add(new AutorizationDTO(auth.getIdAutorization(),
																		   auth.getUsersIdusers(),
																		   auth.getAutorizationStatus(),
																		   auth.getAutorizationCreator(),
																		   auth.getAutorizationDate(),
																		   trainer.getName(),
																		   25,
																		   "Funcionales",
																		   auth.getPlace().getPlaceName(),
																		   authStatus,
																		   authVisible));
					}//end if
				}// end for
				
				
			}
			
		}catch(Exception e){
			log.info(e.toString());
		}
		
		return dataCenproRequest;
	}

	public void setDataCenproRequest(List<AutorizationDTO> dataCenproRequest) {
		this.dataCenproRequest = dataCenproRequest;
	}

	public List<PlaceDTO> getDataPlaces() {
	try{
		if(dataPlaces == null){
			dataPlaces = new ArrayList<PlaceDTO>();
			dataPlaces = businessDelegatorView.getDataPlace();	
		}
		
	}catch(Exception e){
		log.info(e.toString());
	}
		
		return dataPlaces;
	}

	public void setDataPlaces(List<PlaceDTO> dataPlaces) {
		this.dataPlaces = dataPlaces;
	}

	public boolean isShowCenproDialog() {
		return showCenproDialog;
	}

	public void setShowCenproDialog(boolean showCenproDialog) {
		this.showCenproDialog = showCenproDialog;
	}

	public List<AutorizationDTO> getDataPostuledTrainers() {
		
    	try{	
    		
    		if(this.dataPostuledTrainers == null){
    			
    				List<Place> placeList = businessDelegatorView.findByCriteriaInPlace(new Object[]{"accountID",false, idAccount, "="},
    																				null,
    																				null);
    			 
    			//Si tiene centro deportivos
    			
    			if(placeList.size() > 0){
    				
    				this.dataPostuledTrainers = new ArrayList<AutorizationDTO>();
    				
    				
    				for(Place place: placeList){
    					
    					int idPlace = place.getIdPlace();
    					
    					List<Autorization> listAuth = businessDelegatorView.findByCriteriaInAutorization(new Object[]{"place.idPlace",false, idPlace, "=","autorizationtype.idAutorizationtype",false, 2, "="},
    																								 null,
    																								 null);
    					boolean authVisible = false;
    					
    					for(Autorization auth: listAuth){
    						
    						Users trainer = businessDelegatorView.getUsers(auth.getUsersIdusers());
    						
    						String authStatus = "";
    						
    						if(auth.getAutorizationStatus() == 1){
    							authStatus = "Aceptada";
    							authVisible = false;
    						}else if(auth.getAutorizationStatus() == 2){
    							authStatus = "En espera de respuesta";
    							authVisible = true;
    						}else{
    							authStatus = "No aceptada";
    							authVisible = false;
    						}
    						
    						if(trainer != null){
    							this.dataPostuledTrainers.add(new AutorizationDTO(auth.getIdAutorization(),
    																			   auth.getUsersIdusers(),
    																			   auth.getAutorizationStatus(),
    																			   auth.getAutorizationCreator(),
    																			   auth.getAutorizationDate(),
    																			   trainer.getName(),
    																			   25,
    																			   "Funcionales",
    																			   place.getPlaceName(),
    																			   authStatus,
    																			   authVisible));
    						}//end if
    					}// end for
    					
    				}// end for place
    				
    		}
    	}// end is null
    			
    	}catch(Exception e){
    		log.info(e.toString());
    	}
		
		return dataPostuledTrainers;
	}

	public void setDataPostuledTrainers(List<AutorizationDTO> dataPostuledTrainers) {
		this.dataPostuledTrainers = dataPostuledTrainers;
	}

	
    
    
}
