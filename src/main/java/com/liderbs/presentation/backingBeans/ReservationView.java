package com.liderbs.presentation.backingBeans;

import com.liderbs.exceptions.*;

import com.liderbs.modelo.*;
import com.liderbs.modelo.dto.ReservationDTO;

import com.liderbs.presentation.businessDelegate.*;

import com.liderbs.utilities.*;

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
public class ReservationView implements Serializable {
	
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(ReservationView.class);
    
    private InputText txtReservationHolderCode;
    private InputText txtReservationHolderEmail;
    private InputText txtReservationHolderId;
    private InputText txtReservationHolderName;
    private InputText txtReservationHolderTel;
    private InputText txtReservationIdclass;
    private InputText txtReservationClassDesc;
    private InputText txtReservationStatus;
    private InputText txtIdreservation;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<ReservationDTO> data;
    private ReservationDTO selectedReservation;
    private Reservation entity;
    private boolean showDialog;
    private String txtCodigoReserva;
    private Users usuarioapp;
    private Integer idAccount = 0;
    private Integer tipoBusqueda;
    
    
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public ReservationView() {
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
    
    public Users getUsuarioapp() {
  		if(usuarioapp==null) {				
  			  usuarioapp = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
  			  //FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("rolsaldosefa_session", usuarioapp.get);				
  		}
  		return usuarioapp;
  	}
    
    public String action_consumo(ActionEvent evt) {
    	   
        try{
       
            selectedReservation = (ReservationDTO) (evt.getComponent()
                                                       .getAttributes()
                                                       .get("selectedReservation"));
            
            int idReservation = selectedReservation.getIdreservation();
            
            List<Consumption> listConsumption = businessDelegatorView.findByCriteriaInConsumption(new Object[]{"idreservation",false, idReservation, "="},
            																					  null,
            																					  null);
            
            int idConsumption = 0;
            
            for(Consumption consumo:listConsumption){
            	idConsumption = consumo.getIdconsumption();
            }
            
            //
            if(idConsumption == 0){
            	FacesUtils.addErrorMessage("No se encontraron consumos asociados pero la reserva esta activa, por favor contacte con soporte@govirfit.com");
            }else{
            	
            	Consumption consumo = businessDelegatorView.getConsumption(idConsumption);
            	
            //Verificar el saldo
            	int comprado = 0;
            	int consumido = 0;
            	int saldo = 0;
            	
            	comprado = consumo.getCantidad();
            	consumido = consumo.getConsumido();
            	saldo = comprado - consumido;
            	
            	if(saldo > 0){
            		
            		Date fecha = new Date();
            		Users user = getUsuarioapp();
            		
            		consumido = consumido+1;
            		consumo.setConsumido(consumido);
            		businessDelegatorView.updateConsumption(consumo);
            		
            		ConsumptionDetail consumoDetail = new ConsumptionDetail();
                  	 
                  	consumoDetail.setIdconsumption(consumo.getIdconsumption());
                  	consumoDetail.setFecha(fecha);
                  	consumoDetail.setHora(fecha);
                  	consumoDetail.setCantidad(1);
                  	consumoDetail.setIdusuarioCreo(user.getIdusers());
                  	consumoDetail.setUsuarioCreo(user.getUsername());
                  	 
                  	businessDelegatorView.saveConsumptionDetail(consumoDetail);
            		
            		FacesUtils.addErrorMessage("Se aplico el consumo satisfactoriamente");
            		
            	}else{
            		FacesUtils.addErrorMessage("No es posible consumir la reserva saldo igual a "+saldo);
            	}//End if-else
            	
            }//end if-else
            
        }catch(Exception e){
        	log.info(e.toString());
        }
        
        return "";
     }
    
    public void activarReserva(){
    	
    try{
    	
    	if(FacesUtils.checkString(txtReservationHolderCode) == null){
    		FacesUtils.addErrorMessage("Ingrese un codigo de reserva");
    	}else if(FacesUtils.checkString(txtReservationHolderId) == null){
    		FacesUtils.addErrorMessage("Ingrese la identificacion del cliente");
    	}else if(FacesUtils.checkString(txtReservationHolderName) == null){
    		FacesUtils.addErrorMessage("Ingrese el nombre del cliente");
    	}else if(FacesUtils.checkString(txtReservationHolderEmail) == null){
    		FacesUtils.addErrorMessage("Ingrese el correo del cliente");
    	}else if(FacesUtils.checkString(txtReservationHolderTel) == null){
    		FacesUtils.addErrorMessage("Ingrese el telefono del cliente");
    	}else if(FacesUtils.checkInteger(txtReservationIdclass) == null){
    		FacesUtils.addErrorMessage("No se pudo identificar la clase, contacte con soporte@govirfit.com");
    	}else if(FacesUtils.checkString(txtIdreservation) == null){
    		FacesUtils.addErrorMessage("No se pudo identificar la reserva, contacte con soporte@govirfit.com");
    	}else{
    		
       	 Users user = getUsuarioapp();
    	 
       	 int idReservation = FacesUtils.checkInteger(txtIdreservation);
       	 int idClass = FacesUtils.checkInteger(txtReservationIdclass);
       	 
       	 Reservation reserv = businessDelegatorView.getReservation(idReservation);
       	 
       	 Date fechaReservation = new Date();
       	 String identificacionUsuario = FacesUtils.checkString(txtReservationHolderId);
       	 String usuarioNombre = FacesUtils.checkString(txtReservationHolderName);
       	 String codeReservation = FacesUtils.checkString(txtReservationHolderCode);
       	 
       	 Specialclass specClass= businessDelegatorView.getSpecialclass(idClass);
       	 
       	 //Buscar si el usuario actual es un gimnasio
       	 
       	 List<Place> placeList = businessDelegatorView.findByCriteriaInPlace(new Object[]{"accountID",false, idAccount, "="},
       																			 null,
       																			 null);
           	
       	 int idPlace = 0;
       	 String placeName = "";
       	 
       	 
       		if(placeList.size() > 0){
       			for(Place place: placeList){
       				idPlace = place.getIdPlace();
       				placeName = place.getPlaceName();
       			}
       		}
       	
       	 //End buscar si es gimnasio
       	 
       	 Consumption consumo = new Consumption();
       	 
       	 consumo.setIdreservation(idReservation);
       	 consumo.setFecha(fechaReservation);
       	 consumo.setUsuariodocumento(identificacionUsuario);
       	 consumo.setUsuarionombre(usuarioNombre);
       	 consumo.setReservation(codeReservation);
       	 consumo.setPlaceId(idPlace);
       	 consumo.setPlaceName(placeName);
       	 
       	 if(specClass.getIspackage() == 1){
       		consumo.setCantidad(specClass.getCantidadPackage());
          	consumo.setConsumido(1);
       	 }else{
       		consumo.setCantidad(1);
          	consumo.setConsumido(1);
       	 }
       	 
       	 businessDelegatorView.saveConsumption(consumo);
       	 
       	 ConsumptionDetail consumoDetail = new ConsumptionDetail();
       	 
       	 consumoDetail.setIdconsumption(consumo.getIdconsumption());
       	 consumoDetail.setFecha(fechaReservation);
       	 consumoDetail.setHora(fechaReservation);
       	 consumoDetail.setCantidad(1);
       	 consumoDetail.setIdusuarioCreo(user.getIdusers());
       	 consumoDetail.setUsuarioCreo(user.getUsername());
       	 
       	 businessDelegatorView.saveConsumptionDetail(consumoDetail);
       	 
       	if(specClass.getIspackage() == 1){
       		reserv.setReservationStatus(2);
       	 }else{
       		reserv.setReservationStatus(3);
       	 }
       	 
       	 reserv.setReservationHolderId(identificacionUsuario);
       	
       	 businessDelegatorView.updateReservation(reserv);
       	 
       	 FacesUtils.addInfoMessage("Reserva activada satisfactoriamente");
       	 
       	data = null;
       	setShowDialog(false);
    	action_clear();
    		
    	}//end if-else
    	
    	

    }catch(Exception e){
    	log.info(e.toString());
    }//end try-catch
		
    }
    
    public void buscarReserva(){
    
    	try{
    		
    		int valid = 0;
    		
    		if(tipoBusqueda == null){
    			FacesUtils.addErrorMessage("Seleccione un tipo de busqueda");
    		}else{
    			
        		if(txtCodigoReserva == null || txtCodigoReserva == ""){
        			if(tipoBusqueda == 1){
        				FacesUtils.addErrorMessage("Por favor ingrese un codigo de reserva");
        			}else{
        				FacesUtils.addErrorMessage("Por favor ingrese un numero de identificacion");
        			}
        		}else{
        		
        			if(data == null){
        				data = new ArrayList<ReservationDTO>();
        			}
        			
        			data.clear();
        		
        			String codigoReserva = txtCodigoReserva;
        			List<Reservation> list = new ArrayList<Reservation>();
        			list.clear();
        			
        		
        			if(tipoBusqueda == 1){
        				
        				list = businessDelegatorView.findByCriteriaInReservation(new Object[]{"reservationHolderCode",true, codigoReserva, "="},
								   null,
								   null);
        				
        			}else{
        				
        				list = businessDelegatorView.findByCriteriaInReservation(new Object[]{"reservationHolderId",true, codigoReserva, "="},
								   null,
								   null);
        				
        			}
        			
        			if(list.size() == 0){
        				FacesUtils.addErrorMessage("No se encontraron reservas con los parametros ingresados");
        			}else{
        				
              			String nombreClase = "";
            			String estadoReserva = "";
            			String renderReserveButton = "";
            		    String renderConsumeButton = "";
            			
            			for(Reservation reserva: list){
            				
            				nombreClase = "";
                			estadoReserva = "";
                			renderReserveButton = "";
                		    renderConsumeButton = "";
            				
            				if(reserva.getReservationIdclass() != null){
            					
            					Specialclass clase = businessDelegatorView.getSpecialclass(reserva.getReservationIdclass());
            					
            					if(clase != null){
            						nombreClase = clase.getClassTitle();
            					}
            					
            				}
            				
            				if(reserva.getReservationStatus() != null){
            					if(reserva.getReservationStatus() == 1){
            						estadoReserva = "Sin activar";
            						renderReserveButton = "true";
            						renderConsumeButton = "false";
            					}else if(reserva.getReservationStatus() == 2){
            						renderReserveButton = "false";
            						renderConsumeButton = "true";
            						estadoReserva = "Activa";
            					}else if(reserva.getReservationStatus() == 3){
            						renderReserveButton = "false";
            						renderConsumeButton = "false";
            						estadoReserva = "Completada";
            					}else{
            						renderReserveButton = "false";
            						renderConsumeButton = "false";
                					estadoReserva = "Indeterminada";
            					}
            				}else{
            					renderReserveButton = "false";
        						renderConsumeButton = "false";
            					estadoReserva = "Indeterminada";
            				}
            				
            				data.add(new ReservationDTO(reserva.getIdreservation(),
            						                    reserva.getReservationIdclass(),
            						                    reserva.getReservationHolderCode(),
            						                    reserva.getReservationHolderEmail(),
            						                    reserva.getReservationHolderId(),
            						                    reserva.getReservationHolderName(),
            						                    reserva.getReservationHolderTel(),
            						                    reserva.getReservationStatus(),
            						                    nombreClase,
            						                    estadoReserva,
            						                    renderReserveButton,
            						                    renderConsumeButton));
            				
            			}//end for
        				
        				
        			}//end list contains values
        			
        			
  
        		
        	}//end if

    	}//end tipo busqueda
	
    	}catch(Exception e){
    		FacesUtils.addErrorMessage("Se ha presentado un error consultando la reserva, contactanos a soporte@govirfit.com para ayudarte");
    		log.info(e.toString());
    	}
    		
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            ReservationDTO reservationDTO = (ReservationDTO) e.getObject();

            if (txtReservationHolderCode == null) {
                txtReservationHolderCode = new InputText();
            }

            txtReservationHolderCode.setValue(reservationDTO.getReservationHolderCode());

            if (txtReservationHolderEmail == null) {
                txtReservationHolderEmail = new InputText();
            }

            txtReservationHolderEmail.setValue(reservationDTO.getReservationHolderEmail());

            if (txtReservationHolderId == null) {
                txtReservationHolderId = new InputText();
            }

            txtReservationHolderId.setValue(reservationDTO.getReservationHolderId());

            if (txtReservationHolderName == null) {
                txtReservationHolderName = new InputText();
            }

            txtReservationHolderName.setValue(reservationDTO.getReservationHolderName());

            if (txtReservationHolderTel == null) {
                txtReservationHolderTel = new InputText();
            }

            txtReservationHolderTel.setValue(reservationDTO.getReservationHolderTel());

            if (txtReservationIdclass == null) {
                txtReservationIdclass = new InputText();
            }

            txtReservationIdclass.setValue(reservationDTO.getReservationIdclass());

            if (txtReservationStatus == null) {
                txtReservationStatus = new InputText();
            }

            txtReservationStatus.setValue(reservationDTO.getReservationStatus());

            if (txtIdreservation == null) {
                txtIdreservation = new InputText();
            }

            txtIdreservation.setValue(reservationDTO.getIdreservation());

            Integer idreservation = FacesUtils.checkInteger(txtIdreservation);
            entity = businessDelegatorView.getReservation(idreservation);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedReservation = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedReservation = null;

        if (txtReservationHolderCode != null) {
            txtReservationHolderCode.setValue(null);
            txtReservationHolderCode.setDisabled(true);
        }

        if (txtReservationHolderEmail != null) {
            txtReservationHolderEmail.setValue(null);
            txtReservationHolderEmail.setDisabled(true);
        }

        if (txtReservationHolderId != null) {
            txtReservationHolderId.setValue(null);
            txtReservationHolderId.setDisabled(true);
        }

        if (txtReservationHolderName != null) {
            txtReservationHolderName.setValue(null);
            txtReservationHolderName.setDisabled(true);
        }

        if (txtReservationHolderTel != null) {
            txtReservationHolderTel.setValue(null);
            txtReservationHolderTel.setDisabled(true);
        }

        if (txtReservationIdclass != null) {
            txtReservationIdclass.setValue(null);
            txtReservationIdclass.setDisabled(true);
        }

        if (txtReservationStatus != null) {
            txtReservationStatus.setValue(null);
            txtReservationStatus.setDisabled(true);
        }

        if (txtIdreservation != null) {
            txtIdreservation.setValue(null);
            txtIdreservation.setDisabled(false);
        }

        if (btnSave != null) {
            btnSave.setDisabled(true);
        }

        if (btnDelete != null) {
            btnDelete.setDisabled(true);
        }

        return "";
    }

    public void listener_txtId() {
        try {
            Integer idreservation = FacesUtils.checkInteger(txtIdreservation);
            entity = (idreservation != null)
                ? businessDelegatorView.getReservation(idreservation) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtReservationHolderCode.setDisabled(false);
            txtReservationHolderEmail.setDisabled(false);
            txtReservationHolderId.setDisabled(false);
            txtReservationHolderName.setDisabled(false);
            txtReservationHolderTel.setDisabled(false);
            txtReservationIdclass.setDisabled(false);
            txtReservationStatus.setDisabled(false);
            txtIdreservation.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtReservationHolderCode.setValue(entity.getReservationHolderCode());
            txtReservationHolderCode.setDisabled(false);
            txtReservationHolderEmail.setValue(entity.getReservationHolderEmail());
            txtReservationHolderEmail.setDisabled(false);
            txtReservationHolderId.setValue(entity.getReservationHolderId());
            txtReservationHolderId.setDisabled(false);
            txtReservationHolderName.setValue(entity.getReservationHolderName());
            txtReservationHolderName.setDisabled(false);
            txtReservationHolderTel.setValue(entity.getReservationHolderTel());
            txtReservationHolderTel.setDisabled(false);
            txtReservationIdclass.setValue(entity.getReservationIdclass());
            txtReservationIdclass.setDisabled(false);
            txtReservationStatus.setValue(entity.getReservationStatus());
            txtReservationStatus.setDisabled(false);
            txtIdreservation.setValue(entity.getIdreservation());
            txtIdreservation.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
   
    try{
   
        selectedReservation = (ReservationDTO) (evt.getComponent()
                                                   .getAttributes()
                                                   .get("selectedReservation"));
        
        txtIdreservation.setValue(selectedReservation.getIdreservation());
        txtIdreservation.setDisabled(false);
        
        txtReservationIdclass.setValue(selectedReservation.getReservationIdclass());
        txtReservationIdclass.setDisabled(false);
        
        txtReservationHolderCode.setValue(selectedReservation.getReservationHolderCode());
        txtReservationHolderCode.setDisabled(false);
        
        txtReservationHolderEmail.setValue(selectedReservation.getReservationHolderEmail());
        txtReservationHolderEmail.setDisabled(false);
        
        txtReservationHolderId.setValue(selectedReservation.getReservationHolderId());
        txtReservationHolderId.setDisabled(false);
        
        txtReservationHolderName.setValue(selectedReservation.getReservationHolderName());
        txtReservationHolderName.setDisabled(false);
        
        txtReservationHolderTel.setValue(selectedReservation.getReservationHolderTel());
        txtReservationHolderTel.setDisabled(false);
        
        txtReservationIdclass.setValue(selectedReservation.getReservationIdclass());
        txtReservationIdclass.setDisabled(false);
        
        txtReservationStatus.setValue(selectedReservation.getReservationStatus());
        txtReservationStatus.setDisabled(false);
        
        txtIdreservation.setValue(selectedReservation.getIdreservation());
        txtIdreservation.setDisabled(false);
        
        String classDesc = "";
        
        if(selectedReservation.getReservationIdclass() != null){
        	int idClase = 0;
        	idClase = selectedReservation.getReservationIdclass();
        	Specialclass claseEsp = businessDelegatorView.getSpecialclass(idClase);
        	classDesc = claseEsp.getClassTitle();
        }
        
        txtReservationClassDesc.setValue(classDesc);
        txtReservationClassDesc.setDisabled(false);
        
        btnSave.setDisabled(false);
        
    	}catch(Exception e){
    		log.info(e.toString());
    	}
        
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedReservation == null) && (entity == null)) {
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
            entity = new Reservation();

            Integer idreservation = FacesUtils.checkInteger(txtIdreservation);

            entity.setIdreservation(idreservation);
            entity.setReservationHolderCode(FacesUtils.checkString(
                    txtReservationHolderCode));
            entity.setReservationHolderEmail(FacesUtils.checkString(
                    txtReservationHolderEmail));
            entity.setReservationHolderId(FacesUtils.checkString(
                    txtReservationHolderId));
            entity.setReservationHolderName(FacesUtils.checkString(
                    txtReservationHolderName));
            entity.setReservationHolderTel(FacesUtils.checkString(
                    txtReservationHolderTel));
            entity.setReservationIdclass(FacesUtils.checkInteger(
                    txtReservationIdclass));
            entity.setReservationStatus(FacesUtils.checkInteger(
                    txtReservationStatus));
            businessDelegatorView.saveReservation(entity);
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
                Integer idreservation = new Integer(selectedReservation.getIdreservation());
                entity = businessDelegatorView.getReservation(idreservation);
            }

            entity.setReservationHolderCode(FacesUtils.checkString(
                    txtReservationHolderCode));
            entity.setReservationHolderEmail(FacesUtils.checkString(
                    txtReservationHolderEmail));
            entity.setReservationHolderId(FacesUtils.checkString(
                    txtReservationHolderId));
            entity.setReservationHolderName(FacesUtils.checkString(
                    txtReservationHolderName));
            entity.setReservationHolderTel(FacesUtils.checkString(
                    txtReservationHolderTel));
            entity.setReservationIdclass(FacesUtils.checkInteger(
                    txtReservationIdclass));
            entity.setReservationStatus(FacesUtils.checkInteger(
                    txtReservationStatus));
            businessDelegatorView.updateReservation(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedReservation = (ReservationDTO) (evt.getComponent()
                                                       .getAttributes()
                                                       .get("selectedReservation"));

            Integer idreservation = new Integer(selectedReservation.getIdreservation());
            entity = businessDelegatorView.getReservation(idreservation);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer idreservation = FacesUtils.checkInteger(txtIdreservation);
            entity = businessDelegatorView.getReservation(idreservation);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteReservation(entity);
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
            selectedReservation = (ReservationDTO) (evt.getComponent()
                                                       .getAttributes()
                                                       .get("selectedReservation"));

            Integer idreservation = new Integer(selectedReservation.getIdreservation());
            entity = businessDelegatorView.getReservation(idreservation);
            businessDelegatorView.deleteReservation(entity);
            data.remove(selectedReservation);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Integer idreservation,
        String reservationHolderCode, String reservationHolderEmail,
        String reservationHolderId, String reservationHolderName,
        String reservationHolderTel, Integer reservationIdclass,
        Integer reservationStatus) throws Exception {
        try {
            entity.setReservationHolderCode(FacesUtils.checkString(
                    reservationHolderCode));
            entity.setReservationHolderEmail(FacesUtils.checkString(
                    reservationHolderEmail));
            entity.setReservationHolderId(FacesUtils.checkString(
                    reservationHolderId));
            entity.setReservationHolderName(FacesUtils.checkString(
                    reservationHolderName));
            entity.setReservationHolderTel(FacesUtils.checkString(
                    reservationHolderTel));
            entity.setReservationIdclass(FacesUtils.checkInteger(
                    reservationIdclass));
            entity.setReservationStatus(FacesUtils.checkInteger(
                    reservationStatus));
            businessDelegatorView.updateReservation(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("ReservationView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtReservationHolderCode() {
        return txtReservationHolderCode;
    }

    public void setTxtReservationHolderCode(InputText txtReservationHolderCode) {
        this.txtReservationHolderCode = txtReservationHolderCode;
    }

    public InputText getTxtReservationHolderEmail() {
        return txtReservationHolderEmail;
    }

    public void setTxtReservationHolderEmail(
        InputText txtReservationHolderEmail) {
        this.txtReservationHolderEmail = txtReservationHolderEmail;
    }

    public InputText getTxtReservationHolderId() {
        return txtReservationHolderId;
    }

    public void setTxtReservationHolderId(InputText txtReservationHolderId) {
        this.txtReservationHolderId = txtReservationHolderId;
    }

    public InputText getTxtReservationHolderName() {
        return txtReservationHolderName;
    }

    public void setTxtReservationHolderName(InputText txtReservationHolderName) {
        this.txtReservationHolderName = txtReservationHolderName;
    }

    public InputText getTxtReservationHolderTel() {
        return txtReservationHolderTel;
    }

    public void setTxtReservationHolderTel(InputText txtReservationHolderTel) {
        this.txtReservationHolderTel = txtReservationHolderTel;
    }

    public InputText getTxtReservationIdclass() {
        return txtReservationIdclass;
    }

    public void setTxtReservationIdclass(InputText txtReservationIdclass) {
        this.txtReservationIdclass = txtReservationIdclass;
    }

    public InputText getTxtReservationStatus() {
        return txtReservationStatus;
    }

    public void setTxtReservationStatus(InputText txtReservationStatus) {
        this.txtReservationStatus = txtReservationStatus;
    }

    public InputText getTxtIdreservation() {
        return txtIdreservation;
    }

    public void setTxtIdreservation(InputText txtIdreservation) {
        this.txtIdreservation = txtIdreservation;
    }

    public List<ReservationDTO> getData() {
        return data;
    }

    public void setData(List<ReservationDTO> reservationDTO) {
        this.data = reservationDTO;
    }

    public ReservationDTO getSelectedReservation() {
        return selectedReservation;
    }

    public void setSelectedReservation(ReservationDTO reservation) {
        this.selectedReservation = reservation;
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

	public String getTxtCodigoReserva() {
		return txtCodigoReserva;
	}

	public void setTxtCodigoReserva(String txtCodigoReserva) {
		this.txtCodigoReserva = txtCodigoReserva;
	}

	public InputText getTxtReservationClassDesc() {
		return txtReservationClassDesc;
	}

	public void setTxtReservationClassDesc(InputText txtReservationClassDesc) {
		this.txtReservationClassDesc = txtReservationClassDesc;
	}

	public Integer getTipoBusqueda() {
		return tipoBusqueda;
	}

	public void setTipoBusqueda(Integer tipoBusqueda) {
		this.tipoBusqueda = tipoBusqueda;
	}
    
    
}
