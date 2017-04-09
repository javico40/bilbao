package com.liderbs.modelo.control;

import com.liderbs.modelo.Account;
import com.liderbs.modelo.dto.AccountDTO;

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
public interface IAccountLogic {
    public List<Account> getAccount() throws Exception;

    /**
         * Save an new Account entity
         */
    public void saveAccount(Account entity) throws Exception;

    /**
         * Delete an existing Account entity
         *
         */
    public void deleteAccount(Account entity) throws Exception;

    /**
        * Update an existing Account entity
        *
        */
    public void updateAccount(Account entity) throws Exception;

    /**
         * Load an existing Account entity
         *
         */
    public Account getAccount(Integer idAccount) throws Exception;

    public List<Account> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Account> findPageAccount(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberAccount() throws Exception;

    public List<AccountDTO> getDataAccount() throws Exception;
}
