package com.liderbs.presentation.businessDelegate;

import com.liderbs.modelo.AccountType;
import com.liderbs.modelo.Options;
import com.liderbs.modelo.Profile;
import com.liderbs.modelo.Users;
import com.liderbs.modelo.control.AccountTypeLogic;
import com.liderbs.modelo.control.IAccountTypeLogic;
import com.liderbs.modelo.control.IOptionsLogic;
import com.liderbs.modelo.control.IProfileLogic;
import com.liderbs.modelo.control.IUsersLogic;
import com.liderbs.modelo.control.OptionsLogic;
import com.liderbs.modelo.control.ProfileLogic;
import com.liderbs.modelo.control.UsersLogic;
import com.liderbs.modelo.dto.AccountTypeDTO;
import com.liderbs.modelo.dto.OptionsDTO;
import com.liderbs.modelo.dto.ProfileDTO;
import com.liderbs.modelo.dto.UsersDTO;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
public interface IBusinessDelegatorView {
    public List<AccountType> getAccountType() throws Exception;

    public void saveAccountType(AccountType entity) throws Exception;

    public void deleteAccountType(AccountType entity) throws Exception;

    public void updateAccountType(AccountType entity) throws Exception;

    public AccountType getAccountType(Integer idaccountType)
        throws Exception;

    public List<AccountType> findByCriteriaInAccountType(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<AccountType> findPageAccountType(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberAccountType() throws Exception;

    public List<AccountTypeDTO> getDataAccountType() throws Exception;

    public List<Options> getOptions() throws Exception;

    public void saveOptions(Options entity) throws Exception;

    public void deleteOptions(Options entity) throws Exception;

    public void updateOptions(Options entity) throws Exception;

    public Options getOptions(Integer idoptions) throws Exception;

    public List<Options> findByCriteriaInOptions(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Options> findPageOptions(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberOptions() throws Exception;

    public List<OptionsDTO> getDataOptions() throws Exception;

    public List<Profile> getProfile() throws Exception;

    public void saveProfile(Profile entity) throws Exception;

    public void deleteProfile(Profile entity) throws Exception;

    public void updateProfile(Profile entity) throws Exception;

    public Profile getProfile(Integer idprofile) throws Exception;

    public List<Profile> findByCriteriaInProfile(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Profile> findPageProfile(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberProfile() throws Exception;

    public List<ProfileDTO> getDataProfile() throws Exception;

    public List<Users> getUsers() throws Exception;

    public void saveUsers(Users entity) throws Exception;

    public void deleteUsers(Users entity) throws Exception;

    public void updateUsers(Users entity) throws Exception;

    public Users getUsers(Integer idusers) throws Exception;

    public List<Users> findByCriteriaInUsers(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Users> findPageUsers(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberUsers() throws Exception;

    public List<UsersDTO> getDataUsers() throws Exception;
}
