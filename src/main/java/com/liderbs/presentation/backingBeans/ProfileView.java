package com.liderbs.presentation.backingBeans;

import com.liderbs.exceptions.*;

import com.liderbs.modelo.*;
import com.liderbs.modelo.dto.ProfileDTO;

import com.liderbs.presentation.businessDelegate.*;

import com.liderbs.utilities.*;

import org.hibernate.Hibernate;
import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;

import org.primefaces.event.RowEditEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

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
public class ProfileView implements Serializable {
	
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(ProfileView.class);
    private InputText txtProfileDescription;
    private InputText txtProfileName;
    private InputText txtProfileUserCreated;
    private InputText txtIdprofile;
    private Calendar txtProfileCreated;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<ProfileDTO> data;
    private ProfileDTO selectedProfile;
    private Profile entity;
    private boolean showDialog;
    private Integer[] selectOpciones;
    private Map<String,Integer> opciones;
    private Users usuarioapp;
    
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public ProfileView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            ProfileDTO profileDTO = (ProfileDTO) e.getObject();

            if (txtProfileDescription == null) {
                txtProfileDescription = new InputText();
            }

            txtProfileDescription.setValue(profileDTO.getProfileDescription());

            if (txtProfileName == null) {
                txtProfileName = new InputText();
            }

            txtProfileName.setValue(profileDTO.getProfileName());

            if (txtProfileUserCreated == null) {
                txtProfileUserCreated = new InputText();
            }

            txtProfileUserCreated.setValue(profileDTO.getProfileUserCreated());

            if (txtIdprofile == null) {
                txtIdprofile = new InputText();
            }

            txtIdprofile.setValue(profileDTO.getIdprofile());

            if (txtProfileCreated == null) {
                txtProfileCreated = new Calendar();
            }

            txtProfileCreated.setValue(profileDTO.getProfileCreated());

            Integer idprofile = FacesUtils.checkInteger(txtIdprofile);
            entity = businessDelegatorView.getProfile(idprofile);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedProfile = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedProfile = null;

        if (txtProfileDescription != null) {
            txtProfileDescription.setValue(null);
            txtProfileDescription.setDisabled(false);
        }

        if (txtProfileName != null) {
            txtProfileName.setValue(null);
            txtProfileName.setDisabled(false);
        }

        if (btnSave != null) {
            btnSave.setDisabled(false);
        }

        if (btnDelete != null) {
            btnDelete.setDisabled(true);
        }

        return "";
    }

    public void listener_txtProfileCreated() {
        Date inputDate = (Date) txtProfileCreated.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtId() {
        try {
            Integer idprofile = FacesUtils.checkInteger(txtIdprofile);
            entity = (idprofile != null)
                ? businessDelegatorView.getProfile(idprofile) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtProfileDescription.setDisabled(false);
            txtProfileName.setDisabled(false);
            txtProfileUserCreated.setDisabled(false);
            txtProfileCreated.setDisabled(false);
            txtIdprofile.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtProfileCreated.setValue(entity.getProfileCreated());
            txtProfileCreated.setDisabled(false);
            txtProfileDescription.setValue(entity.getProfileDescription());
            txtProfileDescription.setDisabled(false);
            txtProfileName.setValue(entity.getProfileName());
            txtProfileName.setDisabled(false);
            txtProfileUserCreated.setValue(entity.getProfileUserCreated());
            txtProfileUserCreated.setDisabled(false);
            txtIdprofile.setValue(entity.getIdprofile());
            txtIdprofile.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
    	
        selectedProfile = (ProfileDTO) (evt.getComponent().getAttributes()
                                           .get("selectedProfile"));
        
        txtProfileName.setValue(selectedProfile.getProfileName());
        txtProfileName.setDisabled(false);
        txtProfileDescription.setValue(selectedProfile.getProfileDescription());
        txtProfileDescription.setDisabled(false);
        
        Hibernate.initialize(selectedProfile.getOptionses());
        Set<Options> opciones = selectedProfile.getOptionses();
        
        selectOpciones = new Integer[opciones.size()];
        
        int i = 0;
        
        for (Iterator<Options> it = opciones.iterator(); it.hasNext(); ) {
   		 
		 	Options opt = it.next();
		 	selectOpciones[i] = opt.getIdoptions();
		 	i++;
        }
        
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedProfile == null) && (entity == null)) {
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
    
    public Users getUsuarioapp() {
		if(usuarioapp==null) {				
			  usuarioapp = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			  //FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("rolsaldosefa_session", usuarioapp.get);				
		}
		return usuarioapp;
	}

    public String action_create() {
        try {
            
        	Date today = new Date();
        	Users user = getUsuarioapp();
			
        	
        	entity = new Profile();
        	entity.setProfileName(FacesUtils.checkString(txtProfileName));
        	entity.setProfileDescription(FacesUtils.checkString(txtProfileDescription));  
            entity.setProfileCreated(today);
            entity.setProfileUserCreated(user.getUsername());
            
            if(selectOpciones!=null && selectOpciones.length>0){
            	Set<Options> opciones = new HashSet();
            	for (int i = 0; i < selectOpciones.length; i++) {
	            	 
            		Options opc = businessDelegatorView.getOptions(selectOpciones[i]);
            		opciones.add(opc);
	            }
            	 entity.setOptionses(opciones);
            }
            
            businessDelegatorView.saveProfile(entity);
            FacesUtils.addInfoMessage("Perfil creado satisfactoriamente");
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
                Integer idprofile = new Integer(selectedProfile.getIdprofile());
                entity = businessDelegatorView.getProfile(idprofile);
            }

            entity.setProfileName(FacesUtils.checkString(txtProfileName));
            entity.setProfileDescription(FacesUtils.checkString(
                    txtProfileDescription));
            
            businessDelegatorView.updateProfile(entity);
            
            Set<Options> opciones = entity.getOptionses();   
            opciones.clear();
            
            for (int i = 0; i < selectOpciones.length; i++) {  	 
        		Options opc = businessDelegatorView.getOptions(selectOpciones[i]);
        		opciones.add(opc);
            }
            
            entity.setOptionses(opciones);
            
            businessDelegatorView.updateProfile(entity);
            FacesUtils.addInfoMessage("Perfil actualizado satisfactoriamente");
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedProfile = (ProfileDTO) (evt.getComponent().getAttributes()
                                               .get("selectedProfile"));

            Integer idprofile = new Integer(selectedProfile.getIdprofile());
            entity = businessDelegatorView.getProfile(idprofile);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer idprofile = FacesUtils.checkInteger(txtIdprofile);
            entity = businessDelegatorView.getProfile(idprofile);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteProfile(entity);
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
            selectedProfile = (ProfileDTO) (evt.getComponent().getAttributes()
                                               .get("selectedProfile"));

            Integer idprofile = new Integer(selectedProfile.getIdprofile());
            entity = businessDelegatorView.getProfile(idprofile);
            businessDelegatorView.deleteProfile(entity);
            data.remove(selectedProfile);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Integer idprofile, Date profileCreated,
        String profileDescription, String profileName, String profileUserCreated)
        throws Exception {
        try {
            entity.setProfileCreated(FacesUtils.checkDate(profileCreated));
            entity.setProfileDescription(FacesUtils.checkString(
                    profileDescription));
            entity.setProfileName(FacesUtils.checkString(profileName));
            entity.setProfileUserCreated(FacesUtils.checkString(
                    profileUserCreated));
            businessDelegatorView.updateProfile(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("ProfileView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtProfileDescription() {
        return txtProfileDescription;
    }

    public void setTxtProfileDescription(InputText txtProfileDescription) {
        this.txtProfileDescription = txtProfileDescription;
    }

    public InputText getTxtProfileName() {
        return txtProfileName;
    }

    public void setTxtProfileName(InputText txtProfileName) {
        this.txtProfileName = txtProfileName;
    }

    public InputText getTxtProfileUserCreated() {
        return txtProfileUserCreated;
    }

    public void setTxtProfileUserCreated(InputText txtProfileUserCreated) {
        this.txtProfileUserCreated = txtProfileUserCreated;
    }

    public Calendar getTxtProfileCreated() {
        return txtProfileCreated;
    }

    public void setTxtProfileCreated(Calendar txtProfileCreated) {
        this.txtProfileCreated = txtProfileCreated;
    }

    public InputText getTxtIdprofile() {
        return txtIdprofile;
    }

    public void setTxtIdprofile(InputText txtIdprofile) {
        this.txtIdprofile = txtIdprofile;
    }

    public List<ProfileDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataProfile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<ProfileDTO> profileDTO) {
        this.data = profileDTO;
    }

    public ProfileDTO getSelectedProfile() {
        return selectedProfile;
    }

    public void setSelectedProfile(ProfileDTO profile) {
        this.selectedProfile = profile;
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
