package com.liderbs.modelo;
// Generated 14-abr-2017 19:41:32 by Hibernate Tools 4.0.0


import java.util.Date;

/**
 * Autorization generated by hbm2java
 */
public class Autorization  implements java.io.Serializable {


     private Integer idAutorization;
     private Autorizationtype autorizationtype;
     private Place place;
     private Date autorizationDate;
     private Integer autorizationStatus;
     private Integer usersIdusers;
     private Integer autorizationCreator;
     private String trainerName;
     private Integer trainerAge;
     private String trainerCategory;

    public Autorization() {
    }

	
    public Autorization(Autorizationtype autorizationtype, Place place, Integer usersIdusers) {
        this.autorizationtype = autorizationtype;
        this.place = place;
        this.usersIdusers = usersIdusers;
    }
    public Autorization(Autorizationtype autorizationtype, Place place, Date autorizationDate, Integer autorizationStatus, Integer usersIdusers, Integer autorizationCreator) {
       this.autorizationtype = autorizationtype;
       this.place = place;
       this.autorizationDate = autorizationDate;
       this.autorizationStatus = autorizationStatus;
       this.usersIdusers = usersIdusers;
       this.autorizationCreator = autorizationCreator;
    }
   
    public Integer getIdAutorization() {
        return this.idAutorization;
    }
    
    public void setIdAutorization(Integer idAutorization) {
        this.idAutorization = idAutorization;
    }
    public Autorizationtype getAutorizationtype() {
        return this.autorizationtype;
    }
    
    public void setAutorizationtype(Autorizationtype autorizationtype) {
        this.autorizationtype = autorizationtype;
    }
    public Place getPlace() {
        return this.place;
    }
    
    public void setPlace(Place place) {
        this.place = place;
    }
    public Date getAutorizationDate() {
        return this.autorizationDate;
    }
    
    public void setAutorizationDate(Date autorizationDate) {
        this.autorizationDate = autorizationDate;
    }
    public Integer getAutorizationStatus() {
        return this.autorizationStatus;
    }
    
    public void setAutorizationStatus(Integer autorizationStatus) {
        this.autorizationStatus = autorizationStatus;
    }
    public Integer getUsersIdusers() {
        return this.usersIdusers;
    }
    
    public void setUsersIdusers(Integer usersIdusers) {
        this.usersIdusers = usersIdusers;
    }
    public Integer getAutorizationCreator() {
        return this.autorizationCreator;
    }
    
    public void setAutorizationCreator(Integer autorizationCreator) {
        this.autorizationCreator = autorizationCreator;
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




}

