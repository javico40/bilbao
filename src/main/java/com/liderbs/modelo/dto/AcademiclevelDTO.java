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
public class AcademiclevelDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(AcademiclevelDTO.class);
    private Integer idacademiclevel;
    private String name;

    public Integer getIdacademiclevel() {
        return idacademiclevel;
    }

    public void setIdacademiclevel(Integer idacademiclevel) {
        this.idacademiclevel = idacademiclevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
