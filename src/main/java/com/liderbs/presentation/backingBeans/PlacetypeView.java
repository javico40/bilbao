package com.liderbs.presentation.backingBeans;

import com.liderbs.exceptions.*;

import com.liderbs.modelo.*;
import com.liderbs.modelo.dto.PlacetypeDTO;

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
public class PlacetypeView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PlacetypeView.class);
    private InputText txtName;
    private InputText txtIdplacetype;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<PlacetypeDTO> data;
    private PlacetypeDTO selectedPlacetype;
    private Placetype entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public PlacetypeView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            PlacetypeDTO placetypeDTO = (PlacetypeDTO) e.getObject();

            if (txtName == null) {
                txtName = new InputText();
            }

            txtName.setValue(placetypeDTO.getName());

            if (txtIdplacetype == null) {
                txtIdplacetype = new InputText();
            }

            txtIdplacetype.setValue(placetypeDTO.getIdplacetype());

            Integer idplacetype = FacesUtils.checkInteger(txtIdplacetype);
            entity = businessDelegatorView.getPlacetype(idplacetype);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedPlacetype = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedPlacetype = null;

        if (txtName != null) {
            txtName.setValue(null);
            txtName.setDisabled(true);
        }

        if (txtIdplacetype != null) {
            txtIdplacetype.setValue(null);
            txtIdplacetype.setDisabled(false);
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
            Integer idplacetype = FacesUtils.checkInteger(txtIdplacetype);
            entity = (idplacetype != null)
                ? businessDelegatorView.getPlacetype(idplacetype) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtName.setDisabled(false);
            txtIdplacetype.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtName.setValue(entity.getName());
            txtName.setDisabled(false);
            txtIdplacetype.setValue(entity.getIdplacetype());
            txtIdplacetype.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedPlacetype = (PlacetypeDTO) (evt.getComponent().getAttributes()
                                               .get("selectedPlacetype"));
        txtName.setValue(selectedPlacetype.getName());
        txtName.setDisabled(false);
        txtIdplacetype.setValue(selectedPlacetype.getIdplacetype());
        txtIdplacetype.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedPlacetype == null) && (entity == null)) {
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
            entity = new Placetype();

            Integer idplacetype = FacesUtils.checkInteger(txtIdplacetype);

            entity.setIdplacetype(idplacetype);
            entity.setName(FacesUtils.checkString(txtName));
            //entity.setPlaces(FacesUtils.checkPlace(txtPlaces));
            businessDelegatorView.savePlacetype(entity);
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
                Integer idplacetype = new Integer(selectedPlacetype.getIdplacetype());
                entity = businessDelegatorView.getPlacetype(idplacetype);
            }

            entity.setName(FacesUtils.checkString(txtName));
            //entity.setPlaces(FacesUtils.checkPlace(txtPlaces));
            businessDelegatorView.updatePlacetype(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedPlacetype = (PlacetypeDTO) (evt.getComponent()
                                                   .getAttributes()
                                                   .get("selectedPlacetype"));

            Integer idplacetype = new Integer(selectedPlacetype.getIdplacetype());
            entity = businessDelegatorView.getPlacetype(idplacetype);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer idplacetype = FacesUtils.checkInteger(txtIdplacetype);
            entity = businessDelegatorView.getPlacetype(idplacetype);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deletePlacetype(entity);
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
            selectedPlacetype = (PlacetypeDTO) (evt.getComponent()
                                                   .getAttributes()
                                                   .get("selectedPlacetype"));

            Integer idplacetype = new Integer(selectedPlacetype.getIdplacetype());
            entity = businessDelegatorView.getPlacetype(idplacetype);
            businessDelegatorView.deletePlacetype(entity);
            data.remove(selectedPlacetype);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Integer idplacetype, String name)
        throws Exception {
        try {
            entity.setName(FacesUtils.checkString(name));
            businessDelegatorView.updatePlacetype(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("PlacetypeView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtName() {
        return txtName;
    }

    public void setTxtName(InputText txtName) {
        this.txtName = txtName;
    }

    public InputText getTxtIdplacetype() {
        return txtIdplacetype;
    }

    public void setTxtIdplacetype(InputText txtIdplacetype) {
        this.txtIdplacetype = txtIdplacetype;
    }

    public List<PlacetypeDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataPlacetype();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<PlacetypeDTO> placetypeDTO) {
        this.data = placetypeDTO;
    }

    public PlacetypeDTO getSelectedPlacetype() {
        return selectedPlacetype;
    }

    public void setSelectedPlacetype(PlacetypeDTO placetype) {
        this.selectedPlacetype = placetype;
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
