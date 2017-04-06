package com.liderbs.modelo.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

import java.sql.*;

import java.util.Date;


/**
*
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
public class MenuHasOptionsDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(MenuHasOptionsDTO.class);
    private Integer menuIdMenu;
    private Integer optionsIdoptions;
    private Integer idMenu_Menu;

    public Integer getMenuIdMenu() {
        return menuIdMenu;
    }

    public void setMenuIdMenu(Integer menuIdMenu) {
        this.menuIdMenu = menuIdMenu;
    }

    public Integer getOptionsIdoptions() {
        return optionsIdoptions;
    }

    public void setOptionsIdoptions(Integer optionsIdoptions) {
        this.optionsIdoptions = optionsIdoptions;
    }

    public Integer getIdMenu_Menu() {
        return idMenu_Menu;
    }

    public void setIdMenu_Menu(Integer idMenu_Menu) {
        this.idMenu_Menu = idMenu_Menu;
    }
}
