package com.liderbs.presentation.businessDelegate;

import com.liderbs.modelo.Academiclevel;
import com.liderbs.modelo.Account;
import com.liderbs.modelo.Autorization;
import com.liderbs.modelo.Autorizationtype;
import com.liderbs.modelo.Category;
import com.liderbs.modelo.Courses;
import com.liderbs.modelo.Day;
import com.liderbs.modelo.Estado;
import com.liderbs.modelo.EstadoId;
import com.liderbs.modelo.Experience;
import com.liderbs.modelo.Identification;
import com.liderbs.modelo.Job;
import com.liderbs.modelo.Level;
import com.liderbs.modelo.Menu;
import com.liderbs.modelo.Options;
import com.liderbs.modelo.Pais;
import com.liderbs.modelo.Place;
import com.liderbs.modelo.Profile;
import com.liderbs.modelo.Schedule;
import com.liderbs.modelo.Trainer;
import com.liderbs.modelo.Users;
import com.liderbs.modelo.UsuariosCategorias;
import com.liderbs.modelo.Vprofile;
import com.liderbs.modelo.control.AccountLogic;
import com.liderbs.modelo.control.IAcademiclevelLogic;
import com.liderbs.modelo.control.IAccountLogic;
import com.liderbs.modelo.control.IAutorizationLogic;
import com.liderbs.modelo.control.IAutorizationtypeLogic;
import com.liderbs.modelo.control.ICategoryLogic;
import com.liderbs.modelo.control.ICoursesLogic;
import com.liderbs.modelo.control.IDayLogic;
import com.liderbs.modelo.control.IEstadoLogic;
import com.liderbs.modelo.control.IExperienceLogic;
import com.liderbs.modelo.control.IIdentificationLogic;
import com.liderbs.modelo.control.IJobLogic;
import com.liderbs.modelo.control.ILevelLogic;
import com.liderbs.modelo.control.IMenuLogic;
import com.liderbs.modelo.control.IOptionsLogic;
import com.liderbs.modelo.control.IPaisLogic;
import com.liderbs.modelo.control.IPlaceLogic;
import com.liderbs.modelo.control.IProfileLogic;
import com.liderbs.modelo.control.IScheduleLogic;
import com.liderbs.modelo.control.ITrainerLogic;
import com.liderbs.modelo.control.IUsersLogic;
import com.liderbs.modelo.control.IUsuariosCategoriasLogic;
import com.liderbs.modelo.control.IVprofileLogic;
import com.liderbs.modelo.control.MenuLogic;
import com.liderbs.modelo.control.OptionsLogic;
import com.liderbs.modelo.control.ProfileLogic;
import com.liderbs.modelo.control.UsersLogic;
import com.liderbs.modelo.dto.AcademiclevelDTO;
import com.liderbs.modelo.dto.AccountDTO;
import com.liderbs.modelo.dto.AutorizationDTO;
import com.liderbs.modelo.dto.AutorizationtypeDTO;
import com.liderbs.modelo.dto.CategoryDTO;
import com.liderbs.modelo.dto.CoursesDTO;
import com.liderbs.modelo.dto.DayDTO;
import com.liderbs.modelo.dto.EstadoDTO;
import com.liderbs.modelo.dto.ExperienceDTO;
import com.liderbs.modelo.dto.IdentificationDTO;
import com.liderbs.modelo.dto.JobDTO;
import com.liderbs.modelo.dto.LevelDTO;
import com.liderbs.modelo.dto.MenuDTO;
import com.liderbs.modelo.dto.OptionsDTO;
import com.liderbs.modelo.dto.PaisDTO;
import com.liderbs.modelo.dto.PlaceDTO;
import com.liderbs.modelo.dto.ProfileDTO;
import com.liderbs.modelo.dto.ScheduleDTO;
import com.liderbs.modelo.dto.TrainerDTO;
import com.liderbs.modelo.dto.UsersDTO;
import com.liderbs.modelo.dto.UsuariosCategoriasDTO;
import com.liderbs.modelo.dto.VprofileDTO;
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
    @Autowired
    private IAutorizationLogic autorizationLogic;
    @Autowired
    private IAutorizationtypeLogic autorizationtypeLogic;
    @Autowired
    private IPlaceLogic placeLogic;
    @Autowired
    private IAcademiclevelLogic academiclevelLogic;
    @Autowired
    private ICoursesLogic coursesLogic;
    @Autowired
    private IEstadoLogic estadoLogic;
    @Autowired
    private IExperienceLogic experienceLogic;
    @Autowired
    private IIdentificationLogic identificationLogic;
    @Autowired
    private IJobLogic jobLogic;
    @Autowired
    private IPaisLogic paisLogic;
    @Autowired
    private ITrainerLogic trainerLogic;
    @Autowired
    private IVprofileLogic vprofileLogic;
    @Autowired
    private ICategoryLogic categoryLogic;
    @Autowired
    private ILevelLogic levelLogic;
    @Autowired
    private IDayLogic dayLogic;
    @Autowired
    private IScheduleLogic scheduleLogic;


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
    
    public List<Autorization> getAutorization() throws Exception {
        return autorizationLogic.getAutorization();
    }

    public void saveAutorization(Autorization entity) throws Exception {
        autorizationLogic.saveAutorization(entity);
    }

    public void deleteAutorization(Autorization entity)
        throws Exception {
        autorizationLogic.deleteAutorization(entity);
    }

    public void updateAutorization(Autorization entity)
        throws Exception {
        autorizationLogic.updateAutorization(entity);
    }

    public Autorization getAutorization(Integer idAutorization)
        throws Exception {
        Autorization autorization = null;

        try {
            autorization = autorizationLogic.getAutorization(idAutorization);
        } catch (Exception e) {
            throw e;
        }

        return autorization;
    }

    public List<Autorization> findByCriteriaInAutorization(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return autorizationLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Autorization> findPageAutorization(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return autorizationLogic.findPageAutorization(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberAutorization() throws Exception {
        return autorizationLogic.findTotalNumberAutorization();
    }

    public List<AutorizationDTO> getDataAutorization()
        throws Exception {
        return autorizationLogic.getDataAutorization();
    }

    public List<Autorizationtype> getAutorizationtype()
        throws Exception {
        return autorizationtypeLogic.getAutorizationtype();
    }

    public void saveAutorizationtype(Autorizationtype entity)
        throws Exception {
        autorizationtypeLogic.saveAutorizationtype(entity);
    }

    public void deleteAutorizationtype(Autorizationtype entity)
        throws Exception {
        autorizationtypeLogic.deleteAutorizationtype(entity);
    }

    public void updateAutorizationtype(Autorizationtype entity)
        throws Exception {
        autorizationtypeLogic.updateAutorizationtype(entity);
    }

    public Autorizationtype getAutorizationtype(Integer idAutorizationtype)
        throws Exception {
        Autorizationtype autorizationtype = null;

        try {
            autorizationtype = autorizationtypeLogic.getAutorizationtype(idAutorizationtype);
        } catch (Exception e) {
            throw e;
        }

        return autorizationtype;
    }

    public List<Autorizationtype> findByCriteriaInAutorizationtype(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return autorizationtypeLogic.findByCriteria(variables,
            variablesBetween, variablesBetweenDates);
    }

    public List<Autorizationtype> findPageAutorizationtype(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        return autorizationtypeLogic.findPageAutorizationtype(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberAutorizationtype() throws Exception {
        return autorizationtypeLogic.findTotalNumberAutorizationtype();
    }

    public List<AutorizationtypeDTO> getDataAutorizationtype()
        throws Exception {
        return autorizationtypeLogic.getDataAutorizationtype();
    }

    public List<Place> getPlace() throws Exception {
        return placeLogic.getPlace();
    }

    public void savePlace(Place entity) throws Exception {
        placeLogic.savePlace(entity);
    }

    public void deletePlace(Place entity) throws Exception {
        placeLogic.deletePlace(entity);
    }

    public void updatePlace(Place entity) throws Exception {
        placeLogic.updatePlace(entity);
    }

    public Place getPlace(Integer idPlace) throws Exception {
        Place place = null;

        try {
            place = placeLogic.getPlace(idPlace);
        } catch (Exception e) {
            throw e;
        }

        return place;
    }

    public List<Place> findByCriteriaInPlace(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return placeLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Place> findPagePlace(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return placeLogic.findPagePlace(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberPlace() throws Exception {
        return placeLogic.findTotalNumberPlace();
    }

    public List<PlaceDTO> getDataPlace() throws Exception {
        return placeLogic.getDataPlace();
    }
    
    public List<Academiclevel> getAcademiclevel() throws Exception {
        return academiclevelLogic.getAcademiclevel();
    }

    public void saveAcademiclevel(Academiclevel entity)
        throws Exception {
        academiclevelLogic.saveAcademiclevel(entity);
    }

    public void deleteAcademiclevel(Academiclevel entity)
        throws Exception {
        academiclevelLogic.deleteAcademiclevel(entity);
    }

    public void updateAcademiclevel(Academiclevel entity)
        throws Exception {
        academiclevelLogic.updateAcademiclevel(entity);
    }

    public Academiclevel getAcademiclevel(Integer idacademiclevel)
        throws Exception {
        Academiclevel academiclevel = null;

        try {
            academiclevel = academiclevelLogic.getAcademiclevel(idacademiclevel);
        } catch (Exception e) {
            throw e;
        }

        return academiclevel;
    }

    public List<Academiclevel> findByCriteriaInAcademiclevel(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return academiclevelLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Academiclevel> findPageAcademiclevel(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return academiclevelLogic.findPageAcademiclevel(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberAcademiclevel() throws Exception {
        return academiclevelLogic.findTotalNumberAcademiclevel();
    }

    public List<AcademiclevelDTO> getDataAcademiclevel()
        throws Exception {
        return academiclevelLogic.getDataAcademiclevel();
    }

    public List<Courses> getCourses() throws Exception {
        return coursesLogic.getCourses();
    }

    public void saveCourses(Courses entity) throws Exception {
        coursesLogic.saveCourses(entity);
    }

    public void deleteCourses(Courses entity) throws Exception {
        coursesLogic.deleteCourses(entity);
    }

    public void updateCourses(Courses entity) throws Exception {
        coursesLogic.updateCourses(entity);
    }

    public Courses getCourses(Integer idcourses) throws Exception {
        Courses courses = null;

        try {
            courses = coursesLogic.getCourses(idcourses);
        } catch (Exception e) {
            throw e;
        }

        return courses;
    }

    public List<Courses> findByCriteriaInCourses(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return coursesLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Courses> findPageCourses(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return coursesLogic.findPageCourses(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberCourses() throws Exception {
        return coursesLogic.findTotalNumberCourses();
    }

    public List<CoursesDTO> getDataCourses() throws Exception {
        return coursesLogic.getDataCourses();
    }

    public List<Estado> getEstado() throws Exception {
        return estadoLogic.getEstado();
    }

    public void saveEstado(Estado entity) throws Exception {
        estadoLogic.saveEstado(entity);
    }

    public void deleteEstado(Estado entity) throws Exception {
        estadoLogic.deleteEstado(entity);
    }

    public void updateEstado(Estado entity) throws Exception {
        estadoLogic.updateEstado(entity);
    }

    public Estado getEstado(EstadoId id) throws Exception {
        Estado estado = null;

        try {
            estado = estadoLogic.getEstado(id);
        } catch (Exception e) {
            throw e;
        }

        return estado;
    }

    public List<Estado> findByCriteriaInEstado(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return estadoLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Estado> findPageEstado(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return estadoLogic.findPageEstado(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberEstado() throws Exception {
        return estadoLogic.findTotalNumberEstado();
    }

    public List<EstadoDTO> getDataEstado() throws Exception {
        return estadoLogic.getDataEstado();
    }

    public List<Experience> getExperience() throws Exception {
        return experienceLogic.getExperience();
    }

    public void saveExperience(Experience entity) throws Exception {
        experienceLogic.saveExperience(entity);
    }

    public void deleteExperience(Experience entity) throws Exception {
        experienceLogic.deleteExperience(entity);
    }

    public void updateExperience(Experience entity) throws Exception {
        experienceLogic.updateExperience(entity);
    }

    public Experience getExperience(Integer idexperience)
        throws Exception {
        Experience experience = null;

        try {
            experience = experienceLogic.getExperience(idexperience);
        } catch (Exception e) {
            throw e;
        }

        return experience;
    }

    public List<Experience> findByCriteriaInExperience(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return experienceLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Experience> findPageExperience(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return experienceLogic.findPageExperience(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberExperience() throws Exception {
        return experienceLogic.findTotalNumberExperience();
    }

    public List<ExperienceDTO> getDataExperience() throws Exception {
        return experienceLogic.getDataExperience();
    }

    public List<Identification> getIdentification() throws Exception {
        return identificationLogic.getIdentification();
    }

    public void saveIdentification(Identification entity)
        throws Exception {
        identificationLogic.saveIdentification(entity);
    }

    public void deleteIdentification(Identification entity)
        throws Exception {
        identificationLogic.deleteIdentification(entity);
    }

    public void updateIdentification(Identification entity)
        throws Exception {
        identificationLogic.updateIdentification(entity);
    }

    public Identification getIdentification(Integer ididentification)
        throws Exception {
        Identification identification = null;

        try {
            identification = identificationLogic.getIdentification(ididentification);
        } catch (Exception e) {
            throw e;
        }

        return identification;
    }

    public List<Identification> findByCriteriaInIdentification(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return identificationLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Identification> findPageIdentification(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return identificationLogic.findPageIdentification(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberIdentification() throws Exception {
        return identificationLogic.findTotalNumberIdentification();
    }

    public List<IdentificationDTO> getDataIdentification()
        throws Exception {
        return identificationLogic.getDataIdentification();
    }

    public List<Job> getJob() throws Exception {
        return jobLogic.getJob();
    }

    public void saveJob(Job entity) throws Exception {
        jobLogic.saveJob(entity);
    }

    public void deleteJob(Job entity) throws Exception {
        jobLogic.deleteJob(entity);
    }

    public void updateJob(Job entity) throws Exception {
        jobLogic.updateJob(entity);
    }

    public Job getJob(Integer idjob) throws Exception {
        Job job = null;

        try {
            job = jobLogic.getJob(idjob);
        } catch (Exception e) {
            throw e;
        }

        return job;
    }

    public List<Job> findByCriteriaInJob(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return jobLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Job> findPageJob(String sortColumnName, boolean sortAscending,
        int startRow, int maxResults) throws Exception {
        return jobLogic.findPageJob(sortColumnName, sortAscending, startRow,
            maxResults);
    }

    public Long findTotalNumberJob() throws Exception {
        return jobLogic.findTotalNumberJob();
    }

    public List<JobDTO> getDataJob() throws Exception {
        return jobLogic.getDataJob();
    }

    public List<Pais> getPais() throws Exception {
        return paisLogic.getPais();
    }

    public void savePais(Pais entity) throws Exception {
        paisLogic.savePais(entity);
    }

    public void deletePais(Pais entity) throws Exception {
        paisLogic.deletePais(entity);
    }

    public void updatePais(Pais entity) throws Exception {
        paisLogic.updatePais(entity);
    }

    public Pais getPais(Integer idpais) throws Exception {
        Pais pais = null;

        try {
            pais = paisLogic.getPais(idpais);
        } catch (Exception e) {
            throw e;
        }

        return pais;
    }

    public List<Pais> findByCriteriaInPais(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return paisLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Pais> findPagePais(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return paisLogic.findPagePais(sortColumnName, sortAscending, startRow,
            maxResults);
    }

    public Long findTotalNumberPais() throws Exception {
        return paisLogic.findTotalNumberPais();
    }

    public List<PaisDTO> getDataPais() throws Exception {
        return paisLogic.getDataPais();
    }

    public List<Trainer> getTrainer() throws Exception {
        return trainerLogic.getTrainer();
    }

    public void saveTrainer(Trainer entity) throws Exception {
        trainerLogic.saveTrainer(entity);
    }

    public void deleteTrainer(Trainer entity) throws Exception {
        trainerLogic.deleteTrainer(entity);
    }

    public void updateTrainer(Trainer entity) throws Exception {
        trainerLogic.updateTrainer(entity);
    }

    public Trainer getTrainer(Integer idtrainer) throws Exception {
        Trainer trainer = null;

        try {
            trainer = trainerLogic.getTrainer(idtrainer);
        } catch (Exception e) {
            throw e;
        }

        return trainer;
    }

    public List<Trainer> findByCriteriaInTrainer(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return trainerLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Trainer> findPageTrainer(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return trainerLogic.findPageTrainer(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberTrainer() throws Exception {
        return trainerLogic.findTotalNumberTrainer();
    }

    public List<TrainerDTO> getDataTrainer() throws Exception {
        return trainerLogic.getDataTrainer();
    }

    public List<Vprofile> getVprofile() throws Exception {
        return vprofileLogic.getVprofile();
    }

    public void saveVprofile(Vprofile entity) throws Exception {
        vprofileLogic.saveVprofile(entity);
    }

    public void deleteVprofile(Vprofile entity) throws Exception {
        vprofileLogic.deleteVprofile(entity);
    }

    public void updateVprofile(Vprofile entity) throws Exception {
        vprofileLogic.updateVprofile(entity);
    }

    public Vprofile getVprofile(Integer idvprofile) throws Exception {
        Vprofile vprofile = null;

        try {
            vprofile = vprofileLogic.getVprofile(idvprofile);
        } catch (Exception e) {
            throw e;
        }

        return vprofile;
    }

    public List<Vprofile> findByCriteriaInVprofile(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return vprofileLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Vprofile> findPageVprofile(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return vprofileLogic.findPageVprofile(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberVprofile() throws Exception {
        return vprofileLogic.findTotalNumberVprofile();
    }

    public List<VprofileDTO> getDataVprofile() throws Exception {
        return vprofileLogic.getDataVprofile();
    }
    
    private IUsuariosCategoriasLogic usuariosCategoriasLogic;

    public List<Category> getCategory() throws Exception {
        return categoryLogic.getCategory();
    }

    public void saveCategory(Category entity) throws Exception {
        categoryLogic.saveCategory(entity);
    }

    public void deleteCategory(Category entity) throws Exception {
        categoryLogic.deleteCategory(entity);
    }

    public void updateCategory(Category entity) throws Exception {
        categoryLogic.updateCategory(entity);
    }

    public Category getCategory(Integer idcategory) throws Exception {
        Category category = null;

        try {
            category = categoryLogic.getCategory(idcategory);
        } catch (Exception e) {
            throw e;
        }

        return category;
    }

    public List<Category> findByCriteriaInCategory(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return categoryLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Category> findPageCategory(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return categoryLogic.findPageCategory(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberCategory() throws Exception {
        return categoryLogic.findTotalNumberCategory();
    }

    public List<CategoryDTO> getDataCategory() throws Exception {
        return categoryLogic.getDataCategory();
    }
    
    public List<Level> getLevel() throws Exception {
        return levelLogic.getLevel();
    }

    public void saveLevel(Level entity) throws Exception {
        levelLogic.saveLevel(entity);
    }

    public void deleteLevel(Level entity) throws Exception {
        levelLogic.deleteLevel(entity);
    }

    public void updateLevel(Level entity) throws Exception {
        levelLogic.updateLevel(entity);
    }

    public Level getLevel(Integer idlevel) throws Exception {
        Level level = null;

        try {
            level = levelLogic.getLevel(idlevel);
        } catch (Exception e) {
            throw e;
        }

        return level;
    }

    public List<Level> findByCriteriaInLevel(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return levelLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Level> findPageLevel(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return levelLogic.findPageLevel(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberLevel() throws Exception {
        return levelLogic.findTotalNumberLevel();
    }

    public List<LevelDTO> getDataLevel() throws Exception {
        return levelLogic.getDataLevel();
    }

    public List<UsuariosCategorias> getUsuariosCategorias()
        throws Exception {
        return usuariosCategoriasLogic.getUsuariosCategorias();
    }

    public void saveUsuariosCategorias(UsuariosCategorias entity)
        throws Exception {
        usuariosCategoriasLogic.saveUsuariosCategorias(entity);
    }

    public void deleteUsuariosCategorias(UsuariosCategorias entity)
        throws Exception {
        usuariosCategoriasLogic.deleteUsuariosCategorias(entity);
    }

    public void updateUsuariosCategorias(UsuariosCategorias entity)
        throws Exception {
        usuariosCategoriasLogic.updateUsuariosCategorias(entity);
    }

    public UsuariosCategorias getUsuariosCategorias(
        Integer idusuariosCategorias) throws Exception {
        UsuariosCategorias usuariosCategorias = null;

        try {
            usuariosCategorias = usuariosCategoriasLogic.getUsuariosCategorias(idusuariosCategorias);
        } catch (Exception e) {
            throw e;
        }

        return usuariosCategorias;
    }

    public List<UsuariosCategorias> findByCriteriaInUsuariosCategorias(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return usuariosCategoriasLogic.findByCriteria(variables,
            variablesBetween, variablesBetweenDates);
    }

    public List<UsuariosCategorias> findPageUsuariosCategorias(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        return usuariosCategoriasLogic.findPageUsuariosCategorias(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberUsuariosCategorias() throws Exception {
        return usuariosCategoriasLogic.findTotalNumberUsuariosCategorias();
    }

    public List<UsuariosCategoriasDTO> getDataUsuariosCategorias()
        throws Exception {
        return usuariosCategoriasLogic.getDataUsuariosCategorias();
    }
    
    public List<Day> getDay() throws Exception {
        return dayLogic.getDay();
    }

    public void saveDay(Day entity) throws Exception {
        dayLogic.saveDay(entity);
    }

    public void deleteDay(Day entity) throws Exception {
        dayLogic.deleteDay(entity);
    }

    public void updateDay(Day entity) throws Exception {
        dayLogic.updateDay(entity);
    }

    public Day getDay(Integer idday) throws Exception {
        Day day = null;

        try {
            day = dayLogic.getDay(idday);
        } catch (Exception e) {
            throw e;
        }

        return day;
    }

    public List<Day> findByCriteriaInDay(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return dayLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Day> findPageDay(String sortColumnName, boolean sortAscending,
        int startRow, int maxResults) throws Exception {
        return dayLogic.findPageDay(sortColumnName, sortAscending, startRow,
            maxResults);
    }

    public Long findTotalNumberDay() throws Exception {
        return dayLogic.findTotalNumberDay();
    }

    public List<DayDTO> getDataDay() throws Exception {
        return dayLogic.getDataDay();
    }

    public List<Schedule> getSchedule() throws Exception {
        return scheduleLogic.getSchedule();
    }

    public void saveSchedule(Schedule entity) throws Exception {
        scheduleLogic.saveSchedule(entity);
    }

    public void deleteSchedule(Schedule entity) throws Exception {
        scheduleLogic.deleteSchedule(entity);
    }

    public void updateSchedule(Schedule entity) throws Exception {
        scheduleLogic.updateSchedule(entity);
    }

    public Schedule getSchedule(Integer idschedule) throws Exception {
        Schedule schedule = null;

        try {
            schedule = scheduleLogic.getSchedule(idschedule);
        } catch (Exception e) {
            throw e;
        }

        return schedule;
    }

    public List<Schedule> findByCriteriaInSchedule(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return scheduleLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Schedule> findPageSchedule(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return scheduleLogic.findPageSchedule(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberSchedule() throws Exception {
        return scheduleLogic.findTotalNumberSchedule();
    }

    public List<ScheduleDTO> getDataSchedule() throws Exception {
        return scheduleLogic.getDataSchedule();
    }
}
