package com.liderbs.modelo.control;

import com.liderbs.dataaccess.dao.*;

import com.liderbs.exceptions.*;

import com.liderbs.modelo.*;
import com.liderbs.modelo.dto.TrainerDTO;

import com.liderbs.utilities.Utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
@Scope("singleton")
@Service("TrainerLogic")
public class TrainerLogic implements ITrainerLogic {
    private static final Logger log = LoggerFactory.getLogger(TrainerLogic.class);

    /**
     * DAO injected by Spring that manages Trainer entities
     *
     */
    @Autowired
    private ITrainerDAO trainerDAO;

    /**
    * Logic injected by Spring that manages Academiclevel entities
    *
    */
    @Autowired
    IAcademiclevelLogic logicAcademiclevel1;

    /**
    * Logic injected by Spring that manages Identification entities
    *
    */
    @Autowired
    IIdentificationLogic logicIdentification2;

    /**
    * Logic injected by Spring that manages Vprofile entities
    *
    */
    @Autowired
    IVprofileLogic logicVprofile3;

    @Transactional(readOnly = true)
    public List<Trainer> getTrainer() throws Exception {
        log.debug("finding all Trainer instances");

        List<Trainer> list = new ArrayList<Trainer>();

        try {
            list = trainerDAO.findAll();
        } catch (Exception e) {
            log.error("finding all Trainer failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "Trainer");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveTrainer(Trainer entity) throws Exception {
        log.debug("saving Trainer instance");

        try {
            if (entity.getAcademiclevel() == null) {
                throw new ZMessManager().new ForeignException("academiclevel");
            }

            if (entity.getIdentification() == null) {
                throw new ZMessManager().new ForeignException("identification");
            }

            if (entity.getVprofile() == null) {
                throw new ZMessManager().new ForeignException("vprofile");
            }

            if ((entity.getAcademicArea() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getAcademicArea(), 100) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "academicArea");
            }

            if ((entity.getAddress() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getAddress(), 200) == false)) {
                throw new ZMessManager().new NotValidFormatException("address");
            }

            if ((entity.getCitizenship() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getCitizenship(), 100) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "citizenship");
            }

            if ((entity.getCity() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getCity(), 100) == false)) {
                throw new ZMessManager().new NotValidFormatException("city");
            }

            if ((entity.getLastname() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getLastname(), 100) == false)) {
                throw new ZMessManager().new NotValidFormatException("lastname");
            }

            if ((entity.getName() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getName(), 100) == false)) {
                throw new ZMessManager().new NotValidFormatException("name");
            }

            if ((entity.getResumefile() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getResumefile(), 100) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "resumefile");
            }

            if ((entity.getTrainerAcknowledgement() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getTrainerAcknowledgement(), 600) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "trainerAcknowledgement");
            }

            if (entity.getUsersIdusers() == null) {
                throw new ZMessManager().new EmptyFieldException("usersIdusers");
            }

            if (entity.getAcademiclevel().getIdacademiclevel() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "idacademiclevel_Academiclevel");
            }

            if (entity.getIdentification().getIdidentification() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "ididentification_Identification");
            }

            if (entity.getVprofile().getIdvprofile() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "idvprofile_Vprofile");
            }

            if (getTrainer(entity.getIdtrainer()) != null) {
                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
            }

            trainerDAO.save(entity);

            log.debug("save Trainer successful");
        } catch (Exception e) {
            log.error("save Trainer failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteTrainer(Trainer entity) throws Exception {
        log.debug("deleting Trainer instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("Trainer");
        }

        if (entity.getIdtrainer() == null) {
            throw new ZMessManager().new EmptyFieldException("idtrainer");
        }

        try {
            trainerDAO.delete(entity);

            log.debug("delete Trainer successful");
        } catch (Exception e) {
            log.error("delete Trainer failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateTrainer(Trainer entity) throws Exception {
        log.debug("updating Trainer instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("Trainer");
            }

            if (entity.getAcademiclevel() == null) {
                throw new ZMessManager().new ForeignException("academiclevel");
            }

            if (entity.getIdentification() == null) {
                throw new ZMessManager().new ForeignException("identification");
            }

            if (entity.getVprofile() == null) {
                throw new ZMessManager().new ForeignException("vprofile");
            }

            if ((entity.getAcademicArea() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getAcademicArea(), 100) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "academicArea");
            }

            if ((entity.getAddress() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getAddress(), 200) == false)) {
                throw new ZMessManager().new NotValidFormatException("address");
            }

            if ((entity.getCitizenship() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getCitizenship(), 100) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "citizenship");
            }

            if ((entity.getCity() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getCity(), 100) == false)) {
                throw new ZMessManager().new NotValidFormatException("city");
            }

            if ((entity.getLastname() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getLastname(), 100) == false)) {
                throw new ZMessManager().new NotValidFormatException("lastname");
            }

            if ((entity.getName() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getName(), 100) == false)) {
                throw new ZMessManager().new NotValidFormatException("name");
            }

            if ((entity.getResumefile() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getResumefile(), 100) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "resumefile");
            }

            if ((entity.getTrainerAcknowledgement() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getTrainerAcknowledgement(), 600) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "trainerAcknowledgement");
            }

            if (entity.getUsersIdusers() == null) {
                throw new ZMessManager().new EmptyFieldException("usersIdusers");
            }

            if (entity.getAcademiclevel().getIdacademiclevel() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "idacademiclevel_Academiclevel");
            }

            if (entity.getIdentification().getIdidentification() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "ididentification_Identification");
            }

            if (entity.getVprofile().getIdvprofile() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "idvprofile_Vprofile");
            }

            trainerDAO.update(entity);

            log.debug("update Trainer successful");
        } catch (Exception e) {
            log.error("update Trainer failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<TrainerDTO> getDataTrainer() throws Exception {
        try {
            List<Trainer> trainer = trainerDAO.findAll();

            List<TrainerDTO> trainerDTO = new ArrayList<TrainerDTO>();

            for (Trainer trainerTmp : trainer) {
                TrainerDTO trainerDTO2 = new TrainerDTO();

                trainerDTO2.setIdtrainer(trainerTmp.getIdtrainer());
                trainerDTO2.setAcademicArea((trainerTmp.getAcademicArea() != null)
                    ? trainerTmp.getAcademicArea() : null);
                trainerDTO2.setAddress((trainerTmp.getAddress() != null)
                    ? trainerTmp.getAddress() : null);
                trainerDTO2.setBorndate(trainerTmp.getBorndate());
                trainerDTO2.setCitizenship((trainerTmp.getCitizenship() != null)
                    ? trainerTmp.getCitizenship() : null);
                trainerDTO2.setCity((trainerTmp.getCity() != null)
                    ? trainerTmp.getCity() : null);
                trainerDTO2.setCountry((trainerTmp.getCountry() != null)
                    ? trainerTmp.getCountry() : null);
                trainerDTO2.setLastname((trainerTmp.getLastname() != null)
                    ? trainerTmp.getLastname() : null);
                trainerDTO2.setName((trainerTmp.getName() != null)
                    ? trainerTmp.getName() : null);
                trainerDTO2.setRegion((trainerTmp.getRegion() != null)
                    ? trainerTmp.getRegion() : null);
                trainerDTO2.setResumefile((trainerTmp.getResumefile() != null)
                    ? trainerTmp.getResumefile() : null);
                trainerDTO2.setTrainerAcknowledgement((trainerTmp.getTrainerAcknowledgement() != null)
                    ? trainerTmp.getTrainerAcknowledgement() : null);
                trainerDTO2.setUsersIdusers((trainerTmp.getUsersIdusers() != null)
                    ? trainerTmp.getUsersIdusers() : null);
                trainerDTO2.setIdacademiclevel_Academiclevel((trainerTmp.getAcademiclevel()
                                                                        .getIdacademiclevel() != null)
                    ? trainerTmp.getAcademiclevel().getIdacademiclevel() : null);
                trainerDTO2.setIdidentification_Identification((trainerTmp.getIdentification()
                                                                          .getIdidentification() != null)
                    ? trainerTmp.getIdentification().getIdidentification() : null);
                trainerDTO2.setIdvprofile_Vprofile((trainerTmp.getVprofile()
                                                              .getIdvprofile() != null)
                    ? trainerTmp.getVprofile().getIdvprofile() : null);
                trainerDTO.add(trainerDTO2);
            }

            return trainerDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public Trainer getTrainer(Integer idtrainer) throws Exception {
        log.debug("getting Trainer instance");

        Trainer entity = null;

        try {
            entity = trainerDAO.findById(idtrainer);
        } catch (Exception e) {
            log.error("get Trainer failed", e);
            throw new ZMessManager().new FindingException("Trainer");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<Trainer> findPageTrainer(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<Trainer> entity = null;

        try {
            entity = trainerDAO.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Trainer Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberTrainer() throws Exception {
        Long entity = null;

        try {
            entity = trainerDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Trainer Count");
        } finally {
        }

        return entity;
    }

    /**
    *
    * @param varibles
    *            este arreglo debera tener:
    *
    * [0] = String variable = (String) varibles[i]; representa como se llama la
    * variable en el pojo
    *
    * [1] = Boolean booVariable = (Boolean) varibles[i + 1]; representa si el
    * valor necesita o no ''(comillas simples)usado para campos de tipo string
    *
    * [2] = Object value = varibles[i + 2]; representa el valor que se va a
    * buscar en la BD
    *
    * [3] = String comparator = (String) varibles[i + 3]; representa que tipo
    * de busqueda voy a hacer.., ejemplo: where nombre=william o where nombre<>william,
        * en este campo iria el tipo de comparador que quiero si es = o <>
            *
            * Se itera de 4 en 4..., entonces 4 registros del arreglo representan 1
            * busqueda en un campo, si se ponen mas pues el continuara buscando en lo
            * que se le ingresen en los otros 4
            *
            *
            * @param variablesBetween
            *
            * la diferencia son estas dos posiciones
            *
            * [0] = String variable = (String) varibles[j]; la variable ne la BD que va
            * a ser buscada en un rango
            *
            * [1] = Object value = varibles[j + 1]; valor 1 para buscar en un rango
            *
            * [2] = Object value2 = varibles[j + 2]; valor 2 para buscar en un rango
            * ejempolo: a > 1 and a < 5 --> 1 seria value y 5 seria value2
                *
                * [3] = String comparator1 = (String) varibles[j + 3]; comparador 1
                * ejemplo: a comparator1 1 and a < 5
                    *
                    * [4] = String comparator2 = (String) varibles[j + 4]; comparador 2
                    * ejemplo: a comparador1>1  and a comparador2<5  (el original: a > 1 and a <
                            * 5) *
                            * @param variablesBetweenDates(en
                            *            este caso solo para mysql)
                            *  [0] = String variable = (String) varibles[k]; el nombre de la variable que hace referencia a
                            *            una fecha
                            *
                            * [1] = Object object1 = varibles[k + 2]; fecha 1 a comparar(deben ser
                            * dates)
                            *
                            * [2] = Object object2 = varibles[k + 3]; fecha 2 a comparar(deben ser
                            * dates)
                            *
                            * esto hace un between entre las dos fechas.
                            *
                            * @return lista con los objetos que se necesiten
                            * @throws Exception
                            */
    @Transactional(readOnly = true)
    public List<Trainer> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<Trainer> list = new ArrayList<Trainer>();
        String where = new String();
        String tempWhere = new String();

        if (variables != null) {
            for (int i = 0; i < variables.length; i++) {
                if ((variables[i] != null) && (variables[i + 1] != null) &&
                        (variables[i + 2] != null) &&
                        (variables[i + 3] != null)) {
                    String variable = (String) variables[i];
                    Boolean booVariable = (Boolean) variables[i + 1];
                    Object value = variables[i + 2];
                    String comparator = (String) variables[i + 3];

                    if (booVariable.booleanValue()) {
                        tempWhere = (tempWhere.length() == 0)
                            ? ("(model." + variable + " " + comparator + " \'" +
                            value + "\' )")
                            : (tempWhere + " AND (model." + variable + " " +
                            comparator + " \'" + value + "\' )");
                    } else {
                        tempWhere = (tempWhere.length() == 0)
                            ? ("(model." + variable + " " + comparator + " " +
                            value + " )")
                            : (tempWhere + " AND (model." + variable + " " +
                            comparator + " " + value + " )");
                    }
                }

                i = i + 3;
            }
        }

        if (variablesBetween != null) {
            for (int j = 0; j < variablesBetween.length; j++) {
                if ((variablesBetween[j] != null) &&
                        (variablesBetween[j + 1] != null) &&
                        (variablesBetween[j + 2] != null) &&
                        (variablesBetween[j + 3] != null) &&
                        (variablesBetween[j + 4] != null)) {
                    String variable = (String) variablesBetween[j];
                    Object value = variablesBetween[j + 1];
                    Object value2 = variablesBetween[j + 2];
                    String comparator1 = (String) variablesBetween[j + 3];
                    String comparator2 = (String) variablesBetween[j + 4];
                    tempWhere = (tempWhere.length() == 0)
                        ? ("(" + value + " " + comparator1 + " " + variable +
                        " and " + variable + " " + comparator2 + " " + value2 +
                        " )")
                        : (tempWhere + " AND (" + value + " " + comparator1 +
                        " " + variable + " and " + variable + " " +
                        comparator2 + " " + value2 + " )");
                }

                j = j + 4;
            }
        }

        if (variablesBetweenDates != null) {
            for (int k = 0; k < variablesBetweenDates.length; k++) {
                if ((variablesBetweenDates[k] != null) &&
                        (variablesBetweenDates[k + 1] != null) &&
                        (variablesBetweenDates[k + 2] != null)) {
                    String variable = (String) variablesBetweenDates[k];
                    Object object1 = variablesBetweenDates[k + 1];
                    Object object2 = variablesBetweenDates[k + 2];
                    String value = null;
                    String value2 = null;

                    try {
                        Date date1 = (Date) object1;
                        Date date2 = (Date) object2;
                        value = Utilities.formatDateWithoutTimeInAStringForBetweenWhere(date1);
                        value2 = Utilities.formatDateWithoutTimeInAStringForBetweenWhere(date2);
                    } catch (Exception e) {
                        list = null;
                        throw e;
                    }

                    tempWhere = (tempWhere.length() == 0)
                        ? ("(model." + variable + " between \'" + value +
                        "\' and \'" + value2 + "\')")
                        : (tempWhere + " AND (model." + variable +
                        " between \'" + value + "\' and \'" + value2 + "\')");
                }

                k = k + 2;
            }
        }

        if (tempWhere.length() == 0) {
            where = null;
        } else {
            where = "(" + tempWhere + ")";
        }

        try {
            list = trainerDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
}
