package com.liderbs.modelo.control;

import com.liderbs.modelo.Trainer;
import com.liderbs.modelo.dto.TrainerDTO;

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
public interface ITrainerLogic {
    public List<Trainer> getTrainer() throws Exception;

    /**
         * Save an new Trainer entity
         */
    public void saveTrainer(Trainer entity) throws Exception;

    /**
         * Delete an existing Trainer entity
         *
         */
    public void deleteTrainer(Trainer entity) throws Exception;

    /**
        * Update an existing Trainer entity
        *
        */
    public void updateTrainer(Trainer entity) throws Exception;

    /**
         * Load an existing Trainer entity
         *
         */
    public Trainer getTrainer(Integer idtrainer) throws Exception;

    public List<Trainer> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Trainer> findPageTrainer(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberTrainer() throws Exception;

    public List<TrainerDTO> getDataTrainer() throws Exception;
}
