package com.liderbs.modelo;
// Generated 07-abr-2017 10:59:26 by Hibernate Tools 4.0.0


import java.util.HashSet;
import java.util.Set;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Options generated by hbm2java
 */
public class Options  implements java.io.Serializable {


	 @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
     private Integer idoptions;
     private String optionsName;
     private String optionsUrl;
     private String optionsStatus;
     private Set<Menu> menus = new HashSet<Menu>(0);
     private Set<Profile> profiles = new HashSet<Profile>(0);

    public Options() {
    }

    public Options(String optionsName, String optionsUrl, String optionsStatus, Set<Menu> menus, Set<Profile> profiles) {
       this.optionsName = optionsName;
       this.optionsUrl = optionsUrl;
       this.optionsStatus = optionsStatus;
       this.menus = menus;
       this.profiles = profiles;
    }
   
    public Integer getIdoptions() {
        return this.idoptions;
    }
    
    public void setIdoptions(Integer idoptions) {
        this.idoptions = idoptions;
    }
    public String getOptionsName() {
        return this.optionsName;
    }
    
    public void setOptionsName(String optionsName) {
        this.optionsName = optionsName;
    }
    public String getOptionsUrl() {
        return this.optionsUrl;
    }
    
    public void setOptionsUrl(String optionsUrl) {
        this.optionsUrl = optionsUrl;
    }
    public String getOptionsStatus() {
        return this.optionsStatus;
    }
    
    public void setOptionsStatus(String optionsStatus) {
        this.optionsStatus = optionsStatus;
    }
    public Set<Menu> getMenus() {
        return this.menus;
    }
    
    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
    }
    public Set<Profile> getProfiles() {
        return this.profiles;
    }
    
    public void setProfiles(Set<Profile> profiles) {
        this.profiles = profiles;
    }




}


