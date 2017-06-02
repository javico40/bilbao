package com.liderbs.modelo.control;

import com.liderbs.modelo.Day;
import com.liderbs.modelo.dto.DayDTO;

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
public interface IDayLogic {
    public List<Day> getDay() throws Exception;

    /**
         * Save an new Day entity
         */
    public void saveDay(Day entity) throws Exception;

    /**
         * Delete an existing Day entity
         *
         */
    public void deleteDay(Day entity) throws Exception;

    /**
        * Update an existing Day entity
        *
        */
    public void updateDay(Day entity) throws Exception;

    /**
         * Load an existing Day entity
         *
         */
    public Day getDay(Integer idday) throws Exception;

    public List<Day> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Day> findPageDay(String sortColumnName, boolean sortAscending,
        int startRow, int maxResults) throws Exception;

    public Long findTotalNumberDay() throws Exception;

    public List<DayDTO> getDataDay() throws Exception;
}
