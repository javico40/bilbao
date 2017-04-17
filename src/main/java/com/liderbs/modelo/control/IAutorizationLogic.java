package com.liderbs.modelo.control;

import com.liderbs.modelo.Autorization;
import com.liderbs.modelo.dto.AutorizationDTO;

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
public interface IAutorizationLogic {
    public List<Autorization> getAutorization() throws Exception;

    /**
         * Save an new Autorization entity
         */
    public void saveAutorization(Autorization entity) throws Exception;

    /**
         * Delete an existing Autorization entity
         *
         */
    public void deleteAutorization(Autorization entity)
        throws Exception;

    /**
        * Update an existing Autorization entity
        *
        */
    public void updateAutorization(Autorization entity)
        throws Exception;

    /**
         * Load an existing Autorization entity
         *
         */
    public Autorization getAutorization(Integer idAutorization)
        throws Exception;

    public List<Autorization> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Autorization> findPageAutorization(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberAutorization() throws Exception;

    public List<AutorizationDTO> getDataAutorization()
        throws Exception;
}
