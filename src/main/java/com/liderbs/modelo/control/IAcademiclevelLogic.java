package com.liderbs.modelo.control;

import com.liderbs.modelo.Academiclevel;
import com.liderbs.modelo.dto.AcademiclevelDTO;

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
public interface IAcademiclevelLogic {
    public List<Academiclevel> getAcademiclevel() throws Exception;

    /**
         * Save an new Academiclevel entity
         */
    public void saveAcademiclevel(Academiclevel entity)
        throws Exception;

    /**
         * Delete an existing Academiclevel entity
         *
         */
    public void deleteAcademiclevel(Academiclevel entity)
        throws Exception;

    /**
        * Update an existing Academiclevel entity
        *
        */
    public void updateAcademiclevel(Academiclevel entity)
        throws Exception;

    /**
         * Load an existing Academiclevel entity
         *
         */
    public Academiclevel getAcademiclevel(Integer idacademiclevel)
        throws Exception;

    public List<Academiclevel> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Academiclevel> findPageAcademiclevel(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberAcademiclevel() throws Exception;

    public List<AcademiclevelDTO> getDataAcademiclevel()
        throws Exception;
}
