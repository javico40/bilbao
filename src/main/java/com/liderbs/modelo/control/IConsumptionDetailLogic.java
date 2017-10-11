package com.liderbs.modelo.control;

import com.liderbs.modelo.ConsumptionDetail;
import com.liderbs.modelo.dto.ConsumptionDetailDTO;

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
public interface IConsumptionDetailLogic {
    public List<ConsumptionDetail> getConsumptionDetail()
        throws Exception;

    /**
         * Save an new ConsumptionDetail entity
         */
    public void saveConsumptionDetail(ConsumptionDetail entity)
        throws Exception;

    /**
         * Delete an existing ConsumptionDetail entity
         *
         */
    public void deleteConsumptionDetail(ConsumptionDetail entity)
        throws Exception;

    /**
        * Update an existing ConsumptionDetail entity
        *
        */
    public void updateConsumptionDetail(ConsumptionDetail entity)
        throws Exception;

    /**
         * Load an existing ConsumptionDetail entity
         *
         */
    public ConsumptionDetail getConsumptionDetail(Integer idconsumptionDetail)
        throws Exception;

    public List<ConsumptionDetail> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<ConsumptionDetail> findPageConsumptionDetail(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberConsumptionDetail() throws Exception;

    public List<ConsumptionDetailDTO> getDataConsumptionDetail()
        throws Exception;
}
