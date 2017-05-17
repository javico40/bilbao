package com.liderbs.presentation.backingBeans;

import com.liderbs.exceptions.*;

import com.liderbs.modelo.*;
import com.liderbs.modelo.dto.LevelDTO;

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
public class LevelView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(LevelView.class);
    private InputText txtDescription;
    private InputText txtIdlevel;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<LevelDTO> data;
    private LevelDTO selectedLevel;
    private Level entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public LevelView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            LevelDTO levelDTO = (LevelDTO) e.getObject();

            if (txtDescription == null) {
                txtDescription = new InputText();
            }

            txtDescription.setValue(levelDTO.getDescription());

            if (txtIdlevel == null) {
                txtIdlevel = new InputText();
            }

            txtIdlevel.setValue(levelDTO.getIdlevel());

            Integer idlevel = FacesUtils.checkInteger(txtIdlevel);
            entity = businessDelegatorView.getLevel(idlevel);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedLevel = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedLevel = null;

        if (txtDescription != null) {
            txtDescription.setValue(null);
            txtDescription.setDisabled(true);
        }

        if (txtIdlevel != null) {
            txtIdlevel.setValue(null);
            txtIdlevel.setDisabled(false);
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
            Integer idlevel = FacesUtils.checkInteger(txtIdlevel);
            entity = (idlevel != null)
                ? businessDelegatorView.getLevel(idlevel) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtDescription.setDisabled(false);
            txtIdlevel.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtDescription.setValue(entity.getDescription());
            txtDescription.setDisabled(false);
            txtIdlevel.setValue(entity.getIdlevel());
            txtIdlevel.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedLevel = (LevelDTO) (evt.getComponent().getAttributes()
                                       .get("selectedLevel"));
        txtDescription.setValue(selectedLevel.getDescription());
        txtDescription.setDisabled(false);
        txtIdlevel.setValue(selectedLevel.getIdlevel());
        txtIdlevel.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedLevel == null) && (entity == null)) {
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
            entity = new Level();

            Integer idlevel = FacesUtils.checkInteger(txtIdlevel);

            entity.setDescription(FacesUtils.checkString(txtDescription));
            entity.setIdlevel(idlevel);
            businessDelegatorView.saveLevel(entity);
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
                Integer idlevel = new Integer(selectedLevel.getIdlevel());
                entity = businessDelegatorView.getLevel(idlevel);
            }

            entity.setDescription(FacesUtils.checkString(txtDescription));
            businessDelegatorView.updateLevel(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedLevel = (LevelDTO) (evt.getComponent().getAttributes()
                                           .get("selectedLevel"));

            Integer idlevel = new Integer(selectedLevel.getIdlevel());
            entity = businessDelegatorView.getLevel(idlevel);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer idlevel = FacesUtils.checkInteger(txtIdlevel);
            entity = businessDelegatorView.getLevel(idlevel);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteLevel(entity);
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
            selectedLevel = (LevelDTO) (evt.getComponent().getAttributes()
                                           .get("selectedLevel"));

            Integer idlevel = new Integer(selectedLevel.getIdlevel());
            entity = businessDelegatorView.getLevel(idlevel);
            businessDelegatorView.deleteLevel(entity);
            data.remove(selectedLevel);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String description, Integer idlevel)
        throws Exception {
        try {
            entity.setDescription(FacesUtils.checkString(description));
            businessDelegatorView.updateLevel(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("LevelView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtDescription() {
        return txtDescription;
    }

    public void setTxtDescription(InputText txtDescription) {
        this.txtDescription = txtDescription;
    }

    public InputText getTxtIdlevel() {
        return txtIdlevel;
    }

    public void setTxtIdlevel(InputText txtIdlevel) {
        this.txtIdlevel = txtIdlevel;
    }

    public List<LevelDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataLevel();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<LevelDTO> levelDTO) {
        this.data = levelDTO;
    }

    public LevelDTO getSelectedLevel() {
        return selectedLevel;
    }

    public void setSelectedLevel(LevelDTO level) {
        this.selectedLevel = level;
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
