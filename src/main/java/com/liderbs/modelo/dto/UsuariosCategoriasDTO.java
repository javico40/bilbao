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
public class UsuariosCategoriasDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(UsuariosCategoriasDTO.class);
    private Integer idusuariosCategorias;
    private Integer usuario;
    private Integer idcategory_Category;
    private String descripcionCategoria;
    
    

    public UsuariosCategoriasDTO() {
		super();
	}
    
    

	public UsuariosCategoriasDTO(Integer idusuariosCategorias, String descripcionCategoria) {
		super();
		this.idusuariosCategorias = idusuariosCategorias;
		this.descripcionCategoria = descripcionCategoria;
	}



	public Integer getIdusuariosCategorias() {
        return idusuariosCategorias;
    }

    public void setIdusuariosCategorias(Integer idusuariosCategorias) {
        this.idusuariosCategorias = idusuariosCategorias;
    }

    public Integer getUsuario() {
        return usuario;
    }

    public void setUsuario(Integer usuario) {
        this.usuario = usuario;
    }

    public Integer getIdcategory_Category() {
        return idcategory_Category;
    }

    public void setIdcategory_Category(Integer idcategory_Category) {
        this.idcategory_Category = idcategory_Category;
    }

	public String getDescripcionCategoria() {
		return descripcionCategoria;
	}

	public void setDescripcionCategoria(String descripcionCategoria) {
		this.descripcionCategoria = descripcionCategoria;
	}
    
    
}
