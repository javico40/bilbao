package com.liderbs.modelo.control;

import com.liderbs.modelo.Schedule;
import com.liderbs.modelo.dto.ScheduleDTO;

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
public interface IScheduleLogic {
    public List<Schedule> getSchedule() throws Exception;

    /**
         * Save an new Schedule entity
         */
    public void saveSchedule(Schedule entity) throws Exception;

    /**
         * Delete an existing Schedule entity
         *
         */
    public void deleteSchedule(Schedule entity) throws Exception;

    /**
        * Update an existing Schedule entity
        *
        */
    public void updateSchedule(Schedule entity) throws Exception;

    /**
         * Load an existing Schedule entity
         *
         */
    public Schedule getSchedule(Integer idschedule) throws Exception;

    public List<Schedule> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Schedule> findPageSchedule(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberSchedule() throws Exception;

    public List<ScheduleDTO> getDataSchedule() throws Exception;
}
