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
public class PlaceDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PlaceDTO.class);
    private Integer idPlace;
    private String placeAddress;
    private Date placeCreated;
    private Integer placeIsVzone;
    private String placeName;
    private Integer placeOwner;
    private String placePhone;
    private String placePhoneAlt;
    private Integer placeStatus;

    public Integer getIdPlace() {
        return idPlace;
    }

    public void setIdPlace(Integer idPlace) {
        this.idPlace = idPlace;
    }

    public String getPlaceAddress() {
        return placeAddress;
    }

    public void setPlaceAddress(String placeAddress) {
        this.placeAddress = placeAddress;
    }

    public Date getPlaceCreated() {
        return placeCreated;
    }

    public void setPlaceCreated(Date placeCreated) {
        this.placeCreated = placeCreated;
    }

    public Integer getPlaceIsVzone() {
        return placeIsVzone;
    }

    public void setPlaceIsVzone(Integer placeIsVzone) {
        this.placeIsVzone = placeIsVzone;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public Integer getPlaceOwner() {
        return placeOwner;
    }

    public void setPlaceOwner(Integer placeOwner) {
        this.placeOwner = placeOwner;
    }

    public String getPlacePhone() {
        return placePhone;
    }

    public void setPlacePhone(String placePhone) {
        this.placePhone = placePhone;
    }

    public String getPlacePhoneAlt() {
        return placePhoneAlt;
    }

    public void setPlacePhoneAlt(String placePhoneAlt) {
        this.placePhoneAlt = placePhoneAlt;
    }

    public Integer getPlaceStatus() {
        return placeStatus;
    }

    public void setPlaceStatus(Integer placeStatus) {
        this.placeStatus = placeStatus;
    }
}
