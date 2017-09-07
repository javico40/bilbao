package com.liderbs.modelo;
// Generated 07-sep-2017 15:46:21 by Hibernate Tools 4.0.0


import java.util.HashSet;
import java.util.Set;

/**
 * Skill generated by hbm2java
 */
public class Skill  implements java.io.Serializable {


     private Integer idskill;
     private String skillDescription;
     private Set<Trainer> trainers = new HashSet<Trainer>(0);

    public Skill() {
    }

    public Skill(String skillDescription, Set<Trainer> trainers) {
       this.skillDescription = skillDescription;
       this.trainers = trainers;
    }
   
    public Integer getIdskill() {
        return this.idskill;
    }
    
    public void setIdskill(Integer idskill) {
        this.idskill = idskill;
    }
    public String getSkillDescription() {
        return this.skillDescription;
    }
    
    public void setSkillDescription(String skillDescription) {
        this.skillDescription = skillDescription;
    }
    public Set<Trainer> getTrainers() {
        return this.trainers;
    }
    
    public void setTrainers(Set<Trainer> trainers) {
        this.trainers = trainers;
    }




}


