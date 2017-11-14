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
public class SpecialclassDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(SpecialclassDTO.class);
    private String classPicture;
    private String classTitle;
    private String description;
    private Date endHour;
    private Date fecha;
    private Integer havebathroom;
    private Integer havechangeclothes;
    private Integer haveparkingbike;
    private Integer haveparkingcar;
    private Integer haveshower;
    private Integer idspecialclass;
    private String latitude;
    private String longitud;
    private String placeAddress;
    private String placeName;
    private Double price;
    private Date startHour;
    private Integer dayWeek;
    private Integer ispackage;
    private Integer cantidadPackage;
    private String descripcionMovil;

    public String getClassPicture() {
        return classPicture;
    }

    public void setClassPicture(String classPicture) {
        this.classPicture = classPicture;
    }

    public String getClassTitle() {
        return classTitle;
    }

    public void setClassTitle(String classTitle) {
        this.classTitle = classTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getEndHour() {
        return endHour;
    }

    public void setEndHour(Date endHour) {
        this.endHour = endHour;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getHavebathroom() {
        return havebathroom;
    }

    public void setHavebathroom(Integer havebathroom) {
        this.havebathroom = havebathroom;
    }

    public Integer getHavechangeclothes() {
        return havechangeclothes;
    }

    public void setHavechangeclothes(Integer havechangeclothes) {
        this.havechangeclothes = havechangeclothes;
    }

    public Integer getHaveparkingbike() {
        return haveparkingbike;
    }

    public void setHaveparkingbike(Integer haveparkingbike) {
        this.haveparkingbike = haveparkingbike;
    }

    public Integer getHaveparkingcar() {
        return haveparkingcar;
    }

    public void setHaveparkingcar(Integer haveparkingcar) {
        this.haveparkingcar = haveparkingcar;
    }

    public Integer getHaveshower() {
        return haveshower;
    }

    public void setHaveshower(Integer haveshower) {
        this.haveshower = haveshower;
    }

    public Integer getIdspecialclass() {
        return idspecialclass;
    }

    public void setIdspecialclass(Integer idspecialclass) {
        this.idspecialclass = idspecialclass;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getPlaceAddress() {
        return placeAddress;
    }

    public void setPlaceAddress(String placeAddress) {
        this.placeAddress = placeAddress;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getStartHour() {
        return startHour;
    }

    public void setStartHour(Date startHour) {
        this.startHour = startHour;
    }

	public Integer getDayWeek() {
		return dayWeek;
	}

	public void setDayWeek(Integer dayWeek) {
		this.dayWeek = dayWeek;
	}

	public Integer getIspackage() {
		return ispackage;
	}

	public void setIspackage(Integer ispackage) {
		this.ispackage = ispackage;
	}

	public Integer getCantidadPackage() {
		return cantidadPackage;
	}

	public void setCantidadPackage(Integer cantidadPackage) {
		this.cantidadPackage = cantidadPackage;
	}

	public String getDescripcionMovil() {
		return descripcionMovil;
	}

	public void setDescripcionMovil(String descripcionMovil) {
		this.descripcionMovil = descripcionMovil;
	}
    
	
    
}
