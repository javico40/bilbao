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
public class CoursesDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(CoursesDTO.class);
    private String certificator;
    private Date datecertified;
    private String description;
    private Integer idcourses;
    private String name;

    public String getCertificator() {
        return certificator;
    }

    public void setCertificator(String certificator) {
        this.certificator = certificator;
    }

    public Date getDatecertified() {
        return datecertified;
    }

    public void setDatecertified(Date datecertified) {
        this.datecertified = datecertified;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIdcourses() {
        return idcourses;
    }

    public void setIdcourses(Integer idcourses) {
        this.idcourses = idcourses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
