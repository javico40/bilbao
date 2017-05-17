package com.liderbs.modelo.control;

import com.liderbs.modelo.Category;
import com.liderbs.modelo.dto.CategoryDTO;

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
public interface ICategoryLogic {
    public List<Category> getCategory() throws Exception;

    /**
         * Save an new Category entity
         */
    public void saveCategory(Category entity) throws Exception;

    /**
         * Delete an existing Category entity
         *
         */
    public void deleteCategory(Category entity) throws Exception;

    /**
        * Update an existing Category entity
        *
        */
    public void updateCategory(Category entity) throws Exception;

    /**
         * Load an existing Category entity
         *
         */
    public Category getCategory(Integer idcategory) throws Exception;

    public List<Category> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Category> findPageCategory(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberCategory() throws Exception;

    public List<CategoryDTO> getDataCategory() throws Exception;
}
