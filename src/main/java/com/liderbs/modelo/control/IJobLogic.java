package com.liderbs.modelo.control;

import com.liderbs.modelo.Job;
import com.liderbs.modelo.dto.JobDTO;

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
public interface IJobLogic {
    public List<Job> getJob() throws Exception;

    /**
         * Save an new Job entity
         */
    public void saveJob(Job entity) throws Exception;

    /**
         * Delete an existing Job entity
         *
         */
    public void deleteJob(Job entity) throws Exception;

    /**
        * Update an existing Job entity
        *
        */
    public void updateJob(Job entity) throws Exception;

    /**
         * Load an existing Job entity
         *
         */
    public Job getJob(Integer idjob) throws Exception;

    public List<Job> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Job> findPageJob(String sortColumnName, boolean sortAscending,
        int startRow, int maxResults) throws Exception;

    public Long findTotalNumberJob() throws Exception;

    public List<JobDTO> getDataJob() throws Exception;
}
