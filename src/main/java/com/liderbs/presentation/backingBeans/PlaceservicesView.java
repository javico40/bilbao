package com.liderbs.presentation.backingBeans;

import com.liderbs.exceptions.*;

import com.liderbs.modelo.*;
import com.liderbs.modelo.dto.PlaceservicesDTO;

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
public class PlaceservicesView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PlaceservicesView.class);
    private InputText txtNames;
    private InputText txtIdplaceservices;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<PlaceservicesDTO> data;
    private PlaceservicesDTO selectedPlaceservices;
    private Placeservices entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public PlaceservicesView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            PlaceservicesDTO placeservicesDTO = (PlaceservicesDTO) e.getObject();

            if (txtNames == null) {
                txtNames = new InputText();
            }

            txtNames.setValue(placeservicesDTO.getNames());

            if (txtIdplaceservices == null) {
                txtIdplaceservices = new InputText();
            }

            txtIdplaceservices.setValue(placeservicesDTO.getIdplaceservices());

            Integer idplaceservices = FacesUtils.checkInteger(txtIdplaceservices);
            entity = businessDelegatorView.getPlaceservices(idplaceservices);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedPlaceservices = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedPlaceservices = null;

        if (txtNames != null) {
            txtNames.setValue(null);
            txtNames.setDisabled(true);
        }

        if (txtIdplaceservices != null) {
            txtIdplaceservices.setValue(null);
            txtIdplaceservices.setDisabled(false);
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
            Integer idplaceservices = FacesUtils.checkInteger(txtIdplaceservices);
            entity = (idplaceservices != null)
                ? businessDelegatorView.getPlaceservices(idplaceservices) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtNames.setDisabled(false);
            txtIdplaceservices.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtNames.setValue(entity.getNames());
            txtNames.setDisabled(false);
            txtIdplaceservices.setValue(entity.getIdplaceservices());
            txtIdplaceservices.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedPlaceservices = (PlaceservicesDTO) (evt.getComponent()
                                                       .getAttributes()
                                                       .get("selectedPlaceservices"));
        txtNames.setValue(selectedPlaceservices.getNames());
        txtNames.setDisabled(false);
        txtIdplaceservices.setValue(selectedPlaceservices.getIdplaceservices());
        txtIdplaceservices.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedPlaceservices == null) && (entity == null)) {
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
            entity = new Placeservices();

            Integer idplaceservices = FacesUtils.checkInteger(txtIdplaceservices);

            entity.setIdplaceservices(idplaceservices);
            entity.setNames(FacesUtils.checkString(txtNames));
            //entity.setPlaces(FacesUtils.checkPlace(txtPlaces));
            businessDelegatorView.savePlaceservices(entity);
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
                Integer idplaceservices = new Integer(selectedPlaceservices.getIdplaceservices());
                entity = businessDelegatorView.getPlaceservices(idplaceservices);
            }

            entity.setNames(FacesUtils.checkString(txtNames));
            //entity.setPlaces(FacesUtils.checkPlace(txtPlaces));
            businessDelegatorView.updatePlaceservices(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedPlaceservices = (PlaceservicesDTO) (evt.getComponent()
                                                           .getAttributes()
                                                           .get("selectedPlaceservices"));

            Integer idplaceservices = new Integer(selectedPlaceservices.getIdplaceservices());
            entity = businessDelegatorView.getPlaceservices(idplaceservices);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer idplaceservices = FacesUtils.checkInteger(txtIdplaceservices);
            entity = businessDelegatorView.getPlaceservices(idplaceservices);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deletePlaceservices(entity);
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
            selectedPlaceservices = (PlaceservicesDTO) (evt.getComponent()
                                                           .getAttributes()
                                                           .get("selectedPlaceservices"));

            Integer idplaceservices = new Integer(selectedPlaceservices.getIdplaceservices());
            entity = businessDelegatorView.getPlaceservices(idplaceservices);
            businessDelegatorView.deletePlaceservices(entity);
            data.remove(selectedPlaceservices);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Integer idplaceservices, String names)
        throws Exception {
        try {
            entity.setNames(FacesUtils.checkString(names));
            businessDelegatorView.updatePlaceservices(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("PlaceservicesView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtNames() {
        return txtNames;
    }

    public void setTxtNames(InputText txtNames) {
        this.txtNames = txtNames;
    }

    public InputText getTxtIdplaceservices() {
        return txtIdplaceservices;
    }

    public void setTxtIdplaceservices(InputText txtIdplaceservices) {
        this.txtIdplaceservices = txtIdplaceservices;
    }

    public List<PlaceservicesDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataPlaceservices();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<PlaceservicesDTO> placeservicesDTO) {
        this.data = placeservicesDTO;
    }

    public PlaceservicesDTO getSelectedPlaceservices() {
        return selectedPlaceservices;
    }

    public void setSelectedPlaceservices(PlaceservicesDTO placeservices) {
        this.selectedPlaceservices = placeservices;
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
