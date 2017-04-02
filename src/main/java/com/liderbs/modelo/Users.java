package com.liderbs.modelo;
// Generated 02-abr-2017 11:42:14 by Hibernate Tools 4.0.0


import java.util.Date;

/**
 * Users generated by hbm2java
 */
public class Users  implements java.io.Serializable {


     private Integer idusers;
     private Integer accountIdaccount;
     private String username;
     private String password;
     private String name;
     private String lastname;
     private String userid;
     private String fixedphone;
     private String cellphone;
     private Date created;
     private Date lastlogin;

    public Users() {
    }

	
    public Users(Integer accountIdaccount) {
        this.accountIdaccount = accountIdaccount;
    }
    public Users(Integer accountIdaccount, String username, String password, String name, String lastname, String userid, String fixedphone, String cellphone, Date created, Date lastlogin) {
       this.accountIdaccount = accountIdaccount;
       this.username = username;
       this.password = password;
       this.name = name;
       this.lastname = lastname;
       this.userid = userid;
       this.fixedphone = fixedphone;
       this.cellphone = cellphone;
       this.created = created;
       this.lastlogin = lastlogin;
    }
   
    public Integer getIdusers() {
        return this.idusers;
    }
    
    public void setIdusers(Integer idusers) {
        this.idusers = idusers;
    }
    public Integer getAccountIdaccount() {
        return this.accountIdaccount;
    }
    
    public void setAccountIdaccount(Integer accountIdaccount) {
        this.accountIdaccount = accountIdaccount;
    }
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getLastname() {
        return this.lastname;
    }
    
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getUserid() {
        return this.userid;
    }
    
    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getFixedphone() {
        return this.fixedphone;
    }
    
    public void setFixedphone(String fixedphone) {
        this.fixedphone = fixedphone;
    }
    public String getCellphone() {
        return this.cellphone;
    }
    
    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }
    public Date getCreated() {
        return this.created;
    }
    
    public void setCreated(Date created) {
        this.created = created;
    }
    public Date getLastlogin() {
        return this.lastlogin;
    }
    
    public void setLastlogin(Date lastlogin) {
        this.lastlogin = lastlogin;
    }




}


