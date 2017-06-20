package com.liderbs.presentation.backingBeans;

import com.liderbs.exceptions.*;

import com.liderbs.modelo.*;
import com.liderbs.modelo.dto.TimetableDTO;

import com.liderbs.presentation.businessDelegate.*;

import com.liderbs.utilities.*;

//import org.primefaces.component.calendar.Calendar;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;

import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

import java.sql.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
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
public class TimetableView implements Serializable {
	
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(TimetableView.class);
    private InputText txtIdday_Day;
    private InputText txtIdusers_Users;
    private InputText txtIdtimetable;
    private org.primefaces.component.calendar.Calendar txtDateCreated;
    private org.primefaces.component.calendar.Calendar txtTimeEnd;
    private org.primefaces.component.calendar.Calendar txtTimeStart;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<TimetableDTO> data;
    private TimetableDTO selectedTimetable;
    private Timetable entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;
    
    private ScheduleModel eventModel;
    private boolean showEventDialog;
    private Integer timeStatus;
    private boolean restrictZone; 
    private Integer zoneRestriction;
    private Integer placeID;
    private Date horaInicio;
    private Date horaFin;
    

    public TimetableView() {
        super();
    }
    
    @PostConstruct
    public void init() {
    	
    	eventModel = new DefaultScheduleModel();
    	
        //eventModel.addEvent(new DefaultScheduleEvent("Champions League Match", previousDay8Pm(), previousDay11Pm()));
        //eventModel.addEvent(new DefaultScheduleEvent("Birthday Party", today1Pm(), today6Pm()));
        //eventModel.addEvent(new DefaultScheduleEvent("Breakfast at Tiffanys", nextDay9Am(), nextDay11Am()));
        //eventModel.addEvent(new DefaultScheduleEvent("Plant the new garden stuff", theDayAfter3Pm(), fourDaysLater3pm()));
        
    }
    
    public void agregarTiempo(){
    	this.showEventDialog = true;
    }
    
    private Calendar today() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);
 
        return calendar;
    }
     
    private Date previousDay8Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) - 1);
        t.set(Calendar.HOUR, 8);
         
        return t.getTime();
    }
     
    private Date previousDay11Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) - 1);
        t.set(Calendar.HOUR, 11);
         
        return t.getTime();
    }
     
    private Date today1Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.HOUR, 1);
         
        return t.getTime();
    }
     
    private Date theDayAfter3Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 2);     
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.HOUR, 3);
         
        return t.getTime();
    }
 
    private Date today6Pm() {
        Calendar t = (Calendar) today().clone(); 
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.HOUR, 6);
         
        return t.getTime();
    }
     
    private Date nextDay9Am() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.AM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 1);
        t.set(Calendar.HOUR, 9);
         
        return t.getTime();
    }
     
    private Date nextDay11Am() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.AM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 1);
        t.set(Calendar.HOUR, 11);
         
        return t.getTime();
    }
     
    private Date fourDaysLater3pm() {
        Calendar t = (Calendar) today().clone(); 
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 4);
        t.set(Calendar.HOUR, 3);
         
        return t.getTime();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            TimetableDTO timetableDTO = (TimetableDTO) e.getObject();

            if (txtIdday_Day == null) {
                txtIdday_Day = new InputText();
            }

            txtIdday_Day.setValue(timetableDTO.getIdday_Day());

            if (txtIdusers_Users == null) {
                txtIdusers_Users = new InputText();
            }

            txtIdusers_Users.setValue(timetableDTO.getIdusers_Users());

            if (txtIdtimetable == null) {
                txtIdtimetable = new InputText();
            }

            txtIdtimetable.setValue(timetableDTO.getIdtimetable());

            if (txtDateCreated == null) {
                txtDateCreated = new org.primefaces.component.calendar.Calendar();
            }

            txtDateCreated.setValue(timetableDTO.getDateCreated());

            if (txtTimeEnd == null) {
                txtTimeEnd = new org.primefaces.component.calendar.Calendar();
            }

            txtTimeEnd.setValue(timetableDTO.getTimeEnd());

            if (txtTimeStart == null) {
                txtTimeStart = new org.primefaces.component.calendar.Calendar();
            }

            txtTimeStart.setValue(timetableDTO.getTimeStart());

            Integer idtimetable = FacesUtils.checkInteger(txtIdtimetable);
            entity = businessDelegatorView.getTimetable(idtimetable);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedTimetable = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedTimetable = null;

        if (txtIdday_Day != null) {
            txtIdday_Day.setValue(null);
            txtIdday_Day.setDisabled(true);
        }

        if (txtIdusers_Users != null) {
            txtIdusers_Users.setValue(null);
            txtIdusers_Users.setDisabled(true);
        }

        if (txtDateCreated != null) {
            txtDateCreated.setValue(null);
            txtDateCreated.setDisabled(true);
        }

        if (txtTimeEnd != null) {
            txtTimeEnd.setValue(null);
            txtTimeEnd.setDisabled(true);
        }

        if (txtTimeStart != null) {
            txtTimeStart.setValue(null);
            txtTimeStart.setDisabled(true);
        }

        if (txtIdtimetable != null) {
            txtIdtimetable.setValue(null);
            txtIdtimetable.setDisabled(false);
        }

        if (btnSave != null) {
            btnSave.setDisabled(true);
        }

        if (btnDelete != null) {
            btnDelete.setDisabled(true);
        }

        return "";
    }

    public void listener_txtDateCreated() {
        Date inputDate = (Date) txtDateCreated.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtTimeEnd() {
        Date inputDate = (Date) txtTimeEnd.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtTimeStart() {
        Date inputDate = (Date) txtTimeStart.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtId() {
        try {
            Integer idtimetable = FacesUtils.checkInteger(txtIdtimetable);
            entity = (idtimetable != null)
                ? businessDelegatorView.getTimetable(idtimetable) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtIdday_Day.setDisabled(false);
            txtIdusers_Users.setDisabled(false);
            txtDateCreated.setDisabled(false);
            txtTimeEnd.setDisabled(false);
            txtTimeStart.setDisabled(false);
            txtIdtimetable.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtDateCreated.setValue(entity.getDateCreated());
            txtDateCreated.setDisabled(false);
            txtTimeEnd.setValue(entity.getTimeEnd());
            txtTimeEnd.setDisabled(false);
            txtTimeStart.setValue(entity.getTimeStart());
            txtTimeStart.setDisabled(false);
            txtIdday_Day.setValue(entity.getDay().getIdday());
            txtIdday_Day.setDisabled(false);
            txtIdusers_Users.setValue(entity.getUsers().getIdusers());
            txtIdusers_Users.setDisabled(false);
            txtIdtimetable.setValue(entity.getIdtimetable());
            txtIdtimetable.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedTimetable = (TimetableDTO) (evt.getComponent().getAttributes()
                                               .get("selectedTimetable"));
        txtDateCreated.setValue(selectedTimetable.getDateCreated());
        txtDateCreated.setDisabled(false);
        txtTimeEnd.setValue(selectedTimetable.getTimeEnd());
        txtTimeEnd.setDisabled(false);
        txtTimeStart.setValue(selectedTimetable.getTimeStart());
        txtTimeStart.setDisabled(false);
        txtIdday_Day.setValue(selectedTimetable.getIdday_Day());
        txtIdday_Day.setDisabled(false);
        txtIdusers_Users.setValue(selectedTimetable.getIdusers_Users());
        txtIdusers_Users.setDisabled(false);
        txtIdtimetable.setValue(selectedTimetable.getIdtimetable());
        txtIdtimetable.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedTimetable == null) && (entity == null)) {
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
            entity = new Timetable();

            Integer idtimetable = FacesUtils.checkInteger(txtIdtimetable);

            entity.setDateCreated(FacesUtils.checkDate(txtDateCreated));
            entity.setIdtimetable(idtimetable);
            entity.setTimeEnd(FacesUtils.checkDate(txtTimeEnd));
            entity.setTimeStart(FacesUtils.checkDate(txtTimeStart));
            entity.setDay((FacesUtils.checkInteger(txtIdday_Day) != null)
                ? businessDelegatorView.getDay(FacesUtils.checkInteger(
                        txtIdday_Day)) : null);
            entity.setUsers((FacesUtils.checkInteger(txtIdusers_Users) != null)
                ? businessDelegatorView.getUsers(FacesUtils.checkInteger(
                        txtIdusers_Users)) : null);
            businessDelegatorView.saveTimetable(entity);
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
                Integer idtimetable = new Integer(selectedTimetable.getIdtimetable());
                entity = businessDelegatorView.getTimetable(idtimetable);
            }

            entity.setDateCreated(FacesUtils.checkDate(txtDateCreated));
            entity.setTimeEnd(FacesUtils.checkDate(txtTimeEnd));
            entity.setTimeStart(FacesUtils.checkDate(txtTimeStart));
            entity.setDay((FacesUtils.checkInteger(txtIdday_Day) != null)
                ? businessDelegatorView.getDay(FacesUtils.checkInteger(
                        txtIdday_Day)) : null);
            entity.setUsers((FacesUtils.checkInteger(txtIdusers_Users) != null)
                ? businessDelegatorView.getUsers(FacesUtils.checkInteger(
                        txtIdusers_Users)) : null);
            businessDelegatorView.updateTimetable(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedTimetable = (TimetableDTO) (evt.getComponent()
                                                   .getAttributes()
                                                   .get("selectedTimetable"));

            Integer idtimetable = new Integer(selectedTimetable.getIdtimetable());
            entity = businessDelegatorView.getTimetable(idtimetable);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer idtimetable = FacesUtils.checkInteger(txtIdtimetable);
            entity = businessDelegatorView.getTimetable(idtimetable);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteTimetable(entity);
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
            selectedTimetable = (TimetableDTO) (evt.getComponent()
                                                   .getAttributes()
                                                   .get("selectedTimetable"));

            Integer idtimetable = new Integer(selectedTimetable.getIdtimetable());
            entity = businessDelegatorView.getTimetable(idtimetable);
            businessDelegatorView.deleteTimetable(entity);
            data.remove(selectedTimetable);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Date dateCreated, Integer idtimetable,
        Date timeEnd, Date timeStart, Integer idday_Day, Integer idusers_Users)
        throws Exception {
        try {
            entity.setDateCreated(FacesUtils.checkDate(dateCreated));
            entity.setTimeEnd(FacesUtils.checkDate(timeEnd));
            entity.setTimeStart(FacesUtils.checkDate(timeStart));
            businessDelegatorView.updateTimetable(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("TimetableView").requestRender();
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

    public org.primefaces.component.calendar.Calendar getTxtDateCreated() {
        return txtDateCreated;
    }

    public void setTxtDateCreated(org.primefaces.component.calendar.Calendar txtDateCreated) {
        this.txtDateCreated = txtDateCreated;
    }

    public org.primefaces.component.calendar.Calendar getTxtTimeEnd() {
        return txtTimeEnd;
    }

    public void setTxtTimeEnd(org.primefaces.component.calendar.Calendar txtTimeEnd) {
        this.txtTimeEnd = txtTimeEnd;
    }

    public org.primefaces.component.calendar.Calendar getTxtTimeStart() {
        return txtTimeStart;
    }

    public void setTxtTimeStart(org.primefaces.component.calendar.Calendar txtTimeStart) {
        this.txtTimeStart = txtTimeStart;
    }

    public InputText getTxtIdtimetable() {
        return txtIdtimetable;
    }

    public void setTxtIdtimetable(InputText txtIdtimetable) {
        this.txtIdtimetable = txtIdtimetable;
    }

    public List<TimetableDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataTimetable();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<TimetableDTO> timetableDTO) {
        this.data = timetableDTO;
    }

    public TimetableDTO getSelectedTimetable() {
        return selectedTimetable;
    }

    public void setSelectedTimetable(TimetableDTO timetable) {
        this.selectedTimetable = timetable;
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

	public ScheduleModel getEventModel() {
		return eventModel;
	}

	public void setEventModel(ScheduleModel eventModel) {
		this.eventModel = eventModel;
	}

	public boolean isShowEventDialog() {
		return showEventDialog;
	}

	public void setShowEventDialog(boolean showEventDialog) {
		this.showEventDialog = showEventDialog;
	}

	public Integer getTimeStatus() {
		return timeStatus;
	}

	public void setTimeStatus(Integer timeStatus) {
		this.timeStatus = timeStatus;
	}

	public boolean isRestrictZone() {
		return restrictZone;
	}

	public void setRestrictZone(boolean restrictZone) {
		this.restrictZone = restrictZone;
	}

	public Integer getZoneRestriction() {
		return zoneRestriction;
	}

	public void setZoneRestriction(Integer zoneRestriction) {
		this.zoneRestriction = zoneRestriction;
	}

	public Integer getPlaceID() {
		return placeID;
	}

	public void setPlaceID(Integer placeID) {
		this.placeID = placeID;
	}

	public Date getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Date getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(Date horaFin) {
		this.horaFin = horaFin;
	}
	
	
    
    
}
