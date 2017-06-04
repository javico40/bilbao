package com.liderbs.modelo.control;

import com.liderbs.modelo.Placeservices;
import com.liderbs.modelo.dto.PlaceservicesDTO;

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
public interface IPlaceservicesLogic {
    public List<Placeservices> getPlaceservices() throws Exception;

    /**
         * Save an new Placeservices entity
         */
    public void savePlaceservices(Placeservices entity)
        throws Exception;

    /**
         * Delete an existing Placeservices entity
         *
         */
    public void deletePlaceservices(Placeservices entity)
        throws Exception;

    /**
        * Update an existing Placeservices entity
        *
        */
    public void updatePlaceservices(Placeservices entity)
        throws Exception;

    /**
         * Load an existing Placeservices entity
         *
         */
    public Placeservices getPlaceservices(Integer idplaceservices)
        throws Exception;

    public List<Placeservices> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Placeservices> findPagePlaceservices(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberPlaceservices() throws Exception;

    public List<PlaceservicesDTO> getDataPlaceservices()
        throws Exception;
}
