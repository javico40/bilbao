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
public class ConsumptionDetailDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(ConsumptionDetailDTO.class);
    private Integer cantidad;
    private Date fecha;
    private Date hora;
    private Integer idconsumption;
    private Integer idconsumptionDetail;
    private Integer idusuarioCreo;
    private String usuarioCreo;

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public Integer getIdconsumption() {
        return idconsumption;
    }

    public void setIdconsumption(Integer idconsumption) {
        this.idconsumption = idconsumption;
    }

    public Integer getIdconsumptionDetail() {
        return idconsumptionDetail;
    }

    public void setIdconsumptionDetail(Integer idconsumptionDetail) {
        this.idconsumptionDetail = idconsumptionDetail;
    }

    public Integer getIdusuarioCreo() {
        return idusuarioCreo;
    }

    public void setIdusuarioCreo(Integer idusuarioCreo) {
        this.idusuarioCreo = idusuarioCreo;
    }

    public String getUsuarioCreo() {
        return usuarioCreo;
    }

    public void setUsuarioCreo(String usuarioCreo) {
        this.usuarioCreo = usuarioCreo;
    }
}
