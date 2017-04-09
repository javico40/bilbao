package com.liderbs.presentation.businessDelegate;

import com.liderbs.modelo.Account;
import com.liderbs.modelo.Menu;
import com.liderbs.modelo.Options;
import com.liderbs.modelo.Profile;
import com.liderbs.modelo.Users;
import com.liderbs.modelo.control.AccountLogic;
import com.liderbs.modelo.control.IAccountLogic;
import com.liderbs.modelo.control.IMenuLogic;
import com.liderbs.modelo.control.IOptionsLogic;
import com.liderbs.modelo.control.IProfileLogic;
import com.liderbs.modelo.control.IUsersLogic;
import com.liderbs.modelo.control.MenuLogic;
import com.liderbs.modelo.control.OptionsLogic;
import com.liderbs.modelo.control.ProfileLogic;
import com.liderbs.modelo.control.UsersLogic;
import com.liderbs.modelo.dto.AccountDTO;
import com.liderbs.modelo.dto.MenuDTO;
import com.liderbs.modelo.dto.OptionsDTO;
import com.liderbs.modelo.dto.ProfileDTO;
import com.liderbs.modelo.dto.UsersDTO;

import com.liderbs.presentation.businessDelegate.IBusinessDelegatorView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import java.sql.*;

import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* Use a Business Delegate to reduce coupling between presentation-tier clients and business services.
* The Business Delegate hides the underlying implementation details of the business service, such as lookup and access details of the EJB architecture.
*
* The Business Delegate acts as a client-side business abstraction; it provides an abstraction for, and thus hides,
* the implementation of the business services. Using a Business Delegate reduces the coupling between presentation-tier clients and
* the system's business services. Depending on the implementation strategy, the Business Delegate may shield clients from possible
* volatility in the implementation of the business service API. Potentially, this reduces the number of changes that must be made to the
* presentation-tier client code when the business service API or its underlying implementation changes.
*
* However, interface methods in the Business Delegate may still require modification if the underlying business service API changes.
* Admittedly, though, it is more likely that changes will be made to the business service rather than to the Business Delegate.
*
* Often, developers are skeptical when a design goal such as abstracting the business layer causes additional upfront work in return
* for future gains. However, using this pattern or its strategies results in only a small amount of additional upfront work and provides
* considerable benefits. The main benefit is hiding the details of the underlying service. For example, the client can become transparent
* to naming and lookup services. The Business Delegate also handles the exceptions from the business services, such as java.rmi.Remote
* exceptions, Java Messages Service (JMS) exceptions and so on. The Business Delegate may intercept such service level exceptions and
* generate application level exceptions instead. Application level exceptions are easier to handle by the clients, and may be user friendly.
* The Business Delegate may also transparently perform any retry or recovery operations necessary in the event of a service failure without
* exposing the client to the problem until it is determined that the problem is not resolvable. These gains present a compelling reason to
* use the pattern.
*
* Another benefit is that the delegate may cache results and references to remote business services. Caching can significantly improve performance,
* because it limits unnecessary and potentially costly round trips over the network.
*
* A Business Delegate uses a component called the Lookup Service. The Lookup Service is responsible for hiding the underlying implementation
* details of the business service lookup code. The Lookup Service may be written as part of the Delegate, but we recommend that it be
* implemented as a separate component, as outlined in the Service Locator pattern (See "Service Locator" on page 368.)
*
* When the Business Delegate is used with a Session Facade, typically there is a one-to-one relationship between the two.
* This one-to-one relationship exists because logic that might have been encapsulated in a Business Delegate relating to its interaction
* with multiple business services (creating a one-to-many relationship) will often be factored back into a Session Facade.
*
* Finally, it should be noted that this pattern could be used to reduce coupling between other tiers, not simply the presentation and the
* business tiers.
*
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
@Scope("singleton")
@Service("BusinessDelegatorView")
public class BusinessDelegatorView implements IBusinessDelegatorView {
    private static final Logger log = LoggerFactory.getLogger(BusinessDelegatorView.class);
    @Autowired
    private IAccountLogic accountLogic;
    @Autowired
    private IMenuLogic menuLogic;
    @Autowired
    private IOptionsLogic optionsLogic;
    @Autowired
    private IProfileLogic profileLogic;
    @Autowired
    private IUsersLogic usersLogic;

    public List<Account> getAccount() throws Exception {
        return accountLogic.getAccount();
    }

    public void saveAccount(Account entity) throws Exception {
        accountLogic.saveAccount(entity);
    }

    public void deleteAccount(Account entity) throws Exception {
        accountLogic.deleteAccount(entity);
    }

    public void updateAccount(Account entity) throws Exception {
        accountLogic.updateAccount(entity);
    }

    public Account getAccount(Integer idAccount) throws Exception {
        Account account = null;

        try {
            account = accountLogic.getAccount(idAccount);
        } catch (Exception e) {
            throw e;
        }

        return account;
    }

    public List<Account> findByCriteriaInAccount(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return accountLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Account> findPageAccount(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return accountLogic.findPageAccount(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberAccount() throws Exception {
        return accountLogic.findTotalNumberAccount();
    }

    public List<AccountDTO> getDataAccount() throws Exception {
        return accountLogic.getDataAccount();
    }

    public List<Menu> getMenu() throws Exception {
        return menuLogic.getMenu();
    }

    public void saveMenu(Menu entity) throws Exception {
        menuLogic.saveMenu(entity);
    }

    public void deleteMenu(Menu entity) throws Exception {
        menuLogic.deleteMenu(entity);
    }

    public void updateMenu(Menu entity) throws Exception {
        menuLogic.updateMenu(entity);
    }

    public Menu getMenu(Integer idMenu) throws Exception {
        Menu menu = null;

        try {
            menu = menuLogic.getMenu(idMenu);
        } catch (Exception e) {
            throw e;
        }

        return menu;
    }

    public List<Menu> findByCriteriaInMenu(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return menuLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Menu> findPageMenu(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return menuLogic.findPageMenu(sortColumnName, sortAscending, startRow,
            maxResults);
    }

    public Long findTotalNumberMenu() throws Exception {
        return menuLogic.findTotalNumberMenu();
    }

    public List<MenuDTO> getDataMenu() throws Exception {
        return menuLogic.getDataMenu();
    }

    public List<Options> getOptions() throws Exception {
        return optionsLogic.getOptions();
    }

    public void saveOptions(Options entity) throws Exception {
        optionsLogic.saveOptions(entity);
    }

    public void deleteOptions(Options entity) throws Exception {
        optionsLogic.deleteOptions(entity);
    }

    public void updateOptions(Options entity) throws Exception {
        optionsLogic.updateOptions(entity);
    }

    public Options getOptions(Integer idoptions) throws Exception {
        Options options = null;

        try {
            options = optionsLogic.getOptions(idoptions);
        } catch (Exception e) {
            throw e;
        }

        return options;
    }

    public List<Options> findByCriteriaInOptions(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return optionsLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Options> findPageOptions(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return optionsLogic.findPageOptions(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberOptions() throws Exception {
        return optionsLogic.findTotalNumberOptions();
    }

    public List<OptionsDTO> getDataOptions() throws Exception {
        return optionsLogic.getDataOptions();
    }

    public List<Profile> getProfile() throws Exception {
        return profileLogic.getProfile();
    }

    public void saveProfile(Profile entity) throws Exception {
        profileLogic.saveProfile(entity);
    }

    public void deleteProfile(Profile entity) throws Exception {
        profileLogic.deleteProfile(entity);
    }

    public void updateProfile(Profile entity) throws Exception {
        profileLogic.updateProfile(entity);
    }

    public Profile getProfile(Integer idprofile) throws Exception {
        Profile profile = null;

        try {
            profile = profileLogic.getProfile(idprofile);
        } catch (Exception e) {
            throw e;
        }

        return profile;
    }

    public List<Profile> findByCriteriaInProfile(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return profileLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Profile> findPageProfile(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return profileLogic.findPageProfile(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberProfile() throws Exception {
        return profileLogic.findTotalNumberProfile();
    }

    public List<ProfileDTO> getDataProfile() throws Exception {
        return profileLogic.getDataProfile();
    }

    public List<Users> getUsers() throws Exception {
        return usersLogic.getUsers();
    }

    public void saveUsers(Users entity) throws Exception {
        usersLogic.saveUsers(entity);
    }

    public void deleteUsers(Users entity) throws Exception {
        usersLogic.deleteUsers(entity);
    }

    public void updateUsers(Users entity) throws Exception {
        usersLogic.updateUsers(entity);
    }

    public Users getUsers(Integer idusers) throws Exception {
        Users users = null;

        try {
            users = usersLogic.getUsers(idusers);
        } catch (Exception e) {
            throw e;
        }

        return users;
    }

    public List<Users> findByCriteriaInUsers(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return usersLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Users> findPageUsers(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return usersLogic.findPageUsers(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberUsers() throws Exception {
        return usersLogic.findTotalNumberUsers();
    }

    public List<UsersDTO> getDataUsers() throws Exception {
        return usersLogic.getDataUsers();
    }
}
