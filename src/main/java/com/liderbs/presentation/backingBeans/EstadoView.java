package com.liderbs.presentation.backingBeans;

import com.liderbs.exceptions.*;

import com.liderbs.modelo.*;
import com.liderbs.modelo.dto.EstadoDTO;

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
public class EstadoView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(EstadoView.class);
    private InputText txtEstadonombre;
    private InputText txtIdpais_Pais;
    private InputText txtIdestado;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<EstadoDTO> data;
    private EstadoDTO selectedEstado;
    private Estado entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public EstadoView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            EstadoDTO estadoDTO = (EstadoDTO) e.getObject();

            if (txtEstadonombre == null) {
                txtEstadonombre = new InputText();
            }

            txtEstadonombre.setValue(estadoDTO.getEstadonombre());

            if (txtIdpais_Pais == null) {
                txtIdpais_Pais = new InputText();
            }

            txtIdpais_Pais.setValue(estadoDTO.getIdpais_Pais());

            if (txtIdestado == null) {
                txtIdestado = new InputText();
            }

            txtIdestado.setValue(estadoDTO.getIdestado());

            Integer idestado = FacesUtils.checkInteger(txtIdestado);
            entity = businessDelegatorView.getEstado(idestado);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedEstado = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedEstado = null;

        if (txtEstadonombre != null) {
            txtEstadonombre.setValue(null);
            txtEstadonombre.setDisabled(true);
        }

        if (txtIdpais_Pais != null) {
            txtIdpais_Pais.setValue(null);
            txtIdpais_Pais.setDisabled(true);
        }

        if (txtIdestado != null) {
            txtIdestado.setValue(null);
            txtIdestado.setDisabled(false);
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
            Integer idestado = FacesUtils.checkInteger(txtIdestado);
            entity = (idestado != null)
                ? businessDelegatorView.getEstado(idestado) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtEstadonombre.setDisabled(false);
            txtIdpais_Pais.setDisabled(false);
            txtIdestado.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtEstadonombre.setValue(entity.getEstadonombre());
            txtEstadonombre.setDisabled(false);
            txtIdpais_Pais.setValue(entity.getPais().getIdpais());
            txtIdpais_Pais.setDisabled(false);
            txtIdestado.setValue(entity.getIdestado());
            txtIdestado.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedEstado = (EstadoDTO) (evt.getComponent().getAttributes()
                                         .get("selectedEstado"));
        txtEstadonombre.setValue(selectedEstado.getEstadonombre());
        txtEstadonombre.setDisabled(false);
        txtIdpais_Pais.setValue(selectedEstado.getIdpais_Pais());
        txtIdpais_Pais.setDisabled(false);
        txtIdestado.setValue(selectedEstado.getIdestado());
        txtIdestado.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedEstado == null) && (entity == null)) {
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
            entity = new Estado();

            Integer idestado = FacesUtils.checkInteger(txtIdestado);

            entity.setEstadonombre(FacesUtils.checkString(txtEstadonombre));
            entity.setIdestado(idestado);
            entity.setPais((FacesUtils.checkInteger(txtIdpais_Pais) != null)
                ? businessDelegatorView.getPais(FacesUtils.checkInteger(
                        txtIdpais_Pais)) : null);
            businessDelegatorView.saveEstado(entity);
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
                Integer idestado = new Integer(selectedEstado.getIdestado());
                entity = businessDelegatorView.getEstado(idestado);
            }

            entity.setEstadonombre(FacesUtils.checkString(txtEstadonombre));
            entity.setPais((FacesUtils.checkInteger(txtIdpais_Pais) != null)
                ? businessDelegatorView.getPais(FacesUtils.checkInteger(
                        txtIdpais_Pais)) : null);
            businessDelegatorView.updateEstado(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedEstado = (EstadoDTO) (evt.getComponent().getAttributes()
                                             .get("selectedEstado"));

            Integer idestado = new Integer(selectedEstado.getIdestado());
            entity = businessDelegatorView.getEstado(idestado);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer idestado = FacesUtils.checkInteger(txtIdestado);
            entity = businessDelegatorView.getEstado(idestado);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteEstado(entity);
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
            selectedEstado = (EstadoDTO) (evt.getComponent().getAttributes()
                                             .get("selectedEstado"));

            Integer idestado = new Integer(selectedEstado.getIdestado());
            entity = businessDelegatorView.getEstado(idestado);
            businessDelegatorView.deleteEstado(entity);
            data.remove(selectedEstado);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String estadonombre, Integer idestado,
        Integer idpais_Pais) throws Exception {
        try {
            entity.setEstadonombre(FacesUtils.checkString(estadonombre));
            businessDelegatorView.updateEstado(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("EstadoView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtEstadonombre() {
        return txtEstadonombre;
    }

    public void setTxtEstadonombre(InputText txtEstadonombre) {
        this.txtEstadonombre = txtEstadonombre;
    }

    public InputText getTxtIdpais_Pais() {
        return txtIdpais_Pais;
    }

    public void setTxtIdpais_Pais(InputText txtIdpais_Pais) {
        this.txtIdpais_Pais = txtIdpais_Pais;
    }

    public InputText getTxtIdestado() {
        return txtIdestado;
    }

    public void setTxtIdestado(InputText txtIdestado) {
        this.txtIdestado = txtIdestado;
    }

    public List<EstadoDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataEstado();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<EstadoDTO> estadoDTO) {
        this.data = estadoDTO;
    }

    public EstadoDTO getSelectedEstado() {
        return selectedEstado;
    }

    public void setSelectedEstado(EstadoDTO estado) {
        this.selectedEstado = estado;
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
