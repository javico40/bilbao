package com.liderbs.presentation.backingBeans;

import com.liderbs.exceptions.*;

import com.liderbs.modelo.*;
import com.liderbs.modelo.dto.AccountTypeDTO;

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


/**
 * @author Zathura Code Generator http://code.google.com/p/zathura
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class AccountTypeView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(AccountTypeView.class);
    private InputText txtAccountTypeDescription;
    private InputText txtAccountTypeName;
    private InputText txtIdaccountType;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<AccountTypeDTO> data;
    private AccountTypeDTO selectedAccountType;
    private AccountType entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public AccountTypeView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            AccountTypeDTO accountTypeDTO = (AccountTypeDTO) e.getObject();

            if (txtAccountTypeDescription == null) {
                txtAccountTypeDescription = new InputText();
            }

            txtAccountTypeDescription.setValue(accountTypeDTO.getAccountTypeDescription());

            if (txtAccountTypeName == null) {
                txtAccountTypeName = new InputText();
            }

            txtAccountTypeName.setValue(accountTypeDTO.getAccountTypeName());

            if (txtIdaccountType == null) {
                txtIdaccountType = new InputText();
            }

            txtIdaccountType.setValue(accountTypeDTO.getIdaccountType());

            Integer idaccountType = FacesUtils.checkInteger(txtIdaccountType);
            entity = businessDelegatorView.getAccountType(idaccountType);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedAccountType = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedAccountType = null;

        if (txtAccountTypeDescription != null) {
            txtAccountTypeDescription.setValue(null);
            txtAccountTypeDescription.setDisabled(true);
        }

        if (txtAccountTypeName != null) {
            txtAccountTypeName.setValue(null);
            txtAccountTypeName.setDisabled(true);
        }

        if (txtIdaccountType != null) {
            txtIdaccountType.setValue(null);
            txtIdaccountType.setDisabled(false);
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
            Integer idaccountType = FacesUtils.checkInteger(txtIdaccountType);
            entity = (idaccountType != null)
                ? businessDelegatorView.getAccountType(idaccountType) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtAccountTypeDescription.setDisabled(false);
            txtAccountTypeName.setDisabled(false);
            txtIdaccountType.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtAccountTypeDescription.setValue(entity.getAccountTypeDescription());
            txtAccountTypeDescription.setDisabled(false);
            txtAccountTypeName.setValue(entity.getAccountTypeName());
            txtAccountTypeName.setDisabled(false);
            txtIdaccountType.setValue(entity.getIdaccountType());
            txtIdaccountType.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedAccountType = (AccountTypeDTO) (evt.getComponent()
                                                   .getAttributes()
                                                   .get("selectedAccountType"));
        txtAccountTypeDescription.setValue(selectedAccountType.getAccountTypeDescription());
        txtAccountTypeDescription.setDisabled(false);
        txtAccountTypeName.setValue(selectedAccountType.getAccountTypeName());
        txtAccountTypeName.setDisabled(false);
        txtIdaccountType.setValue(selectedAccountType.getIdaccountType());
        txtIdaccountType.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedAccountType == null) && (entity == null)) {
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
            entity = new AccountType();

            Integer idaccountType = FacesUtils.checkInteger(txtIdaccountType);

            entity.setAccountTypeDescription(FacesUtils.checkString(
                    txtAccountTypeDescription));
            entity.setAccountTypeName(FacesUtils.checkString(txtAccountTypeName));
            entity.setIdaccountType(idaccountType);
            businessDelegatorView.saveAccountType(entity);
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
                Integer idaccountType = new Integer(selectedAccountType.getIdaccountType());
                entity = businessDelegatorView.getAccountType(idaccountType);
            }

            entity.setAccountTypeDescription(FacesUtils.checkString(
                    txtAccountTypeDescription));
            entity.setAccountTypeName(FacesUtils.checkString(txtAccountTypeName));
            businessDelegatorView.updateAccountType(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedAccountType = (AccountTypeDTO) (evt.getComponent()
                                                       .getAttributes()
                                                       .get("selectedAccountType"));

            Integer idaccountType = new Integer(selectedAccountType.getIdaccountType());
            entity = businessDelegatorView.getAccountType(idaccountType);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer idaccountType = FacesUtils.checkInteger(txtIdaccountType);
            entity = businessDelegatorView.getAccountType(idaccountType);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteAccountType(entity);
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
            selectedAccountType = (AccountTypeDTO) (evt.getComponent()
                                                       .getAttributes()
                                                       .get("selectedAccountType"));

            Integer idaccountType = new Integer(selectedAccountType.getIdaccountType());
            entity = businessDelegatorView.getAccountType(idaccountType);
            businessDelegatorView.deleteAccountType(entity);
            data.remove(selectedAccountType);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String accountTypeDescription,
        String accountTypeName, Integer idaccountType)
        throws Exception {
        try {
            entity.setAccountTypeDescription(FacesUtils.checkString(
                    accountTypeDescription));
            entity.setAccountTypeName(FacesUtils.checkString(accountTypeName));
            businessDelegatorView.updateAccountType(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("AccountTypeView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtAccountTypeDescription() {
        return txtAccountTypeDescription;
    }

    public void setTxtAccountTypeDescription(
        InputText txtAccountTypeDescription) {
        this.txtAccountTypeDescription = txtAccountTypeDescription;
    }

    public InputText getTxtAccountTypeName() {
        return txtAccountTypeName;
    }

    public void setTxtAccountTypeName(InputText txtAccountTypeName) {
        this.txtAccountTypeName = txtAccountTypeName;
    }

    public InputText getTxtIdaccountType() {
        return txtIdaccountType;
    }

    public void setTxtIdaccountType(InputText txtIdaccountType) {
        this.txtIdaccountType = txtIdaccountType;
    }

    public List<AccountTypeDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataAccountType();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<AccountTypeDTO> accountTypeDTO) {
        this.data = accountTypeDTO;
    }

    public AccountTypeDTO getSelectedAccountType() {
        return selectedAccountType;
    }

    public void setSelectedAccountType(AccountTypeDTO accountType) {
        this.selectedAccountType = accountType;
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
}
