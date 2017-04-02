package com.liderbs.modelo;
// Generated 02-abr-2017 11:42:14 by Hibernate Tools 4.0.0


import java.util.HashSet;
import java.util.Set;

/**
 * Profile generated by hbm2java
 */
public class Profile  implements java.io.Serializable {


     private Integer idprofile;
     private String profileName;
     private String profileDescription;
     private Set<Options> optionses = new HashSet<Options>(0);

    public Profile() {
    }

    public Profile(String profileName, String profileDescription, Set<Options> optionses) {
       this.profileName = profileName;
       this.profileDescription = profileDescription;
       this.optionses = optionses;
    }
   
    public Integer getIdprofile() {
        return this.idprofile;
    }
    
    public void setIdprofile(Integer idprofile) {
        this.idprofile = idprofile;
    }
    public String getProfileName() {
        return this.profileName;
    }
    
    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }
    public String getProfileDescription() {
        return this.profileDescription;
    }
    
    public void setProfileDescription(String profileDescription) {
        this.profileDescription = profileDescription;
    }
    public Set<Options> getOptionses() {
        return this.optionses;
    }
    
    public void setOptionses(Set<Options> optionses) {
        this.optionses = optionses;
    }




}


