package com.liderbs.presentation.backingBeans;

import com.liderbs.exceptions.*;

import com.liderbs.modelo.*;
import com.liderbs.modelo.dto.ScheduleDTO;

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
public class ScheduleView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(ScheduleView.class);
    private InputText txtIdday_Day;
    private InputText txtIdusers_Users;
    private InputText txtIdschedule;
    private Calendar txtEndtime;
    private Calendar txtStarttime;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<ScheduleDTO> data;
    private ScheduleDTO selectedSchedule;
    private Schedule entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public ScheduleView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            ScheduleDTO scheduleDTO = (ScheduleDTO) e.getObject();

            if (txtIdday_Day == null) {
                txtIdday_Day = new InputText();
            }

            txtIdday_Day.setValue(scheduleDTO.getIdday_Day());

            if (txtIdusers_Users == null) {
                txtIdusers_Users = new InputText();
            }

            txtIdusers_Users.setValue(scheduleDTO.getIdusers_Users());

            if (txtIdschedule == null) {
                txtIdschedule = new InputText();
            }

            txtIdschedule.setValue(scheduleDTO.getIdschedule());

            if (txtEndtime == null) {
                txtEndtime = new Calendar();
            }

            txtEndtime.setValue(scheduleDTO.getEndtime());

            if (txtStarttime == null) {
                txtStarttime = new Calendar();
            }

            txtStarttime.setValue(scheduleDTO.getStarttime());

            Integer idschedule = FacesUtils.checkInteger(txtIdschedule);
            entity = businessDelegatorView.getSchedule(idschedule);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedSchedule = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedSchedule = null;

        if (txtIdday_Day != null) {
            txtIdday_Day.setValue(null);
            txtIdday_Day.setDisabled(true);
        }

        if (txtIdusers_Users != null) {
            txtIdusers_Users.setValue(null);
            txtIdusers_Users.setDisabled(true);
        }

        if (txtEndtime != null) {
            txtEndtime.setValue(null);
            txtEndtime.setDisabled(true);
        }

        if (txtStarttime != null) {
            txtStarttime.setValue(null);
            txtStarttime.setDisabled(true);
        }

        if (txtIdschedule != null) {
            txtIdschedule.setValue(null);
            txtIdschedule.setDisabled(false);
        }

        if (btnSave != null) {
            btnSave.setDisabled(true);
        }

        if (btnDelete != null) {
            btnDelete.setDisabled(true);
        }

        return "";
    }

    public void listener_txtEndtime() {
        Date inputDate = (Date) txtEndtime.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtStarttime() {
        Date inputDate = (Date) txtStarttime.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtId() {
        try {
            Integer idschedule = FacesUtils.checkInteger(txtIdschedule);
            entity = (idschedule != null)
                ? businessDelegatorView.getSchedule(idschedule) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtIdday_Day.setDisabled(false);
            txtIdusers_Users.setDisabled(false);
            txtEndtime.setDisabled(false);
            txtStarttime.setDisabled(false);
            txtIdschedule.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtEndtime.setValue(entity.getEndtime());
            txtEndtime.setDisabled(false);
            txtStarttime.setValue(entity.getStarttime());
            txtStarttime.setDisabled(false);
            txtIdday_Day.setValue(entity.getDay().getIdday());
            txtIdday_Day.setDisabled(false);
            txtIdusers_Users.setValue(entity.getUsers().getIdusers());
            txtIdusers_Users.setDisabled(false);
            txtIdschedule.setValue(entity.getIdschedule());
            txtIdschedule.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedSchedule = (ScheduleDTO) (evt.getComponent().getAttributes()
                                             .get("selectedSchedule"));
        txtEndtime.setValue(selectedSchedule.getEndtime());
        txtEndtime.setDisabled(false);
        txtStarttime.setValue(selectedSchedule.getStarttime());
        txtStarttime.setDisabled(false);
        txtIdday_Day.setValue(selectedSchedule.getIdday_Day());
        txtIdday_Day.setDisabled(false);
        txtIdusers_Users.setValue(selectedSchedule.getIdusers_Users());
        txtIdusers_Users.setDisabled(false);
        txtIdschedule.setValue(selectedSchedule.getIdschedule());
        txtIdschedule.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedSchedule == null) && (entity == null)) {
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
            entity = new Schedule();

            Integer idschedule = FacesUtils.checkInteger(txtIdschedule);

            entity.setEndtime(FacesUtils.checkDate(txtEndtime));
            entity.setIdschedule(idschedule);
            entity.setStarttime(FacesUtils.checkDate(txtStarttime));
            entity.setDay((FacesUtils.checkInteger(txtIdday_Day) != null)
                ? businessDelegatorView.getDay(FacesUtils.checkInteger(
                        txtIdday_Day)) : null);
            entity.setUsers((FacesUtils.checkInteger(txtIdusers_Users) != null)
                ? businessDelegatorView.getUsers(FacesUtils.checkInteger(
                        txtIdusers_Users)) : null);
            businessDelegatorView.saveSchedule(entity);
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
                Integer idschedule = new Integer(selectedSchedule.getIdschedule());
                entity = businessDelegatorView.getSchedule(idschedule);
            }

            entity.setEndtime(FacesUtils.checkDate(txtEndtime));
            entity.setStarttime(FacesUtils.checkDate(txtStarttime));
            entity.setDay((FacesUtils.checkInteger(txtIdday_Day) != null)
                ? businessDelegatorView.getDay(FacesUtils.checkInteger(
                        txtIdday_Day)) : null);
            entity.setUsers((FacesUtils.checkInteger(txtIdusers_Users) != null)
                ? businessDelegatorView.getUsers(FacesUtils.checkInteger(
                        txtIdusers_Users)) : null);
            businessDelegatorView.updateSchedule(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedSchedule = (ScheduleDTO) (evt.getComponent().getAttributes()
                                                 .get("selectedSchedule"));

            Integer idschedule = new Integer(selectedSchedule.getIdschedule());
            entity = businessDelegatorView.getSchedule(idschedule);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer idschedule = FacesUtils.checkInteger(txtIdschedule);
            entity = businessDelegatorView.getSchedule(idschedule);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteSchedule(entity);
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
            selectedSchedule = (ScheduleDTO) (evt.getComponent().getAttributes()
                                                 .get("selectedSchedule"));

            Integer idschedule = new Integer(selectedSchedule.getIdschedule());
            entity = businessDelegatorView.getSchedule(idschedule);
            businessDelegatorView.deleteSchedule(entity);
            data.remove(selectedSchedule);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Date endtime, Integer idschedule,
        Date starttime, Integer idday_Day, Integer idusers_Users)
        throws Exception {
        try {
            entity.setEndtime(FacesUtils.checkDate(endtime));
            entity.setStarttime(FacesUtils.checkDate(starttime));
            businessDelegatorView.updateSchedule(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("ScheduleView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtIdday_Day() {
        return txtIdday_Day;
    }

    public void setTxtIdday_Day(InputText txtIdday_Day) {
        this.txtIdday_Day = txtIdday_Day;
    }

    public InputText getTxtIdusers_Users() {
        return txtIdusers_Users;
    }

    public void setTxtIdusers_Users(InputText txtIdusers_Users) {
        this.txtIdusers_Users = txtIdusers_Users;
    }

    public Calendar getTxtEndtime() {
        return txtEndtime;
    }

    public void setTxtEndtime(Calendar txtEndtime) {
        this.txtEndtime = txtEndtime;
    }

    public Calendar getTxtStarttime() {
        return txtStarttime;
    }

    public void setTxtStarttime(Calendar txtStarttime) {
        this.txtStarttime = txtStarttime;
    }

    public InputText getTxtIdschedule() {
        return txtIdschedule;
    }

    public void setTxtIdschedule(InputText txtIdschedule) {
        this.txtIdschedule = txtIdschedule;
    }

    public List<ScheduleDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataSchedule();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<ScheduleDTO> scheduleDTO) {
        this.data = scheduleDTO;
    }

    public ScheduleDTO getSelectedSchedule() {
        return selectedSchedule;
    }

    public void setSelectedSchedule(ScheduleDTO schedule) {
        this.selectedSchedule = schedule;
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
