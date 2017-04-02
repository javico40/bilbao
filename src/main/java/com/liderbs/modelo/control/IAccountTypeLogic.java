package com.liderbs.modelo.control;

import com.liderbs.modelo.AccountType;
import com.liderbs.modelo.dto.AccountTypeDTO;

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
public interface IAccountTypeLogic {
    public List<AccountType> getAccountType() throws Exception;

    /**
         * Save an new AccountType entity
         */
    public void saveAccountType(AccountType entity) throws Exception;

    /**
         * Delete an existing AccountType entity
         *
         */
    public void deleteAccountType(AccountType entity) throws Exception;

    /**
        * Update an existing AccountType entity
        *
        */
    public void updateAccountType(AccountType entity) throws Exception;

    /**
         * Load an existing AccountType entity
         *
         */
    public AccountType getAccountType(Integer idaccountType)
        throws Exception;

    public List<AccountType> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<AccountType> findPageAccountType(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberAccountType() throws Exception;

    public List<AccountTypeDTO> getDataAccountType() throws Exception;
}
