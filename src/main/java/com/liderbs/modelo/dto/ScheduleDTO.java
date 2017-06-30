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
    private Date classDate;
    private Date endtime;
    private Integer idschedule;
    private Integer maxUsers;
    private Date starttime;
    private Integer idcategory_Category;
    private Integer idday_Day;
    private Integer idPlace_Place;
    private Integer idtrainer_Trainer;
    private Integer idusers_Users;

    public Date getClassDate() {
        return classDate;
    }

    public void setClassDate(Date classDate) {
        this.classDate = classDate;
    }

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

    public Integer getMaxUsers() {
        return maxUsers;
    }

    public void setMaxUsers(Integer maxUsers) {
        this.maxUsers = maxUsers;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Integer getIdcategory_Category() {
        return idcategory_Category;
    }

    public void setIdcategory_Category(Integer idcategory_Category) {
        this.idcategory_Category = idcategory_Category;
    }

    public Integer getIdday_Day() {
        return idday_Day;
    }

    public void setIdday_Day(Integer idday_Day) {
        this.idday_Day = idday_Day;
    }

    public Integer getIdPlace_Place() {
        return idPlace_Place;
    }

    public void setIdPlace_Place(Integer idPlace_Place) {
        this.idPlace_Place = idPlace_Place;
    }

    public Integer getIdtrainer_Trainer() {
        return idtrainer_Trainer;
    }

    public void setIdtrainer_Trainer(Integer idtrainer_Trainer) {
        this.idtrainer_Trainer = idtrainer_Trainer;
    }

    public Integer getIdusers_Users() {
        return idusers_Users;
    }

    public void setIdusers_Users(Integer idusers_Users) {
        this.idusers_Users = idusers_Users;
    }
}
