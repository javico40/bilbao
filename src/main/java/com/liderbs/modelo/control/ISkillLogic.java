package com.liderbs.modelo.control;

import com.liderbs.modelo.Skill;
import com.liderbs.modelo.dto.SkillDTO;

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
public interface ISkillLogic {
    public List<Skill> getSkill() throws Exception;

    /**
         * Save an new Skill entity
         */
    public void saveSkill(Skill entity) throws Exception;

    /**
         * Delete an existing Skill entity
         *
         */
    public void deleteSkill(Skill entity) throws Exception;

    /**
        * Update an existing Skill entity
        *
        */
    public void updateSkill(Skill entity) throws Exception;

    /**
         * Load an existing Skill entity
         *
         */
    public Skill getSkill(Integer idskill) throws Exception;

    public List<Skill> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Skill> findPageSkill(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberSkill() throws Exception;

    public List<SkillDTO> getDataSkill() throws Exception;
}
