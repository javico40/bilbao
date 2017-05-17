package com.liderbs.modelo.control;

import com.liderbs.modelo.UsuariosCategorias;
import com.liderbs.modelo.dto.UsuariosCategoriasDTO;

import java.math.BigDecimal;

import java.util.*;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
public interface IUsuariosCategoriasLogic {
    public List<UsuariosCategorias> getUsuariosCategorias()
        throws Exception;

    /**
         * Save an new UsuariosCategorias entity
         */
    public void saveUsuariosCategorias(UsuariosCategorias entity)
        throws Exception;

    /**
         * Delete an existing UsuariosCategorias entity
         *
         */
    public void deleteUsuariosCategorias(UsuariosCategorias entity)
        throws Exception;

    /**
        * Update an existing UsuariosCategorias entity
        *
        */
    public void updateUsuariosCategorias(UsuariosCategorias entity)
        throws Exception;

    /**
         * Load an existing UsuariosCategorias entity
         *
         */
    public UsuariosCategorias getUsuariosCategorias(
        Integer idusuariosCategorias) throws Exception;

    public List<UsuariosCategorias> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<UsuariosCategorias> findPageUsuariosCategorias(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberUsuariosCategorias() throws Exception;

    public List<UsuariosCategoriasDTO> getDataUsuariosCategorias()
        throws Exception;
}
