package com.liderbs.modelo.control;

import com.liderbs.modelo.Menu;
import com.liderbs.modelo.dto.MenuDTO;

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
public interface IMenuLogic {
    public List<Menu> getMenu() throws Exception;

    /**
         * Save an new Menu entity
         */
    public void saveMenu(Menu entity) throws Exception;

    /**
         * Delete an existing Menu entity
         *
         */
    public void deleteMenu(Menu entity) throws Exception;

    /**
        * Update an existing Menu entity
        *
        */
    public void updateMenu(Menu entity) throws Exception;

    /**
         * Load an existing Menu entity
         *
         */
    public Menu getMenu(Integer idMenu) throws Exception;

    public List<Menu> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Menu> findPageMenu(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberMenu() throws Exception;

    public List<MenuDTO> getDataMenu() throws Exception;
}
