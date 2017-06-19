package com.liderbs.modelo.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

import java.sql.*;

import java.util.Date;


/**
*
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
public class TimetableDTO implements Serializable {
	
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(TimetableDTO.class);
    private Date dateCreated;
    private Integer idtimetable;
    private Date timeEnd;
    private Date timeStart;
    private Integer idday_Day;
    private Integer idusers_Users;
    
    //Days
    
    private Integer timetableLunesId;
    private String timetableLunesStatus;
    private String timetableMartesStatus;
    private String timetableMiercolesStatus;
    private String timetableJuevesStatus;
    private String timetableViernesStatus;
    private String timetableSabadoStatus;
    private String timetableDomingoStatus;
    
    public TimetableDTO() {
		super();
	}
    
    
    

	public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Integer getIdtimetable() {
        return idtimetable;
    }

    public void setIdtimetable(Integer idtimetable) {
        this.idtimetable = idtimetable;
    }

    public Date getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Date timeEnd) {
        this.timeEnd = timeEnd;
    }

    public Date getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Date timeStart) {
        this.timeStart = timeStart;
    }

    public Integer getIdday_Day() {
        return idday_Day;
    }

    public void setIdday_Day(Integer idday_Day) {
        this.idday_Day = idday_Day;
    }

    public Integer getIdusers_Users() {
        return idusers_Users;
    }

    public void setIdusers_Users(Integer idusers_Users) {
        this.idusers_Users = idusers_Users;
    }



	public String getTimetableLunesStatus() {
		return timetableLunesStatus;
	}



	public void setTimetableLunesStatus(String timetableLunesStatus) {
		this.timetableLunesStatus = timetableLunesStatus;
	}



	public String getTimetableMartesStatus() {
		return timetableMartesStatus;
	}



	public void setTimetableMartesStatus(String timetableMartesStatus) {
		this.timetableMartesStatus = timetableMartesStatus;
	}



	public String getTimetableMiercolesStatus() {
		return timetableMiercolesStatus;
	}



	public void setTimetableMiercolesStatus(String timetableMiercolesStatus) {
		this.timetableMiercolesStatus = timetableMiercolesStatus;
	}



	public String getTimetableJuevesStatus() {
		return timetableJuevesStatus;
	}



	public void setTimetableJuevesStatus(String timetableJuevesStatus) {
		this.timetableJuevesStatus = timetableJuevesStatus;
	}



	public String getTimetableViernesStatus() {
		return timetableViernesStatus;
	}



	public void setTimetableViernesStatus(String timetableViernesStatus) {
		this.timetableViernesStatus = timetableViernesStatus;
	}



	public String getTimetableSabadoStatus() {
		return timetableSabadoStatus;
	}



	public void setTimetableSabadoStatus(String timetableSabadoStatus) {
		this.timetableSabadoStatus = timetableSabadoStatus;
	}



	public String getTimetableDomingoStatus() {
		return timetableDomingoStatus;
	}



	public void setTimetableDomingoStatus(String timetableDomingoStatus) {
		this.timetableDomingoStatus = timetableDomingoStatus;
	}
    
    
}
