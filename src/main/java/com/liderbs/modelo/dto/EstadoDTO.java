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
public class EstadoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(EstadoDTO.class);
    private String estadonombre;
    private Integer idestado;
    private Integer idpais_Pais;

    public String getEstadonombre() {
        return estadonombre;
    }

    public void setEstadonombre(String estadonombre) {
        this.estadonombre = estadonombre;
    }

    public Integer getIdestado() {
        return idestado;
    }

    public void setIdestado(Integer idestado) {
        this.idestado = idestado;
    }

    public Integer getIdpais_Pais() {
        return idpais_Pais;
    }

    public void setIdpais_Pais(Integer idpais_Pais) {
        this.idpais_Pais = idpais_Pais;
    }
}
