package com.liderbs.presentation.backingBeans;

import com.liderbs.exceptions.*;

import com.liderbs.modelo.*;
import com.liderbs.modelo.dto.AutorizationtypeDTO;

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
public class AutorizationtypeView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(AutorizationtypeView.class);
    private InputText txtAutorizationtypeName;
    private InputText txtIdAutorizationtype;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<AutorizationtypeDTO> data;
    private AutorizationtypeDTO selectedAutorizationtype;
    private Autorizationtype entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public AutorizationtypeView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            AutorizationtypeDTO autorizationtypeDTO = (AutorizationtypeDTO) e.getObject();

            if (txtAutorizationtypeName == null) {
                txtAutorizationtypeName = new InputText();
            }

            txtAutorizationtypeName.setValue(autorizationtypeDTO.getAutorizationtypeName());

            if (txtIdAutorizationtype == null) {
                txtIdAutorizationtype = new InputText();
            }

            txtIdAutorizationtype.setValue(autorizationtypeDTO.getIdAutorizationtype());

            Integer idAutorizationtype = FacesUtils.checkInteger(txtIdAutorizationtype);
            entity = businessDelegatorView.getAutorizationtype(idAutorizationtype);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedAutorizationtype = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedAutorizationtype = null;

        if (txtAutorizationtypeName != null) {
            txtAutorizationtypeName.setValue(null);
            txtAutorizationtypeName.setDisabled(true);
        }

        if (txtIdAutorizationtype != null) {
            txtIdAutorizationtype.setValue(null);
            txtIdAutorizationtype.setDisabled(false);
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
            Integer idAutorizationtype = FacesUtils.checkInteger(txtIdAutorizationtype);
            entity = (idAutorizationtype != null)
                ? businessDelegatorView.getAutorizationtype(idAutorizationtype)
                : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtAutorizationtypeName.setDisabled(false);
            txtIdAutorizationtype.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtAutorizationtypeName.setValue(entity.getAutorizationtypeName());
            txtAutorizationtypeName.setDisabled(false);
            txtIdAutorizationtype.setValue(entity.getIdAutorizationtype());
            txtIdAutorizationtype.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedAutorizationtype = (AutorizationtypeDTO) (evt.getComponent()
                                                             .getAttributes()
                                                             .get("selectedAutorizationtype"));
        txtAutorizationtypeName.setValue(selectedAutorizationtype.getAutorizationtypeName());
        txtAutorizationtypeName.setDisabled(false);
        txtIdAutorizationtype.setValue(selectedAutorizationtype.getIdAutorizationtype());
        txtIdAutorizationtype.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedAutorizationtype == null) && (entity == null)) {
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
            entity = new Autorizationtype();

            Integer idAutorizationtype = FacesUtils.checkInteger(txtIdAutorizationtype);

            entity.setAutorizationtypeName(FacesUtils.checkString(
                    txtAutorizationtypeName));
            entity.setIdAutorizationtype(idAutorizationtype);
            businessDelegatorView.saveAutorizationtype(entity);
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
                Integer idAutorizationtype = new Integer(selectedAutorizationtype.getIdAutorizationtype());
                entity = businessDelegatorView.getAutorizationtype(idAutorizationtype);
            }

            entity.setAutorizationtypeName(FacesUtils.checkString(
                    txtAutorizationtypeName));
            businessDelegatorView.updateAutorizationtype(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedAutorizationtype = (AutorizationtypeDTO) (evt.getComponent()
                                                                 .getAttributes()
                                                                 .get("selectedAutorizationtype"));

            Integer idAutorizationtype = new Integer(selectedAutorizationtype.getIdAutorizationtype());
            entity = businessDelegatorView.getAutorizationtype(idAutorizationtype);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer idAutorizationtype = FacesUtils.checkInteger(txtIdAutorizationtype);
            entity = businessDelegatorView.getAutorizationtype(idAutorizationtype);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteAutorizationtype(entity);
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
            selectedAutorizationtype = (AutorizationtypeDTO) (evt.getComponent()
                                                                 .getAttributes()
                                                                 .get("selectedAutorizationtype"));

            Integer idAutorizationtype = new Integer(selectedAutorizationtype.getIdAutorizationtype());
            entity = businessDelegatorView.getAutorizationtype(idAutorizationtype);
            businessDelegatorView.deleteAutorizationtype(entity);
            data.remove(selectedAutorizationtype);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String autorizationtypeName,
        Integer idAutorizationtype) throws Exception {
        try {
            entity.setAutorizationtypeName(FacesUtils.checkString(
                    autorizationtypeName));
            businessDelegatorView.updateAutorizationtype(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("AutorizationtypeView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtAutorizationtypeName() {
        return txtAutorizationtypeName;
    }

    public void setTxtAutorizationtypeName(InputText txtAutorizationtypeName) {
        this.txtAutorizationtypeName = txtAutorizationtypeName;
    }

    public InputText getTxtIdAutorizationtype() {
        return txtIdAutorizationtype;
    }

    public void setTxtIdAutorizationtype(InputText txtIdAutorizationtype) {
        this.txtIdAutorizationtype = txtIdAutorizationtype;
    }

    public List<AutorizationtypeDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataAutorizationtype();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<AutorizationtypeDTO> autorizationtypeDTO) {
        this.data = autorizationtypeDTO;
    }

    public AutorizationtypeDTO getSelectedAutorizationtype() {
        return selectedAutorizationtype;
    }

    public void setSelectedAutorizationtype(
        AutorizationtypeDTO autorizationtype) {
        this.selectedAutorizationtype = autorizationtype;
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
