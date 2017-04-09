package com.liderbs.modelo.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liderbs.modelo.Options;

import java.io.Serializable;

import java.sql.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
*
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
public class ProfileDTO implements Serializable {
	
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(ProfileDTO.class);
    private Integer idprofile;
    private Date profileCreated;
    private String profileDescription;
    private String profileName;
    private String profileUserCreated;
    private Set<Options> optionses = new HashSet<Options>(0);

    public Integer getIdprofile() {
        return idprofile;
    }

    public void setIdprofile(Integer idprofile) {
        this.idprofile = idprofile;
    }

    public Date getProfileCreated() {
        return profileCreated;
    }

    public void setProfileCreated(Date profileCreated) {
        this.profileCreated = profileCreated;
    }

    public String getProfileDescription() {
        return profileDescription;
    }

    public void setProfileDescription(String profileDescription) {
        this.profileDescription = profileDescription;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getProfileUserCreated() {
        return profileUserCreated;
    }

    public void setProfileUserCreated(String profileUserCreated) {
        this.profileUserCreated = profileUserCreated;
    }

	public Set<Options> getOptionses() {
		return optionses;
	}

	public void setOptionses(Set<Options> optionses) {
		this.optionses = optionses;
	}
    
    
}
