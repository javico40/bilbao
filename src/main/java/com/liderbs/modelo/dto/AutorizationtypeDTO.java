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
public class AutorizationtypeDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(AutorizationtypeDTO.class);
    private String autorizationtypeName;
    private Integer idAutorizationtype;

    public String getAutorizationtypeName() {
        return autorizationtypeName;
    }

    public void setAutorizationtypeName(String autorizationtypeName) {
        this.autorizationtypeName = autorizationtypeName;
    }

    public Integer getIdAutorizationtype() {
        return idAutorizationtype;
    }

    public void setIdAutorizationtype(Integer idAutorizationtype) {
        this.idAutorizationtype = idAutorizationtype;
    }
}
