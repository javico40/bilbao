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
public class UsersDTO implements Serializable {
	
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(UsersDTO.class);
    private String cellphone;
    private Date created;
    private String fixedphone;
    private Integer idusers;
    private Date lastlogin;
    private String email;
    private String name;
    private String password;
    private Float saldo;
    private Integer status;
    private String userid;
    private String username;
    private Integer trainerProfileStatus;
    private String trainerPic;
    private String statusDescription;
    private String trainerProfileStatusDesc;
    private String isTrainer;
    
    
    public UsersDTO() {
		super();
	}
    
    
    public UsersDTO(Integer idusers, String username, String password, String name, String cellphone, String email,
			String fixedphone, Integer status, String statusDescription, Float saldo, Date created, Date lastlogin,
			Integer trainerProfileStatus, String trainerPic, String trainerProfileStatusDesc, String isTrainer) {
		
    	super();
		this.idusers = idusers;
		this.username = username;
		this.password = password;
		this.name = name;
		this.cellphone = cellphone;
		this.email = email;
		this.fixedphone = fixedphone;
		this.status = status;
		this.statusDescription = statusDescription;
		this.saldo = saldo;
		this.created = created;
		this.lastlogin = lastlogin;
		this.trainerProfileStatus = trainerProfileStatus;
		this.trainerPic = trainerPic;
		this.trainerProfileStatusDesc = trainerProfileStatusDesc;
		this.isTrainer = isTrainer;
		
	}




	public UsersDTO(Integer idusers, String username, String trainerPic){
    	
    	this.idusers = idusers;
    	this.username = username;
    	this.trainerPic = trainerPic;
    }
    

	public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getFixedphone() {
        return fixedphone;
    }

    public void setFixedphone(String fixedphone) {
        this.fixedphone = fixedphone;
    }

    public Integer getIdusers() {
        return idusers;
    }

    public void setIdusers(Integer idusers) {
        this.idusers = idusers;
    }

    public Date getLastlogin() {
        return lastlogin;
    }

    public void setLastlogin(Date lastlogin) {
        this.lastlogin = lastlogin;
    }

   

    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Float getSaldo() {
        return saldo;
    }

    public void setSaldo(Float saldo) {
        this.saldo = saldo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

	public Integer getTrainerProfileStatus() {
		return trainerProfileStatus;
	}
	
	public void setTrainerProfileStatus(Integer trainerProfileStatus) {
		this.trainerProfileStatus = trainerProfileStatus;
	}

	public String getTrainerPic() {
		return trainerPic;
	}

	public void setTrainerPic(String trainerPic) {
		this.trainerPic = trainerPic;
	}

	public String getStatusDescription() {
		return statusDescription;
	}

	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}

	public String getTrainerProfileStatusDesc() {
		return trainerProfileStatusDesc;
	}

	public void setTrainerProfileStatusDesc(String trainerProfileStatusDesc) {
		this.trainerProfileStatusDesc = trainerProfileStatusDesc;
	}

	public String getIsTrainer() {
		return isTrainer;
	}

	public void setIsTrainer(String isTrainer) {
		this.isTrainer = isTrainer;
	}
	
	
	
}
