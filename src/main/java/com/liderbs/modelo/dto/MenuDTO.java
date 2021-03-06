package com.liderbs.modelo.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.liderbs.modelo.Options;
import java.io.Serializable;
import java.sql.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
*
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
public class MenuDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(MenuDTO.class);
    private String caption;
    private String description;
    private String icon;
    private Integer idMenu;
    private String path;
    private List<Options> opciones;
    private Set<Options> optionses = new HashSet<Options>(0);

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Integer idMenu) {
        this.idMenu = idMenu;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

	public List<Options> getOpciones() {
		return opciones;
	}

	public void setOpciones(List<Options> opciones) {
		this.opciones = opciones;
	}

	public Set<Options> getOptionses() {
		return optionses;
	}

	public void setOptionses(Set<Options> optionses) {
		this.optionses = optionses;
	}
    
    
}
