package com.liderbs.modelo.control;

import com.liderbs.modelo.Consumption;
import com.liderbs.modelo.dto.ConsumptionDTO;

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
public interface IConsumptionLogic {
    public List<Consumption> getConsumption() throws Exception;

    /**
         * Save an new Consumption entity
         */
    public void saveConsumption(Consumption entity) throws Exception;

    /**
         * Delete an existing Consumption entity
         *
         */
    public void deleteConsumption(Consumption entity) throws Exception;

    /**
        * Update an existing Consumption entity
        *
        */
    public void updateConsumption(Consumption entity) throws Exception;

    /**
         * Load an existing Consumption entity
         *
         */
    public Consumption getConsumption(Integer idconsumption)
        throws Exception;

    public List<Consumption> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Consumption> findPageConsumption(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberConsumption() throws Exception;

    public List<ConsumptionDTO> getDataConsumption() throws Exception;
}
