package com.liderbs.modelo.control;

import com.liderbs.modelo.Profile;
import com.liderbs.modelo.dto.ProfileDTO;

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
public interface IProfileLogic {
    public List<Profile> getProfile() throws Exception;

    /**
         * Save an new Profile entity
         */
    public void saveProfile(Profile entity) throws Exception;

    /**
         * Delete an existing Profile entity
         *
         */
    public void deleteProfile(Profile entity) throws Exception;

    /**
        * Update an existing Profile entity
        *
        */
    public void updateProfile(Profile entity) throws Exception;

    /**
         * Load an existing Profile entity
         *
         */
    public Profile getProfile(Integer idprofile) throws Exception;

    public List<Profile> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Profile> findPageProfile(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberProfile() throws Exception;

    public List<ProfileDTO> getDataProfile() throws Exception;
}
