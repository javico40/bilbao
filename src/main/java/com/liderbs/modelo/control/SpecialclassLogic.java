package com.liderbs.modelo.control;

import com.liderbs.dataaccess.dao.*;

import com.liderbs.exceptions.*;

import com.liderbs.modelo.*;
import com.liderbs.modelo.dto.SpecialclassDTO;

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
@Service("SpecialclassLogic")
public class SpecialclassLogic implements ISpecialclassLogic {
    private static final Logger log = LoggerFactory.getLogger(SpecialclassLogic.class);

    /**
     * DAO injected by Spring that manages Specialclass entities
     *
     */
    @Autowired
    private ISpecialclassDAO specialclassDAO;

    @Transactional(readOnly = true)
    public List<Specialclass> getSpecialclass() throws Exception {
        log.debug("finding all Specialclass instances");

        List<Specialclass> list = new ArrayList<Specialclass>();

        try {
            list = specialclassDAO.findAll();
        } catch (Exception e) {
            log.error("finding all Specialclass failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "Specialclass");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveSpecialclass(Specialclass entity) throws Exception {
        log.debug("saving Specialclass instance");

        try {

        	if ((entity.getClassTitle() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getClassTitle(), 1000) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "classTitle");
            }

            if ((entity.getDescription() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getDescription(), 10000) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "description");
            }

           

            specialclassDAO.save(entity);

            log.debug("save Specialclass successful");
        } catch (Exception e) {
            log.error("save Specialclass failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteSpecialclass(Specialclass entity)
        throws Exception {
        log.debug("deleting Specialclass instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("Specialclass");
        }

        if (entity.getIdspecialclass() == null) {
            throw new ZMessManager().new EmptyFieldException("idspecialclass");
        }

        try {
            specialclassDAO.delete(entity);

            log.debug("delete Specialclass successful");
        } catch (Exception e) {
            log.error("delete Specialclass failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateSpecialclass(Specialclass entity)
        throws Exception {
        log.debug("updating Specialclass instance");

        try {
        	
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("Specialclass");
            }

            if ((entity.getClassPicture() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getClassPicture(), 5000) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "classPicture");
            }

            if ((entity.getClassTitle() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getClassTitle(), 1000) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "classTitle");
            }

            if ((entity.getDescription() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getDescription(), 10000) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "description");
            }


            specialclassDAO.update(entity);

            log.debug("update Specialclass successful");
        } catch (Exception e) {
            log.error("update Specialclass failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<SpecialclassDTO> getDataSpecialclass()
        throws Exception {
        try {
            List<Specialclass> specialclass = specialclassDAO.findAll();

            List<SpecialclassDTO> specialclassDTO = new ArrayList<SpecialclassDTO>();

            for (Specialclass specialclassTmp : specialclass) {
                SpecialclassDTO specialclassDTO2 = new SpecialclassDTO();

                specialclassDTO2.setIdspecialclass(specialclassTmp.getIdspecialclass());
                specialclassDTO2.setClassPicture((specialclassTmp.getClassPicture() != null)
                    ? specialclassTmp.getClassPicture() : null);
                specialclassDTO2.setClassTitle((specialclassTmp.getClassTitle() != null)
                    ? specialclassTmp.getClassTitle() : null);
                specialclassDTO2.setDescription((specialclassTmp.getDescription() != null)
                    ? specialclassTmp.getDescription() : null);
                specialclassDTO2.setEndHour(specialclassTmp.getEndHour());
                specialclassDTO2.setFecha(specialclassTmp.getFecha());
                specialclassDTO2.setHavebathroom((specialclassTmp.getHavebathroom() != null)
                    ? specialclassTmp.getHavebathroom() : null);
                specialclassDTO2.setHavechangeclothes((specialclassTmp.getHavechangeclothes() != null)
                    ? specialclassTmp.getHavechangeclothes() : null);
                specialclassDTO2.setHaveparkingbike((specialclassTmp.getHaveparkingbike() != null)
                    ? specialclassTmp.getHaveparkingbike() : null);
                specialclassDTO2.setHaveparkingcar((specialclassTmp.getHaveparkingcar() != null)
                    ? specialclassTmp.getHaveparkingcar() : null);
                specialclassDTO2.setHaveshower((specialclassTmp.getHaveshower() != null)
                    ? specialclassTmp.getHaveshower() : null);
                specialclassDTO2.setLatitude((specialclassTmp.getLatitude() != null)
                    ? specialclassTmp.getLatitude() : null);
                specialclassDTO2.setLongitud((specialclassTmp.getLongitud() != null)
                    ? specialclassTmp.getLongitud() : null);
                specialclassDTO2.setPlaceAddress((specialclassTmp.getPlaceAddress() != null)
                    ? specialclassTmp.getPlaceAddress() : null);
                specialclassDTO2.setPlaceName((specialclassTmp.getPlaceName() != null)
                    ? specialclassTmp.getPlaceName() : null);
                specialclassDTO2.setPrice((specialclassTmp.getPrice() != null)
                    ? specialclassTmp.getPrice() : null);
                specialclassDTO2.setStartHour(specialclassTmp.getStartHour());
                specialclassDTO2.setDayWeek(specialclassTmp.getDayWeek());
                
                specialclassDTO.add(specialclassDTO2);
            }

            return specialclassDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public Specialclass getSpecialclass(Integer idspecialclass)
        throws Exception {
        log.debug("getting Specialclass instance");

        Specialclass entity = null;

        try {
            entity = specialclassDAO.findById(idspecialclass);
        } catch (Exception e) {
            log.error("get Specialclass failed", e);
            throw new ZMessManager().new FindingException("Specialclass");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<Specialclass> findPageSpecialclass(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<Specialclass> entity = null;

        try {
            entity = specialclassDAO.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Specialclass Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberSpecialclass() throws Exception {
        Long entity = null;

        try {
            entity = specialclassDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Specialclass Count");
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
    public List<Specialclass> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<Specialclass> list = new ArrayList<Specialclass>();
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
            list = specialclassDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
}
