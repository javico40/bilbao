package com.liderbs.modelo.control;

import com.liderbs.modelo.Timetable;
import com.liderbs.modelo.dto.TimetableDTO;

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
public interface ITimetableLogic {
    public List<Timetable> getTimetable() throws Exception;

    /**
         * Save an new Timetable entity
         */
    public void saveTimetable(Timetable entity) throws Exception;

    /**
         * Delete an existing Timetable entity
         *
         */
    public void deleteTimetable(Timetable entity) throws Exception;

    /**
        * Update an existing Timetable entity
        *
        */
    public void updateTimetable(Timetable entity) throws Exception;

    /**
         * Load an existing Timetable entity
         *
         */
    public Timetable getTimetable(Integer idtimetable)
        throws Exception;

    public List<Timetable> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Timetable> findPageTimetable(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberTimetable() throws Exception;

    public List<TimetableDTO> getDataTimetable() throws Exception;
}
