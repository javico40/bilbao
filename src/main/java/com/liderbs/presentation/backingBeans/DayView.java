package com.liderbs.presentation.backingBeans;

import com.liderbs.exceptions.*;

import com.liderbs.modelo.*;
import com.liderbs.modelo.dto.DayDTO;

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
public class DayView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(DayView.class);
    private InputText txtName;
    private InputText txtIdday;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<DayDTO> data;
    private DayDTO selectedDay;
    private Day entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public DayView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            DayDTO dayDTO = (DayDTO) e.getObject();

            if (txtName == null) {
                txtName = new InputText();
            }

            txtName.setValue(dayDTO.getName());

            if (txtIdday == null) {
                txtIdday = new InputText();
            }

            txtIdday.setValue(dayDTO.getIdday());

            Integer idday = FacesUtils.checkInteger(txtIdday);
            entity = businessDelegatorView.getDay(idday);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedDay = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedDay = null;

        if (txtName != null) {
            txtName.setValue(null);
            txtName.setDisabled(true);
        }

        if (txtIdday != null) {
            txtIdday.setValue(null);
            txtIdday.setDisabled(false);
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
            Integer idday = FacesUtils.checkInteger(txtIdday);
            entity = (idday != null) ? businessDelegatorView.getDay(idday) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtName.setDisabled(false);
            txtIdday.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtName.setValue(entity.getName());
            txtName.setDisabled(false);
            txtIdday.setValue(entity.getIdday());
            txtIdday.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedDay = (DayDTO) (evt.getComponent().getAttributes()
                                   .get("selectedDay"));
        txtName.setValue(selectedDay.getName());
        txtName.setDisabled(false);
        txtIdday.setValue(selectedDay.getIdday());
        txtIdday.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedDay == null) && (entity == null)) {
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
            entity = new Day();

            Integer idday = FacesUtils.checkInteger(txtIdday);

            entity.setIdday(idday);
            entity.setName(FacesUtils.checkString(txtName));
            businessDelegatorView.saveDay(entity);
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
                Integer idday = new Integer(selectedDay.getIdday());
                entity = businessDelegatorView.getDay(idday);
            }

            entity.setName(FacesUtils.checkString(txtName));
            businessDelegatorView.updateDay(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedDay = (DayDTO) (evt.getComponent().getAttributes()
                                       .get("selectedDay"));

            Integer idday = new Integer(selectedDay.getIdday());
            entity = businessDelegatorView.getDay(idday);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer idday = FacesUtils.checkInteger(txtIdday);
            entity = businessDelegatorView.getDay(idday);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteDay(entity);
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
            selectedDay = (DayDTO) (evt.getComponent().getAttributes()
                                       .get("selectedDay"));

            Integer idday = new Integer(selectedDay.getIdday());
            entity = businessDelegatorView.getDay(idday);
            businessDelegatorView.deleteDay(entity);
            data.remove(selectedDay);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Integer idday, String name)
        throws Exception {
        try {
            entity.setName(FacesUtils.checkString(name));
            businessDelegatorView.updateDay(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("DayView").requestRender();
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

    public InputText getTxtIdday() {
        return txtIdday;
    }

    public void setTxtIdday(InputText txtIdday) {
        this.txtIdday = txtIdday;
    }

    public List<DayDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataDay();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<DayDTO> dayDTO) {
        this.data = dayDTO;
    }

    public DayDTO getSelectedDay() {
        return selectedDay;
    }

    public void setSelectedDay(DayDTO day) {
        this.selectedDay = day;
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
