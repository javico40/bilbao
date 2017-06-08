package com.liderbs.modelo.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liderbs.modelo.Options;
import com.liderbs.modelo.Placeservices;
import com.liderbs.modelo.Placetype;

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
    private String facebook;
    private String instagram;
    private String website;
    private Date inWeekly;
    private Date outWeekly;
    private Date inSaturday;
    private Date outSaturday;
    private Date inSunday;
    private Date outSunday;
    private Double dailyCost;
    private Double monthlyCost;
    private Set<Placetype> placetypes = new HashSet<Placetype>(0);
    private Set<Placeservices> placeserviceses = new HashSet<Placeservices>(0);
    
    

    public PlaceDTO() {
		super();
	}
    
	public PlaceDTO(Integer idPlace, String fiscalID,  String placeName, String placeAddress, String placePhone, Set<Placetype> placetypes, Set<Placeservices> placeserviceses) {
		super();
		this.idPlace = idPlace;
		this.placeName = placeName;
		this.placeAddress = placeAddress;
		this.placePhone = placePhone;
		this.placetypes = placetypes;
		this.placeserviceses = placeserviceses;
		this.fiscalID = fiscalID;
		
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

	public Set<Placetype> getPlacetypes() {
		return placetypes;
	}

	public void setPlacetypes(Set<Placetype> placetypes) {
		this.placetypes = placetypes;
	}

	public Set<Placeservices> getPlaceserviceses() {
		return placeserviceses;
	}

	public void setPlaceserviceses(Set<Placeservices> placeserviceses) {
		this.placeserviceses = placeserviceses;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getInstagram() {
		return instagram;
	}

	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public Date getInWeekly() {
		return inWeekly;
	}

	public void setInWeekly(Date inWeekly) {
		this.inWeekly = inWeekly;
	}

	public Date getOutWeekly() {
		return outWeekly;
	}

	public void setOutWeekly(Date outWeekly) {
		this.outWeekly = outWeekly;
	}

	public Date getInSatuday() {
		return inSaturday;
	}

	public void setInSatuday(Date inSatuday) {
		this.inSaturday = inSatuday;
	}

	public Date getOutSaturday() {
		return outSaturday;
	}

	public void setOutSaturday(Date outSaturday) {
		this.outSaturday = outSaturday;
	}

	public Date getInSunday() {
		return inSunday;
	}

	public void setInSunday(Date inSunday) {
		this.inSunday = inSunday;
	}

	public Date getOutSunday() {
		return outSunday;
	}

	public void setOutSunday(Date outSunday) {
		this.outSunday = outSunday;
	}

	public Double getDailyCost() {
		return dailyCost;
	}

	public void setDailyCost(Double dailyCost) {
		this.dailyCost = dailyCost;
	}

	public Double getMonthlyCost() {
		return monthlyCost;
	}

	public void setMonthlyCost(Double monthlyCost) {
		this.monthlyCost = monthlyCost;
	}

	public Date getInSaturday() {
		return inSaturday;
	}

	public void setInSaturday(Date inSaturday) {
		this.inSaturday = inSaturday;
	}
	
	
	
}
