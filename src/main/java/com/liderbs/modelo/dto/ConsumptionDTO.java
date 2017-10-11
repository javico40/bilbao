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
public class ConsumptionDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(ConsumptionDTO.class);
    private Integer cantidad;
    private Integer consumido;
    private Date fecha;
    private Integer idconsumption;
    private Integer idreservation;
    private Integer placeId;
    private String placeName;
    private String reservation;
    private String usuariodocumento;
    private String usuarionombre;

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getConsumido() {
        return consumido;
    }

    public void setConsumido(Integer consumido) {
        this.consumido = consumido;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getIdconsumption() {
        return idconsumption;
    }

    public void setIdconsumption(Integer idconsumption) {
        this.idconsumption = idconsumption;
    }

    public Integer getIdreservation() {
        return idreservation;
    }

    public void setIdreservation(Integer idreservation) {
        this.idreservation = idreservation;
    }

    public Integer getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Integer placeId) {
        this.placeId = placeId;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getReservation() {
        return reservation;
    }

    public void setReservation(String reservation) {
        this.reservation = reservation;
    }

    public String getUsuariodocumento() {
        return usuariodocumento;
    }

    public void setUsuariodocumento(String usuariodocumento) {
        this.usuariodocumento = usuariodocumento;
    }

    public String getUsuarionombre() {
        return usuarionombre;
    }

    public void setUsuarionombre(String usuarionombre) {
        this.usuarionombre = usuarionombre;
    }
}
