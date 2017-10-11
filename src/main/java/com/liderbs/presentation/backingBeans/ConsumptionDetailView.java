package com.liderbs.presentation.backingBeans;

import com.liderbs.exceptions.*;

import com.liderbs.modelo.*;
import com.liderbs.modelo.dto.ConsumptionDetailDTO;

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
public class ConsumptionDetailView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(ConsumptionDetailView.class);
    private InputText txtCantidad;
    private InputText txtIdconsumption;
    private InputText txtIdusuarioCreo;
    private InputText txtUsuarioCreo;
    private InputText txtIdconsumptionDetail;
    private Calendar txtFecha;
    private Calendar txtHora;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<ConsumptionDetailDTO> data;
    private ConsumptionDetailDTO selectedConsumptionDetail;
    private ConsumptionDetail entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public ConsumptionDetailView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            ConsumptionDetailDTO consumptionDetailDTO = (ConsumptionDetailDTO) e.getObject();

            if (txtCantidad == null) {
                txtCantidad = new InputText();
            }

            txtCantidad.setValue(consumptionDetailDTO.getCantidad());

            if (txtIdconsumption == null) {
                txtIdconsumption = new InputText();
            }

            txtIdconsumption.setValue(consumptionDetailDTO.getIdconsumption());

            if (txtIdusuarioCreo == null) {
                txtIdusuarioCreo = new InputText();
            }

            txtIdusuarioCreo.setValue(consumptionDetailDTO.getIdusuarioCreo());

            if (txtUsuarioCreo == null) {
                txtUsuarioCreo = new InputText();
            }

            txtUsuarioCreo.setValue(consumptionDetailDTO.getUsuarioCreo());

            if (txtIdconsumptionDetail == null) {
                txtIdconsumptionDetail = new InputText();
            }

            txtIdconsumptionDetail.setValue(consumptionDetailDTO.getIdconsumptionDetail());

            if (txtFecha == null) {
                txtFecha = new Calendar();
            }

            txtFecha.setValue(consumptionDetailDTO.getFecha());

            if (txtHora == null) {
                txtHora = new Calendar();
            }

            txtHora.setValue(consumptionDetailDTO.getHora());

            Integer idconsumptionDetail = FacesUtils.checkInteger(txtIdconsumptionDetail);
            entity = businessDelegatorView.getConsumptionDetail(idconsumptionDetail);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedConsumptionDetail = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedConsumptionDetail = null;

        if (txtCantidad != null) {
            txtCantidad.setValue(null);
            txtCantidad.setDisabled(true);
        }

        if (txtIdconsumption != null) {
            txtIdconsumption.setValue(null);
            txtIdconsumption.setDisabled(true);
        }

        if (txtIdusuarioCreo != null) {
            txtIdusuarioCreo.setValue(null);
            txtIdusuarioCreo.setDisabled(true);
        }

        if (txtUsuarioCreo != null) {
            txtUsuarioCreo.setValue(null);
            txtUsuarioCreo.setDisabled(true);
        }

        if (txtFecha != null) {
            txtFecha.setValue(null);
            txtFecha.setDisabled(true);
        }

        if (txtHora != null) {
            txtHora.setValue(null);
            txtHora.setDisabled(true);
        }

        if (txtIdconsumptionDetail != null) {
            txtIdconsumptionDetail.setValue(null);
            txtIdconsumptionDetail.setDisabled(false);
        }

        if (btnSave != null) {
            btnSave.setDisabled(true);
        }

        if (btnDelete != null) {
            btnDelete.setDisabled(true);
        }

        return "";
    }

    public void listener_txtFecha() {
        Date inputDate = (Date) txtFecha.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtHora() {
        Date inputDate = (Date) txtHora.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtId() {
        try {
            Integer idconsumptionDetail = FacesUtils.checkInteger(txtIdconsumptionDetail);
            entity = (idconsumptionDetail != null)
                ? businessDelegatorView.getConsumptionDetail(idconsumptionDetail)
                : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtCantidad.setDisabled(false);
            txtIdconsumption.setDisabled(false);
            txtIdusuarioCreo.setDisabled(false);
            txtUsuarioCreo.setDisabled(false);
            txtFecha.setDisabled(false);
            txtHora.setDisabled(false);
            txtIdconsumptionDetail.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtCantidad.setValue(entity.getCantidad());
            txtCantidad.setDisabled(false);
            txtFecha.setValue(entity.getFecha());
            txtFecha.setDisabled(false);
            txtHora.setValue(entity.getHora());
            txtHora.setDisabled(false);
            txtIdconsumption.setValue(entity.getIdconsumption());
            txtIdconsumption.setDisabled(false);
            txtIdusuarioCreo.setValue(entity.getIdusuarioCreo());
            txtIdusuarioCreo.setDisabled(false);
            txtUsuarioCreo.setValue(entity.getUsuarioCreo());
            txtUsuarioCreo.setDisabled(false);
            txtIdconsumptionDetail.setValue(entity.getIdconsumptionDetail());
            txtIdconsumptionDetail.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedConsumptionDetail = (ConsumptionDetailDTO) (evt.getComponent()
                                                               .getAttributes()
                                                               .get("selectedConsumptionDetail"));
        txtCantidad.setValue(selectedConsumptionDetail.getCantidad());
        txtCantidad.setDisabled(false);
        txtFecha.setValue(selectedConsumptionDetail.getFecha());
        txtFecha.setDisabled(false);
        txtHora.setValue(selectedConsumptionDetail.getHora());
        txtHora.setDisabled(false);
        txtIdconsumption.setValue(selectedConsumptionDetail.getIdconsumption());
        txtIdconsumption.setDisabled(false);
        txtIdusuarioCreo.setValue(selectedConsumptionDetail.getIdusuarioCreo());
        txtIdusuarioCreo.setDisabled(false);
        txtUsuarioCreo.setValue(selectedConsumptionDetail.getUsuarioCreo());
        txtUsuarioCreo.setDisabled(false);
        txtIdconsumptionDetail.setValue(selectedConsumptionDetail.getIdconsumptionDetail());
        txtIdconsumptionDetail.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedConsumptionDetail == null) && (entity == null)) {
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
            entity = new ConsumptionDetail();

            Integer idconsumptionDetail = FacesUtils.checkInteger(txtIdconsumptionDetail);

            entity.setCantidad(FacesUtils.checkInteger(txtCantidad));
            entity.setFecha(FacesUtils.checkDate(txtFecha));
            entity.setHora(FacesUtils.checkDate(txtHora));
            entity.setIdconsumption(FacesUtils.checkInteger(txtIdconsumption));
            entity.setIdconsumptionDetail(idconsumptionDetail);
            entity.setIdusuarioCreo(FacesUtils.checkInteger(txtIdusuarioCreo));
            entity.setUsuarioCreo(FacesUtils.checkString(txtUsuarioCreo));
            businessDelegatorView.saveConsumptionDetail(entity);
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
                Integer idconsumptionDetail = new Integer(selectedConsumptionDetail.getIdconsumptionDetail());
                entity = businessDelegatorView.getConsumptionDetail(idconsumptionDetail);
            }

            entity.setCantidad(FacesUtils.checkInteger(txtCantidad));
            entity.setFecha(FacesUtils.checkDate(txtFecha));
            entity.setHora(FacesUtils.checkDate(txtHora));
            entity.setIdconsumption(FacesUtils.checkInteger(txtIdconsumption));
            entity.setIdusuarioCreo(FacesUtils.checkInteger(txtIdusuarioCreo));
            entity.setUsuarioCreo(FacesUtils.checkString(txtUsuarioCreo));
            businessDelegatorView.updateConsumptionDetail(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedConsumptionDetail = (ConsumptionDetailDTO) (evt.getComponent()
                                                                   .getAttributes()
                                                                   .get("selectedConsumptionDetail"));

            Integer idconsumptionDetail = new Integer(selectedConsumptionDetail.getIdconsumptionDetail());
            entity = businessDelegatorView.getConsumptionDetail(idconsumptionDetail);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer idconsumptionDetail = FacesUtils.checkInteger(txtIdconsumptionDetail);
            entity = businessDelegatorView.getConsumptionDetail(idconsumptionDetail);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteConsumptionDetail(entity);
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
            selectedConsumptionDetail = (ConsumptionDetailDTO) (evt.getComponent()
                                                                   .getAttributes()
                                                                   .get("selectedConsumptionDetail"));

            Integer idconsumptionDetail = new Integer(selectedConsumptionDetail.getIdconsumptionDetail());
            entity = businessDelegatorView.getConsumptionDetail(idconsumptionDetail);
            businessDelegatorView.deleteConsumptionDetail(entity);
            data.remove(selectedConsumptionDetail);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Integer cantidad, Date fecha, Date hora,
        Integer idconsumption, Integer idconsumptionDetail,
        Integer idusuarioCreo, String usuarioCreo) throws Exception {
        try {
            entity.setCantidad(FacesUtils.checkInteger(cantidad));
            entity.setFecha(FacesUtils.checkDate(fecha));
            entity.setHora(FacesUtils.checkDate(hora));
            entity.setIdconsumption(FacesUtils.checkInteger(idconsumption));
            entity.setIdusuarioCreo(FacesUtils.checkInteger(idusuarioCreo));
            entity.setUsuarioCreo(FacesUtils.checkString(usuarioCreo));
            businessDelegatorView.updateConsumptionDetail(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("ConsumptionDetailView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtCantidad() {
        return txtCantidad;
    }

    public void setTxtCantidad(InputText txtCantidad) {
        this.txtCantidad = txtCantidad;
    }

    public InputText getTxtIdconsumption() {
        return txtIdconsumption;
    }

    public void setTxtIdconsumption(InputText txtIdconsumption) {
        this.txtIdconsumption = txtIdconsumption;
    }

    public InputText getTxtIdusuarioCreo() {
        return txtIdusuarioCreo;
    }

    public void setTxtIdusuarioCreo(InputText txtIdusuarioCreo) {
        this.txtIdusuarioCreo = txtIdusuarioCreo;
    }

    public InputText getTxtUsuarioCreo() {
        return txtUsuarioCreo;
    }

    public void setTxtUsuarioCreo(InputText txtUsuarioCreo) {
        this.txtUsuarioCreo = txtUsuarioCreo;
    }

    public Calendar getTxtFecha() {
        return txtFecha;
    }

    public void setTxtFecha(Calendar txtFecha) {
        this.txtFecha = txtFecha;
    }

    public Calendar getTxtHora() {
        return txtHora;
    }

    public void setTxtHora(Calendar txtHora) {
        this.txtHora = txtHora;
    }

    public InputText getTxtIdconsumptionDetail() {
        return txtIdconsumptionDetail;
    }

    public void setTxtIdconsumptionDetail(InputText txtIdconsumptionDetail) {
        this.txtIdconsumptionDetail = txtIdconsumptionDetail;
    }

    public List<ConsumptionDetailDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataConsumptionDetail();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<ConsumptionDetailDTO> consumptionDetailDTO) {
        this.data = consumptionDetailDTO;
    }

    public ConsumptionDetailDTO getSelectedConsumptionDetail() {
        return selectedConsumptionDetail;
    }

    public void setSelectedConsumptionDetail(
        ConsumptionDetailDTO consumptionDetail) {
        this.selectedConsumptionDetail = consumptionDetail;
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
