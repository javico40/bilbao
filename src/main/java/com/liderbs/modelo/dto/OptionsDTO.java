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
public class OptionsDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(OptionsDTO.class);
    private Integer idoptions;
    private String optionsName;
    private String optionsStatus;
    private String optionsUrl;

    public Integer getIdoptions() {
        return idoptions;
    }

    public void setIdoptions(Integer idoptions) {
        this.idoptions = idoptions;
    }

    public String getOptionsName() {
        return optionsName;
    }

    public void setOptionsName(String optionsName) {
        this.optionsName = optionsName;
    }

    public String getOptionsStatus() {
        return optionsStatus;
    }

    public void setOptionsStatus(String optionsStatus) {
        this.optionsStatus = optionsStatus;
    }

    public String getOptionsUrl() {
        return optionsUrl;
    }

    public void setOptionsUrl(String optionsUrl) {
        this.optionsUrl = optionsUrl;
    }
}
