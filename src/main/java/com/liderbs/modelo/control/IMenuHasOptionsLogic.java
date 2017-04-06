package com.liderbs.modelo.control;

import com.liderbs.modelo.MenuHasOptions;
import com.liderbs.modelo.dto.MenuHasOptionsDTO;

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
public interface IMenuHasOptionsLogic {
    public List<MenuHasOptions> getMenuHasOptions() throws Exception;

    /**
         * Save an new MenuHasOptions entity
         */
    public void saveMenuHasOptions(MenuHasOptions entity)
        throws Exception;

    /**
         * Delete an existing MenuHasOptions entity
         *
         */
    public void deleteMenuHasOptions(MenuHasOptions entity)
        throws Exception;

    /**
        * Update an existing MenuHasOptions entity
        *
        */
    public void updateMenuHasOptions(MenuHasOptions entity)
        throws Exception;

    /**
         * Load an existing MenuHasOptions entity
         *
         */
    public MenuHasOptions getMenuHasOptions(Long id)
        throws Exception;

    public List<MenuHasOptions> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<MenuHasOptions> findPageMenuHasOptions(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberMenuHasOptions() throws Exception;

    public List<MenuHasOptionsDTO> getDataMenuHasOptions()
        throws Exception;
}
