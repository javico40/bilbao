package com.liderbs.presentation.backingBeans;

import com.liderbs.exceptions.*;

import com.liderbs.modelo.*;
import com.liderbs.modelo.dto.SkillDTO;

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
public class SkillView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(SkillView.class);
    private InputText txtSkillDescription;
    private InputText txtIdskill;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<SkillDTO> data;
    private SkillDTO selectedSkill;
    private Skill entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public SkillView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            SkillDTO skillDTO = (SkillDTO) e.getObject();

            if (txtSkillDescription == null) {
                txtSkillDescription = new InputText();
            }

            txtSkillDescription.setValue(skillDTO.getSkillDescription());

            if (txtIdskill == null) {
                txtIdskill = new InputText();
            }

            txtIdskill.setValue(skillDTO.getIdskill());

            Integer idskill = FacesUtils.checkInteger(txtIdskill);
            entity = businessDelegatorView.getSkill(idskill);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedSkill = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedSkill = null;

        if (txtSkillDescription != null) {
            txtSkillDescription.setValue(null);
            txtSkillDescription.setDisabled(true);
        }

        if (txtIdskill != null) {
            txtIdskill.setValue(null);
            txtIdskill.setDisabled(false);
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
            Integer idskill = FacesUtils.checkInteger(txtIdskill);
            entity = (idskill != null)
                ? businessDelegatorView.getSkill(idskill) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtSkillDescription.setDisabled(false);
            txtIdskill.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtSkillDescription.setValue(entity.getSkillDescription());
            txtSkillDescription.setDisabled(false);
            txtIdskill.setValue(entity.getIdskill());
            txtIdskill.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedSkill = (SkillDTO) (evt.getComponent().getAttributes()
                                       .get("selectedSkill"));
        txtSkillDescription.setValue(selectedSkill.getSkillDescription());
        txtSkillDescription.setDisabled(false);
        txtIdskill.setValue(selectedSkill.getIdskill());
        txtIdskill.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedSkill == null) && (entity == null)) {
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
            entity = new Skill();

            Integer idskill = FacesUtils.checkInteger(txtIdskill);

            entity.setIdskill(idskill);
            entity.setSkillDescription(FacesUtils.checkString(
                    txtSkillDescription));
            //entity.setTrainers(FacesUtils.checkTrainer(txtTrainers));
            businessDelegatorView.saveSkill(entity);
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
                Integer idskill = new Integer(selectedSkill.getIdskill());
                entity = businessDelegatorView.getSkill(idskill);
            }

            entity.setSkillDescription(FacesUtils.checkString(
                    txtSkillDescription));
            //entity.setTrainers(FacesUtils.checkTrainer(txtTrainers));
            businessDelegatorView.updateSkill(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedSkill = (SkillDTO) (evt.getComponent().getAttributes()
                                           .get("selectedSkill"));

            Integer idskill = new Integer(selectedSkill.getIdskill());
            entity = businessDelegatorView.getSkill(idskill);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer idskill = FacesUtils.checkInteger(txtIdskill);
            entity = businessDelegatorView.getSkill(idskill);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteSkill(entity);
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
            selectedSkill = (SkillDTO) (evt.getComponent().getAttributes()
                                           .get("selectedSkill"));

            Integer idskill = new Integer(selectedSkill.getIdskill());
            entity = businessDelegatorView.getSkill(idskill);
            businessDelegatorView.deleteSkill(entity);
            data.remove(selectedSkill);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Integer idskill, String skillDescription)
        throws Exception {
        try {
            entity.setSkillDescription(FacesUtils.checkString(skillDescription));
            businessDelegatorView.updateSkill(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("SkillView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtSkillDescription() {
        return txtSkillDescription;
    }

    public void setTxtSkillDescription(InputText txtSkillDescription) {
        this.txtSkillDescription = txtSkillDescription;
    }

    public InputText getTxtIdskill() {
        return txtIdskill;
    }

    public void setTxtIdskill(InputText txtIdskill) {
        this.txtIdskill = txtIdskill;
    }

    public List<SkillDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataSkill();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<SkillDTO> skillDTO) {
        this.data = skillDTO;
    }

    public SkillDTO getSelectedSkill() {
        return selectedSkill;
    }

    public void setSelectedSkill(SkillDTO skill) {
        this.selectedSkill = skill;
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
