package com.liderbs.modelo.control;

import com.liderbs.modelo.Experience;
import com.liderbs.modelo.dto.ExperienceDTO;

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
public interface IExperienceLogic {
    public List<Experience> getExperience() throws Exception;

    /**
         * Save an new Experience entity
         */
    public void saveExperience(Experience entity) throws Exception;

    /**
         * Delete an existing Experience entity
         *
         */
    public void deleteExperience(Experience entity) throws Exception;

    /**
        * Update an existing Experience entity
        *
        */
    public void updateExperience(Experience entity) throws Exception;

    /**
         * Load an existing Experience entity
         *
         */
    public Experience getExperience(Integer idexperience)
        throws Exception;

    public List<Experience> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Experience> findPageExperience(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberExperience() throws Exception;

    public List<ExperienceDTO> getDataExperience() throws Exception;
}
