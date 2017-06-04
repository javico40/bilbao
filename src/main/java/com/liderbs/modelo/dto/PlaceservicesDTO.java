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
public class PlaceservicesDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PlaceservicesDTO.class);
    private Integer idplaceservices;
    private String names;

    public Integer getIdplaceservices() {
        return idplaceservices;
    }

    public void setIdplaceservices(Integer idplaceservices) {
        this.idplaceservices = idplaceservices;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }
}
