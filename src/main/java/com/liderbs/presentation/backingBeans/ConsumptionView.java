package com.liderbs.presentation.backingBeans;

import com.liderbs.exceptions.*;

import com.liderbs.modelo.*;
import com.liderbs.modelo.dto.ConsumptionDTO;

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
public class ConsumptionView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(ConsumptionView.class);
    private InputText txtCantidad;
    private InputText txtConsumido;
    private InputText txtIdreservation;
    private InputText txtPlaceId;
    private InputText txtPlaceName;
    private InputText txtReservation;
    private InputText txtUsuariodocumento;
    private InputText txtUsuarionombre;
    private InputText txtIdconsumption;
    private Calendar txtFecha;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<ConsumptionDTO> data;
    private ConsumptionDTO selectedConsumption;
    private Consumption entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public ConsumptionView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            ConsumptionDTO consumptionDTO = (ConsumptionDTO) e.getObject();

            if (txtCantidad == null) {
                txtCantidad = new InputText();
            }

            txtCantidad.setValue(consumptionDTO.getCantidad());

            if (txtConsumido == null) {
                txtConsumido = new InputText();
            }

            txtConsumido.setValue(consumptionDTO.getConsumido());

            if (txtIdreservation == null) {
                txtIdreservation = new InputText();
            }

            txtIdreservation.setValue(consumptionDTO.getIdreservation());

            if (txtPlaceId == null) {
                txtPlaceId = new InputText();
            }

            txtPlaceId.setValue(consumptionDTO.getPlaceId());

            if (txtPlaceName == null) {
                txtPlaceName = new InputText();
            }

            txtPlaceName.setValue(consumptionDTO.getPlaceName());

            if (txtReservation == null) {
                txtReservation = new InputText();
            }

            txtReservation.setValue(consumptionDTO.getReservation());

            if (txtUsuariodocumento == null) {
                txtUsuariodocumento = new InputText();
            }

            txtUsuariodocumento.setValue(consumptionDTO.getUsuariodocumento());

            if (txtUsuarionombre == null) {
                txtUsuarionombre = new InputText();
            }

            txtUsuarionombre.setValue(consumptionDTO.getUsuarionombre());

            if (txtIdconsumption == null) {
                txtIdconsumption = new InputText();
            }

            txtIdconsumption.setValue(consumptionDTO.getIdconsumption());

            if (txtFecha == null) {
                txtFecha = new Calendar();
            }

            txtFecha.setValue(consumptionDTO.getFecha());

            Integer idconsumption = FacesUtils.checkInteger(txtIdconsumption);
            entity = businessDelegatorView.getConsumption(idconsumption);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedConsumption = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedConsumption = null;

        if (txtCantidad != null) {
            txtCantidad.setValue(null);
            txtCantidad.setDisabled(true);
        }

        if (txtConsumido != null) {
            txtConsumido.setValue(null);
            txtConsumido.setDisabled(true);
        }

        if (txtIdreservation != null) {
            txtIdreservation.setValue(null);
            txtIdreservation.setDisabled(true);
        }

        if (txtPlaceId != null) {
            txtPlaceId.setValue(null);
            txtPlaceId.setDisabled(true);
        }

        if (txtPlaceName != null) {
            txtPlaceName.setValue(null);
            txtPlaceName.setDisabled(true);
        }

        if (txtReservation != null) {
            txtReservation.setValue(null);
            txtReservation.setDisabled(true);
        }

        if (txtUsuariodocumento != null) {
            txtUsuariodocumento.setValue(null);
            txtUsuariodocumento.setDisabled(true);
        }

        if (txtUsuarionombre != null) {
            txtUsuarionombre.setValue(null);
            txtUsuarionombre.setDisabled(true);
        }

        if (txtFecha != null) {
            txtFecha.setValue(null);
            txtFecha.setDisabled(true);
        }

        if (txtIdconsumption != null) {
            txtIdconsumption.setValue(null);
            txtIdconsumption.setDisabled(false);
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

    public void listener_txtId() {
        try {
            Integer idconsumption = FacesUtils.checkInteger(txtIdconsumption);
            entity = (idconsumption != null)
                ? businessDelegatorView.getConsumption(idconsumption) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtCantidad.setDisabled(false);
            txtConsumido.setDisabled(false);
            txtIdreservation.setDisabled(false);
            txtPlaceId.setDisabled(false);
            txtPlaceName.setDisabled(false);
            txtReservation.setDisabled(false);
            txtUsuariodocumento.setDisabled(false);
            txtUsuarionombre.setDisabled(false);
            txtFecha.setDisabled(false);
            txtIdconsumption.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtCantidad.setValue(entity.getCantidad());
            txtCantidad.setDisabled(false);
            txtConsumido.setValue(entity.getConsumido());
            txtConsumido.setDisabled(false);
            txtFecha.setValue(entity.getFecha());
            txtFecha.setDisabled(false);
            txtIdreservation.setValue(entity.getIdreservation());
            txtIdreservation.setDisabled(false);
            txtPlaceId.setValue(entity.getPlaceId());
            txtPlaceId.setDisabled(false);
            txtPlaceName.setValue(entity.getPlaceName());
            txtPlaceName.setDisabled(false);
            txtReservation.setValue(entity.getReservation());
            txtReservation.setDisabled(false);
            txtUsuariodocumento.setValue(entity.getUsuariodocumento());
            txtUsuariodocumento.setDisabled(false);
            txtUsuarionombre.setValue(entity.getUsuarionombre());
            txtUsuarionombre.setDisabled(false);
            txtIdconsumption.setValue(entity.getIdconsumption());
            txtIdconsumption.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedConsumption = (ConsumptionDTO) (evt.getComponent()
                                                   .getAttributes()
                                                   .get("selectedConsumption"));
        txtCantidad.setValue(selectedConsumption.getCantidad());
        txtCantidad.setDisabled(false);
        txtConsumido.setValue(selectedConsumption.getConsumido());
        txtConsumido.setDisabled(false);
        txtFecha.setValue(selectedConsumption.getFecha());
        txtFecha.setDisabled(false);
        txtIdreservation.setValue(selectedConsumption.getIdreservation());
        txtIdreservation.setDisabled(false);
        txtPlaceId.setValue(selectedConsumption.getPlaceId());
        txtPlaceId.setDisabled(false);
        txtPlaceName.setValue(selectedConsumption.getPlaceName());
        txtPlaceName.setDisabled(false);
        txtReservation.setValue(selectedConsumption.getReservation());
        txtReservation.setDisabled(false);
        txtUsuariodocumento.setValue(selectedConsumption.getUsuariodocumento());
        txtUsuariodocumento.setDisabled(false);
        txtUsuarionombre.setValue(selectedConsumption.getUsuarionombre());
        txtUsuarionombre.setDisabled(false);
        txtIdconsumption.setValue(selectedConsumption.getIdconsumption());
        txtIdconsumption.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedConsumption == null) && (entity == null)) {
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
            entity = new Consumption();

            Integer idconsumption = FacesUtils.checkInteger(txtIdconsumption);

            entity.setCantidad(FacesUtils.checkInteger(txtCantidad));
            entity.setConsumido(FacesUtils.checkInteger(txtConsumido));
            entity.setFecha(FacesUtils.checkDate(txtFecha));
            entity.setIdconsumption(idconsumption);
            entity.setIdreservation(FacesUtils.checkInteger(txtIdreservation));
            entity.setPlaceId(FacesUtils.checkInteger(txtPlaceId));
            entity.setPlaceName(FacesUtils.checkString(txtPlaceName));
            entity.setReservation(FacesUtils.checkString(txtReservation));
            entity.setUsuariodocumento(FacesUtils.checkString(
                    txtUsuariodocumento));
            entity.setUsuarionombre(FacesUtils.checkString(txtUsuarionombre));
            businessDelegatorView.saveConsumption(entity);
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
                Integer idconsumption = new Integer(selectedConsumption.getIdconsumption());
                entity = businessDelegatorView.getConsumption(idconsumption);
            }

            entity.setCantidad(FacesUtils.checkInteger(txtCantidad));
            entity.setConsumido(FacesUtils.checkInteger(txtConsumido));
            entity.setFecha(FacesUtils.checkDate(txtFecha));
            entity.setIdreservation(FacesUtils.checkInteger(txtIdreservation));
            entity.setPlaceId(FacesUtils.checkInteger(txtPlaceId));
            entity.setPlaceName(FacesUtils.checkString(txtPlaceName));
            entity.setReservation(FacesUtils.checkString(txtReservation));
            entity.setUsuariodocumento(FacesUtils.checkString(
                    txtUsuariodocumento));
            entity.setUsuarionombre(FacesUtils.checkString(txtUsuarionombre));
            businessDelegatorView.updateConsumption(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedConsumption = (ConsumptionDTO) (evt.getComponent()
                                                       .getAttributes()
                                                       .get("selectedConsumption"));

            Integer idconsumption = new Integer(selectedConsumption.getIdconsumption());
            entity = businessDelegatorView.getConsumption(idconsumption);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer idconsumption = FacesUtils.checkInteger(txtIdconsumption);
            entity = businessDelegatorView.getConsumption(idconsumption);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteConsumption(entity);
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
            selectedConsumption = (ConsumptionDTO) (evt.getComponent()
                                                       .getAttributes()
                                                       .get("selectedConsumption"));

            Integer idconsumption = new Integer(selectedConsumption.getIdconsumption());
            entity = businessDelegatorView.getConsumption(idconsumption);
            businessDelegatorView.deleteConsumption(entity);
            data.remove(selectedConsumption);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Integer cantidad, Integer consumido,
        Date fecha, Integer idconsumption, Integer idreservation,
        Integer placeId, String placeName, String reservation,
        String usuariodocumento, String usuarionombre)
        throws Exception {
        try {
            entity.setCantidad(FacesUtils.checkInteger(cantidad));
            entity.setConsumido(FacesUtils.checkInteger(consumido));
            entity.setFecha(FacesUtils.checkDate(fecha));
            entity.setIdreservation(FacesUtils.checkInteger(idreservation));
            entity.setPlaceId(FacesUtils.checkInteger(placeId));
            entity.setPlaceName(FacesUtils.checkString(placeName));
            entity.setReservation(FacesUtils.checkString(reservation));
            entity.setUsuariodocumento(FacesUtils.checkString(usuariodocumento));
            entity.setUsuarionombre(FacesUtils.checkString(usuarionombre));
            businessDelegatorView.updateConsumption(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("ConsumptionView").requestRender();
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

    public InputText getTxtConsumido() {
        return txtConsumido;
    }

    public void setTxtConsumido(InputText txtConsumido) {
        this.txtConsumido = txtConsumido;
    }

    public InputText getTxtIdreservation() {
        return txtIdreservation;
    }

    public void setTxtIdreservation(InputText txtIdreservation) {
        this.txtIdreservation = txtIdreservation;
    }

    public InputText getTxtPlaceId() {
        return txtPlaceId;
    }

    public void setTxtPlaceId(InputText txtPlaceId) {
        this.txtPlaceId = txtPlaceId;
    }

    public InputText getTxtPlaceName() {
        return txtPlaceName;
    }

    public void setTxtPlaceName(InputText txtPlaceName) {
        this.txtPlaceName = txtPlaceName;
    }

    public InputText getTxtReservation() {
        return txtReservation;
    }

    public void setTxtReservation(InputText txtReservation) {
        this.txtReservation = txtReservation;
    }

    public InputText getTxtUsuariodocumento() {
        return txtUsuariodocumento;
    }

    public void setTxtUsuariodocumento(InputText txtUsuariodocumento) {
        this.txtUsuariodocumento = txtUsuariodocumento;
    }

    public InputText getTxtUsuarionombre() {
        return txtUsuarionombre;
    }

    public void setTxtUsuarionombre(InputText txtUsuarionombre) {
        this.txtUsuarionombre = txtUsuarionombre;
    }

    public Calendar getTxtFecha() {
        return txtFecha;
    }

    public void setTxtFecha(Calendar txtFecha) {
        this.txtFecha = txtFecha;
    }

    public InputText getTxtIdconsumption() {
        return txtIdconsumption;
    }

    public void setTxtIdconsumption(InputText txtIdconsumption) {
        this.txtIdconsumption = txtIdconsumption;
    }

    public List<ConsumptionDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataConsumption();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<ConsumptionDTO> consumptionDTO) {
        this.data = consumptionDTO;
    }

    public ConsumptionDTO getSelectedConsumption() {
        return selectedConsumption;
    }

    public void setSelectedConsumption(ConsumptionDTO consumption) {
        this.selectedConsumption = consumption;
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
