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
public class ReservationDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(ReservationDTO.class);
    private Integer idreservation;
    private String reservationHolderCode;
    private String reservationHolderEmail;
    private String reservationHolderId;
    private String reservationHolderName;
    private String reservationHolderTel;
    private Integer reservationIdclass;
    private Integer reservationStatus;

    public Integer getIdreservation() {
        return idreservation;
    }

    public void setIdreservation(Integer idreservation) {
        this.idreservation = idreservation;
    }

    public String getReservationHolderCode() {
        return reservationHolderCode;
    }

    public void setReservationHolderCode(String reservationHolderCode) {
        this.reservationHolderCode = reservationHolderCode;
    }

    public String getReservationHolderEmail() {
        return reservationHolderEmail;
    }

    public void setReservationHolderEmail(String reservationHolderEmail) {
        this.reservationHolderEmail = reservationHolderEmail;
    }

    public String getReservationHolderId() {
        return reservationHolderId;
    }

    public void setReservationHolderId(String reservationHolderId) {
        this.reservationHolderId = reservationHolderId;
    }

    public String getReservationHolderName() {
        return reservationHolderName;
    }

    public void setReservationHolderName(String reservationHolderName) {
        this.reservationHolderName = reservationHolderName;
    }

    public String getReservationHolderTel() {
        return reservationHolderTel;
    }

    public void setReservationHolderTel(String reservationHolderTel) {
        this.reservationHolderTel = reservationHolderTel;
    }

    public Integer getReservationIdclass() {
        return reservationIdclass;
    }

    public void setReservationIdclass(Integer reservationIdclass) {
        this.reservationIdclass = reservationIdclass;
    }

    public Integer getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(Integer reservationStatus) {
        this.reservationStatus = reservationStatus;
    }
}
