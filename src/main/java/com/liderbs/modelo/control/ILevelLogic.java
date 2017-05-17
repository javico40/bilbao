package com.liderbs.modelo.control;

import com.liderbs.modelo.Level;
import com.liderbs.modelo.dto.LevelDTO;

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
public interface ILevelLogic {
    public List<Level> getLevel() throws Exception;

    /**
         * Save an new Level entity
         */
    public void saveLevel(Level entity) throws Exception;

    /**
         * Delete an existing Level entity
         *
         */
    public void deleteLevel(Level entity) throws Exception;

    /**
        * Update an existing Level entity
        *
        */
    public void updateLevel(Level entity) throws Exception;

    /**
         * Load an existing Level entity
         *
         */
    public Level getLevel(Integer idlevel) throws Exception;

    public List<Level> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Level> findPageLevel(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberLevel() throws Exception;

    public List<LevelDTO> getDataLevel() throws Exception;
}
