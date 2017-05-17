package com.liderbs.modelo.control;

import com.liderbs.modelo.Identification;
import com.liderbs.modelo.dto.IdentificationDTO;

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
public interface IIdentificationLogic {
    public List<Identification> getIdentification() throws Exception;

    /**
         * Save an new Identification entity
         */
    public void saveIdentification(Identification entity)
        throws Exception;

    /**
         * Delete an existing Identification entity
         *
         */
    public void deleteIdentification(Identification entity)
        throws Exception;

    /**
        * Update an existing Identification entity
        *
        */
    public void updateIdentification(Identification entity)
        throws Exception;

    /**
         * Load an existing Identification entity
         *
         */
    public Identification getIdentification(Integer ididentification)
        throws Exception;

    public List<Identification> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Identification> findPageIdentification(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberIdentification() throws Exception;

    public List<IdentificationDTO> getDataIdentification()
        throws Exception;
}
