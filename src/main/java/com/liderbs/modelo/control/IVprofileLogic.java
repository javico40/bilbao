package com.liderbs.modelo.control;

import com.liderbs.modelo.Vprofile;
import com.liderbs.modelo.dto.VprofileDTO;

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
public interface IVprofileLogic {
    public List<Vprofile> getVprofile() throws Exception;

    /**
         * Save an new Vprofile entity
         */
    public void saveVprofile(Vprofile entity) throws Exception;

    /**
         * Delete an existing Vprofile entity
         *
         */
    public void deleteVprofile(Vprofile entity) throws Exception;

    /**
        * Update an existing Vprofile entity
        *
        */
    public void updateVprofile(Vprofile entity) throws Exception;

    /**
         * Load an existing Vprofile entity
         *
         */
    public Vprofile getVprofile(Integer idvprofile) throws Exception;

    public List<Vprofile> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Vprofile> findPageVprofile(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberVprofile() throws Exception;

    public List<VprofileDTO> getDataVprofile() throws Exception;
}
