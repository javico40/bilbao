package com.liderbs.presentation.backingBeans;

import com.liderbs.exceptions.*;

import com.liderbs.modelo.*;
import com.liderbs.modelo.dto.UsuariosCategoriasDTO;

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
public class UsuariosCategoriasView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(UsuariosCategoriasView.class);
    private InputText txtUsuario;
    private InputText txtIdcategory_Category;
    private InputText txtIdusuariosCategorias;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<UsuariosCategoriasDTO> data;
    private UsuariosCategoriasDTO selectedUsuariosCategorias;
    private UsuariosCategorias entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public UsuariosCategoriasView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            UsuariosCategoriasDTO usuariosCategoriasDTO = (UsuariosCategoriasDTO) e.getObject();

            if (txtUsuario == null) {
                txtUsuario = new InputText();
            }

            txtUsuario.setValue(usuariosCategoriasDTO.getUsuario());

            if (txtIdcategory_Category == null) {
                txtIdcategory_Category = new InputText();
            }

            txtIdcategory_Category.setValue(usuariosCategoriasDTO.getIdcategory_Category());

            if (txtIdusuariosCategorias == null) {
                txtIdusuariosCategorias = new InputText();
            }

            txtIdusuariosCategorias.setValue(usuariosCategoriasDTO.getIdusuariosCategorias());

            Integer idusuariosCategorias = FacesUtils.checkInteger(txtIdusuariosCategorias);
            entity = businessDelegatorView.getUsuariosCategorias(idusuariosCategorias);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedUsuariosCategorias = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedUsuariosCategorias = null;

        if (txtUsuario != null) {
            txtUsuario.setValue(null);
            txtUsuario.setDisabled(true);
        }

        if (txtIdcategory_Category != null) {
            txtIdcategory_Category.setValue(null);
            txtIdcategory_Category.setDisabled(true);
        }

        if (txtIdusuariosCategorias != null) {
            txtIdusuariosCategorias.setValue(null);
            txtIdusuariosCategorias.setDisabled(false);
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
            Integer idusuariosCategorias = FacesUtils.checkInteger(txtIdusuariosCategorias);
            entity = (idusuariosCategorias != null)
                ? businessDelegatorView.getUsuariosCategorias(idusuariosCategorias)
                : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtUsuario.setDisabled(false);
            txtIdcategory_Category.setDisabled(false);
            txtIdusuariosCategorias.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtUsuario.setValue(entity.getUsuario());
            txtUsuario.setDisabled(false);
            txtIdcategory_Category.setValue(entity.getCategory().getIdcategory());
            txtIdcategory_Category.setDisabled(false);
            txtIdusuariosCategorias.setValue(entity.getIdusuariosCategorias());
            txtIdusuariosCategorias.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedUsuariosCategorias = (UsuariosCategoriasDTO) (evt.getComponent()
                                                                 .getAttributes()
                                                                 .get("selectedUsuariosCategorias"));
        txtUsuario.setValue(selectedUsuariosCategorias.getUsuario());
        txtUsuario.setDisabled(false);
        txtIdcategory_Category.setValue(selectedUsuariosCategorias.getIdcategory_Category());
        txtIdcategory_Category.setDisabled(false);
        txtIdusuariosCategorias.setValue(selectedUsuariosCategorias.getIdusuariosCategorias());
        txtIdusuariosCategorias.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedUsuariosCategorias == null) && (entity == null)) {
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
            entity = new UsuariosCategorias();

            Integer idusuariosCategorias = FacesUtils.checkInteger(txtIdusuariosCategorias);

            entity.setIdusuariosCategorias(idusuariosCategorias);
            entity.setUsuario(FacesUtils.checkInteger(txtUsuario));
            entity.setCategory((FacesUtils.checkInteger(txtIdcategory_Category) != null)
                ? businessDelegatorView.getCategory(FacesUtils.checkInteger(
                        txtIdcategory_Category)) : null);
            businessDelegatorView.saveUsuariosCategorias(entity);
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
                Integer idusuariosCategorias = new Integer(selectedUsuariosCategorias.getIdusuariosCategorias());
                entity = businessDelegatorView.getUsuariosCategorias(idusuariosCategorias);
            }

            entity.setUsuario(FacesUtils.checkInteger(txtUsuario));
            entity.setCategory((FacesUtils.checkInteger(txtIdcategory_Category) != null)
                ? businessDelegatorView.getCategory(FacesUtils.checkInteger(
                        txtIdcategory_Category)) : null);
            businessDelegatorView.updateUsuariosCategorias(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedUsuariosCategorias = (UsuariosCategoriasDTO) (evt.getComponent()
                                                                     .getAttributes()
                                                                     .get("selectedUsuariosCategorias"));

            Integer idusuariosCategorias = new Integer(selectedUsuariosCategorias.getIdusuariosCategorias());
            entity = businessDelegatorView.getUsuariosCategorias(idusuariosCategorias);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer idusuariosCategorias = FacesUtils.checkInteger(txtIdusuariosCategorias);
            entity = businessDelegatorView.getUsuariosCategorias(idusuariosCategorias);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteUsuariosCategorias(entity);
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
            selectedUsuariosCategorias = (UsuariosCategoriasDTO) (evt.getComponent()
                                                                     .getAttributes()
                                                                     .get("selectedUsuariosCategorias"));

            Integer idusuariosCategorias = new Integer(selectedUsuariosCategorias.getIdusuariosCategorias());
            entity = businessDelegatorView.getUsuariosCategorias(idusuariosCategorias);
            businessDelegatorView.deleteUsuariosCategorias(entity);
            data.remove(selectedUsuariosCategorias);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Integer idusuariosCategorias,
        Integer usuario, Integer idcategory_Category) throws Exception {
        try {
            entity.setUsuario(FacesUtils.checkInteger(usuario));
            businessDelegatorView.updateUsuariosCategorias(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("UsuariosCategoriasView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtUsuario() {
        return txtUsuario;
    }

    public void setTxtUsuario(InputText txtUsuario) {
        this.txtUsuario = txtUsuario;
    }

    public InputText getTxtIdcategory_Category() {
        return txtIdcategory_Category;
    }

    public void setTxtIdcategory_Category(InputText txtIdcategory_Category) {
        this.txtIdcategory_Category = txtIdcategory_Category;
    }

    public InputText getTxtIdusuariosCategorias() {
        return txtIdusuariosCategorias;
    }

    public void setTxtIdusuariosCategorias(InputText txtIdusuariosCategorias) {
        this.txtIdusuariosCategorias = txtIdusuariosCategorias;
    }

    public List<UsuariosCategoriasDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataUsuariosCategorias();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<UsuariosCategoriasDTO> usuariosCategoriasDTO) {
        this.data = usuariosCategoriasDTO;
    }

    public UsuariosCategoriasDTO getSelectedUsuariosCategorias() {
        return selectedUsuariosCategorias;
    }

    public void setSelectedUsuariosCategorias(
        UsuariosCategoriasDTO usuariosCategorias) {
        this.selectedUsuariosCategorias = usuariosCategorias;
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
