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
public class ScheduleDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(ScheduleDTO.class);
    private Date endtime;
    private Integer idschedule;
    private Date starttime;
    private Integer idday_Day;
    private Integer idusers_Users;

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Integer getIdschedule() {
        return idschedule;
    }

    public void setIdschedule(Integer idschedule) {
        this.idschedule = idschedule;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
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
}
