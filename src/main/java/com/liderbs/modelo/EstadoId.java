package com.liderbs.modelo;
// Generated 26-abr-2017 15:46:42 by Hibernate Tools 4.0.0



/**
 * EstadoId generated by hbm2java
 */
public class EstadoId  implements java.io.Serializable {


     private Integer idestado;
     private Integer paisId;

    public EstadoId() {
    }

    public EstadoId(Integer idestado, Integer paisId) {
       this.idestado = idestado;
       this.paisId = paisId;
    }
   
    public Integer getIdestado() {
        return this.idestado;
    }
    
    public void setIdestado(Integer idestado) {
        this.idestado = idestado;
    }
    public Integer getPaisId() {
        return this.paisId;
    }
    
    public void setPaisId(Integer paisId) {
        this.paisId = paisId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof EstadoId) ) return false;
		 EstadoId castOther = ( EstadoId ) other; 
         
		 return ( (this.getIdestado()==castOther.getIdestado()) || ( this.getIdestado()!=null && castOther.getIdestado()!=null && this.getIdestado().equals(castOther.getIdestado()) ) )
 && ( (this.getPaisId()==castOther.getPaisId()) || ( this.getPaisId()!=null && castOther.getPaisId()!=null && this.getPaisId().equals(castOther.getPaisId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getIdestado() == null ? 0 : this.getIdestado().hashCode() );
         result = 37 * result + ( getPaisId() == null ? 0 : this.getPaisId().hashCode() );
         return result;
   }   


}


