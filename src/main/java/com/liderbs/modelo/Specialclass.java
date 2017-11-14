package com.liderbs.modelo;
// Generated 27-sep-2017 22:04:37 by Hibernate Tools 4.0.0


import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Specialclass generated by hbm2java
 */
public class Specialclass  implements java.io.Serializable {

	 @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
     private Integer idspecialclass;
     private String classTitle;
     private Date fecha;
     private Date startHour;
     private Date endHour;
     private String placeName;
     private String placeAddress;
     private String latitude;
     private String longitud;
     private String description;
     private Integer haveparkingcar;
     private Integer haveparkingbike;
     private Integer havechangeclothes;
     private Integer havebathroom;
     private Integer haveshower;
     private Double price;
     private String classPicture;
     private Integer dayWeek;
     private Integer ispackage;
     private Integer cantidadPackage;
     private String descripcionMovil;

    public Specialclass() {
    }

    public Specialclass(String classTitle, Date fecha, Date startHour, Date endHour, String placeName, String placeAddress, String latitude, String longitud, String description, Integer haveparkingcar, Integer haveparkingbike, Integer havechangeclothes, Integer havebathroom, Integer haveshower, Double price, String classPicture) {
       this.classTitle = classTitle;
       this.fecha = fecha;
       this.startHour = startHour;
       this.endHour = endHour;
       this.placeName = placeName;
       this.placeAddress = placeAddress;
       this.latitude = latitude;
       this.longitud = longitud;
       this.description = description;
       this.haveparkingcar = haveparkingcar;
       this.haveparkingbike = haveparkingbike;
       this.havechangeclothes = havechangeclothes;
       this.havebathroom = havebathroom;
       this.haveshower = haveshower;
       this.price = price;
       this.classPicture = classPicture;
    }
   
    public Integer getIdspecialclass() {
        return this.idspecialclass;
    }
    
    public void setIdspecialclass(Integer idspecialclass) {
        this.idspecialclass = idspecialclass;
    }
    public String getClassTitle() {
        return this.classTitle;
    }
    
    public void setClassTitle(String classTitle) {
        this.classTitle = classTitle;
    }
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public Date getStartHour() {
        return this.startHour;
    }
    
    public void setStartHour(Date startHour) {
        this.startHour = startHour;
    }
    public Date getEndHour() {
        return this.endHour;
    }
    
    public void setEndHour(Date endHour) {
        this.endHour = endHour;
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
    public String getLatitude() {
        return this.latitude;
    }
    
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
    public String getLongitud() {
        return this.longitud;
    }
    
    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    public Integer getHaveparkingcar() {
        return this.haveparkingcar;
    }
    
    public void setHaveparkingcar(Integer haveparkingcar) {
        this.haveparkingcar = haveparkingcar;
    }
    public Integer getHaveparkingbike() {
        return this.haveparkingbike;
    }
    
    public void setHaveparkingbike(Integer haveparkingbike) {
        this.haveparkingbike = haveparkingbike;
    }
    public Integer getHavechangeclothes() {
        return this.havechangeclothes;
    }
    
    public void setHavechangeclothes(Integer havechangeclothes) {
        this.havechangeclothes = havechangeclothes;
    }
    public Integer getHavebathroom() {
        return this.havebathroom;
    }
    
    public void setHavebathroom(Integer havebathroom) {
        this.havebathroom = havebathroom;
    }
    public Integer getHaveshower() {
        return this.haveshower;
    }
    
    public void setHaveshower(Integer haveshower) {
        this.haveshower = haveshower;
    }
    public Double getPrice() {
        return this.price;
    }
    
    public void setPrice(Double price) {
        this.price = price;
    }
    public String getClassPicture() {
        return this.classPicture;
    }
    
    public void setClassPicture(String classPicture) {
        this.classPicture = classPicture;
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


