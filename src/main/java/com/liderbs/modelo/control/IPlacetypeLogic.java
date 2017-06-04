package com.liderbs.modelo.control;

import com.liderbs.modelo.Placetype;
import com.liderbs.modelo.dto.PlacetypeDTO;

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
public interface IPlacetypeLogic {
    public List<Placetype> getPlacetype() throws Exception;

    /**
         * Save an new Placetype entity
         */
    public void savePlacetype(Placetype entity) throws Exception;

    /**
         * Delete an existing Placetype entity
         *
         */
    public void deletePlacetype(Placetype entity) throws Exception;

    /**
        * Update an existing Placetype entity
        *
        */
    public void updatePlacetype(Placetype entity) throws Exception;

    /**
         * Load an existing Placetype entity
         *
         */
    public Placetype getPlacetype(Integer idplacetype)
        throws Exception;

    public List<Placetype> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Placetype> findPagePlacetype(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberPlacetype() throws Exception;

    public List<PlacetypeDTO> getDataPlacetype() throws Exception;
}
