package com.liderbs.presentation.backingBeans;

import com.liderbs.exceptions.*;
import com.liderbs.modelo.*;
import com.liderbs.modelo.dto.MenuDTO;
import com.liderbs.presentation.businessDelegate.*;
import com.liderbs.utilities.*;

import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.io.Serializable;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
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
public class MenusView implements Serializable {
	
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(MenusView.class);
    private boolean txtEstado;
    private InputText txtMendescv;
    private InputText txtMendescvjci;
    private InputText txtMenicon;
    private InputText txtMenpath;
    private InputText txtMenpathjci;
    private InputText txtUsuariomodificacion;
    private InputText txtMenuidn;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private LazyDataModel<MenuDTO> data;
    private List<MenuDTO> dataapp;
    private MenuDTO selectedMenus;
    private Menu entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;
    private Users usuarioapp;
    private String busqueda;
    private String userimage;

    public MenusView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
        	
            MenuDTO menusDTO = (MenuDTO) e.getObject();

  /*        
            if (txtMendescv == null) {
                txtMendescv = new InputText();
            }

            txtMendescv.setValue(menusDTO.getMendescv());

            if (txtMendescvjci == null) {
                txtMendescvjci = new InputText();
            }

            txtMendescvjci.setValue(menusDTO.getMendescvjci());

            if (txtMenicon == null) {
                txtMenicon = new InputText();
            }

            txtMenicon.setValue(menusDTO.getMenicon());

            if (txtMenpath == null) {
                txtMenpath = new InputText();
            }

            txtMenpath.setValue(menusDTO.getMenpath());

            if (txtMenpathjci == null) {
                txtMenpathjci = new InputText();
            }

            txtMenpathjci.setValue(menusDTO.getMenpathjci());

            if (txtUsuariomodificacion == null) {
                txtUsuariomodificacion = new InputText();
            }

            txtUsuariomodificacion.setValue(menusDTO.getUsuariomodificacion());

            if (txtMenuidn == null) {
                txtMenuidn = new InputText();
            }

            txtMenuidn.setValue(menusDTO.getMenuidn());

            Long menuidn = FacesUtils.checkLong(txtMenuidn);
            entity = businessDelegatorView.getMenus(menuidn);
*/
            
            
            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedMenus = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedMenus = null;

        txtEstado = false;
        
        if (txtMendescvjci != null) {
            txtMendescvjci.setValue(null);
           
        }

        if (txtMendescv != null) {
            txtMendescv.setValue(null);
            txtMendescv.setDisabled(true);
        }

        if (txtMenicon != null) {
            txtMenicon.setValue(null);
            txtMenicon.setDisabled(true);
        }

        if (txtMenpath != null) {
            txtMenpath.setValue(null);
            txtMenpath.setDisabled(true);
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
    	/*
    	try {
            String mendescv = FacesUtils.checkString(txtMendescvjci);
            entity = (mendescv != null)? businessDelegatorView.findByCriteriaInMenus(new Object[]{"mendescvjci",true,mendescv.trim(),"="},null,null).get(0) : null;          
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtMendescv.setDisabled(false);           
            txtMenicon.setDisabled(false);
            txtMenpath.setDisabled(false);         
            txtEstado=false;
            btnSave.setDisabled(false);
        } else {
        	txtEstado=(entity.getEstado()!=null&&entity.getEstado().equals(1L)?true:false); 
        	txtMendescvjci.setValue(entity.getMendescvjci());
            txtMendescv.setValue(entity.getMendescv());
            txtMendescv.setDisabled(false);            
            txtMenicon.setValue(entity.getMenicon());
            txtMenicon.setDisabled(false);
            txtMenpath.setValue(entity.getMenpath());
            txtMenpath.setDisabled(false);            
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
        */
    }

    public String action_edit(ActionEvent evt) {
    	
        selectedMenus = (MenuDTO) (evt.getComponent().getAttributes()
                                       .get("selectedMenus"));
 /*
        txtEstado=(selectedMenus.getEstado()!=null&&selectedMenus.getEstado().equals(1L)?true:false); 
        txtMendescvjci.setValue(selectedMenus.getMendescvjci());
        txtMendescv.setValue(selectedMenus.getMendescv());
        txtMendescv.setDisabled(false);        
        txtMenicon.setValue(selectedMenus.getMenicon());
        txtMenicon.setDisabled(false);
        txtMenpath.setValue(selectedMenus.getMenpath());
        txtMenpath.setDisabled(false);          
        btnSave.setDisabled(false);
        setShowDialog(true);
*/
        return "";
    }

    public String action_save() {
        try {
            if ((selectedMenus == null) && (entity == null)) {
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
        	if(!(FacesUtils.checkString(txtMendescvjci)==null||FacesUtils.checkString(txtMendescv)==null||FacesUtils.checkString(txtMenicon)==null||FacesUtils.checkString(txtMenpath)==null)){  
	                
	            entity = new Menu();
	
	            /*
	            entity.setMendescv(FacesUtils.checkString(txtMendescv));
	            entity.setMendescvjci(FacesUtils.checkString(txtMendescvjci));
	            entity.setMenicon(FacesUtils.checkString(txtMenicon));
	            entity.setMenpath(FacesUtils.checkString(txtMenpath));
	            entity.setMenpathjci("XHTML");
	            entity.setEstado(txtEstado==true?1L:0L);
	            Users usuarioapp = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	            //entity.setUsuariomodificacion(usuarioapp.getUserid());	           
	            
	            
	            businessDelegatorView.saveMenus(entity);
	            */
	            FacesUtils.addInfoMessage(FacesContext.getCurrentInstance().getApplication().getResourceBundle( FacesContext.getCurrentInstance(), "msg").getString(ZMessManager.ENTITY_SUCCESFULLYSAVED));            
	            action_clear();
        	}else{
          		 FacesUtils.addErrorMessage(FacesContext.getCurrentInstance().getApplication().getResourceBundle( FacesContext.getCurrentInstance(), "msg").getString("digitecampos"));
          	 }
        } catch (Exception e) {
            entity = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modify() {
        try {
        	if(!(FacesUtils.checkString(txtMendescvjci)==null||FacesUtils.checkString(txtMendescv)==null||FacesUtils.checkString(txtMenicon)==null||FacesUtils.checkString(txtMenpath)==null)){  
    	        /*
	            if (entity == null) {
	                Long menuidn = new Long(selectedMenus.getMenuidn());
	                entity = businessDelegatorView.getMenus(menuidn);
	            }
	
	            entity.setMendescv(FacesUtils.checkString(txtMendescv));
	            entity.setMendescvjci(FacesUtils.checkString(txtMendescvjci));
	            entity.setMenicon(FacesUtils.checkString(txtMenicon));
	            entity.setMenpath(FacesUtils.checkString(txtMenpath));
	            entity.setMenpathjci("XHTML");
	            entity.setEstado(txtEstado==true?1L:0L);
	            Users usuarioapp = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	            //entity.setUsuariomodificacion(usuarioapp.getUsrlogin());	
	            businessDelegatorView.updateMenus(entity);
	            */
	            FacesUtils.addInfoMessage(FacesContext.getCurrentInstance().getApplication().getResourceBundle( FacesContext.getCurrentInstance(), "msg").getString(ZMessManager.ENTITY_SUCCESFULLYMODIFIED));            
		   	}else{
         		 FacesUtils.addErrorMessage(FacesContext.getCurrentInstance().getApplication().getResourceBundle( FacesContext.getCurrentInstance(), "msg").getString("digitecampos"));
         	 }
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedMenus = (MenuDTO) (evt.getComponent().getAttributes()
                                           .get("selectedMenus"));

            entity = businessDelegatorView.getMenu(selectedMenus.getIdMenu());
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer menuidn = FacesUtils.checkInteger(txtMenuidn);
            entity = businessDelegatorView.getMenu(menuidn);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
        	//entity.setEstado(0L);
            //businessDelegatorView.updateMenus(entity);
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
            selectedMenus = (MenuDTO) (evt.getComponent().getAttributes()
                                           .get("selectedMenus"));

            //Long menuidn = new Long(selectedMenus.getMenuidn());
            entity = businessDelegatorView.getMenu(selectedMenus.getIdMenu());
            businessDelegatorView.deleteMenu(entity);
           // data.remove(selectedMenus);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Long estado, String mendescv,
        String mendescvjci, String menicon, String menpath, String menpathjci,
        Long menuidn, String usuariomodificacion) throws Exception {
        try {
        	/*
            entity.setEstado(FacesUtils.checkLong(estado));
            entity.setMendescv(FacesUtils.checkString(mendescv));
            entity.setMendescvjci(FacesUtils.checkString(mendescvjci));
            entity.setMenicon(FacesUtils.checkString(menicon));
            entity.setMenpath(FacesUtils.checkString(menpath));
            entity.setMenpathjci(FacesUtils.checkString(menpathjci));
            entity.setUsuariomodificacion(FacesUtils.checkString(
                    usuariomodificacion));
            */
            businessDelegatorView.updateMenu(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("MenusView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public boolean getTxtEstado() {
        return txtEstado;
    }

    public void setTxtEstado(boolean txtEstado) {
        this.txtEstado = txtEstado;
    }

    public InputText getTxtMendescv() {
        return txtMendescv;
    }

    public void setTxtMendescv(InputText txtMendescv) {
        this.txtMendescv = txtMendescv;
    }

    public InputText getTxtMendescvjci() {
        return txtMendescvjci;
    }

    public void setTxtMendescvjci(InputText txtMendescvjci) {
        this.txtMendescvjci = txtMendescvjci;
    }

    public InputText getTxtMenicon() {
        return txtMenicon;
    }

    public void setTxtMenicon(InputText txtMenicon) {
        this.txtMenicon = txtMenicon;
    }

    public InputText getTxtMenpath() {
        return txtMenpath;
    }

    public void setTxtMenpath(InputText txtMenpath) {
        this.txtMenpath = txtMenpath;
    }

    public InputText getTxtMenpathjci() {
        return txtMenpathjci;
    }

    public void setTxtMenpathjci(InputText txtMenpathjci) {
        this.txtMenpathjci = txtMenpathjci;
    }

    public InputText getTxtUsuariomodificacion() {
        return txtUsuariomodificacion;
    }

    public void setTxtUsuariomodificacion(InputText txtUsuariomodificacion) {
        this.txtUsuariomodificacion = txtUsuariomodificacion;
    }

    public InputText getTxtMenuidn() {
        return txtMenuidn;
    }

    public void setTxtMenuidn(InputText txtMenuidn) {
        this.txtMenuidn = txtMenuidn;
    }

    public LazyDataModel<MenuDTO> getData() {
        try {
            if (data == null) {
                data = new LocalDataModelDTO();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(LazyDataModel<MenuDTO> menusDTO) {
        this.data = menusDTO;
    }

    public MenuDTO getSelectedMenus() {
        return selectedMenus;
    }

    public void setSelectedMenus(MenuDTO menus) {
        this.selectedMenus = menus;
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
	
	public Users getUsuarioapp() {
		if(usuarioapp==null) {				
			  usuarioapp = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			  FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("rolsaldosefa_session", usuarioapp.getProfile().getIdprofile());				
		}
		return usuarioapp;
	}

	public void setUsuarioapp(Users usuarioapp) {
		this.usuarioapp = usuarioapp;
	}

	public String getBusqueda() {
		return busqueda;
	}

	public void setBusqueda(String busqueda) {
		this.busqueda = busqueda;
	}

	public String handleListener() {
		dataapp=null;
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("menuseguridad_session");
		return "";
    }
	
	public void handleKeyEvent(ActionEvent actionEvent) {
		dataapp=null;
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("menuseguridad_session");
    }

	public List<MenuDTO> getDataapp() {
		
    	dataapp=(List<MenuDTO>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("menuseguridad_session");
    	
    	if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()&&dataapp==null) {
    		
    		List<Menu> menusobject =new ArrayList<Menu>();
    		
    		dataapp=new ArrayList<MenuDTO>();
    		
    		  try {
    			
    			Users user = getUsuarioapp();
    			List<Options>	opcroles = businessDelegatorView.findByCriteriaInOptions(new Object[]{"estado",false,1L,"=","roles",false,user.getProfile().getIdprofile(),"=","opciones.opcvarlan",true,"SEGURIDADJCI%"," LIKE ","opciones.opcdescripv",true,"%"+(busqueda!=null&&!busqueda.equals("")?busqueda.toUpperCase():"")+"%"," LIKE "} , null, null);
    		     
    			for (Options opcrole : opcroles) {
    				/*
    		    	 if(!menusobject.contains(opcrole.getOpciones().getMenus())){
    		    		 
    		    		     Menus menu = opcrole.getOpciones().getMenus();
    		    		     List<Opciones> opciones=new ArrayList<Opciones>();
    		    		     opciones.add(opcrole.getOpciones());
    		    		     menu.setOpcioneses(new LinkedHashSet<Opciones>(opciones));
    		    		     menusobject.add(menu);
    		    		     
    					}else{
    						for (Menus mens : menusobject) {						
    							if(opcrole.getOpciones().getMenus().getMendescv().equals(mens.getMendescv().toString())){
    								mens.getOpcioneses().add(opcrole.getOpciones());						
    							}
    						}
    					}*/
    			}
    		  } catch (Exception e) {	
    			e.printStackTrace();
    		}
    		  for (Menu menus : menusobject) {
    			  MenuDTO meDto=new MenuDTO();
    			  
    			  /*
    			  meDto.setMendescv(menus.getMendescv());
    			  meDto.setMenpath(menus.getMenpath());
    			  meDto.setMenuidn(menus.getMenuidn());
    			  meDto.setMenicon(menus.getMenicon());
    			  meDto.setMenpathjci(menus.getMenpathjci());
    			  meDto.setMendescvjci(menus.getMendescvjci());
    			  
    			  List<Options> opciones=new ArrayList<Options>();
    			  
    			  for (Options opc : menu.get) {
    				  opciones.add(opc);
				  }
    			  
    			  meDto.setOpciones(opciones);
    			  */
    			  dataapp.add(meDto);
    		 } 
    		  FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("menuseguridad_session", dataapp);
    	}    	
		return dataapp;
	}

	public void setDataapp(List<MenuDTO> dataapp) {
		this.dataapp = dataapp;
	}
	
	 public void logout(){  
		    try {
		    	SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
			} catch (Exception e) { }	        
	        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("menuseguridad_session");
	        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("rolseguridad_session");
	        dataapp=null;
	        try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("../j_spring_security_logout");
			} catch (Exception e) { }       
	    }
	 public void logoutdenied(){  
		    try {
		    	SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
			} catch (Exception e) { }	        
	        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("menuseguridad_session");
	        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("rolseguridad_session");
	        dataapp=null;
	        try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("j_spring_security_logout");
			} catch (Exception e) { }       
	    }
	 public String getUserimage() {
			if(userimage==null){				
	        	try{
	        	  Users usuario=businessDelegatorView.getUsers(getUsuarioapp().getId());	        	
	        	  userimage=usuario.getUserid().toString();
	        	
	        	}catch(Exception e){        		
						userimage="0";				
	    		}
			}
			return userimage;
		}

		public void setUserimage(String userimage) {
			this.userimage = userimage;
		}
		
		
		private List<MenuDTO> getDataPageDTO (int startRow, int pageSize,String sortField, boolean sortOrder, Map<String, Object> filters) {
			try {
				//return  businessDelegatorView.getDataPageMenu(filters, sortField, sortOrder, startRow, pageSize);
			} catch (Exception e) {			
				e.printStackTrace();
			}
			return null;
		}
		
		private class LocalDataModelDTO extends LazyDataModel<MenuDTO> {	
			
		    private static final long serialVersionUID = 1L;
			private List<MenuDTO> menusDTO;

			public LocalDataModelDTO() {			
			}
			
			@Override
			public List<MenuDTO> load(int startingAt, int maxPerPage, String sortField, SortOrder sortOrder, Map<String, Object> filters) {			
				   if (filters != null) {
					   menusDTO = getDataPageDTO(startingAt, maxPerPage, sortField, (sortOrder.name().equals("ASCENDING")?true:false) ,filters);			 				
			            try {
							//setRowCount(businessDelegatorView.findTotalNumberMenus(filters).intValue());		            
						} catch (Exception e) {							
							e.printStackTrace();
						}		       
			         }
			        setPageSize(maxPerPage);				
				return menusDTO;
				
			  }
			 @Override
			    public MenuDTO getRowData(String rowKey) {
			        for(MenuDTO menusDTOtmp : menusDTO) {
			            if(menusDTOtmp.getIdMenu().toString().equals(rowKey))
			                return menusDTOtmp;
			        }	 
			        return null;
			    }
			 
			 @Override
			    public Object getRowKey(MenuDTO menusDTOtmp) {
			        return menusDTOtmp.getIdMenu();
			    }
						
		    } 
    
}
