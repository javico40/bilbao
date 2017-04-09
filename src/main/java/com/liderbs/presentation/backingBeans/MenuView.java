package com.liderbs.presentation.backingBeans;

import com.liderbs.exceptions.*;

import com.liderbs.modelo.*;
import com.liderbs.modelo.dto.MenuDTO;

import com.liderbs.presentation.businessDelegate.*;

import com.liderbs.utilities.*;

import org.hibernate.Hibernate;
import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;

import org.primefaces.event.RowEditEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

import java.sql.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;


/**
 * @author Zathura Code Generator http://code.google.com/p/zathura
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class MenuView implements Serializable {
	
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(MenuView.class);
    private InputText txtCaption;
    private InputText txtDescription;
    private InputText txtIcon;
    private InputText txtPath;
    private InputText txtIdMenu;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<MenuDTO> data;
    private MenuDTO selectedMenu;
    private Menu entity;
    private boolean showDialog;
    private Integer[] selectOpciones;
    private Map<String,Integer> opciones;
    
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public MenuView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            MenuDTO menuDTO = (MenuDTO) e.getObject();

            if (txtCaption == null) {
                txtCaption = new InputText();
            }

            txtCaption.setValue(menuDTO.getCaption());

            if (txtDescription == null) {
                txtDescription = new InputText();
            }

            txtDescription.setValue(menuDTO.getDescription());

            if (txtIcon == null) {
                txtIcon = new InputText();
            }

            txtIcon.setValue(menuDTO.getIcon());

            if (txtPath == null) {
                txtPath = new InputText();
            }

            txtPath.setValue(menuDTO.getPath());

            if (txtIdMenu == null) {
                txtIdMenu = new InputText();
            }

            txtIdMenu.setValue(menuDTO.getIdMenu());

            Integer idMenu = FacesUtils.checkInteger(txtIdMenu);
            entity = businessDelegatorView.getMenu(idMenu);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedMenu = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedMenu = null;

        if (txtCaption != null) {
            txtCaption.setValue(null);
            txtCaption.setDisabled(false);
        }

        if (txtDescription != null) {
            txtDescription.setValue(null);
            txtDescription.setDisabled(false);
        }

        if (txtIcon != null) {
            txtIcon.setValue(null);
            txtIcon.setDisabled(false);
        }

        if (txtPath != null) {
            txtPath.setValue(null);
            txtPath.setDisabled(false);
        }

        if (btnSave != null) {
            btnSave.setDisabled(false);
        }

        if (btnDelete != null) {
            btnDelete.setDisabled(true);
        }

        return "";
    }

    public void listener_txtId() {
        try {
            Integer idMenu = FacesUtils.checkInteger(txtIdMenu);
            entity = (idMenu != null) ? businessDelegatorView.getMenu(idMenu)
                                      : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtCaption.setDisabled(false);
            txtDescription.setDisabled(false);
            txtIcon.setDisabled(false);
            txtPath.setDisabled(false);
            txtIdMenu.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtCaption.setValue(entity.getCaption());
            txtCaption.setDisabled(false);
            txtDescription.setValue(entity.getDescription());
            txtDescription.setDisabled(false);
            txtIcon.setValue(entity.getIcon());
            txtIcon.setDisabled(false);
            txtPath.setValue(entity.getPath());
            txtPath.setDisabled(false);
            txtIdMenu.setValue(entity.getIdMenu());
            txtIdMenu.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
    	
        selectedMenu = (MenuDTO) (evt.getComponent().getAttributes()
                                     .get("selectedMenu"));
        
        txtCaption.setValue(selectedMenu.getCaption());
        txtCaption.setDisabled(false);
        txtDescription.setValue(selectedMenu.getDescription());
        txtDescription.setDisabled(false);
        txtIcon.setValue(selectedMenu.getIcon());
        txtIcon.setDisabled(false);
        txtPath.setValue(selectedMenu.getPath());
        txtPath.setDisabled(false);
        
        Hibernate.initialize(selectedMenu.getOptionses());
        Set<Options> opciones = selectedMenu.getOptionses();
        
        selectOpciones = new Integer[opciones.size()];
        
        int i = 0;
        
        for (Iterator<Options> it = opciones.iterator(); it.hasNext(); ) {
   		 
		 	Options opt = it.next();
		 	selectOpciones[i] = opt.getIdoptions();
		 	i++;
        }
        
        //selectOpciones=
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedMenu == null) && (entity == null)) {
                action_create();
            } else {
                action_modify();
            }

            data = null;
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_create() {
        
    	try {
        	
            entity = new Menu();

            entity.setCaption(FacesUtils.checkString(txtCaption));
            entity.setDescription(FacesUtils.checkString(txtDescription));
            entity.setIcon(FacesUtils.checkString(txtIcon));
            entity.setPath(FacesUtils.checkString(txtPath));
            
            
            if(selectOpciones!=null && selectOpciones.length>0){
            	Set<Options> opciones = new HashSet();
            	for (int i = 0; i < selectOpciones.length; i++) {
	            	 
            		Options opc = businessDelegatorView.getOptions(selectOpciones[i]);
            		opciones.add(opc);
	            }
            	 entity.setOptionses(opciones);
            }
            	
            businessDelegatorView.saveMenu(entity);
            
            FacesUtils.addInfoMessage("Menu creado satisfactoriamente");
            action_clear();
            
        } catch (Exception e) {
            entity = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modify() {
        try {
        	
            if (entity == null) {
                Integer idMenu = new Integer(selectedMenu.getIdMenu());
                entity = businessDelegatorView.getMenu(idMenu);
            }

            entity.setCaption(FacesUtils.checkString(txtCaption));
            entity.setDescription(FacesUtils.checkString(txtDescription));
            entity.setIcon(FacesUtils.checkString(txtIcon));
            entity.setPath(FacesUtils.checkString(txtPath));
            
            Set<Options> opciones = entity.getOptionses();   
            opciones.clear();
            
            for (int i = 0; i < selectOpciones.length; i++) {  	 
        		Options opc = businessDelegatorView.getOptions(selectOpciones[i]);
        		opciones.add(opc);
            }
            
            entity.setOptionses(opciones);
            
            businessDelegatorView.updateMenu(entity);
            FacesUtils.addInfoMessage("Menu actualizado satisfactoriamente");
            
        } catch (Exception e) {
        	log.info(e.toString());
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedMenu = (MenuDTO) (evt.getComponent().getAttributes()
                                         .get("selectedMenu"));

            Integer idMenu = new Integer(selectedMenu.getIdMenu());
            entity = businessDelegatorView.getMenu(idMenu);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer idMenu = FacesUtils.checkInteger(txtIdMenu);
            entity = businessDelegatorView.getMenu(idMenu);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteMenu(entity);
            FacesUtils.addInfoMessage("Menu borrado satisfactoriamente");
            action_clear();
            data = null;
        } catch (Exception e) {
            throw e;
        }
    }

    public String action_closeDialog() {
        setShowDialog(false);
        action_clear();

        return "";
    }

    public String actionDeleteDataTableEditable(ActionEvent evt) {
        try {
            selectedMenu = (MenuDTO) (evt.getComponent().getAttributes()
                                         .get("selectedMenu"));

            Integer idMenu = new Integer(selectedMenu.getIdMenu());
            entity = businessDelegatorView.getMenu(idMenu);
            businessDelegatorView.deleteMenu(entity);
            data.remove(selectedMenu);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String caption, String description,
        String icon, Integer idMenu, String path) throws Exception {
        try {
            entity.setCaption(FacesUtils.checkString(caption));
            entity.setDescription(FacesUtils.checkString(description));
            entity.setIcon(FacesUtils.checkString(icon));
            entity.setPath(FacesUtils.checkString(path));
            businessDelegatorView.updateMenu(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("MenuView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtCaption() {
        return txtCaption;
    }

    public void setTxtCaption(InputText txtCaption) {
        this.txtCaption = txtCaption;
    }

    public InputText getTxtDescription() {
        return txtDescription;
    }

    public void setTxtDescription(InputText txtDescription) {
        this.txtDescription = txtDescription;
    }

    public InputText getTxtIcon() {
        return txtIcon;
    }

    public void setTxtIcon(InputText txtIcon) {
        this.txtIcon = txtIcon;
    }

    public InputText getTxtPath() {
        return txtPath;
    }

    public void setTxtPath(InputText txtPath) {
        this.txtPath = txtPath;
    }

    public InputText getTxtIdMenu() {
        return txtIdMenu;
    }

    public void setTxtIdMenu(InputText txtIdMenu) {
        this.txtIdMenu = txtIdMenu;
    }

    public List<MenuDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataMenu();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<MenuDTO> menuDTO) {
        this.data = menuDTO;
    }

    public MenuDTO getSelectedMenu() {
        return selectedMenu;
    }

    public void setSelectedMenu(MenuDTO menu) {
        this.selectedMenu = menu;
    }

    public CommandButton getBtnSave() {
        return btnSave;
    }

    public void setBtnSave(CommandButton btnSave) {
        this.btnSave = btnSave;
    }

    public CommandButton getBtnModify() {
        return btnModify;
    }

    public void setBtnModify(CommandButton btnModify) {
        this.btnModify = btnModify;
    }

    public CommandButton getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(CommandButton btnDelete) {
        this.btnDelete = btnDelete;
    }

    public CommandButton getBtnClear() {
        return btnClear;
    }

    public void setBtnClear(CommandButton btnClear) {
        this.btnClear = btnClear;
    }

    public TimeZone getTimeZone() {
        return java.util.TimeZone.getDefault();
    }

    public IBusinessDelegatorView getBusinessDelegatorView() {
        return businessDelegatorView;
    }

    public void setBusinessDelegatorView(
        IBusinessDelegatorView businessDelegatorView) {
        this.businessDelegatorView = businessDelegatorView;
    }

    public boolean isShowDialog() {
        return showDialog;
    }

    public void setShowDialog(boolean showDialog) {
        this.showDialog = showDialog;
    }

	public Integer[] getSelectOpciones() {
		return selectOpciones;
	}

	public void setSelectOpciones(Integer[] selectOpciones) {
		this.selectOpciones = selectOpciones;
	}

	public Map<String, Integer> getOpciones() {
		
		if(opciones==null){
    		try {
				List<Options> opcionesList = businessDelegatorView.getOptions();
				
				opciones = new LinkedHashMap<String, Integer>();
				
				for (Options opcionestmp : opcionesList) {
					opciones.put(opcionestmp.getOptionsName(), opcionestmp.getIdoptions());
				}
			} catch (Exception e) {				
				e.printStackTrace();
			}
    	}
		
		return opciones;
	}

	public void setOpciones(Map<String, Integer> opciones) {
		this.opciones = opciones;
	}
    
    
}
