package com.liderbs.modelo;
// Generated 05-abr-2017 20:45:37 by Hibernate Tools 4.0.0



/**
 * UsersId generated by hbm2java
 */
public class UsersId  implements java.io.Serializable {


     private Integer idusers;
     private Integer profileIdprofile;

    public UsersId() {
    }

    public UsersId(Integer idusers, Integer profileIdprofile) {
       this.idusers = idusers;
       this.profileIdprofile = profileIdprofile;
    }
   
    public Integer getIdusers() {
        return this.idusers;
    }
    
    public void setIdusers(Integer idusers) {
        this.idusers = idusers;
    }
    public Integer getProfileIdprofile() {
        return this.profileIdprofile;
    }
    
    public void setProfileIdprofile(Integer profileIdprofile) {
        this.profileIdprofile = profileIdprofile;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof UsersId) ) return false;
		 UsersId castOther = ( UsersId ) other; 
         
		 return ( (this.getIdusers()==castOther.getIdusers()) || ( this.getIdusers()!=null && castOther.getIdusers()!=null && this.getIdusers().equals(castOther.getIdusers()) ) )
 && ( (this.getProfileIdprofile()==castOther.getProfileIdprofile()) || ( this.getProfileIdprofile()!=null && castOther.getProfileIdprofile()!=null && this.getProfileIdprofile().equals(castOther.getProfileIdprofile()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getIdusers() == null ? 0 : this.getIdusers().hashCode() );
         result = 37 * result + ( getProfileIdprofile() == null ? 0 : this.getProfileIdprofile().hashCode() );
         return result;
   }   


}

