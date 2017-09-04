package com.liderbs.presentation.backingBeans;

import com.liderbs.exceptions.*;

import com.liderbs.modelo.*;
import com.liderbs.modelo.dto.CitiesDTO;

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
public class CitiesView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(CitiesView.class);
    private InputText txtCitiesName;
    private InputText txtIdcities;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<CitiesDTO> data;
    private CitiesDTO selectedCities;
    private Cities entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public CitiesView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            CitiesDTO citiesDTO = (CitiesDTO) e.getObject();

            if (txtCitiesName == null) {
                txtCitiesName = new InputText();
            }

            txtCitiesName.setValue(citiesDTO.getCitiesName());

            if (txtIdcities == null) {
                txtIdcities = new InputText();
            }

            txtIdcities.setValue(citiesDTO.getIdcities());

            Integer idcities = FacesUtils.checkInteger(txtIdcities);
            entity = businessDelegatorView.getCities(idcities);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedCities = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedCities = null;

        if (txtCitiesName != null) {
            txtCitiesName.setValue(null);
            txtCitiesName.setDisabled(true);
        }

        if (txtIdcities != null) {
            txtIdcities.setValue(null);
            txtIdcities.setDisabled(false);
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
            Integer idcities = FacesUtils.checkInteger(txtIdcities);
            entity = (idcities != null)
                ? businessDelegatorView.getCities(idcities) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtCitiesName.setDisabled(false);
            txtIdcities.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtCitiesName.setValue(entity.getCitiesName());
            txtCitiesName.setDisabled(false);
            txtIdcities.setValue(entity.getIdcities());
            txtIdcities.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedCities = (CitiesDTO) (evt.getComponent().getAttributes()
                                         .get("selectedCities"));
        txtCitiesName.setValue(selectedCities.getCitiesName());
        txtCitiesName.setDisabled(false);
        txtIdcities.setValue(selectedCities.getIdcities());
        txtIdcities.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedCities == null) && (entity == null)) {
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
            entity = new Cities();

            Integer idcities = FacesUtils.checkInteger(txtIdcities);

            entity.setCitiesName(FacesUtils.checkString(txtCitiesName));
            entity.setIdcities(idcities);
            businessDelegatorView.saveCities(entity);
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
                Integer idcities = new Integer(selectedCities.getIdcities());
                entity = businessDelegatorView.getCities(idcities);
            }

            entity.setCitiesName(FacesUtils.checkString(txtCitiesName));
            businessDelegatorView.updateCities(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedCities = (CitiesDTO) (evt.getComponent().getAttributes()
                                             .get("selectedCities"));

            Integer idcities = new Integer(selectedCities.getIdcities());
            entity = businessDelegatorView.getCities(idcities);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer idcities = FacesUtils.checkInteger(txtIdcities);
            entity = businessDelegatorView.getCities(idcities);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteCities(entity);
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
            selectedCities = (CitiesDTO) (evt.getComponent().getAttributes()
                                             .get("selectedCities"));

            Integer idcities = new Integer(selectedCities.getIdcities());
            entity = businessDelegatorView.getCities(idcities);
            businessDelegatorView.deleteCities(entity);
            data.remove(selectedCities);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String citiesName, Integer idcities)
        throws Exception {
        try {
            entity.setCitiesName(FacesUtils.checkString(citiesName));
            businessDelegatorView.updateCities(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("CitiesView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtCitiesName() {
        return txtCitiesName;
    }

    public void setTxtCitiesName(InputText txtCitiesName) {
        this.txtCitiesName = txtCitiesName;
    }

    public InputText getTxtIdcities() {
        return txtIdcities;
    }

    public void setTxtIdcities(InputText txtIdcities) {
        this.txtIdcities = txtIdcities;
    }

    public List<CitiesDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataCities();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<CitiesDTO> citiesDTO) {
        this.data = citiesDTO;
    }

    public CitiesDTO getSelectedCities() {
        return selectedCities;
    }

    public void setSelectedCities(CitiesDTO cities) {
        this.selectedCities = cities;
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
