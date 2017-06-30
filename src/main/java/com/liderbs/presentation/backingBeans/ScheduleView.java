package com.liderbs.presentation.backingBeans;

import com.liderbs.exceptions.*;

import com.liderbs.modelo.*;
import com.liderbs.modelo.dto.AutorizationDTO;
import com.liderbs.modelo.dto.ScheduleDTO;

import com.liderbs.presentation.businessDelegate.*;

import com.liderbs.utilities.*;
import com.mysql.fabric.xmlrpc.base.Array;

import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonebutton.SelectOneButton;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.Serializable;

import java.sql.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
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
import javax.faces.model.SelectItem;


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
    private InputText txtMaxUsers;
    private InputText txtIdcategory_Category;
    private InputText txtIdday_Day;
    private InputText txtIdPlace_Place;
    private InputText txtIdtrainer_Trainer;
    private InputText txtIdusers_Users;
    private InputText txtIdschedule;
    private Calendar txtClassDate;
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
    
    //Clases
    
    private Users usuarioapp;
    private ScheduleModel eventModel;
    private boolean showEventDialog;
    private Date horaInicio;
    private Date horaFin;
    private Integer maxUsers;
    private Integer minUsers;
    private Integer day;
    private Integer categoryID;
    private List<SelectItem> listCategory;
    private Integer trainer;
    private List<SelectItem> listTrainer;
    private SelectOneMenu selPlace;
    private List<SelectItem> listPlace; 
    private ScheduleEvent event = new DefaultScheduleEvent();
    private Integer idAccount = 0;
    
    @PostConstruct
    public void init() {
    	
    	Users user = getUsuarioapp();
		Set<Account> list = user.getAccounts();
		
		 for (Iterator<Account> it = list.iterator(); it.hasNext();) {
 		 	Account act = it.next();
 	        
 		 	//Si tiene cuenta activa
 		 	if(act.getAccountStatus() == 1){
 		 		if(act.getAccountDefault() == 1){
 		 			idAccount = act.getIdAccount();
 		 		}
 		 	}
 	    }//end for 
    	
    	populateSchedule();
    }

    public ScheduleView() {
        super();
    }
    
    public void populateSchedule(){
    	try{
    		
    		Users user = getUsuarioapp();
	    	eventModel = new DefaultScheduleModel();
    		
    	}catch(Exception e){
       		log.info(e.toString());
    	}
    }
    
    public void agregarClase(){
    	
    	if(day == null){
    		FacesUtils.addErrorMessage("Seleccione un dia de la Semana");
    	}else if(categoryID == null){
    		FacesUtils.addErrorMessage("Seleccione una Categoria");
    	}else if(trainer == null){
    		FacesUtils.addErrorMessage("Seleccione un Profesor");
    	}else if (horaInicio == null){
    		FacesUtils.addErrorMessage("Seleccione una hora de inicio");
    	}else if (horaFin == null){
    		FacesUtils.addErrorMessage("Seleccione una hora de fin");
    	}else{
    		
    		  try{		    		
    		    		int isInvalid = 0;
    		    		Calendar actual = Calendar.getInstance();
    		    		int hoursDoneStart = 0;
    		    		int minutesDoneStart = 0;
    		    		int hoursDoneEnd = 0;
    		    		int minutesDoneEnd = 0;
    		    		int hoursNewStart = 0;
    		    		int minutesNewStart = 0;
    		    		int hoursNewEnd = 0;
    		    		int minutesNewEnd = 0;
    		    		Date dateStart = new Date();
    			        Date dateEnd = new Date();
    			        String invalidAnswer = "";
    		    			
    		    		//Verificar restricciones en tiempo
    		    		
    		    		List<Timetable> listTime = businessDelegatorView.findByCriteriaInTimetable(new Object[]{"day.idday",false, day, "="},
    		    																				   null,
    		    																				   null);
    		    		
    		    		if(listTime.size() > 0){
    		    			
    		    			for(Timetable time: listTime){
    		    				
    		    				//Get current time events
    		    				dateStart = time.getTimeStart();
    		    	            dateEnd =  time.getTimeEnd();
    		    	            
    		    	            actual.setTime(dateStart);  
    		    	            
    		    	            hoursDoneStart = actual.get(Calendar.HOUR_OF_DAY);
    		     	            minutesDoneStart = actual.get(Calendar.MINUTE);
    		     	            
    		     	            actual.setTime(dateEnd);
    		     	            
    		     	            hoursDoneEnd = actual.get(Calendar.HOUR_OF_DAY);
    		    	            minutesDoneEnd = actual.get(Calendar.MINUTE);
    		    			
    		     	            //Get new event data
    		    	            
    		    	            actual.setTime(horaInicio);  
    		    	            
    		    	            hoursNewStart = actual.get(Calendar.HOUR_OF_DAY);
    		     	            minutesNewStart = actual.get(Calendar.MINUTE);
    		     	            
    		     	            actual.setTime(horaFin);
    		    	            
    		    	            hoursNewEnd = actual.get(Calendar.HOUR_OF_DAY);
    		    	            minutesNewEnd = actual.get(Calendar.MINUTE);
    		    	            
    		    	            //Validate the rules
    		    	            
    		    	            //Hora de inicio del evento entre horas de otro evento
    		    	            if((hoursNewStart > hoursDoneStart) && (hoursNewStart < hoursDoneEnd)){
    		    	            	isInvalid = 1;
    		    	            	invalidAnswer = "Ya existe otro evento en la hora que intenta ingresar";
    		    	            	break;
    		    	            //Hora de fin del evento entre horas de otro evento
    		    	            }else if((hoursNewEnd > hoursDoneStart) && (hoursNewEnd < hoursDoneEnd)){
    		    	            	isInvalid = 1;
    		    	            	invalidAnswer = "Ya existe otro evento en la hora que intenta ingresar";
    		    	            	break;
    		    	            }

    		    			}

    		    		}else{
    		    			isInvalid = 0;
    		    		}
    		    		
    		    		
    		    		if(isInvalid == 0){
    		    			
    		    			Date today = new Date();
    		            	Day selDay = businessDelegatorView.getDay(day);
    		            	
    		            	Timetable time = new Timetable();
    		            	time.setDateCreated(today);
    		            	time.setDay(selDay);
    		            	time.setUsers(usuarioapp);
    		            	time.setTimeStart(horaInicio);
    		            	time.setTimeEnd(horaFin);
    		            	time.setSector(zoneRestriction);
    		            	time.setPlace(placeID);
    		            	businessDelegatorView.saveTimetable(time);
    		            	FacesUtils.addInfoMessage("Tiempo agregado satisfactoriamente");
    		            	eventModel.clear();
    		            	populateSchedule();
    		            	setShowEventDialog(false);
    		    		}else{
    		    			FacesUtils.addErrorMessage(invalidAnswer);
    		    		}
    			
    		    	
    		    }catch(Exception e){
    		    	log.info(e.toString());
    		    }
    	}//end if-else
    }
    
    public Users getUsuarioapp() {
		if(usuarioapp==null) {				
			  usuarioapp = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			  //FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("rolsaldosefa_session", usuarioapp.get);				
		}
		return usuarioapp;
	}
    
    
    public void onEventSelect(SelectEvent selectEvent) {
    	event = (ScheduleEvent) selectEvent.getObject();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            ScheduleDTO scheduleDTO = (ScheduleDTO) e.getObject();

            if (txtMaxUsers == null) {
                txtMaxUsers = new InputText();
            }

            txtMaxUsers.setValue(scheduleDTO.getMaxUsers());

            if (txtIdcategory_Category == null) {
                txtIdcategory_Category = new InputText();
            }

            txtIdcategory_Category.setValue(scheduleDTO.getIdcategory_Category());

            if (txtIdday_Day == null) {
                txtIdday_Day = new InputText();
            }

            txtIdday_Day.setValue(scheduleDTO.getIdday_Day());

            if (txtIdPlace_Place == null) {
                txtIdPlace_Place = new InputText();
            }

            txtIdPlace_Place.setValue(scheduleDTO.getIdPlace_Place());

            if (txtIdtrainer_Trainer == null) {
                txtIdtrainer_Trainer = new InputText();
            }

            txtIdtrainer_Trainer.setValue(scheduleDTO.getIdtrainer_Trainer());

            if (txtIdusers_Users == null) {
                txtIdusers_Users = new InputText();
            }

            txtIdusers_Users.setValue(scheduleDTO.getIdusers_Users());

            if (txtIdschedule == null) {
                txtIdschedule = new InputText();
            }

            txtIdschedule.setValue(scheduleDTO.getIdschedule());

            if (txtClassDate == null) {
                txtClassDate = new Calendar();
            }

            txtClassDate.setValue(scheduleDTO.getClassDate());

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

        if (txtMaxUsers != null) {
            txtMaxUsers.setValue(null);
            txtMaxUsers.setDisabled(true);
        }

        if (txtIdcategory_Category != null) {
            txtIdcategory_Category.setValue(null);
            txtIdcategory_Category.setDisabled(true);
        }

        if (txtIdday_Day != null) {
            txtIdday_Day.setValue(null);
            txtIdday_Day.setDisabled(true);
        }

        if (txtIdPlace_Place != null) {
            txtIdPlace_Place.setValue(null);
            txtIdPlace_Place.setDisabled(true);
        }

        if (txtIdtrainer_Trainer != null) {
            txtIdtrainer_Trainer.setValue(null);
            txtIdtrainer_Trainer.setDisabled(true);
        }

        if (txtIdusers_Users != null) {
            txtIdusers_Users.setValue(null);
            txtIdusers_Users.setDisabled(true);
        }

        if (txtClassDate != null) {
            txtClassDate.setValue(null);
            txtClassDate.setDisabled(true);
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

    public void listener_txtClassDate() {
        Date inputDate = (Date) txtClassDate.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
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
            txtMaxUsers.setDisabled(false);
            txtIdcategory_Category.setDisabled(false);
            txtIdday_Day.setDisabled(false);
            txtIdPlace_Place.setDisabled(false);
            txtIdtrainer_Trainer.setDisabled(false);
            txtIdusers_Users.setDisabled(false);
            txtClassDate.setDisabled(false);
            txtEndtime.setDisabled(false);
            txtStarttime.setDisabled(false);
            txtIdschedule.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtClassDate.setValue(entity.getClassDate());
            txtClassDate.setDisabled(false);
            txtEndtime.setValue(entity.getEndtime());
            txtEndtime.setDisabled(false);
            txtMaxUsers.setValue(entity.getMaxUsers());
            txtMaxUsers.setDisabled(false);
            txtStarttime.setValue(entity.getStarttime());
            txtStarttime.setDisabled(false);
            txtIdcategory_Category.setValue(entity.getCategory().getIdcategory());
            txtIdcategory_Category.setDisabled(false);
            txtIdday_Day.setValue(entity.getDay().getIdday());
            txtIdday_Day.setDisabled(false);
            txtIdPlace_Place.setValue(entity.getPlace().getIdPlace());
            txtIdPlace_Place.setDisabled(false);
            txtIdtrainer_Trainer.setValue(entity.getTrainer().getIdtrainer());
            txtIdtrainer_Trainer.setDisabled(false);
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
        txtClassDate.setValue(selectedSchedule.getClassDate());
        txtClassDate.setDisabled(false);
        txtEndtime.setValue(selectedSchedule.getEndtime());
        txtEndtime.setDisabled(false);
        txtMaxUsers.setValue(selectedSchedule.getMaxUsers());
        txtMaxUsers.setDisabled(false);
        txtStarttime.setValue(selectedSchedule.getStarttime());
        txtStarttime.setDisabled(false);
        txtIdcategory_Category.setValue(selectedSchedule.getIdcategory_Category());
        txtIdcategory_Category.setDisabled(false);
        txtIdday_Day.setValue(selectedSchedule.getIdday_Day());
        txtIdday_Day.setDisabled(false);
        txtIdPlace_Place.setValue(selectedSchedule.getIdPlace_Place());
        txtIdPlace_Place.setDisabled(false);
        txtIdtrainer_Trainer.setValue(selectedSchedule.getIdtrainer_Trainer());
        txtIdtrainer_Trainer.setDisabled(false);
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

            entity.setClassDate(FacesUtils.checkDate(txtClassDate));
            entity.setEndtime(FacesUtils.checkDate(txtEndtime));
            entity.setIdschedule(idschedule);
            entity.setMaxUsers(FacesUtils.checkInteger(txtMaxUsers));
            entity.setStarttime(FacesUtils.checkDate(txtStarttime));
            entity.setCategory((FacesUtils.checkInteger(txtIdcategory_Category) != null)
                ? businessDelegatorView.getCategory(FacesUtils.checkInteger(
                        txtIdcategory_Category)) : null);
            entity.setDay((FacesUtils.checkInteger(txtIdday_Day) != null)
                ? businessDelegatorView.getDay(FacesUtils.checkInteger(
                        txtIdday_Day)) : null);
            entity.setPlace((FacesUtils.checkInteger(txtIdPlace_Place) != null)
                ? businessDelegatorView.getPlace(FacesUtils.checkInteger(
                        txtIdPlace_Place)) : null);
            entity.setTrainer((FacesUtils.checkInteger(txtIdtrainer_Trainer) != null)
                ? businessDelegatorView.getTrainer(FacesUtils.checkInteger(
                        txtIdtrainer_Trainer)) : null);
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

            entity.setClassDate(FacesUtils.checkDate(txtClassDate));
            entity.setEndtime(FacesUtils.checkDate(txtEndtime));
            entity.setMaxUsers(FacesUtils.checkInteger(txtMaxUsers));
            entity.setStarttime(FacesUtils.checkDate(txtStarttime));
            entity.setCategory((FacesUtils.checkInteger(txtIdcategory_Category) != null)
                ? businessDelegatorView.getCategory(FacesUtils.checkInteger(
                        txtIdcategory_Category)) : null);
            entity.setDay((FacesUtils.checkInteger(txtIdday_Day) != null)
                ? businessDelegatorView.getDay(FacesUtils.checkInteger(
                        txtIdday_Day)) : null);
            entity.setPlace((FacesUtils.checkInteger(txtIdPlace_Place) != null)
                ? businessDelegatorView.getPlace(FacesUtils.checkInteger(
                        txtIdPlace_Place)) : null);
            entity.setTrainer((FacesUtils.checkInteger(txtIdtrainer_Trainer) != null)
                ? businessDelegatorView.getTrainer(FacesUtils.checkInteger(
                        txtIdtrainer_Trainer)) : null);
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

    public String action_modifyWitDTO(Date classDate, Date endtime,
        Integer idschedule, Integer maxUsers, Date starttime,
        Integer idcategory_Category, Integer idday_Day, Integer idPlace_Place,
        Integer idtrainer_Trainer, Integer idusers_Users)
        throws Exception {
        try {
            entity.setClassDate(FacesUtils.checkDate(classDate));
            entity.setEndtime(FacesUtils.checkDate(endtime));
            entity.setMaxUsers(FacesUtils.checkInteger(maxUsers));
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

    public InputText getTxtMaxUsers() {
        return txtMaxUsers;
    }

    public void setTxtMaxUsers(InputText txtMaxUsers) {
        this.txtMaxUsers = txtMaxUsers;
    }

    public InputText getTxtIdcategory_Category() {
        return txtIdcategory_Category;
    }

    public void setTxtIdcategory_Category(InputText txtIdcategory_Category) {
        this.txtIdcategory_Category = txtIdcategory_Category;
    }

    public InputText getTxtIdday_Day() {
        return txtIdday_Day;
    }

    public void setTxtIdday_Day(InputText txtIdday_Day) {
        this.txtIdday_Day = txtIdday_Day;
    }

    public InputText getTxtIdPlace_Place() {
        return txtIdPlace_Place;
    }

    public void setTxtIdPlace_Place(InputText txtIdPlace_Place) {
        this.txtIdPlace_Place = txtIdPlace_Place;
    }

    public InputText getTxtIdtrainer_Trainer() {
        return txtIdtrainer_Trainer;
    }

    public void setTxtIdtrainer_Trainer(InputText txtIdtrainer_Trainer) {
        this.txtIdtrainer_Trainer = txtIdtrainer_Trainer;
    }

    public InputText getTxtIdusers_Users() {
        return txtIdusers_Users;
    }

    public void setTxtIdusers_Users(InputText txtIdusers_Users) {
        this.txtIdusers_Users = txtIdusers_Users;
    }

    public Calendar getTxtClassDate() {
        return txtClassDate;
    }

    public void setTxtClassDate(Calendar txtClassDate) {
        this.txtClassDate = txtClassDate;
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

	public Integer getMaxUsers() {
		return maxUsers;
	}

	public void setMaxUsers(Integer maxUsers) {
		this.maxUsers = maxUsers;
	}

	public Integer getMinUsers() {
		return minUsers;
	}

	public void setMinUsers(Integer minUsers) {
		this.minUsers = minUsers;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}


	public List<SelectItem> getListCategory() {
		
		if(listCategory == null){
			
		try{
			
			listCategory = new ArrayList<SelectItem>();
			
			List<Category> list = businessDelegatorView.getCategory();
			
			for(Category cat:list){
				listCategory.add(new SelectItem(cat.getIdcategory(), cat.getDescription()));
			}
			
		}catch(Exception e){
			log.info(e.toString());
		}
	
		}
		
		return listCategory;
	}

	public void setListCategory(List<SelectItem> listCategory) {
		this.listCategory = listCategory;
	}

	public SelectOneMenu getSelPlace() {
		return selPlace;
	}

	public void setSelPlace(SelectOneMenu selPlace) {
		this.selPlace = selPlace;
	}

	public List<SelectItem> getListPlace() {
		return listPlace;
	}

	public void setListPlace(List<SelectItem> listPlace) {
		this.listPlace = listPlace;
	}

	public ScheduleEvent getEvent() {
		return event;
	}

	public void setEvent(ScheduleEvent event) {
		this.event = event;
	}

	public Integer getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(Integer categoryID) {
		this.categoryID = categoryID;
	}

	public Integer getTrainer() {
		return trainer;
	}

	public void setTrainer(Integer trainer) {
		this.trainer = trainer;
	}

	public List<SelectItem> getListTrainer() {
		
		if(listTrainer == null){
		
		try{
			
			listTrainer = new ArrayList<SelectItem>();
			
			//Busco los lugares asociados a la cuenta del usuario
			 
			List<Place> placeList = businessDelegatorView.findByCriteriaInPlace(new Object[]{"accountID",false, idAccount, "="},
																				null,
																				null);
			
			if(placeList.size() > 0){
				
				List<AutorizationDTO> dataAutorizedTrainers = new ArrayList<AutorizationDTO>();
				
		for(Place place: placeList){
					
					int idPlace = place.getIdPlace();
					
					List<Autorization> listAuth = businessDelegatorView.findByCriteriaInAutorization(new Object[]{"place.idPlace",false, idPlace, "=","autorizationStatus",false, 1L, "="},
																								 null,
																								 null);
					
					boolean authVisible = false;
					
					for(Autorization auth: listAuth){
						
						Users trainer = businessDelegatorView.getUsers(auth.getUsersIdusers());
						
						String authStatus = "";
						
						if(auth.getAutorizationStatus() == 1){
							authStatus = "Aceptada";
							authVisible = false;
						}else if(auth.getAutorizationStatus() == 2){
							authStatus = "En espera de respuesta";
							authVisible = true;
						}else{
							authStatus = "No aceptada";
							authVisible = false;
						}
						
						if(trainer != null){		
							listTrainer.add(new SelectItem(trainer.getIdusers(), trainer.getName()));
						}//end if
					}// end for
				}
				
			}//end if
			
		}catch(Exception e){
			log.info(e.toString());
		}	
			
			
		}
		
		return listTrainer;
	}

	public void setListTrainer(List<SelectItem> listTrainer) {
		this.listTrainer = listTrainer;
	}
    
    
}
