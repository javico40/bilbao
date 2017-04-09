package com.liderbs.modelo.control;

import com.liderbs.dataaccess.dao.*;

import com.liderbs.exceptions.*;

import com.liderbs.modelo.*;
import com.liderbs.modelo.dto.AccountDTO;

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
@Service("AccountLogic")
public class AccountLogic implements IAccountLogic {
    private static final Logger log = LoggerFactory.getLogger(AccountLogic.class);

    /**
     * DAO injected by Spring that manages Account entities
     *
     */
    @Autowired
    private IAccountDAO accountDAO;

    /**
    * Logic injected by Spring that manages Profile entities
    *
    */
    @Autowired
    IProfileLogic logicProfile1;

    @Transactional(readOnly = true)
    public List<Account> getAccount() throws Exception {
        log.debug("finding all Account instances");

        List<Account> list = new ArrayList<Account>();

        try {
            list = accountDAO.findAll();
        } catch (Exception e) {
            log.error("finding all Account failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "Account");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveAccount(Account entity) throws Exception {
        log.debug("saving Account instance");

        try {
            if (entity.getProfile() == null) {
                throw new ZMessManager().new ForeignException("profile");
            }

            if ((entity.getAccountDescription() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getAccountDescription(), 300) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "accountDescription");
            }

            if ((entity.getAccountName() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getAccountName(), 45) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "accountName");
            }

            if ((entity.getAccountUserCreated() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getAccountUserCreated(), 45) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "accountUserCreated");
            }

            if (entity.getProfile().getIdprofile() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "idprofile_Profile");
            }

            if (getAccount(entity.getIdAccount()) != null) {
                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
            }

            accountDAO.save(entity);

            log.debug("save Account successful");
        } catch (Exception e) {
            log.error("save Account failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteAccount(Account entity) throws Exception {
        log.debug("deleting Account instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("Account");
        }

        if (entity.getIdAccount() == null) {
            throw new ZMessManager().new EmptyFieldException("idAccount");
        }

        try {
            accountDAO.delete(entity);

            log.debug("delete Account successful");
        } catch (Exception e) {
            log.error("delete Account failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateAccount(Account entity) throws Exception {
        log.debug("updating Account instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("Account");
            }

            if (entity.getProfile() == null) {
                throw new ZMessManager().new ForeignException("profile");
            }

            if ((entity.getAccountDescription() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getAccountDescription(), 300) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "accountDescription");
            }

            if ((entity.getAccountName() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getAccountName(), 45) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "accountName");
            }

            if ((entity.getAccountUserCreated() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getAccountUserCreated(), 45) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "accountUserCreated");
            }

            if (entity.getProfile().getIdprofile() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "idprofile_Profile");
            }

            accountDAO.update(entity);

            log.debug("update Account successful");
        } catch (Exception e) {
            log.error("update Account failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<AccountDTO> getDataAccount() throws Exception {
        try {
            List<Account> account = accountDAO.findAll();

            List<AccountDTO> accountDTO = new ArrayList<AccountDTO>();

            for (Account accountTmp : account) {
                AccountDTO accountDTO2 = new AccountDTO();

                accountDTO2.setIdAccount(accountTmp.getIdAccount());
                accountDTO2.setAccountCreated(accountTmp.getAccountCreated());
                accountDTO2.setAccountDescription((accountTmp.getAccountDescription() != null)
                    ? accountTmp.getAccountDescription() : null);
                accountDTO2.setAccountName((accountTmp.getAccountName() != null)
                    ? accountTmp.getAccountName() : null);
                accountDTO2.setAccountStatus((accountTmp.getAccountStatus() != null)
                    ? accountTmp.getAccountStatus() : null);
                accountDTO2.setAccountUserCreated((accountTmp.getAccountUserCreated() != null)
                    ? accountTmp.getAccountUserCreated() : null);
                accountDTO2.setIdprofile_Profile((accountTmp.getProfile()
                                                            .getIdprofile() != null)
                    ? accountTmp.getProfile().getIdprofile() : null);
                accountDTO.add(accountDTO2);
            }

            return accountDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public Account getAccount(Integer idAccount) throws Exception {
        log.debug("getting Account instance");

        Account entity = null;

        try {
            entity = accountDAO.findById(idAccount);
        } catch (Exception e) {
            log.error("get Account failed", e);
            throw new ZMessManager().new FindingException("Account");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<Account> findPageAccount(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<Account> entity = null;

        try {
            entity = accountDAO.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Account Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberAccount() throws Exception {
        Long entity = null;

        try {
            entity = accountDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Account Count");
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
    public List<Account> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<Account> list = new ArrayList<Account>();
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
            list = accountDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
}
