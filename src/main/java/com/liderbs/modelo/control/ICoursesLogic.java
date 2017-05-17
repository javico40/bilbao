package com.liderbs.modelo.control;

import com.liderbs.modelo.Courses;
import com.liderbs.modelo.dto.CoursesDTO;

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
public interface ICoursesLogic {
    public List<Courses> getCourses() throws Exception;

    /**
         * Save an new Courses entity
         */
    public void saveCourses(Courses entity) throws Exception;

    /**
         * Delete an existing Courses entity
         *
         */
    public void deleteCourses(Courses entity) throws Exception;

    /**
        * Update an existing Courses entity
        *
        */
    public void updateCourses(Courses entity) throws Exception;

    /**
         * Load an existing Courses entity
         *
         */
    public Courses getCourses(Integer idcourses) throws Exception;

    public List<Courses> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Courses> findPageCourses(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberCourses() throws Exception;

    public List<CoursesDTO> getDataCourses() throws Exception;
}
