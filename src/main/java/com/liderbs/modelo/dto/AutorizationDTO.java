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
public class AutorizationDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(AutorizationDTO.class);
    private Integer autorizationCreator;
    private Date autorizationDate;
    private Integer autorizationStatus;
    private Integer idAutorization;
    private Integer usersIdusers;
    private Integer idAutorizationtype_Autorizationtype;
    private Integer idPlace_Place;
    private String trainerName;
    private Integer trainerAge;
    private String trainerCategory;
    private String place;
    
    public AutorizationDTO(){
    	
    }

    public AutorizationDTO(Integer idAutorization, Integer usersIdusers, Integer autorizationStatus,
			Integer autorizationCreator, Date autorizationDate, String trainerName, Integer trainerAge,
			String trainerCategory, String place) {
		super();
		this.idAutorization = idAutorization;
		this.usersIdusers = usersIdusers;
		this.autorizationStatus = autorizationStatus;
		this.autorizationCreator = autorizationCreator;
		this.autorizationDate = autorizationDate;
		this.trainerName = trainerName;
		this.trainerAge = trainerAge;
		this.trainerCategory = trainerCategory;
		this.place = place;
	}

	public Integer getAutorizationCreator() {
        return autorizationCreator;
    }

    public void setAutorizationCreator(Integer autorizationCreator) {
        this.autorizationCreator = autorizationCreator;
    }

    public Date getAutorizationDate() {
        return autorizationDate;
    }

    public void setAutorizationDate(Date autorizationDate) {
        this.autorizationDate = autorizationDate;
    }

    public Integer getAutorizationStatus() {
        return autorizationStatus;
    }

    public void setAutorizationStatus(Integer autorizationStatus) {
        this.autorizationStatus = autorizationStatus;
    }

    public Integer getIdAutorization() {
        return idAutorization;
    }

    public void setIdAutorization(Integer idAutorization) {
        this.idAutorization = idAutorization;
    }

    public Integer getUsersIdusers() {
        return usersIdusers;
    }

    public void setUsersIdusers(Integer usersIdusers) {
        this.usersIdusers = usersIdusers;
    }

    public Integer getIdAutorizationtype_Autorizationtype() {
        return idAutorizationtype_Autorizationtype;
    }

    public void setIdAutorizationtype_Autorizationtype(
        Integer idAutorizationtype_Autorizationtype) {
        this.idAutorizationtype_Autorizationtype = idAutorizationtype_Autorizationtype;
    }

    public Integer getIdPlace_Place() {
        return idPlace_Place;
    }

    public void setIdPlace_Place(Integer idPlace_Place) {
        this.idPlace_Place = idPlace_Place;
    }

	public String getTrainerName() {
		return trainerName;
	}

	public void setTrainerName(String trainerName) {
		this.trainerName = trainerName;
	}

	public Integer getTrainerAge() {
		return trainerAge;
	}

	public void setTrainerAge(Integer trainerAge) {
		this.trainerAge = trainerAge;
	}

	public String getTrainerCategory() {
		return trainerCategory;
	}

	public void setTrainerCategory(String trainerCategory) {
		this.trainerCategory = trainerCategory;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}
    
    
}
