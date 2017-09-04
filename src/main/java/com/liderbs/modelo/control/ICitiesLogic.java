package com.liderbs.modelo.control;

import com.liderbs.modelo.Cities;
import com.liderbs.modelo.dto.CitiesDTO;

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
public interface ICitiesLogic {
    public List<Cities> getCities() throws Exception;

    /**
         * Save an new Cities entity
         */
    public void saveCities(Cities entity) throws Exception;

    /**
         * Delete an existing Cities entity
         *
         */
    public void deleteCities(Cities entity) throws Exception;

    /**
        * Update an existing Cities entity
        *
        */
    public void updateCities(Cities entity) throws Exception;

    /**
         * Load an existing Cities entity
         *
         */
    public Cities getCities(Integer idcities) throws Exception;

    public List<Cities> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Cities> findPageCities(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberCities() throws Exception;

    public List<CitiesDTO> getDataCities() throws Exception;
}
