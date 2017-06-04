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
    private Integer accountID;
    private String fiscalID;
    private Integer country;
    private Integer province;
    private String city;
    private String owner_name;
    
    

    public PlaceDTO() {
		super();
	}
    
	public PlaceDTO(Integer idPlace, String placeName, String placeAddress, String placePhone) {
		super();
		this.idPlace = idPlace;
		this.placeName = placeName;
		this.placeAddress = placeAddress;
		this.placePhone = placePhone;
	}



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

	public Integer getAccountID() {
		return accountID;
	}

	public void setAccountID(Integer accountID) {
		this.accountID = accountID;
	}

	public String getFiscalID() {
		return fiscalID;
	}

	public void setFiscalID(String fiscalID) {
		this.fiscalID = fiscalID;
	}

	public Integer getCountry() {
		return country;
	}

	public void setCountry(Integer country) {
		this.country = country;
	}

	public Integer getProvince() {
		return province;
	}

	public void setProvince(Integer province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getOwner_name() {
		return owner_name;
	}

	public void setOwner_name(String owner_name) {
		this.owner_name = owner_name;
	}
	
	
    
    
}
