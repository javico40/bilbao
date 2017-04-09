package com.liderbs.presentation.backingBeans;

import com.liderbs.exceptions.*;

import com.liderbs.modelo.*;
import com.liderbs.modelo.dto.OptionsDTO;

import com.liderbs.presentation.businessDelegate.*;

import com.liderbs.utilities.*;

import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;

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


/**
 * @author Zathura Code Generator http://code.google.com/p/zathura
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class OptionsView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(OptionsView.class);
    private InputText txtOptionsName;
    private InputText txtOptionsStatus;
    private InputText txtOptionsUrl;
    private InputText txtIdoptions;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<OptionsDTO> data;
    private OptionsDTO selectedOptions;
    private Options entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public OptionsView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            OptionsDTO optionsDTO = (OptionsDTO) e.getObject();

            if (txtOptionsName == null) {
                txtOptionsName = new InputText();
            }

            txtOptionsName.setValue(optionsDTO.getOptionsName());

            if (txtOptionsStatus == null) {
                txtOptionsStatus = new InputText();
            }

            txtOptionsStatus.setValue(optionsDTO.getOptionsStatus());

            if (txtOptionsUrl == null) {
                txtOptionsUrl = new InputText();
            }

            txtOptionsUrl.setValue(optionsDTO.getOptionsUrl());

            if (txtIdoptions == null) {
                txtIdoptions = new InputText();
            }

            txtIdoptions.setValue(optionsDTO.getIdoptions());

            Integer idoptions = FacesUtils.checkInteger(txtIdoptions);
            entity = businessDelegatorView.getOptions(idoptions);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedOptions = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedOptions = null;

        if (txtOptionsName != null) {
            txtOptionsName.setValue(null);
            txtOptionsName.setDisabled(false);
        }

        if (txtOptionsStatus != null) {
            txtOptionsStatus.setValue(null);
            txtOptionsStatus.setDisabled(false);
        }

        if (txtOptionsUrl != null) {
            txtOptionsUrl.setValue(null);
            txtOptionsUrl.setDisabled(false);
        }

        if (btnSave != null) {
            btnSave.setDisabled(false);
        }

        if (btnDelete != null) {
            btnDelete.setDisabled(true);
        }

        return "";
    }

    public void listener_txtId() {
        try {
            Integer idoptions = FacesUtils.checkInteger(txtIdoptions);
            entity = (idoptions != null)
                ? businessDelegatorView.getOptions(idoptions) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtOptionsName.setDisabled(false);
            txtOptionsStatus.setDisabled(false);
            txtOptionsUrl.setDisabled(false);
            txtIdoptions.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtOptionsName.setValue(entity.getOptionsName());
            txtOptionsName.setDisabled(false);
            txtOptionsStatus.setValue(entity.getOptionsStatus());
            txtOptionsStatus.setDisabled(false);
            txtOptionsUrl.setValue(entity.getOptionsUrl());
            txtOptionsUrl.setDisabled(false);
            txtIdoptions.setValue(entity.getIdoptions());
            txtIdoptions.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
    	
        selectedOptions = (OptionsDTO) (evt.getComponent().getAttributes()
                                           .get("selectedOptions"));
        txtOptionsName.setValue(selectedOptions.getOptionsName());
        txtOptionsName.setDisabled(false);
        txtOptionsStatus.setValue(selectedOptions.getOptionsStatus());
        txtOptionsStatus.setDisabled(false);
        txtOptionsUrl.setValue(selectedOptions.getOptionsUrl());
        txtOptionsUrl.setDisabled(false);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedOptions == null) && (entity == null)) {
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
        	
            entity = new Options();
            entity.setOptionsName(FacesUtils.checkString(txtOptionsName));
            entity.setOptionsUrl(FacesUtils.checkString(txtOptionsUrl));
            entity.setOptionsStatus(FacesUtils.checkString(txtOptionsStatus));
            
            //entity.setMenus(FacesUtils.checkMenu(txtMenus));
            //entity.setProfiles(FacesUtils.checkProfile(txtProfiles));
            
            businessDelegatorView.saveOptions(entity);
            FacesUtils.addInfoMessage("Opcion creada satisfactoriamente");
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
                Integer idoptions = new Integer(selectedOptions.getIdoptions());
                entity = businessDelegatorView.getOptions(idoptions);
            }

            entity.setOptionsName(FacesUtils.checkString(txtOptionsName));
            entity.setOptionsStatus(FacesUtils.checkString(txtOptionsStatus));
            entity.setOptionsUrl(FacesUtils.checkString(txtOptionsUrl));
            //entity.setMenus(FacesUtils.checkMenu(txtMenus));
            //entity.setProfiles(FacesUtils.checkProfile(txtProfiles));
            businessDelegatorView.updateOptions(entity);
            FacesUtils.addInfoMessage("Opcion modificada satisfactoriamente");
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedOptions = (OptionsDTO) (evt.getComponent().getAttributes()
                                               .get("selectedOptions"));

            Integer idoptions = new Integer(selectedOptions.getIdoptions());
            entity = businessDelegatorView.getOptions(idoptions);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer idoptions = FacesUtils.checkInteger(txtIdoptions);
            entity = businessDelegatorView.getOptions(idoptions);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteOptions(entity);
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
            selectedOptions = (OptionsDTO) (evt.getComponent().getAttributes()
                                               .get("selectedOptions"));

            Integer idoptions = new Integer(selectedOptions.getIdoptions());
            entity = businessDelegatorView.getOptions(idoptions);
            businessDelegatorView.deleteOptions(entity);
            data.remove(selectedOptions);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Integer idoptions, String optionsName,
        String optionsStatus, String optionsUrl) throws Exception {
        try {
            entity.setOptionsName(FacesUtils.checkString(optionsName));
            entity.setOptionsStatus(FacesUtils.checkString(optionsStatus));
            entity.setOptionsUrl(FacesUtils.checkString(optionsUrl));
            businessDelegatorView.updateOptions(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("OptionsView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtOptionsName() {
        return txtOptionsName;
    }

    public void setTxtOptionsName(InputText txtOptionsName) {
        this.txtOptionsName = txtOptionsName;
    }

    public InputText getTxtOptionsStatus() {
        return txtOptionsStatus;
    }

    public void setTxtOptionsStatus(InputText txtOptionsStatus) {
        this.txtOptionsStatus = txtOptionsStatus;
    }

    public InputText getTxtOptionsUrl() {
        return txtOptionsUrl;
    }

    public void setTxtOptionsUrl(InputText txtOptionsUrl) {
        this.txtOptionsUrl = txtOptionsUrl;
    }

    public InputText getTxtIdoptions() {
        return txtIdoptions;
    }

    public void setTxtIdoptions(InputText txtIdoptions) {
        this.txtIdoptions = txtIdoptions;
    }

    public List<OptionsDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataOptions();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<OptionsDTO> optionsDTO) {
        this.data = optionsDTO;
    }

    public OptionsDTO getSelectedOptions() {
        return selectedOptions;
    }

    public void setSelectedOptions(OptionsDTO options) {
        this.selectedOptions = options;
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
}
