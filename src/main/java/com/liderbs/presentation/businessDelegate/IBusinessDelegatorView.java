package com.liderbs.presentation.businessDelegate;

import com.liderbs.modelo.Menu;
import com.liderbs.modelo.MenuHasOptions;
import com.liderbs.modelo.dto.MenuDTO;
import com.liderbs.modelo.dto.MenuHasOptionsDTO;
import com.liderbs.modelo.Options;
import com.liderbs.modelo.Profile;
import com.liderbs.modelo.Users;
import com.liderbs.modelo.UsersId;
import com.liderbs.modelo.control.IOptionsLogic;
import com.liderbs.modelo.control.IProfileLogic;
import com.liderbs.modelo.control.IUsersLogic;
import com.liderbs.modelo.control.OptionsLogic;
import com.liderbs.modelo.control.ProfileLogic;
import com.liderbs.modelo.control.UsersLogic;
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

    public Users getUsers(Long id) throws Exception;

    public List<Users> findByCriteriaInUsers(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Users> findPageUsers(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberUsers() throws Exception;

    public List<UsersDTO> getDataUsers() throws Exception;
    
    public List<Menu> getMenu() throws Exception;
    
    public void saveMenu(Menu entity) throws Exception;

    public void deleteMenu(Menu entity) throws Exception;

    public void updateMenu(Menu entity) throws Exception;

    public Menu getMenu(Integer idMenu) throws Exception;

    public List<Menu> findByCriteriaInMenu(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Menu> findPageMenu(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberMenu() throws Exception;

    public List<MenuDTO> getDataMenu() throws Exception;

    public List<MenuHasOptions> getMenuHasOptions() throws Exception;

    public void saveMenuHasOptions(MenuHasOptions entity)
        throws Exception;

    public void deleteMenuHasOptions(MenuHasOptions entity)
        throws Exception;

    public void updateMenuHasOptions(MenuHasOptions entity)
        throws Exception;

    public MenuHasOptions getMenuHasOptions(MenuHasOptions id)
        throws Exception;

    public List<MenuHasOptions> findByCriteriaInMenuHasOptions(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception;

    public List<MenuHasOptions> findPageMenuHasOptions(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberMenuHasOptions() throws Exception;

    public List<MenuHasOptionsDTO> getDataMenuHasOptions()
        throws Exception;
    

}
