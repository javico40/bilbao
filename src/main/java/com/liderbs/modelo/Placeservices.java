package com.liderbs.modelo;
// Generated 04-jun-2017 16:19:53 by Hibernate Tools 4.0.0


import java.util.HashSet;
import java.util.Set;

/**
 * Placeservices generated by hbm2java
 */
public class Placeservices  implements java.io.Serializable {


     private Integer idplaceservices;
     private String names;
     private Set<Place> places = new HashSet<Place>(0);

    public Placeservices() {
    }

    public Placeservices(String names, Set<Place> places) {
       this.names = names;
       this.places = places;
    }
   
    public Integer getIdplaceservices() {
        return this.idplaceservices;
    }
    
    public void setIdplaceservices(Integer idplaceservices) {
        this.idplaceservices = idplaceservices;
    }
    public String getNames() {
        return this.names;
    }
    
    public void setNames(String names) {
        this.names = names;
    }
    public Set<Place> getPlaces() {
        return this.places;
    }
    
    public void setPlaces(Set<Place> places) {
        this.places = places;
    }




}

