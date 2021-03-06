package com.liderbs.modelo;
// Generated 26-abr-2017 15:46:42 by Hibernate Tools 4.0.0


import java.util.HashSet;
import java.util.Set;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Job generated by hbm2java
 */
public class Job  implements java.io.Serializable {

	 @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
     private Integer idjob;
     private String name;
     private Set<Experience> experiences = new HashSet<Experience>(0);

    public Job() {
    }

    public Job(String name, Set<Experience> experiences) {
       this.name = name;
       this.experiences = experiences;
    }
   
    public Integer getIdjob() {
        return this.idjob;
    }
    
    public void setIdjob(Integer idjob) {
        this.idjob = idjob;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public Set<Experience> getExperiences() {
        return this.experiences;
    }
    
    public void setExperiences(Set<Experience> experiences) {
        this.experiences = experiences;
    }




}


