package com.liderbs.modelo;
// Generated 16-may-2017 16:24:06 by Hibernate Tools 4.0.0


import java.util.HashSet;
import java.util.Set;

/**
 * Category generated by hbm2java
 */
public class Category  implements java.io.Serializable {


     private Integer idcategory;
     private Level level;
     private String description;
     private Set<UsuariosCategorias> usuariosCategoriases = new HashSet<UsuariosCategorias>(0);

    public Category() {
    }

	
    public Category(Level level) {
        this.level = level;
    }
    public Category(Level level, String description, Set<UsuariosCategorias> usuariosCategoriases) {
       this.level = level;
       this.description = description;
       this.usuariosCategoriases = usuariosCategoriases;
    }
   
    public Integer getIdcategory() {
        return this.idcategory;
    }
    
    public void setIdcategory(Integer idcategory) {
        this.idcategory = idcategory;
    }
    public Level getLevel() {
        return this.level;
    }
    
    public void setLevel(Level level) {
        this.level = level;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    public Set<UsuariosCategorias> getUsuariosCategoriases() {
        return this.usuariosCategoriases;
    }
    
    public void setUsuariosCategoriases(Set<UsuariosCategorias> usuariosCategoriases) {
        this.usuariosCategoriases = usuariosCategoriases;
    }




}


