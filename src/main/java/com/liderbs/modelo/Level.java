package com.liderbs.modelo;
// Generated 16-may-2017 16:24:06 by Hibernate Tools 4.0.0


import java.util.HashSet;
import java.util.Set;

/**
 * Level generated by hbm2java
 */
public class Level  implements java.io.Serializable {


     private Integer idlevel;
     private String description;
     private Set<Category> categories = new HashSet<Category>(0);

    public Level() {
    }

    public Level(String description, Set<Category> categories) {
       this.description = description;
       this.categories = categories;
    }
   
    public Integer getIdlevel() {
        return this.idlevel;
    }
    
    public void setIdlevel(Integer idlevel) {
        this.idlevel = idlevel;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    public Set<Category> getCategories() {
        return this.categories;
    }
    
    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }




}


