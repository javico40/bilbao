package com.liderbs.modelo;
// Generated 04-jun-2017 16:19:53 by Hibernate Tools 4.0.0


import java.util.HashSet;
import java.util.Set;

/**
 * Placetype generated by hbm2java
 */
public class Placetype  implements java.io.Serializable {


     private Integer idplacetype;
     private String name;
     private Set<Place> places = new HashSet<Place>(0);

    public Placetype() {
    }

    public Placetype(String name, Set<Place> places) {
       this.name = name;
       this.places = places;
    }
   
    public Integer getIdplacetype() {
        return this.idplacetype;
    }
    
    public void setIdplacetype(Integer idplacetype) {
        this.idplacetype = idplacetype;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public Set<Place> getPlaces() {
        return this.places;
    }
    
    public void setPlaces(Set<Place> places) {
        this.places = places;
    }




}


