package com.liderbs.presentation.backingBeans;

import com.liderbs.exceptions.*;

import com.liderbs.modelo.*;
import com.liderbs.modelo.dto.UsersDTO;

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
public class UsersView implements Serializable {
	
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(UsersView.class);
    private InputText txtCellphone;
    private InputText txtFixedphone;
    private InputText txtLastname;
    private InputText txtName;
    private InputText txtPassword;
    private InputText txtSaldo;
    private InputText txtStatus;
    private InputText txtUserid;
    private InputText txtUsername;
    private InputText txtIdprofile_Profile;
    private InputText txtIdusers;
    private InputText txtProfileIdprofile;
    private Calendar txtCreated;
    private Calendar txtLastlogin;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<UsersDTO> data;
    private UsersDTO selectedUsers;
    private Users entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public UsersView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            UsersDTO usersDTO = (UsersDTO) e.getObject();

            if (txtCellphone == null) {
                txtCellphone = new InputText();
            }

            txtCellphone.setValue(usersDTO.getCellphone());

            if (txtFixedphone == null) {
                txtFixedphone = new InputText();
            }

            txtFixedphone.setValue(usersDTO.getFixedphone());

            if (txtLastname == null) {
                txtLastname = new InputText();
            }

            txtLastname.setValue(usersDTO.getLastname());

            if (txtName == null) {
                txtName = new InputText();
            }

            txtName.setValue(usersDTO.getName());

            if (txtPassword == null) {
                txtPassword = new InputText();
            }

            txtPassword.setValue(usersDTO.getPassword());

            if (txtSaldo == null) {
                txtSaldo = new InputText();
            }

            txtSaldo.setValue(usersDTO.getSaldo());

            if (txtStatus == null) {
                txtStatus = new InputText();
            }

            txtStatus.setValue(usersDTO.getStatus());

            if (txtUserid == null) {
                txtUserid = new InputText();
            }

            txtUserid.setValue(usersDTO.getUserid());

            if (txtUsername == null) {
                txtUsername = new InputText();
            }

            txtUsername.setValue(usersDTO.getUsername());

            if (txtIdprofile_Profile == null) {
                txtIdprofile_Profile = new InputText();
            }

            txtIdprofile_Profile.setValue(usersDTO.getIdprofile_Profile());

            if (txtIdusers == null) {
                txtIdusers = new InputText();
            }

            txtIdusers.setValue(usersDTO.getIdusers());

            if (txtProfileIdprofile == null) {
                txtProfileIdprofile = new InputText();
            }

            txtProfileIdprofile.setValue(usersDTO.getProfileIdprofile());

            if (txtCreated == null) {
                txtCreated = new Calendar();
            }

            txtCreated.setValue(usersDTO.getCreated());

            if (txtLastlogin == null) {
                txtLastlogin = new Calendar();
            }

            txtLastlogin.setValue(usersDTO.getLastlogin());

            UsersId id = new UsersId();
            id.setIdusers((((txtIdusers.getValue()) == null) ||
                (txtIdusers.getValue()).equals("")) ? null
                                                    : FacesUtils.checkInteger(
                    txtIdusers));
            id.setProfileIdprofile((((txtProfileIdprofile.getValue()) == null) ||
                (txtProfileIdprofile.getValue()).equals("")) ? null
                                                             : FacesUtils.checkInteger(
                    txtProfileIdprofile));
            //entity = businessDelegatorView.getUsers(id);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedUsers = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedUsers = null;

        if (txtCellphone != null) {
            txtCellphone.setValue(null);
            txtCellphone.setDisabled(true);
        }

        if (txtFixedphone != null) {
            txtFixedphone.setValue(null);
            txtFixedphone.setDisabled(true);
        }

        if (txtLastname != null) {
            txtLastname.setValue(null);
            txtLastname.setDisabled(true);
        }

        if (txtName != null) {
            txtName.setValue(null);
            txtName.setDisabled(true);
        }

        if (txtPassword != null) {
            txtPassword.setValue(null);
            txtPassword.setDisabled(true);
        }

        if (txtSaldo != null) {
            txtSaldo.setValue(null);
            txtSaldo.setDisabled(true);
        }

        if (txtStatus != null) {
            txtStatus.setValue(null);
            txtStatus.setDisabled(true);
        }

        if (txtUserid != null) {
            txtUserid.setValue(null);
            txtUserid.setDisabled(true);
        }

        if (txtUsername != null) {
            txtUsername.setValue(null);
            txtUsername.setDisabled(true);
        }

        if (txtIdprofile_Profile != null) {
            txtIdprofile_Profile.setValue(null);
            txtIdprofile_Profile.setDisabled(true);
        }

        if (txtCreated != null) {
            txtCreated.setValue(null);
            txtCreated.setDisabled(true);
        }

        if (txtLastlogin != null) {
            txtLastlogin.setValue(null);
            txtLastlogin.setDisabled(true);
        }

        if (txtIdusers != null) {
            txtIdusers.setValue(null);
            txtIdusers.setDisabled(false);
        }

        if (txtProfileIdprofile != null) {
            txtProfileIdprofile.setValue(null);
            txtProfileIdprofile.setDisabled(false);
        }

        if (btnSave != null) {
            btnSave.setDisabled(true);
        }

        if (btnDelete != null) {
            btnDelete.setDisabled(true);
        }

        return "";
    }

    public void listener_txtCreated() {
        Date inputDate = (Date) txtCreated.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtLastlogin() {
        Date inputDate = (Date) txtLastlogin.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtId() {
        try {
            UsersId id = new UsersId();
            id.setIdusers((((txtIdusers.getValue()) == null) ||
                (txtIdusers.getValue()).equals("")) ? null
                                                    : FacesUtils.checkInteger(
                    txtIdusers));
            id.setProfileIdprofile((((txtProfileIdprofile.getValue()) == null) ||
                (txtProfileIdprofile.getValue()).equals("")) ? null
                                                             : FacesUtils.checkInteger(
                    txtProfileIdprofile));
            //entity = (id != null) ? businessDelegatorView.getUsers(id) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtCellphone.setDisabled(false);
            txtFixedphone.setDisabled(false);
            txtLastname.setDisabled(false);
            txtName.setDisabled(false);
            txtPassword.setDisabled(false);
            txtSaldo.setDisabled(false);
            txtStatus.setDisabled(false);
            txtUserid.setDisabled(false);
            txtUsername.setDisabled(false);
            txtIdprofile_Profile.setDisabled(false);
            txtCreated.setDisabled(false);
            txtLastlogin.setDisabled(false);
            txtIdusers.setDisabled(false);
            txtProfileIdprofile.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtCellphone.setValue(entity.getCellphone());
            txtCellphone.setDisabled(false);
            txtCreated.setValue(entity.getCreated());
            txtCreated.setDisabled(false);
            txtFixedphone.setValue(entity.getFixedphone());
            txtFixedphone.setDisabled(false);
            txtLastlogin.setValue(entity.getLastlogin());
            txtLastlogin.setDisabled(false);
            txtLastname.setValue(entity.getLastname());
            txtLastname.setDisabled(false);
            txtName.setValue(entity.getName());
            txtName.setDisabled(false);
            txtPassword.setValue(entity.getPassword());
            txtPassword.setDisabled(false);
            txtSaldo.setValue(entity.getSaldo());
            txtSaldo.setDisabled(false);
            txtStatus.setValue(entity.getStatus());
            txtStatus.setDisabled(false);
            txtUserid.setValue(entity.getUserid());
            txtUserid.setDisabled(false);
            txtUsername.setValue(entity.getUsername());
            txtUsername.setDisabled(false);
            txtIdprofile_Profile.setValue(entity.getProfile().getIdprofile());
            txtIdprofile_Profile.setDisabled(false);
            txtIdusers.setValue(entity.getId());
            txtIdusers.setDisabled(true);
            txtProfileIdprofile.setValue(entity.getProfile());
            txtProfileIdprofile.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedUsers = (UsersDTO) (evt.getComponent().getAttributes()
                                       .get("selectedUsers"));
        txtCellphone.setValue(selectedUsers.getCellphone());
        txtCellphone.setDisabled(false);
        txtCreated.setValue(selectedUsers.getCreated());
        txtCreated.setDisabled(false);
        txtFixedphone.setValue(selectedUsers.getFixedphone());
        txtFixedphone.setDisabled(false);
        txtLastlogin.setValue(selectedUsers.getLastlogin());
        txtLastlogin.setDisabled(false);
        txtLastname.setValue(selectedUsers.getLastname());
        txtLastname.setDisabled(false);
        txtName.setValue(selectedUsers.getName());
        txtName.setDisabled(false);
        txtPassword.setValue(selectedUsers.getPassword());
        txtPassword.setDisabled(false);
        txtSaldo.setValue(selectedUsers.getSaldo());
        txtSaldo.setDisabled(false);
        txtStatus.setValue(selectedUsers.getStatus());
        txtStatus.setDisabled(false);
        txtUserid.setValue(selectedUsers.getUserid());
        txtUserid.setDisabled(false);
        txtUsername.setValue(selectedUsers.getUsername());
        txtUsername.setDisabled(false);
        txtIdprofile_Profile.setValue(selectedUsers.getIdprofile_Profile());
        txtIdprofile_Profile.setDisabled(false);
        txtIdusers.setValue(selectedUsers.getIdusers());
        txtIdusers.setDisabled(true);
        txtProfileIdprofile.setValue(selectedUsers.getProfileIdprofile());
        txtProfileIdprofile.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedUsers == null) && (entity == null)) {
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
            entity = new Users();

            UsersId id = new UsersId();
            
            /*
            id.setIdusers((((txtIdusers.getValue()) == null) ||
                (txtIdusers.getValue()).equals("")) ? null
                                                    : FacesUtils.checkInteger(
                    txtIdusers));
            id.setProfileIdprofile((((txtProfileIdprofile.getValue()) == null) ||
                (txtProfileIdprofile.getValue()).equals("")) ? null
                                                             : FacesUtils.checkInteger(
                    txtProfileIdprofile));
            */
            
            entity.setCellphone(FacesUtils.checkString(txtCellphone));
            entity.setCreated(FacesUtils.checkDate(txtCreated));
            entity.setFixedphone(FacesUtils.checkString(txtFixedphone));
            entity.setId(0L);
            entity.setLastlogin(FacesUtils.checkDate(txtLastlogin));
            entity.setLastname(FacesUtils.checkString(txtLastname));
            entity.setName(FacesUtils.checkString(txtName));
            entity.setPassword(FacesUtils.checkString(txtPassword));
            entity.setSaldo(FacesUtils.checkFloat(txtSaldo));
            entity.setStatus(FacesUtils.checkInteger(txtStatus));
            entity.setUserid(FacesUtils.checkString(txtUserid));
            entity.setUsername(FacesUtils.checkString(txtUsername));
            entity.setProfile(businessDelegatorView.getProfile(
                    entity.getProfile().getIdprofile()));
            businessDelegatorView.saveUsers(entity);
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
                UsersId id = new UsersId();
                id.setIdusers(selectedUsers.getIdusers());
                id.setProfileIdprofile(selectedUsers.getProfileIdprofile());
                //entity = businessDelegatorView.getUsers(id);
            }

            entity.setCellphone(FacesUtils.checkString(txtCellphone));
            entity.setCreated(FacesUtils.checkDate(txtCreated));
            entity.setFixedphone(FacesUtils.checkString(txtFixedphone));
            entity.setLastlogin(FacesUtils.checkDate(txtLastlogin));
            entity.setLastname(FacesUtils.checkString(txtLastname));
            entity.setName(FacesUtils.checkString(txtName));
            entity.setPassword(FacesUtils.checkString(txtPassword));
            entity.setSaldo(FacesUtils.checkFloat(txtSaldo));
            entity.setStatus(FacesUtils.checkInteger(txtStatus));
            entity.setUserid(FacesUtils.checkString(txtUserid));
            entity.setUsername(FacesUtils.checkString(txtUsername));
            businessDelegatorView.updateUsers(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedUsers = (UsersDTO) (evt.getComponent().getAttributes()
                                           .get("selectedUsers"));

            UsersId id = new UsersId();
            id.setIdusers(selectedUsers.getIdusers());
            id.setProfileIdprofile(selectedUsers.getProfileIdprofile());
            //entity = businessDelegatorView.getUsers(id);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            UsersId id = new UsersId();
            id.setIdusers((((txtIdusers.getValue()) == null) ||
                (txtIdusers.getValue()).equals("")) ? null
                                                    : FacesUtils.checkInteger(
                    txtIdusers));
            id.setProfileIdprofile((((txtProfileIdprofile.getValue()) == null) ||
                (txtProfileIdprofile.getValue()).equals("")) ? null
                                                             : FacesUtils.checkInteger(
                    txtProfileIdprofile));
            //entity = businessDelegatorView.getUsers(id);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteUsers(entity);
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
            selectedUsers = (UsersDTO) (evt.getComponent().getAttributes()
                                           .get("selectedUsers"));

            UsersId id = new UsersId();
            id.setIdusers(selectedUsers.getIdusers());
            id.setProfileIdprofile(selectedUsers.getProfileIdprofile());
            //entity = businessDelegatorView.getUsers(id);
            businessDelegatorView.deleteUsers(entity);
            data.remove(selectedUsers);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Integer idusers,
        Integer profileIdprofile, String cellphone, Date created,
        String fixedphone, Date lastlogin, String lastname, String name,
        String password, Float saldo, Integer status, String userid,
        String username, Integer idprofile_Profile) throws Exception {
        try {
            entity.setCellphone(FacesUtils.checkString(cellphone));
            entity.setCreated(FacesUtils.checkDate(created));
            entity.setFixedphone(FacesUtils.checkString(fixedphone));
            entity.setLastlogin(FacesUtils.checkDate(lastlogin));
            entity.setLastname(FacesUtils.checkString(lastname));
            entity.setName(FacesUtils.checkString(name));
            entity.setPassword(FacesUtils.checkString(password));
            entity.setSaldo(FacesUtils.checkFloat(saldo));
            entity.setStatus(FacesUtils.checkInteger(status));
            entity.setUserid(FacesUtils.checkString(userid));
            entity.setUsername(FacesUtils.checkString(username));
            businessDelegatorView.updateUsers(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("UsersView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtCellphone() {
        return txtCellphone;
    }

    public void setTxtCellphone(InputText txtCellphone) {
        this.txtCellphone = txtCellphone;
    }

    public InputText getTxtFixedphone() {
        return txtFixedphone;
    }

    public void setTxtFixedphone(InputText txtFixedphone) {
        this.txtFixedphone = txtFixedphone;
    }

    public InputText getTxtLastname() {
        return txtLastname;
    }

    public void setTxtLastname(InputText txtLastname) {
        this.txtLastname = txtLastname;
    }

    public InputText getTxtName() {
        return txtName;
    }

    public void setTxtName(InputText txtName) {
        this.txtName = txtName;
    }

    public InputText getTxtPassword() {
        return txtPassword;
    }

    public void setTxtPassword(InputText txtPassword) {
        this.txtPassword = txtPassword;
    }

    public InputText getTxtSaldo() {
        return txtSaldo;
    }

    public void setTxtSaldo(InputText txtSaldo) {
        this.txtSaldo = txtSaldo;
    }

    public InputText getTxtStatus() {
        return txtStatus;
    }

    public void setTxtStatus(InputText txtStatus) {
        this.txtStatus = txtStatus;
    }

    public InputText getTxtUserid() {
        return txtUserid;
    }

    public void setTxtUserid(InputText txtUserid) {
        this.txtUserid = txtUserid;
    }

    public InputText getTxtUsername() {
        return txtUsername;
    }

    public void setTxtUsername(InputText txtUsername) {
        this.txtUsername = txtUsername;
    }

    public InputText getTxtIdprofile_Profile() {
        return txtIdprofile_Profile;
    }

    public void setTxtIdprofile_Profile(InputText txtIdprofile_Profile) {
        this.txtIdprofile_Profile = txtIdprofile_Profile;
    }

    public Calendar getTxtCreated() {
        return txtCreated;
    }

    public void setTxtCreated(Calendar txtCreated) {
        this.txtCreated = txtCreated;
    }

    public Calendar getTxtLastlogin() {
        return txtLastlogin;
    }

    public void setTxtLastlogin(Calendar txtLastlogin) {
        this.txtLastlogin = txtLastlogin;
    }

    public InputText getTxtIdusers() {
        return txtIdusers;
    }

    public void setTxtIdusers(InputText txtIdusers) {
        this.txtIdusers = txtIdusers;
    }

    public InputText getTxtProfileIdprofile() {
        return txtProfileIdprofile;
    }

    public void setTxtProfileIdprofile(InputText txtProfileIdprofile) {
        this.txtProfileIdprofile = txtProfileIdprofile;
    }

    public List<UsersDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataUsers();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<UsersDTO> usersDTO) {
        this.data = usersDTO;
    }

    public UsersDTO getSelectedUsers() {
        return selectedUsers;
    }

    public void setSelectedUsers(UsersDTO users) {
        this.selectedUsers = users;
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
