package com.liderbs.modelo.control;

import com.liderbs.modelo.Specialclass;
import com.liderbs.modelo.dto.SpecialclassDTO;

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
public interface ISpecialclassLogic {
    public List<Specialclass> getSpecialclass() throws Exception;

    /**
         * Save an new Specialclass entity
         */
    public void saveSpecialclass(Specialclass entity) throws Exception;

    /**
         * Delete an existing Specialclass entity
         *
         */
    public void deleteSpecialclass(Specialclass entity)
        throws Exception;

    /**
        * Update an existing Specialclass entity
        *
        */
    public void updateSpecialclass(Specialclass entity)
        throws Exception;

    /**
         * Load an existing Specialclass entity
         *
         */
    public Specialclass getSpecialclass(Integer idspecialclass)
        throws Exception;

    public List<Specialclass> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Specialclass> findPageSpecialclass(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberSpecialclass() throws Exception;

    public List<SpecialclassDTO> getDataSpecialclass()
        throws Exception;
}
