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
public class TrainerDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(TrainerDTO.class);
    private String academicArea;
    private String address;
    private Date borndate;
    private String lugar_nacimiento;
    private String city;
    private Integer country;
    private Integer idtrainer;
    private String lastname;
    private String name;
    private Integer region;
    private String resumefile;
    private String trainerAcknowledgement;
    private Integer usersIdusers;
    private Integer idacademiclevel_Academiclevel;
    private Integer ididentification_Identification;
    private Integer idvprofile_Vprofile;
    private Integer trainerProfStatus;

    public String getAcademicArea() {
        return academicArea;
    }

    public void setAcademicArea(String academicArea) {
        this.academicArea = academicArea;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBorndate() {
        return borndate;
    }

    public void setBorndate(Date borndate) {
        this.borndate = borndate;
    }

   
    public String getLugar_nacimiento() {
		return lugar_nacimiento;
	}

	public void setLugar_nacimiento(String lugar_nacimiento) {
		this.lugar_nacimiento = lugar_nacimiento;
	}

	public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getCountry() {
        return country;
    }

    public void setCountry(Integer country) {
        this.country = country;
    }

    public Integer getIdtrainer() {
        return idtrainer;
    }

    public void setIdtrainer(Integer idtrainer) {
        this.idtrainer = idtrainer;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRegion() {
        return region;
    }

    public void setRegion(Integer region) {
        this.region = region;
    }

    public String getResumefile() {
        return resumefile;
    }

    public void setResumefile(String resumefile) {
        this.resumefile = resumefile;
    }

    public String getTrainerAcknowledgement() {
        return trainerAcknowledgement;
    }

    public void setTrainerAcknowledgement(String trainerAcknowledgement) {
        this.trainerAcknowledgement = trainerAcknowledgement;
    }

    public Integer getUsersIdusers() {
        return usersIdusers;
    }

    public void setUsersIdusers(Integer usersIdusers) {
        this.usersIdusers = usersIdusers;
    }

    public Integer getIdacademiclevel_Academiclevel() {
        return idacademiclevel_Academiclevel;
    }

    public void setIdacademiclevel_Academiclevel(
        Integer idacademiclevel_Academiclevel) {
        this.idacademiclevel_Academiclevel = idacademiclevel_Academiclevel;
    }

    public Integer getIdidentification_Identification() {
        return ididentification_Identification;
    }

    public void setIdidentification_Identification(
        Integer ididentification_Identification) {
        this.ididentification_Identification = ididentification_Identification;
    }

    public Integer getIdvprofile_Vprofile() {
        return idvprofile_Vprofile;
    }

    public void setIdvprofile_Vprofile(Integer idvprofile_Vprofile) {
        this.idvprofile_Vprofile = idvprofile_Vprofile;
    }

	public Integer getTrainerProfStatus() {
		return trainerProfStatus;
	}

	public void setTrainerProfStatus(Integer trainerProfStatus) {
		this.trainerProfStatus = trainerProfStatus;
	}
    
    
}
