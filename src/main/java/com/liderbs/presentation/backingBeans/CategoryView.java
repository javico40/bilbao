package com.liderbs.presentation.backingBeans;

import com.liderbs.exceptions.*;

import com.liderbs.modelo.*;
import com.liderbs.modelo.dto.CategoryDTO;

import com.liderbs.presentation.businessDelegate.*;

import com.liderbs.utilities.*;

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
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;


/**
 * @author Zathura Code Generator http://code.google.com/p/zathura
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class CategoryView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(CategoryView.class);
    private InputText txtDescription;
    private InputText txtIdlevel_Level;
    private InputText txtIdcategory;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<CategoryDTO> data;
    private CategoryDTO selectedCategory;
    private Category entity;
    
    private List<SelectItem> listCategory;
    
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public CategoryView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            CategoryDTO categoryDTO = (CategoryDTO) e.getObject();

            if (txtDescription == null) {
                txtDescription = new InputText();
            }

            txtDescription.setValue(categoryDTO.getDescription());

            if (txtIdlevel_Level == null) {
                txtIdlevel_Level = new InputText();
            }

            txtIdlevel_Level.setValue(categoryDTO.getIdlevel_Level());

            if (txtIdcategory == null) {
                txtIdcategory = new InputText();
            }

            txtIdcategory.setValue(categoryDTO.getIdcategory());

            Integer idcategory = FacesUtils.checkInteger(txtIdcategory);
            entity = businessDelegatorView.getCategory(idcategory);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedCategory = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedCategory = null;

        if (txtDescription != null) {
            txtDescription.setValue(null);
            txtDescription.setDisabled(true);
        }

        if (txtIdlevel_Level != null) {
            txtIdlevel_Level.setValue(null);
            txtIdlevel_Level.setDisabled(true);
        }

        if (txtIdcategory != null) {
            txtIdcategory.setValue(null);
            txtIdcategory.setDisabled(false);
        }

        if (btnSave != null) {
            btnSave.setDisabled(true);
        }

        if (btnDelete != null) {
            btnDelete.setDisabled(true);
        }

        return "";
    }

    public void listener_txtId() {
        try {
            Integer idcategory = FacesUtils.checkInteger(txtIdcategory);
            entity = (idcategory != null)
                ? businessDelegatorView.getCategory(idcategory) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtDescription.setDisabled(false);
            txtIdlevel_Level.setDisabled(false);
            txtIdcategory.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtDescription.setValue(entity.getDescription());
            txtDescription.setDisabled(false);
            txtIdlevel_Level.setValue(entity.getLevel().getIdlevel());
            txtIdlevel_Level.setDisabled(false);
            txtIdcategory.setValue(entity.getIdcategory());
            txtIdcategory.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedCategory = (CategoryDTO) (evt.getComponent().getAttributes()
                                             .get("selectedCategory"));
        txtDescription.setValue(selectedCategory.getDescription());
        txtDescription.setDisabled(false);
        txtIdlevel_Level.setValue(selectedCategory.getIdlevel_Level());
        txtIdlevel_Level.setDisabled(false);
        txtIdcategory.setValue(selectedCategory.getIdcategory());
        txtIdcategory.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedCategory == null) && (entity == null)) {
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
            entity = new Category();

            Integer idcategory = FacesUtils.checkInteger(txtIdcategory);

            entity.setDescription(FacesUtils.checkString(txtDescription));
            entity.setIdcategory(idcategory);
            entity.setLevel((FacesUtils.checkInteger(txtIdlevel_Level) != null)
                ? businessDelegatorView.getLevel(FacesUtils.checkInteger(
                        txtIdlevel_Level)) : null);
            //entity.setUserses(FacesUtils.checkUsers(txtUserses));
            businessDelegatorView.saveCategory(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
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
                Integer idcategory = new Integer(selectedCategory.getIdcategory());
                entity = businessDelegatorView.getCategory(idcategory);
            }

            entity.setDescription(FacesUtils.checkString(txtDescription));
            entity.setLevel((FacesUtils.checkInteger(txtIdlevel_Level) != null)
                ? businessDelegatorView.getLevel(FacesUtils.checkInteger(
                        txtIdlevel_Level)) : null);
            //entity.setUserses(FacesUtils.checkUsers(txtUserses));
            businessDelegatorView.updateCategory(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedCategory = (CategoryDTO) (evt.getComponent().getAttributes()
                                                 .get("selectedCategory"));

            Integer idcategory = new Integer(selectedCategory.getIdcategory());
            entity = businessDelegatorView.getCategory(idcategory);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer idcategory = FacesUtils.checkInteger(txtIdcategory);
            entity = businessDelegatorView.getCategory(idcategory);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteCategory(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
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
            selectedCategory = (CategoryDTO) (evt.getComponent().getAttributes()
                                                 .get("selectedCategory"));

            Integer idcategory = new Integer(selectedCategory.getIdcategory());
            entity = businessDelegatorView.getCategory(idcategory);
            businessDelegatorView.deleteCategory(entity);
            data.remove(selectedCategory);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String description, Integer idcategory,
        Integer idlevel_Level) throws Exception {
        try {
            entity.setDescription(FacesUtils.checkString(description));
            businessDelegatorView.updateCategory(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("CategoryView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtDescription() {
        return txtDescription;
    }

    public void setTxtDescription(InputText txtDescription) {
        this.txtDescription = txtDescription;
    }

    public InputText getTxtIdlevel_Level() {
        return txtIdlevel_Level;
    }

    public void setTxtIdlevel_Level(InputText txtIdlevel_Level) {
        this.txtIdlevel_Level = txtIdlevel_Level;
    }

    public InputText getTxtIdcategory() {
        return txtIdcategory;
    }

    public void setTxtIdcategory(InputText txtIdcategory) {
        this.txtIdcategory = txtIdcategory;
    }

    public List<CategoryDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataCategory();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<CategoryDTO> categoryDTO) {
        this.data = categoryDTO;
    }

    public CategoryDTO getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(CategoryDTO category) {
        this.selectedCategory = category;
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

	public List<SelectItem> getListCategory() {
		return listCategory;
	}

	public void setListCategory(List<SelectItem> listCategory) {
		this.listCategory = listCategory;
	}
    
    
}
