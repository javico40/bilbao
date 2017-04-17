package com.liderbs.modelo.control;

import com.liderbs.modelo.Autorizationtype;
import com.liderbs.modelo.dto.AutorizationtypeDTO;

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
public interface IAutorizationtypeLogic {
    public List<Autorizationtype> getAutorizationtype()
        throws Exception;

    /**
         * Save an new Autorizationtype entity
         */
    public void saveAutorizationtype(Autorizationtype entity)
        throws Exception;

    /**
         * Delete an existing Autorizationtype entity
         *
         */
    public void deleteAutorizationtype(Autorizationtype entity)
        throws Exception;

    /**
        * Update an existing Autorizationtype entity
        *
        */
    public void updateAutorizationtype(Autorizationtype entity)
        throws Exception;

    /**
         * Load an existing Autorizationtype entity
         *
         */
    public Autorizationtype getAutorizationtype(Integer idAutorizationtype)
        throws Exception;

    public List<Autorizationtype> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Autorizationtype> findPageAutorizationtype(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberAutorizationtype() throws Exception;

    public List<AutorizationtypeDTO> getDataAutorizationtype()
        throws Exception;
}
