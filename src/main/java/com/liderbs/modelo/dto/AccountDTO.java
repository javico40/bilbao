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
public class AccountDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(AccountDTO.class);
    private Date accountCreated;
    private Integer accountDefault;
    private String accountDescription;
    private String accountName;
    private Integer accountStatus;
    private String accountUserCreated;
    private Integer idAccount;
    private Integer idprofile_Profile;

    public Date getAccountCreated() {
        return accountCreated;
    }

    public void setAccountCreated(Date accountCreated) {
        this.accountCreated = accountCreated;
    }

    public Integer getAccountDefault() {
        return accountDefault;
    }

    public void setAccountDefault(Integer accountDefault) {
        this.accountDefault = accountDefault;
    }

    public String getAccountDescription() {
        return accountDescription;
    }

    public void setAccountDescription(String accountDescription) {
        this.accountDescription = accountDescription;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Integer getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(Integer accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getAccountUserCreated() {
        return accountUserCreated;
    }

    public void setAccountUserCreated(String accountUserCreated) {
        this.accountUserCreated = accountUserCreated;
    }

    public Integer getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(Integer idAccount) {
        this.idAccount = idAccount;
    }

    public Integer getIdprofile_Profile() {
        return idprofile_Profile;
    }

    public void setIdprofile_Profile(Integer idprofile_Profile) {
        this.idprofile_Profile = idprofile_Profile;
    }
}
