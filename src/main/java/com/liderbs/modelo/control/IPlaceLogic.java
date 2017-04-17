package com.liderbs.modelo.control;

import com.liderbs.modelo.Place;
import com.liderbs.modelo.dto.PlaceDTO;

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
public interface IPlaceLogic {
    public List<Place> getPlace() throws Exception;

    /**
         * Save an new Place entity
         */
    public void savePlace(Place entity) throws Exception;

    /**
         * Delete an existing Place entity
         *
         */
    public void deletePlace(Place entity) throws Exception;

    /**
        * Update an existing Place entity
        *
        */
    public void updatePlace(Place entity) throws Exception;

    /**
         * Load an existing Place entity
         *
         */
    public Place getPlace(Integer idPlace) throws Exception;

    public List<Place> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Place> findPagePlace(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberPlace() throws Exception;

    public List<PlaceDTO> getDataPlace() throws Exception;
}
