package com.liderbs.presentation.businessDelegate;

import com.liderbs.modelo.Academiclevel;
import com.liderbs.modelo.Account;
import com.liderbs.modelo.Autorization;
import com.liderbs.modelo.Autorizationtype;
import com.liderbs.modelo.Category;
import com.liderbs.modelo.Cities;
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
import com.liderbs.modelo.Placeservices;
import com.liderbs.modelo.Placetype;
import com.liderbs.modelo.Profile;
import com.liderbs.modelo.Schedule;
import com.liderbs.modelo.Skill;
import com.liderbs.modelo.Timetable;
import com.liderbs.modelo.Trainer;
import com.liderbs.modelo.Users;
import com.liderbs.modelo.UsuariosCategorias;
import com.liderbs.modelo.Vprofile;
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
import com.liderbs.modelo.dto.AcademiclevelDTO;
import com.liderbs.modelo.dto.AccountDTO;
import com.liderbs.modelo.dto.AutorizationDTO;
import com.liderbs.modelo.dto.AutorizationtypeDTO;
import com.liderbs.modelo.dto.CategoryDTO;
import com.liderbs.modelo.dto.CitiesDTO;
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
import com.liderbs.modelo.dto.PlaceservicesDTO;
import com.liderbs.modelo.dto.PlacetypeDTO;
import com.liderbs.modelo.dto.ProfileDTO;
import com.liderbs.modelo.dto.ScheduleDTO;
import com.liderbs.modelo.dto.SkillDTO;
import com.liderbs.modelo.dto.TimetableDTO;
import com.liderbs.modelo.dto.TrainerDTO;
import com.liderbs.modelo.dto.UsersDTO;
import com.liderbs.modelo.dto.UsuariosCategoriasDTO;
import com.liderbs.modelo.dto.VprofileDTO;

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
	
    public List<Account> getAccount() throws Exception;

    public void saveAccount(Account entity) throws Exception;

    public void deleteAccount(Account entity) throws Exception;

    public void updateAccount(Account entity) throws Exception;

    public Account getAccount(Integer idAccount) throws Exception;

    public List<Account> findByCriteriaInAccount(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Account> findPageAccount(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberAccount() throws Exception;

    public List<AccountDTO> getDataAccount() throws Exception;

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
    
    public List<Autorization> getAutorization() throws Exception;

    public void saveAutorization(Autorization entity) throws Exception;

    public void deleteAutorization(Autorization entity)
        throws Exception;

    public void updateAutorization(Autorization entity)
        throws Exception;

    public Autorization getAutorization(Integer idAutorization)
        throws Exception;

    public List<Autorization> findByCriteriaInAutorization(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Autorization> findPageAutorization(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberAutorization() throws Exception;

    public List<AutorizationDTO> getDataAutorization()
        throws Exception;

    public List<Autorizationtype> getAutorizationtype()
        throws Exception;

    public void saveAutorizationtype(Autorizationtype entity)
        throws Exception;

    public void deleteAutorizationtype(Autorizationtype entity)
        throws Exception;

    public void updateAutorizationtype(Autorizationtype entity)
        throws Exception;

    public Autorizationtype getAutorizationtype(Integer idAutorizationtype)
        throws Exception;

    public List<Autorizationtype> findByCriteriaInAutorizationtype(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception;

    public List<Autorizationtype> findPageAutorizationtype(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberAutorizationtype() throws Exception;

    public List<AutorizationtypeDTO> getDataAutorizationtype()
        throws Exception;

    public List<Place> getPlace() throws Exception;

    public void savePlace(Place entity) throws Exception;

    public void deletePlace(Place entity) throws Exception;

    public void updatePlace(Place entity) throws Exception;

    public Place getPlace(Integer idPlace) throws Exception;

    public List<Place> findByCriteriaInPlace(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Place> findPagePlace(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberPlace() throws Exception;

    public List<PlaceDTO> getDataPlace() throws Exception;
    
    public List<Academiclevel> getAcademiclevel() throws Exception;

    public void saveAcademiclevel(Academiclevel entity)
        throws Exception;

    public void deleteAcademiclevel(Academiclevel entity)
        throws Exception;

    public void updateAcademiclevel(Academiclevel entity)
        throws Exception;

    public Academiclevel getAcademiclevel(Integer idacademiclevel)
        throws Exception;

    public List<Academiclevel> findByCriteriaInAcademiclevel(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception;

    public List<Academiclevel> findPageAcademiclevel(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberAcademiclevel() throws Exception;

    public List<AcademiclevelDTO> getDataAcademiclevel()
        throws Exception;

    public List<Courses> getCourses() throws Exception;

    public void saveCourses(Courses entity) throws Exception;

    public void deleteCourses(Courses entity) throws Exception;

    public void updateCourses(Courses entity) throws Exception;

    public Courses getCourses(Integer idcourses) throws Exception;

    public List<Courses> findByCriteriaInCourses(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Courses> findPageCourses(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberCourses() throws Exception;

    public List<CoursesDTO> getDataCourses() throws Exception;
    
    public List<Estado> getEstado() throws Exception;

    public void saveEstado(Estado entity) throws Exception;

    public void deleteEstado(Estado entity) throws Exception;

    public void updateEstado(Estado entity) throws Exception;

    public Estado getEstado(Integer idestado) throws Exception;

    public List<Estado> findByCriteriaInEstado(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Estado> findPageEstado(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberEstado() throws Exception;

    public List<EstadoDTO> getDataEstado() throws Exception;

    public List<Experience> getExperience() throws Exception;

    public void saveExperience(Experience entity) throws Exception;

    public void deleteExperience(Experience entity) throws Exception;

    public void updateExperience(Experience entity) throws Exception;

    public Experience getExperience(Integer idexperience)
        throws Exception;

    public List<Experience> findByCriteriaInExperience(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Experience> findPageExperience(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberExperience() throws Exception;

    public List<ExperienceDTO> getDataExperience() throws Exception;

    public List<Identification> getIdentification() throws Exception;

    public void saveIdentification(Identification entity)
        throws Exception;

    public void deleteIdentification(Identification entity)
        throws Exception;

    public void updateIdentification(Identification entity)
        throws Exception;

    public Identification getIdentification(Integer ididentification)
        throws Exception;

    public List<Identification> findByCriteriaInIdentification(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception;

    public List<Identification> findPageIdentification(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberIdentification() throws Exception;

    public List<IdentificationDTO> getDataIdentification()
        throws Exception;

    public List<Job> getJob() throws Exception;

    public void saveJob(Job entity) throws Exception;

    public void deleteJob(Job entity) throws Exception;

    public void updateJob(Job entity) throws Exception;

    public Job getJob(Integer idjob) throws Exception;

    public List<Job> findByCriteriaInJob(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Job> findPageJob(String sortColumnName, boolean sortAscending,
        int startRow, int maxResults) throws Exception;

    public Long findTotalNumberJob() throws Exception;

    public List<JobDTO> getDataJob() throws Exception;

    public List<Pais> getPais() throws Exception;

    public void savePais(Pais entity) throws Exception;

    public void deletePais(Pais entity) throws Exception;

    public void updatePais(Pais entity) throws Exception;

    public Pais getPais(Integer idpais) throws Exception;

    public List<Pais> findByCriteriaInPais(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Pais> findPagePais(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberPais() throws Exception;

    public List<PaisDTO> getDataPais() throws Exception;

    public List<Trainer> getTrainer() throws Exception;

    public void saveTrainer(Trainer entity) throws Exception;

    public void deleteTrainer(Trainer entity) throws Exception;

    public void updateTrainer(Trainer entity) throws Exception;

    public Trainer getTrainer(Integer idtrainer) throws Exception;

    public List<Trainer> findByCriteriaInTrainer(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Trainer> findPageTrainer(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberTrainer() throws Exception;

    public List<TrainerDTO> getDataTrainer() throws Exception;

    public List<Vprofile> getVprofile() throws Exception;

    public void saveVprofile(Vprofile entity) throws Exception;

    public void deleteVprofile(Vprofile entity) throws Exception;

    public void updateVprofile(Vprofile entity) throws Exception;

    public Vprofile getVprofile(Integer idvprofile) throws Exception;

    public List<Vprofile> findByCriteriaInVprofile(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Vprofile> findPageVprofile(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberVprofile() throws Exception;

    public List<VprofileDTO> getDataVprofile() throws Exception;
    
    public List<Category> getCategory() throws Exception;

    public void saveCategory(Category entity) throws Exception;

    public void deleteCategory(Category entity) throws Exception;

    public void updateCategory(Category entity) throws Exception;

    public Category getCategory(Integer idcategory) throws Exception;

    public List<Category> findByCriteriaInCategory(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Category> findPageCategory(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberCategory() throws Exception;

    public List<CategoryDTO> getDataCategory() throws Exception;

    public List<Level> getLevel() throws Exception;

    public void saveLevel(Level entity) throws Exception;

    public void deleteLevel(Level entity) throws Exception;

    public void updateLevel(Level entity) throws Exception;

    public Level getLevel(Integer idlevel) throws Exception;

    public List<Level> findByCriteriaInLevel(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Level> findPageLevel(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberLevel() throws Exception;

    public List<LevelDTO> getDataLevel() throws Exception;

    public List<UsuariosCategorias> getUsuariosCategorias()
        throws Exception;

    public void saveUsuariosCategorias(UsuariosCategorias entity)
        throws Exception;

    public void deleteUsuariosCategorias(UsuariosCategorias entity)
        throws Exception;

    public void updateUsuariosCategorias(UsuariosCategorias entity)
        throws Exception;

    public UsuariosCategorias getUsuariosCategorias(
        Integer idusuariosCategorias) throws Exception;

    public List<UsuariosCategorias> findByCriteriaInUsuariosCategorias(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception;

    public List<UsuariosCategorias> findPageUsuariosCategorias(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberUsuariosCategorias() throws Exception;

    public List<UsuariosCategoriasDTO> getDataUsuariosCategorias()
        throws Exception;
    
    public List<Day> getDay() throws Exception;

    public void saveDay(Day entity) throws Exception;

    public void deleteDay(Day entity) throws Exception;

    public void updateDay(Day entity) throws Exception;

    public Day getDay(Integer idday) throws Exception;

    public List<Day> findByCriteriaInDay(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Day> findPageDay(String sortColumnName, boolean sortAscending,
        int startRow, int maxResults) throws Exception;

    public Long findTotalNumberDay() throws Exception;

    public List<DayDTO> getDataDay() throws Exception;

    public List<Schedule> getSchedule() throws Exception;

    public void saveSchedule(Schedule entity) throws Exception;

    public void deleteSchedule(Schedule entity) throws Exception;

    public void updateSchedule(Schedule entity) throws Exception;

    public Schedule getSchedule(Integer idschedule) throws Exception;

    public List<Schedule> findByCriteriaInSchedule(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Schedule> findPageSchedule(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberSchedule() throws Exception;

    public List<ScheduleDTO> getDataSchedule() throws Exception;
    
    public List<Placeservices> getPlaceservices() throws Exception;

    public void savePlaceservices(Placeservices entity)
        throws Exception;

    public void deletePlaceservices(Placeservices entity)
        throws Exception;

    public void updatePlaceservices(Placeservices entity)
        throws Exception;

    public Placeservices getPlaceservices(Integer idplaceservices)
        throws Exception;

    public List<Placeservices> findByCriteriaInPlaceservices(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception;

    public List<Placeservices> findPagePlaceservices(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberPlaceservices() throws Exception;

    public List<PlaceservicesDTO> getDataPlaceservices()
        throws Exception;

    public List<Placetype> getPlacetype() throws Exception;

    public void savePlacetype(Placetype entity) throws Exception;

    public void deletePlacetype(Placetype entity) throws Exception;

    public void updatePlacetype(Placetype entity) throws Exception;

    public Placetype getPlacetype(Integer idplacetype)
        throws Exception;

    public List<Placetype> findByCriteriaInPlacetype(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Placetype> findPagePlacetype(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberPlacetype() throws Exception;

    public List<PlacetypeDTO> getDataPlacetype() throws Exception;
    
    public List<Timetable> getTimetable() throws Exception;

    public void saveTimetable(Timetable entity) throws Exception;

    public void deleteTimetable(Timetable entity) throws Exception;

    public void updateTimetable(Timetable entity) throws Exception;

    public Timetable getTimetable(Integer idtimetable)
        throws Exception;

    public List<Timetable> findByCriteriaInTimetable(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Timetable> findPageTimetable(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberTimetable() throws Exception;

    public List<TimetableDTO> getDataTimetable() throws Exception;
    
    public List<Cities> getCities() throws Exception;

    public void saveCities(Cities entity) throws Exception;

    public void deleteCities(Cities entity) throws Exception;

    public void updateCities(Cities entity) throws Exception;

    public Cities getCities(Integer idcities) throws Exception;

    public List<Cities> findByCriteriaInCities(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Cities> findPageCities(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberCities() throws Exception;

    public List<CitiesDTO> getDataCities() throws Exception;
    
    public List<Skill> getSkill() throws Exception;

    public void saveSkill(Skill entity) throws Exception;

    public void deleteSkill(Skill entity) throws Exception;

    public void updateSkill(Skill entity) throws Exception;

    public Skill getSkill(Integer idskill) throws Exception;

    public List<Skill> findByCriteriaInSkill(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Skill> findPageSkill(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberSkill() throws Exception;

    public List<SkillDTO> getDataSkill() throws Exception;


}
