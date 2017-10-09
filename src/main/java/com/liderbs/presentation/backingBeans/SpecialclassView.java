package com.liderbs.presentation.backingBeans;

import com.liderbs.exceptions.*;

import com.liderbs.modelo.*;
import com.liderbs.modelo.dto.SpecialclassDTO;

import com.liderbs.presentation.businessDelegate.*;

import com.liderbs.utilities.*;

import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;

import org.primefaces.event.RowEditEvent;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

import java.sql.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
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
public class SpecialclassView implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
    private static final Logger log = LoggerFactory.getLogger(SpecialclassView.class);
    
    private UploadedFile txtClassPicture;
    private InputText txtClassTitle;
    private String classDecription;
    private InputText txtDescription;
    private boolean txtHavebathroom;
    private boolean txtHavechangeclothes;
    private boolean txtHaveparkingbike;
    private boolean txtHaveparkingcar;
    private boolean txtHaveshower;
    private InputText txtLatitude;
    private InputText txtLongitud;
    private InputText txtPlaceAddress;
    private InputText txtPlaceName;
    private InputText txtPrice;
    private InputText txtIdspecialclass;
    private Calendar txtEndHour;
    private Calendar txtFecha;
    private Calendar txtStartHour;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<SpecialclassDTO> data;
    private SpecialclassDTO selectedSpecialclass;
    private Specialclass entity;
    private boolean showDialog;
    private boolean dayOfWeekClass; 
    
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;
    
    SimpleDateFormat formatImage = new SimpleDateFormat("ddMMyyyy");

    private Date currentDate;
    
    private String classPicturePath;
    
    public SpecialclassView() {
        super();
    }
    
    @PostConstruct
    public void init(){
    	currentDate = new Date();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            SpecialclassDTO specialclassDTO = (SpecialclassDTO) e.getObject();

            
            if (txtClassTitle == null) {
                txtClassTitle = new InputText();
            }

            txtClassTitle.setValue(specialclassDTO.getClassTitle());

            if (txtDescription == null) {
                txtDescription = new InputText();
            }

            txtDescription.setValue(specialclassDTO.getDescription());

            

            if (txtLatitude == null) {
                txtLatitude = new InputText();
            }

            txtLatitude.setValue(specialclassDTO.getLatitude());

            if (txtLongitud == null) {
                txtLongitud = new InputText();
            }

            txtLongitud.setValue(specialclassDTO.getLongitud());

            if (txtPlaceAddress == null) {
                txtPlaceAddress = new InputText();
            }

            txtPlaceAddress.setValue(specialclassDTO.getPlaceAddress());

            if (txtPlaceName == null) {
                txtPlaceName = new InputText();
            }

            txtPlaceName.setValue(specialclassDTO.getPlaceName());

            if (txtPrice == null) {
                txtPrice = new InputText();
            }

            txtPrice.setValue(specialclassDTO.getPrice());

            if (txtIdspecialclass == null) {
                txtIdspecialclass = new InputText();
            }

            txtIdspecialclass.setValue(specialclassDTO.getIdspecialclass());

            if (txtEndHour == null) {
                txtEndHour = new Calendar();
            }

            txtEndHour.setValue(specialclassDTO.getEndHour());

            if (txtFecha == null) {
                txtFecha = new Calendar();
            }

            txtFecha.setValue(specialclassDTO.getFecha());

            if (txtStartHour == null) {
                txtStartHour = new Calendar();
            }

            txtStartHour.setValue(specialclassDTO.getStartHour());

            Integer idspecialclass = FacesUtils.checkInteger(txtIdspecialclass);
            entity = businessDelegatorView.getSpecialclass(idspecialclass);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedSpecialclass = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedSpecialclass = null;

       
        if (txtClassTitle != null) {
            txtClassTitle.setValue(null);
            txtClassTitle.setDisabled(false);
        }

        if (txtDescription != null) {
            txtDescription.setValue(null);
            txtDescription.setDisabled(false);
        }

        if (txtLatitude != null) {
            txtLatitude.setValue(null);
            txtLatitude.setDisabled(false);
        }

        if (txtLongitud != null) {
            txtLongitud.setValue(null);
            txtLongitud.setDisabled(false);
        }

        if (txtPlaceAddress != null) {
            txtPlaceAddress.setValue(null);
            txtPlaceAddress.setDisabled(false);
        }

        if (txtPlaceName != null) {
            txtPlaceName.setValue(null);
            txtPlaceName.setDisabled(false);
        }

        if (txtPrice != null) {
            txtPrice.setValue(null);
            txtPrice.setDisabled(false);
        }

        if (txtEndHour != null) {
            txtEndHour.setValue(null);
            txtEndHour.setDisabled(false);
        }

        if (txtFecha != null) {
            txtFecha.setValue(null);
            txtFecha.setDisabled(false);
        }

        if (txtStartHour != null) {
            txtStartHour.setValue(null);
            txtStartHour.setDisabled(false);
        }


        if (btnSave != null) {
            btnSave.setDisabled(false);
        }

        if (btnDelete != null) {
            btnDelete.setDisabled(false);
        }

        return "";
    }

    public void listener_txtEndHour() {
        Date inputDate = (Date) txtEndHour.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtFecha() {
        Date inputDate = (Date) txtFecha.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtStartHour() {
        Date inputDate = (Date) txtStartHour.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtId() {
        try {
            Integer idspecialclass = FacesUtils.checkInteger(txtIdspecialclass);
            entity = (idspecialclass != null)
                ? businessDelegatorView.getSpecialclass(idspecialclass) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
           txtClassTitle.setDisabled(false);
            txtDescription.setDisabled(false);
             txtLatitude.setDisabled(false);
            txtLongitud.setDisabled(false);
            txtPlaceAddress.setDisabled(false);
            txtPlaceName.setDisabled(false);
            txtPrice.setDisabled(false);
            txtEndHour.setDisabled(false);
            txtFecha.setDisabled(false);
            txtStartHour.setDisabled(false);
            txtIdspecialclass.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtClassTitle.setValue(entity.getClassTitle());
            txtClassTitle.setDisabled(false);
            txtDescription.setValue(entity.getDescription());
            txtDescription.setDisabled(false);
            txtEndHour.setValue(entity.getEndHour());
            txtEndHour.setDisabled(false);
            txtFecha.setValue(entity.getFecha());
            txtFecha.setDisabled(false);
            txtLatitude.setValue(entity.getLatitude());
            txtLatitude.setDisabled(false);
            txtLongitud.setValue(entity.getLongitud());
            txtLongitud.setDisabled(false);
            txtPlaceAddress.setValue(entity.getPlaceAddress());
            txtPlaceAddress.setDisabled(false);
            txtPlaceName.setValue(entity.getPlaceName());
            txtPlaceName.setDisabled(false);
            txtPrice.setValue(entity.getPrice());
            txtPrice.setDisabled(false);
            txtStartHour.setValue(entity.getStartHour());
            txtStartHour.setDisabled(false);
            txtIdspecialclass.setValue(entity.getIdspecialclass());
            txtIdspecialclass.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
    	
        selectedSpecialclass = (SpecialclassDTO) (evt.getComponent()
                                                     .getAttributes()
                                                     .get("selectedSpecialclass"));
        
        txtClassTitle.setValue(selectedSpecialclass.getClassTitle());
        classDecription = selectedSpecialclass.getDescription(); 
        txtFecha.setValue(selectedSpecialclass.getFecha());
        txtStartHour.setValue(selectedSpecialclass.getStartHour());
        txtEndHour.setValue(selectedSpecialclass.getEndHour());
        txtPlaceName.setValue(selectedSpecialclass.getPlaceName());
        txtPlaceAddress.setValue(selectedSpecialclass.getPlaceAddress());
        txtLatitude.setValue(selectedSpecialclass.getLatitude());
        txtLongitud.setValue(selectedSpecialclass.getLongitud());
        txtPrice.setValue(selectedSpecialclass.getPrice());
        
        int dweek = 0;
        dweek = selectedSpecialclass.getDayWeek();
        
        if(dweek == 0){
        	dayOfWeekClass = false;
        }else{
        	dayOfWeekClass = true;
        }
        
        
        
        if(selectedSpecialclass.getClassPicture() != null && selectedSpecialclass.getClassPicture() != ""){
        	classPicturePath = selectedSpecialclass.getClassPicture();
        }else{
        	classPicturePath = null;
        }
  
        
        //txtClassPicture;
        
        if(selectedSpecialclass.getHaveparkingcar() == 1){
        	txtHaveparkingcar = true;
        }else{
        	txtHaveparkingcar = false;
        }
        
        if(selectedSpecialclass.getHaveparkingbike() == 1){
        	txtHaveparkingbike = true;
        }else{
        	txtHaveparkingbike = false;
        }
        
        if(selectedSpecialclass.getHaveshower() == 1){
        	txtHaveshower = true;
        }else{
        	txtHaveshower = false;
        }
        
        if(selectedSpecialclass.getHavebathroom() == 1){
        	txtHavebathroom = true;
        }else{
        	txtHavebathroom = false;
        }
        
        if(selectedSpecialclass.getHavechangeclothes() == 1){
        	txtHavechangeclothes = true;
        }else{
        	txtHavechangeclothes = false;
        }
         
        btnSave.setDisabled(false);
        setShowDialog(true);
        
        return "";
    }

    public String action_save() {
        try {
            if ((selectedSpecialclass == null) && (entity == null)) {
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
        	
        	if(FacesUtils.checkString(txtClassTitle) == null){
        		FacesUtils.addErrorMessage("Por favor ingrese el nombre de la clase");
        	}else if(classDecription == null){
        		FacesUtils.addErrorMessage("Por favor ingrese la descripcion de la clase");
        	}else if(FacesUtils.checkDate(txtFecha) == null){
        		FacesUtils.addErrorMessage("Por favor ingrese la fecha de la clase");
        	}else if(FacesUtils.checkDate(txtStartHour) == null){
        		FacesUtils.addErrorMessage("Por favor ingrese la hora de inicio de la clase");
        	}else if(FacesUtils.checkDate(txtEndHour) == null){
        		FacesUtils.addErrorMessage("Por favor ingrese la hora de fin de la clase");
        	}else{
        		
        		entity = new Specialclass();
        		
        		entity.setClassTitle(FacesUtils.checkString(txtClassTitle));
                entity.setDescription(classDecription);
                entity.setFecha(FacesUtils.checkDate(txtFecha));
                entity.setStartHour(FacesUtils.checkDate(txtStartHour));
                entity.setEndHour(FacesUtils.checkDate(txtEndHour));
                entity.setPlaceName(FacesUtils.checkString(txtPlaceName));
                entity.setPlaceAddress(FacesUtils.checkString(txtPlaceAddress)); 
                entity.setLatitude(FacesUtils.checkString(txtLatitude));
                entity.setLongitud(FacesUtils.checkString(txtLongitud));
                entity.setPrice(FacesUtils.checkDouble(txtPrice));
                
                if(txtHaveparkingcar == true){
                	entity.setHaveparkingcar(1);
                }else{
                	entity.setHaveparkingcar(0);
                }
                
                if(txtHaveparkingbike == true){
                	entity.setHaveparkingbike(1);
                }else{
                	entity.setHaveparkingbike(0);
                }
                
                if(txtHaveshower == true){
                	entity.setHaveshower(1);
                }else{
                	entity.setHaveshower(0);
                }
                
                if(txtHavebathroom == true){
                	entity.setHavebathroom(1);
                }else{
                	entity.setHavebathroom(0);
                }
                
                if(txtHavechangeclothes == true){
                	entity.setHavechangeclothes(1);
                }else{
                	entity.setHavechangeclothes(0);
                }
                
                if(dayOfWeekClass == false){
                	entity.setDayWeek(0);
                }else{
                	entity.setDayWeek(1);
                }
                
                
                businessDelegatorView.saveSpecialclass(entity);
                
                //Save picture
                
                if(txtClassPicture != null) {
	 				
	 				InputStream filecontent = null;
	 				String picName = "";
	 				OutputStream out = null;
	 			
	 				try {
	 				
	 				//final String path = "C:\\desarrollo\\workspaces\\trainerpics\\";
	 			    final String path = "/var/www/html/govirfit/appimg/specialclass/";
	 				
	 				
	 				Date currentDate = new Date();
	 				String formatedDate = formatImage.format(currentDate);
	 	            
	 				//picName = trainerPic.getFileName();
	 				picName = ""+entity.getIdspecialclass()+""+formatedDate+".jpg";
	 				filecontent = txtClassPicture.getInputstream();
	 				
	 				out = new FileOutputStream(new File(path + picName));
	 				
	 				int read = 0;
	 		        final byte[] bytes = new byte[1024];
	 				
	 				while ((read = filecontent.read(bytes)) != -1) {
	 		            out.write(bytes, 0, read);
	 		        }
	 				
	 		        //log.info("New file " + picName + " created at " + path);
	 				entity.setClassPicture(picName);
	 		        
	 				 } catch (FileNotFoundException fne) {
	 					FacesUtils.addErrorMessage("Archivo seleccionado invalido o protegido");
	 					log.info(fne.toString());
	 			    } finally {
	 			        if (out != null) {
	 			            out.close();
	 			        }
	 			        if (filecontent != null) {
	 			            filecontent.close();
	 			        }
	 			    }//end try catch  
	 				
	 				businessDelegatorView.updateSpecialclass(entity);
	                
	 	        }//end picture upload
               
        		
                 FacesUtils.addInfoMessage("Clase creada satisfactoriamente");   
                action_clear();
        	}//end if-else
        
        } catch (Exception e) {
            entity = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modify() {
    	
try {
        	
        	if(FacesUtils.checkString(txtClassTitle) == null){
        		FacesUtils.addErrorMessage("Por favor ingrese el nombre de la clase");
        	}else if(classDecription == null){
        		FacesUtils.addErrorMessage("Por favor ingrese la descripcion de la clase");
        	}else if(FacesUtils.checkDate(txtFecha) == null){
        		FacesUtils.addErrorMessage("Por favor ingrese la fecha de la clase");
        	}else if(FacesUtils.checkDate(txtStartHour) == null){
        		FacesUtils.addErrorMessage("Por favor ingrese la hora de inicio de la clase");
        	}else if(FacesUtils.checkDate(txtEndHour) == null){
        		FacesUtils.addErrorMessage("Por favor ingrese la hora de fin de la clase");
        	}else{
        		
        		if (entity == null) {
                    Integer idspecialclass = new Integer(selectedSpecialclass.getIdspecialclass());
                    entity = businessDelegatorView.getSpecialclass(idspecialclass);
                }
        		
        		entity.setClassTitle(FacesUtils.checkString(txtClassTitle));
                entity.setDescription(classDecription);
                entity.setFecha(FacesUtils.checkDate(txtFecha));
                entity.setStartHour(FacesUtils.checkDate(txtStartHour));
                entity.setEndHour(FacesUtils.checkDate(txtEndHour));
                entity.setPlaceName(FacesUtils.checkString(txtPlaceName));
                entity.setPlaceAddress(FacesUtils.checkString(txtPlaceAddress)); 
                entity.setLatitude(FacesUtils.checkString(txtLatitude));
                entity.setLongitud(FacesUtils.checkString(txtLongitud));
                entity.setPrice(FacesUtils.checkDouble(txtPrice));
                
                if(txtHaveparkingcar == true){
                	entity.setHaveparkingcar(1);
                }else{
                	entity.setHaveparkingcar(0);
                }
                
                if(txtHaveparkingbike == true){
                	entity.setHaveparkingbike(1);
                }else{
                	entity.setHaveparkingbike(0);
                }
                
                if(txtHaveshower == true){
                	entity.setHaveshower(1);
                }else{
                	entity.setHaveshower(0);
                }
                
                if(txtHavebathroom == true){
                	entity.setHavebathroom(1);
                }else{
                	entity.setHavebathroom(0);
                }
                
                if(txtHavechangeclothes == true){
                	entity.setHavechangeclothes(1);
                }else{
                	entity.setHavechangeclothes(0);
                }
                
                if(dayOfWeekClass == false){
                	entity.setDayWeek(0);
                }else{
                	entity.setDayWeek(1);
                }
                
                businessDelegatorView.updateSpecialclass(entity);
                
                //Si tiene ya foto, y no se ha seleccionado una, asignar la que ya tiene
                
                if(classPicturePath != null){
                	
                	entity.setClassPicture(classPicturePath);
                	businessDelegatorView.updateSpecialclass(entity);
               
                //Si no si selecciono una foto actualizarla, sino, dejarla en blanco
                } else if(txtClassPicture != null) {
	 				
	 				InputStream filecontent = null;
	 				String picName = "";
	 				OutputStream out = null;
	 			
	 				try {
	 				
	 				final String path = "C:\\desarrollo\\workspaces\\trainerpics\\";
	 			    //final String path = "/var/www/html/govirfit/appimg/specialclass/";
	 				
	 				
	 				Date currentDate = new Date();
	 				String formatedDate = formatImage.format(currentDate);
	 	            
	 				//picName = trainerPic.getFileName();
	 				picName = ""+entity.getIdspecialclass()+""+formatedDate+".jpg";
	 				filecontent = txtClassPicture.getInputstream();
	 				
	 				out = new FileOutputStream(new File(path + picName));
	 				
	 				int read = 0;
	 		        final byte[] bytes = new byte[1024];
	 				
	 				while ((read = filecontent.read(bytes)) != -1) {
	 		            out.write(bytes, 0, read);
	 		        }
	 				
	 		        //log.info("New file " + picName + " created at " + path);
	 				entity.setClassPicture(picName);
	 		        
	 				 } catch (FileNotFoundException fne) {
	 					FacesUtils.addErrorMessage("Archivo seleccionado invalido o protegido");
	 					log.info(fne.toString());
	 			    } finally {
	 			        if (out != null) {
	 			            out.close();
	 			        }
	 			        if (filecontent != null) {
	 			            filecontent.close();
	 			        }
	 			    }//end try catch  
	 				
	 				businessDelegatorView.updateSpecialclass(entity);
	                
	 	        }else{
	 	        	FacesUtils.addErrorMessage("No selecciono una foto de la clase");
	 	        }
               
                 FacesUtils.addInfoMessage("Clase actualizada satisfactoriamente");
        	}//end if-else
        
        } catch (Exception e) {
            entity = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }


        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedSpecialclass = (SpecialclassDTO) (evt.getComponent()
                                                         .getAttributes()
                                                         .get("selectedSpecialclass"));

            Integer idspecialclass = new Integer(selectedSpecialclass.getIdspecialclass());
            entity = businessDelegatorView.getSpecialclass(idspecialclass);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer idspecialclass = FacesUtils.checkInteger(txtIdspecialclass);
            entity = businessDelegatorView.getSpecialclass(idspecialclass);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteSpecialclass(entity);
            FacesUtils.addInfoMessage("Clase eliminada satisfactoriamente");
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
            selectedSpecialclass = (SpecialclassDTO) (evt.getComponent()
                                                         .getAttributes()
                                                         .get("selectedSpecialclass"));

            Integer idspecialclass = new Integer(selectedSpecialclass.getIdspecialclass());
            entity = businessDelegatorView.getSpecialclass(idspecialclass);
            businessDelegatorView.deleteSpecialclass(entity);
            data.remove(selectedSpecialclass);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String classPicture, String classTitle,
        String description, Date endHour, Date fecha, Integer havebathroom,
        Integer havechangeclothes, Integer haveparkingbike,
        Integer haveparkingcar, Integer haveshower, Integer idspecialclass,
        String latitude, String longitud, String placeAddress,
        String placeName, Double price, Date startHour)
        throws Exception {
        try {
            entity.setClassPicture(FacesUtils.checkString(classPicture));
            entity.setClassTitle(FacesUtils.checkString(classTitle));
            entity.setDescription(FacesUtils.checkString(description));
            entity.setEndHour(FacesUtils.checkDate(endHour));
            entity.setFecha(FacesUtils.checkDate(fecha));
            entity.setHavebathroom(FacesUtils.checkInteger(havebathroom));
            entity.setHavechangeclothes(FacesUtils.checkInteger(
                    havechangeclothes));
            entity.setHaveparkingbike(FacesUtils.checkInteger(haveparkingbike));
            entity.setHaveparkingcar(FacesUtils.checkInteger(haveparkingcar));
            entity.setHaveshower(FacesUtils.checkInteger(haveshower));
            entity.setLatitude(FacesUtils.checkString(latitude));
            entity.setLongitud(FacesUtils.checkString(longitud));
            entity.setPlaceAddress(FacesUtils.checkString(placeAddress));
            entity.setPlaceName(FacesUtils.checkString(placeName));
            entity.setPrice(FacesUtils.checkDouble(price));
            entity.setStartHour(FacesUtils.checkDate(startHour));
            businessDelegatorView.updateSpecialclass(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("SpecialclassView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtClassTitle() {
        return txtClassTitle;
    }

    public void setTxtClassTitle(InputText txtClassTitle) {
        this.txtClassTitle = txtClassTitle;
    }

    public InputText getTxtDescription() {
        return txtDescription;
    }

    public void setTxtDescription(InputText txtDescription) {
        this.txtDescription = txtDescription;
    }

    public InputText getTxtLatitude() {
        return txtLatitude;
    }

    public void setTxtLatitude(InputText txtLatitude) {
        this.txtLatitude = txtLatitude;
    }

    public InputText getTxtLongitud() {
        return txtLongitud;
    }

    public void setTxtLongitud(InputText txtLongitud) {
        this.txtLongitud = txtLongitud;
    }

    public InputText getTxtPlaceAddress() {
        return txtPlaceAddress;
    }

    public void setTxtPlaceAddress(InputText txtPlaceAddress) {
        this.txtPlaceAddress = txtPlaceAddress;
    }

    public InputText getTxtPlaceName() {
        return txtPlaceName;
    }

    public void setTxtPlaceName(InputText txtPlaceName) {
        this.txtPlaceName = txtPlaceName;
    }

    public InputText getTxtPrice() {
        return txtPrice;
    }

    public void setTxtPrice(InputText txtPrice) {
        this.txtPrice = txtPrice;
    }

    public Calendar getTxtEndHour() {
        return txtEndHour;
    }

    public void setTxtEndHour(Calendar txtEndHour) {
        this.txtEndHour = txtEndHour;
    }

    public Calendar getTxtFecha() {
        return txtFecha;
    }

    public void setTxtFecha(Calendar txtFecha) {
        this.txtFecha = txtFecha;
    }

    public Calendar getTxtStartHour() {
        return txtStartHour;
    }

    public void setTxtStartHour(Calendar txtStartHour) {
        this.txtStartHour = txtStartHour;
    }

    public InputText getTxtIdspecialclass() {
        return txtIdspecialclass;
    }

    public void setTxtIdspecialclass(InputText txtIdspecialclass) {
        this.txtIdspecialclass = txtIdspecialclass;
    }

    public List<SpecialclassDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataSpecialclass();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<SpecialclassDTO> specialclassDTO) {
        this.data = specialclassDTO;
    }

    public SpecialclassDTO getSelectedSpecialclass() {
        return selectedSpecialclass;
    }

    public void setSelectedSpecialclass(SpecialclassDTO specialclass) {
        this.selectedSpecialclass = specialclass;
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

	public String getClassDecription() {
		return classDecription;
	}

	public void setClassDecription(String classDecription) {
		this.classDecription = classDecription;
	}

	public boolean isTxtHavebathroom() {
		return txtHavebathroom;
	}

	public void setTxtHavebathroom(boolean txtHavebathroom) {
		this.txtHavebathroom = txtHavebathroom;
	}

	public boolean isTxtHavechangeclothes() {
		return txtHavechangeclothes;
	}

	public void setTxtHavechangeclothes(boolean txtHavechangeclothes) {
		this.txtHavechangeclothes = txtHavechangeclothes;
	}

	public boolean isTxtHaveparkingbike() {
		return txtHaveparkingbike;
	}

	public void setTxtHaveparkingbike(boolean txtHaveparkingbike) {
		this.txtHaveparkingbike = txtHaveparkingbike;
	}

	public boolean isTxtHaveparkingcar() {
		return txtHaveparkingcar;
	}

	public void setTxtHaveparkingcar(boolean txtHaveparkingcar) {
		this.txtHaveparkingcar = txtHaveparkingcar;
	}

	public boolean isTxtHaveshower() {
		return txtHaveshower;
	}

	public void setTxtHaveshower(boolean txtHaveshower) {
		this.txtHaveshower = txtHaveshower;
	}

	public UploadedFile getTxtClassPicture() {
		return txtClassPicture;
	}

	public void setTxtClassPicture(UploadedFile txtClassPicture) {
		this.txtClassPicture = txtClassPicture;
	}

	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	public boolean getDayOfWeekClass() {
		return dayOfWeekClass;
	}

	public void setDayOfWeekClass(boolean dayOfWeekClass) {
		this.dayOfWeekClass = dayOfWeekClass;
	}

	public String getClassPicturePath() {
		return classPicturePath;
	}

	public void setClassPicturePath(String classPicturePath) {
		this.classPicturePath = classPicturePath;
	}
	
	
	
}
