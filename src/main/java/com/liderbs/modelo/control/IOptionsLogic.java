package com.liderbs.modelo.control;

import com.liderbs.modelo.Options;
import com.liderbs.modelo.dto.OptionsDTO;

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
public interface IOptionsLogic {
    public List<Options> getOptions() throws Exception;

    /**
         * Save an new Options entity
         */
    public void saveOptions(Options entity) throws Exception;

    /**
         * Delete an existing Options entity
         *
         */
    public void deleteOptions(Options entity) throws Exception;

    /**
        * Update an existing Options entity
        *
        */
    public void updateOptions(Options entity) throws Exception;

    /**
         * Load an existing Options entity
         *
         */
    public Options getOptions(Integer idoptions) throws Exception;

    public List<Options> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Options> findPageOptions(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberOptions() throws Exception;

    public List<OptionsDTO> getDataOptions() throws Exception;
}
