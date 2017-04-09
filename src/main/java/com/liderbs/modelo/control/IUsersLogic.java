package com.liderbs.modelo.control;

import com.liderbs.modelo.Users;
import com.liderbs.modelo.dto.UsersDTO;

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
public interface IUsersLogic {
    public List<Users> getUsers() throws Exception;

    /**
         * Save an new Users entity
         */
    public void saveUsers(Users entity) throws Exception;

    /**
         * Delete an existing Users entity
         *
         */
    public void deleteUsers(Users entity) throws Exception;

    /**
        * Update an existing Users entity
        *
        */
    public void updateUsers(Users entity) throws Exception;

    /**
         * Load an existing Users entity
         *
         */
    public Users getUsers(Integer idusers) throws Exception;

    public List<Users> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Users> findPageUsers(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberUsers() throws Exception;

    public List<UsersDTO> getDataUsers() throws Exception;
}
