package com.liderbs.presentation.backingBeans;

import com.liderbs.exceptions.*;

import com.liderbs.modelo.*;
import com.liderbs.modelo.dto.PaisDTO;
import com.liderbs.modelo.dto.PlaceDTO;

import com.liderbs.presentation.businessDelegate.*;

import com.liderbs.utilities.*;

import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.event.RowEditEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

import java.sql.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

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
    private PlaceDTO selectedPlace;
    private Place entity;
    private SelectOneMenu selectPais;
    private List<SelectItem> listPaises;
    private boolean lockDepto = true;
    private SelectOneMenu selectDepto;
    private List<SelectItem> listDeptos;
    private InputText txtCiudad;
    private InputText txtAssocAct;
    private InputText txtAssocActOwner;
    private Integer assocAct;
    
    
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public PlaceView() {
        super();
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
    				this.listDeptos.add(new SelectItem(estado.getId(), estado.getEstadonombre()));
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
        txtPlaceCreated.setValue(selectedPlace.getPlaceCreated());
        txtPlaceCreated.setDisabled(false);
        txtPlaceIsVzone.setValue(selectedPlace.getPlaceIsVzone());
        txtPlaceIsVzone.setDisabled(false);
        txtPlaceName.setValue(selectedPlace.getPlaceName());
        txtPlaceName.setDisabled(false);
        txtPlaceOwner.setValue(selectedPlace.getPlaceOwner());
        txtPlaceOwner.setDisabled(false);
        txtPlacePhone.setValue(selectedPlace.getPlacePhone());
        txtPlacePhone.setDisabled(false);
        txtPlacePhoneAlt.setValue(selectedPlace.getPlacePhoneAlt());
        txtPlacePhoneAlt.setDisabled(false);
        txtPlaceStatus.setValue(selectedPlace.getPlaceStatus());
        txtPlaceStatus.setDisabled(false);
        txtIdPlace.setValue(selectedPlace.getIdPlace());
        txtIdPlace.setDisabled(true);
        btnSave.setDisabled(false);
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
    
    public void validarDepto(){
    	
    }

    public String action_create() {
        try {
        	
            entity = new Place();

            Date today = new Date();
            
            entity.setAccountID(assocAct);
            entity.setFiscalID(FacesUtils.checkString(txtPlaceNit));
            entity.setPlaceName(FacesUtils.checkString(txtPlaceName));
            entity.setPlaceAddress(FacesUtils.checkString(txtPlaceAddress));
            entity.setCountry(FacesUtils.checkInteger(selectPais));
            //entity.setProvince(FacesUtils.checkInteger(selectDepto));
            entity.setProvince(1722);
            entity.setCity(FacesUtils.checkString(txtCiudad));
            entity.setPlaceIsVzone(0);
            entity.setPlacePhone(FacesUtils.checkString(txtPlacePhone));
            entity.setPlacePhoneAlt(FacesUtils.checkString(txtPlacePhoneAlt));
            entity.setPlaceOwner(assocAct);
            entity.setPlaceCreated(today);
            entity.setPlaceStatus(2);
            businessDelegatorView.savePlace(entity);
            
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

            entity.setPlaceAddress(FacesUtils.checkString(txtPlaceAddress));
            entity.setPlaceCreated(FacesUtils.checkDate(txtPlaceCreated));
            entity.setPlaceIsVzone(FacesUtils.checkInteger(txtPlaceIsVzone));
            entity.setPlaceName(FacesUtils.checkString(txtPlaceName));
            entity.setPlaceOwner(FacesUtils.checkInteger(txtPlaceOwner));
            entity.setPlacePhone(FacesUtils.checkString(txtPlacePhone));
            entity.setPlacePhoneAlt(FacesUtils.checkString(txtPlacePhoneAlt));
            entity.setPlaceStatus(FacesUtils.checkInteger(txtPlaceStatus));
            businessDelegatorView.updatePlace(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
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

	public SelectOneMenu getSelectDepto() {
		return selectDepto;
	}

	public void setSelectDepto(SelectOneMenu selectDepto) {
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
    
    
}
