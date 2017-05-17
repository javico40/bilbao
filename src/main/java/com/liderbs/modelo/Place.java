package com.liderbs.modelo;
// Generated 14-abr-2017 19:41:32 by Hibernate Tools 4.0.0


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Place generated by hbm2java
 */
public class Place  implements java.io.Serializable {


	 @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
     private Integer idPlace;
     private String placeName;
     private String placeAddress;
     private String placePhone;
     private String placePhoneAlt;
     private Date placeCreated;
     private Integer placeStatus;
     private Integer placeOwner;
     private Integer placeIsVzone;
     private Integer accountID;
     private String fiscalID;
     private Integer country;
     private Integer province;
     private String city;
     private String owner_name;
     
     private Set<Autorization> autorizations = new HashSet<Autorization>(0);

    public Place() {
    }

    public Place(String placeName, String placeAddress, String placePhone, String placePhoneAlt, Date placeCreated, Integer placeStatus, Integer placeOwner, Integer placeIsVzone, Set<Autorization> autorizations) {
       this.placeName = placeName;
       this.placeAddress = placeAddress;
       this.placePhone = placePhone;
       this.placePhoneAlt = placePhoneAlt;
       this.placeCreated = placeCreated;
       this.placeStatus = placeStatus;
       this.placeOwner = placeOwner;
       this.placeIsVzone = placeIsVzone;
       this.autorizations = autorizations;
    }
   
    public Integer getIdPlace() {
        return this.idPlace;
    }
    
    public void setIdPlace(Integer idPlace) {
        this.idPlace = idPlace;
    }
    public String getPlaceName() {
        return this.placeName;
    }
    
    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }
    public String getPlaceAddress() {
        return this.placeAddress;
    }
    
    public void setPlaceAddress(String placeAddress) {
        this.placeAddress = placeAddress;
    }
    public String getPlacePhone() {
        return this.placePhone;
    }
    
    public void setPlacePhone(String placePhone) {
        this.placePhone = placePhone;
    }
    public String getPlacePhoneAlt() {
        return this.placePhoneAlt;
    }
    
    public void setPlacePhoneAlt(String placePhoneAlt) {
        this.placePhoneAlt = placePhoneAlt;
    }
    public Date getPlaceCreated() {
        return this.placeCreated;
    }
    
    public void setPlaceCreated(Date placeCreated) {
        this.placeCreated = placeCreated;
    }
    public Integer getPlaceStatus() {
        return this.placeStatus;
    }
    
    public void setPlaceStatus(Integer placeStatus) {
        this.placeStatus = placeStatus;
    }
    public Integer getPlaceOwner() {
        return this.placeOwner;
    }
    
    public void setPlaceOwner(Integer placeOwner) {
        this.placeOwner = placeOwner;
    }
    public Integer getPlaceIsVzone() {
        return this.placeIsVzone;
    }
    
    public void setPlaceIsVzone(Integer placeIsVzone) {
        this.placeIsVzone = placeIsVzone;
    }
    public Set<Autorization> getAutorizations() {
        return this.autorizations;
    }
    
    public void setAutorizations(Set<Autorization> autorizations) {
        this.autorizations = autorizations;
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


