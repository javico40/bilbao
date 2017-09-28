package com.liderbs.modelo.control;

import com.liderbs.modelo.Reservation;
import com.liderbs.modelo.dto.ReservationDTO;

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
public interface IReservationLogic {
    public List<Reservation> getReservation() throws Exception;

    /**
         * Save an new Reservation entity
         */
    public void saveReservation(Reservation entity) throws Exception;

    /**
         * Delete an existing Reservation entity
         *
         */
    public void deleteReservation(Reservation entity) throws Exception;

    /**
        * Update an existing Reservation entity
        *
        */
    public void updateReservation(Reservation entity) throws Exception;

    /**
         * Load an existing Reservation entity
         *
         */
    public Reservation getReservation(Integer idreservation)
        throws Exception;

    public List<Reservation> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Reservation> findPageReservation(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberReservation() throws Exception;

    public List<ReservationDTO> getDataReservation() throws Exception;
}
