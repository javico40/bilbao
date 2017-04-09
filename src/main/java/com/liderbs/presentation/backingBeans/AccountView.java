package com.liderbs.presentation.backingBeans;

import com.liderbs.exceptions.*;

import com.liderbs.modelo.*;
import com.liderbs.modelo.dto.AccountDTO;

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
public class AccountView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(AccountView.class);
    private InputText txtAccountDescription;
    private InputText txtAccountName;
    private InputText txtAccountStatus;
    private InputText txtAccountUserCreated;
    private InputText txtIdprofile_Profile;
    private InputText txtIdAccount;
    private Calendar txtAccountCreated;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<AccountDTO> data;
    private AccountDTO selectedAccount;
    private Account entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public AccountView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            AccountDTO accountDTO = (AccountDTO) e.getObject();

            if (txtAccountDescription == null) {
                txtAccountDescription = new InputText();
            }

            txtAccountDescription.setValue(accountDTO.getAccountDescription());

            if (txtAccountName == null) {
                txtAccountName = new InputText();
            }

            txtAccountName.setValue(accountDTO.getAccountName());

            if (txtAccountStatus == null) {
                txtAccountStatus = new InputText();
            }

            txtAccountStatus.setValue(accountDTO.getAccountStatus());

            if (txtAccountUserCreated == null) {
                txtAccountUserCreated = new InputText();
            }

            txtAccountUserCreated.setValue(accountDTO.getAccountUserCreated());

            if (txtIdprofile_Profile == null) {
                txtIdprofile_Profile = new InputText();
            }

            txtIdprofile_Profile.setValue(accountDTO.getIdprofile_Profile());

            if (txtIdAccount == null) {
                txtIdAccount = new InputText();
            }

            txtIdAccount.setValue(accountDTO.getIdAccount());

            if (txtAccountCreated == null) {
                txtAccountCreated = new Calendar();
            }

            txtAccountCreated.setValue(accountDTO.getAccountCreated());

            Integer idAccount = FacesUtils.checkInteger(txtIdAccount);
            entity = businessDelegatorView.getAccount(idAccount);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedAccount = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
    	
        entity = null;
        selectedAccount = null;

        if (txtAccountDescription != null) {
            txtAccountDescription.setValue(null);
            txtAccountDescription.setDisabled(false);
        }

        if (txtAccountName != null) {
            txtAccountName.setValue(null);
            txtAccountName.setDisabled(false);
        }

        if (txtAccountStatus != null) {
            txtAccountStatus.setValue(null);
            txtAccountStatus.setDisabled(false);
        }

        if (txtAccountUserCreated != null) {
            txtAccountUserCreated.setValue(null);
            txtAccountUserCreated.setDisabled(false);
        }

        if (txtIdprofile_Profile != null) {
            txtIdprofile_Profile.setValue(null);
            txtIdprofile_Profile.setDisabled(false);
        }

        if (txtAccountCreated != null) {
            txtAccountCreated.setValue(null);
            txtAccountCreated.setDisabled(false);
        }

        if (txtIdAccount != null) {
            txtIdAccount.setValue(null);
            txtIdAccount.setDisabled(false);
        }

        if (btnSave != null) {
            btnSave.setDisabled(false);
        }

        if (btnDelete != null) {
            btnDelete.setDisabled(false);
        }

        return "";
    }

    public void listener_txtAccountCreated() {
        Date inputDate = (Date) txtAccountCreated.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtId() {
        try {
            Integer idAccount = FacesUtils.checkInteger(txtIdAccount);
            entity = (idAccount != null)
                ? businessDelegatorView.getAccount(idAccount) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtAccountDescription.setDisabled(false);
            txtAccountName.setDisabled(false);
            txtAccountStatus.setDisabled(false);
            txtAccountUserCreated.setDisabled(false);
            txtIdprofile_Profile.setDisabled(false);
            txtAccountCreated.setDisabled(false);
            txtIdAccount.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtAccountCreated.setValue(entity.getAccountCreated());
            txtAccountCreated.setDisabled(false);
            txtAccountDescription.setValue(entity.getAccountDescription());
            txtAccountDescription.setDisabled(false);
            txtAccountName.setValue(entity.getAccountName());
            txtAccountName.setDisabled(false);
            txtAccountStatus.setValue(entity.getAccountStatus());
            txtAccountStatus.setDisabled(false);
            txtAccountUserCreated.setValue(entity.getAccountUserCreated());
            txtAccountUserCreated.setDisabled(false);
            txtIdprofile_Profile.setValue(entity.getProfile().getIdprofile());
            txtIdprofile_Profile.setDisabled(false);
            txtIdAccount.setValue(entity.getIdAccount());
            txtIdAccount.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
    	
        selectedAccount = (AccountDTO) (evt.getComponent().getAttributes()
                                           .get("selectedAccount"));
        
        txtAccountName.setValue(selectedAccount.getAccountName());
        txtAccountName.setDisabled(false);
        txtAccountDescription.setValue(selectedAccount.getAccountDescription());
        txtAccountDescription.setDisabled(false);
        txtAccountStatus.setValue(selectedAccount.getAccountStatus());
        txtAccountStatus.setDisabled(false);
        txtIdprofile_Profile.setValue(selectedAccount.getIdprofile_Profile());
        txtIdprofile_Profile.setDisabled(false);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedAccount == null) && (entity == null)) {
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
        	
            entity = new Account();

            entity.setAccountCreated(FacesUtils.checkDate(txtAccountCreated));
            entity.setAccountDescription(FacesUtils.checkString(
                    txtAccountDescription));
            entity.setAccountName(FacesUtils.checkString(txtAccountName));
            entity.setAccountStatus(FacesUtils.checkInteger(txtAccountStatus));
            entity.setAccountUserCreated(FacesUtils.checkString(
                    txtAccountUserCreated));
            entity.setProfile((FacesUtils.checkInteger(txtIdprofile_Profile) != null)
                ? businessDelegatorView.getProfile(FacesUtils.checkInteger(
                        txtIdprofile_Profile)) : null);
            //entity.setUserses(FacesUtils.checkUsers(txtUserses));
            businessDelegatorView.saveAccount(entity);
            FacesUtils.addInfoMessage("Cuenta creada satisfactoriamente");
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
                Integer idAccount = new Integer(selectedAccount.getIdAccount());
                entity = businessDelegatorView.getAccount(idAccount);
            }

            entity.setAccountCreated(FacesUtils.checkDate(txtAccountCreated));
            entity.setAccountDescription(FacesUtils.checkString(
                    txtAccountDescription));
            entity.setAccountName(FacesUtils.checkString(txtAccountName));
            entity.setAccountStatus(FacesUtils.checkInteger(txtAccountStatus));
            entity.setAccountUserCreated(FacesUtils.checkString(
                    txtAccountUserCreated));
            entity.setProfile((FacesUtils.checkInteger(txtIdprofile_Profile) != null)
                ? businessDelegatorView.getProfile(FacesUtils.checkInteger(
                        txtIdprofile_Profile)) : null);
            //entity.setUserses(FacesUtils.checkUsers(txtUserses));
            businessDelegatorView.updateAccount(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedAccount = (AccountDTO) (evt.getComponent().getAttributes()
                                               .get("selectedAccount"));

            Integer idAccount = new Integer(selectedAccount.getIdAccount());
            entity = businessDelegatorView.getAccount(idAccount);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer idAccount = FacesUtils.checkInteger(txtIdAccount);
            entity = businessDelegatorView.getAccount(idAccount);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteAccount(entity);
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
            selectedAccount = (AccountDTO) (evt.getComponent().getAttributes()
                                               .get("selectedAccount"));

            Integer idAccount = new Integer(selectedAccount.getIdAccount());
            entity = businessDelegatorView.getAccount(idAccount);
            businessDelegatorView.deleteAccount(entity);
            data.remove(selectedAccount);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Date accountCreated,
        String accountDescription, String accountName, Integer accountStatus,
        String accountUserCreated, Integer idAccount, Integer idprofile_Profile)
        throws Exception {
        try {
            entity.setAccountCreated(FacesUtils.checkDate(accountCreated));
            entity.setAccountDescription(FacesUtils.checkString(
                    accountDescription));
            entity.setAccountName(FacesUtils.checkString(accountName));
            entity.setAccountStatus(FacesUtils.checkInteger(accountStatus));
            entity.setAccountUserCreated(FacesUtils.checkString(
                    accountUserCreated));
            businessDelegatorView.updateAccount(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("AccountView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtAccountDescription() {
        return txtAccountDescription;
    }

    public void setTxtAccountDescription(InputText txtAccountDescription) {
        this.txtAccountDescription = txtAccountDescription;
    }

    public InputText getTxtAccountName() {
        return txtAccountName;
    }

    public void setTxtAccountName(InputText txtAccountName) {
        this.txtAccountName = txtAccountName;
    }

    public InputText getTxtAccountStatus() {
        return txtAccountStatus;
    }

    public void setTxtAccountStatus(InputText txtAccountStatus) {
        this.txtAccountStatus = txtAccountStatus;
    }

    public InputText getTxtAccountUserCreated() {
        return txtAccountUserCreated;
    }

    public void setTxtAccountUserCreated(InputText txtAccountUserCreated) {
        this.txtAccountUserCreated = txtAccountUserCreated;
    }

    public InputText getTxtIdprofile_Profile() {
        return txtIdprofile_Profile;
    }

    public void setTxtIdprofile_Profile(InputText txtIdprofile_Profile) {
        this.txtIdprofile_Profile = txtIdprofile_Profile;
    }

    public Calendar getTxtAccountCreated() {
        return txtAccountCreated;
    }

    public void setTxtAccountCreated(Calendar txtAccountCreated) {
        this.txtAccountCreated = txtAccountCreated;
    }

    public InputText getTxtIdAccount() {
        return txtIdAccount;
    }

    public void setTxtIdAccount(InputText txtIdAccount) {
        this.txtIdAccount = txtIdAccount;
    }

    public List<AccountDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataAccount();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<AccountDTO> accountDTO) {
        this.data = accountDTO;
    }

    public AccountDTO getSelectedAccount() {
        return selectedAccount;
    }

    public void setSelectedAccount(AccountDTO account) {
        this.selectedAccount = account;
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
