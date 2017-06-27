package com.liderbs.modelo.control;

import com.liderbs.dataaccess.dao.*;

import com.liderbs.exceptions.*;

import com.liderbs.modelo.*;
import com.liderbs.modelo.dto.TimetableDTO;

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
@Service("TimetableLogic")
public class TimetableLogic implements ITimetableLogic {
    private static final Logger log = LoggerFactory.getLogger(TimetableLogic.class);

    /**
     * DAO injected by Spring that manages Timetable entities
     *
     */
    @Autowired
    private ITimetableDAO timetableDAO;

    /**
    * Logic injected by Spring that manages Day entities
    *
    */
    @Autowired
    IDayLogic logicDay1;

    /**
    * Logic injected by Spring that manages Users entities
    *
    */
    @Autowired
    IUsersLogic logicUsers2;

    @Transactional(readOnly = true)
    public List<Timetable> getTimetable() throws Exception {
        log.debug("finding all Timetable instances");

        List<Timetable> list = new ArrayList<Timetable>();

        try {
            list = timetableDAO.findAll();
        } catch (Exception e) {
            log.error("finding all Timetable failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "Timetable");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveTimetable(Timetable entity) throws Exception {
        log.debug("saving Timetable instance");

        try {

            timetableDAO.save(entity);

            log.debug("save Timetable successful");
        } catch (Exception e) {
            log.error("save Timetable failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteTimetable(Timetable entity) throws Exception {
        log.debug("deleting Timetable instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("Timetable");
        }

        if (entity.getIdtimetable() == null) {
            throw new ZMessManager().new EmptyFieldException("idtimetable");
        }

        try {
            timetableDAO.delete(entity);

            log.debug("delete Timetable successful");
        } catch (Exception e) {
            log.error("delete Timetable failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateTimetable(Timetable entity) throws Exception {
        log.debug("updating Timetable instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("Timetable");
            }

            if (entity.getDay() == null) {
                throw new ZMessManager().new ForeignException("day");
            }

            if (entity.getUsers() == null) {
                throw new ZMessManager().new ForeignException("users");
            }

            if (entity.getDay().getIdday() == null) {
                throw new ZMessManager().new EmptyFieldException("idday_Day");
            }

            if (entity.getUsers().getIdusers() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "idusers_Users");
            }

            timetableDAO.update(entity);

            log.debug("update Timetable successful");
        } catch (Exception e) {
            log.error("update Timetable failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<TimetableDTO> getDataTimetable() throws Exception {
        try {
            List<Timetable> timetable = timetableDAO.findAll();

            List<TimetableDTO> timetableDTO = new ArrayList<TimetableDTO>();

            for (Timetable timetableTmp : timetable) {
                TimetableDTO timetableDTO2 = new TimetableDTO();

                timetableDTO2.setIdtimetable(timetableTmp.getIdtimetable());
                timetableDTO2.setDateCreated(timetableTmp.getDateCreated());
                timetableDTO2.setTimeEnd(timetableTmp.getTimeEnd());
                timetableDTO2.setTimeStart(timetableTmp.getTimeStart());
                timetableDTO2.setIdday_Day((timetableTmp.getDay().getIdday() != null)
                    ? timetableTmp.getDay().getIdday() : null);
                timetableDTO2.setIdusers_Users((timetableTmp.getUsers()
                                                            .getIdusers() != null)
                    ? timetableTmp.getUsers().getIdusers() : null);
                timetableDTO.add(timetableDTO2);
            }

            return timetableDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public Timetable getTimetable(Integer idtimetable)
        throws Exception {
        log.debug("getting Timetable instance");

        Timetable entity = null;

        try {
            entity = timetableDAO.findById(idtimetable);
        } catch (Exception e) {
            log.error("get Timetable failed", e);
            throw new ZMessManager().new FindingException("Timetable");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<Timetable> findPageTimetable(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<Timetable> entity = null;

        try {
            entity = timetableDAO.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Timetable Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberTimetable() throws Exception {
        Long entity = null;

        try {
            entity = timetableDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Timetable Count");
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
    public List<Timetable> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<Timetable> list = new ArrayList<Timetable>();
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
            list = timetableDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
}
