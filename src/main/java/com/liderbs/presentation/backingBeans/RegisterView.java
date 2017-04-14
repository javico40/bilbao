package com.liderbs.presentation.backingBeans;

import java.io.IOException;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.component.inputtext.InputText;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liderbs.utilities.FacesUtils;
import com.liderbs.modelo.Account;
import com.liderbs.modelo.Options;
import com.liderbs.modelo.Profile;
import com.liderbs.modelo.Users;
import com.liderbs.presentation.businessDelegate.IBusinessDelegatorView;

@ManagedBean
@ViewScoped
public class RegisterView  implements Serializable {
	
	private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(RegisterView.class);
    
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;
    private InputText names;
    private InputText username;
    private InputText password; 
    
    public RegisterView() {
		
	}
     
    public IBusinessDelegatorView getBusinessDelegatorView() {
		return businessDelegatorView;
	}



	public void setBusinessDelegatorView(IBusinessDelegatorView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}



	public void register(){
    
		try {
			
		if(FacesUtils.checkString(names) == null){
    		FacesUtils.addErrorMessage("Ingrese su nombre y apellido");
    	}else if(FacesUtils.checkString(username) == null){
    		FacesUtils.addErrorMessage("Ingrese un correo valido");
    	}else if(FacesUtils.checkString(password) == null){
    		FacesUtils.addErrorMessage("Ingrese una contraseña valida");
    	}else{
    		
    		String nombres = FacesUtils.checkString(names);
    		String user = FacesUtils.checkString(username);
    		String pass = FacesUtils.checkString(password);
    		
    		
				List<Users> list = businessDelegatorView.findByCriteriaInUsers(new Object[]{"email",true, user, "="},
																			   null,
																			   null);
				
				if(list.size() > 0){
					FacesUtils.addErrorMessage("El correo ya se encuentra registrado");
				}else{
					
					//Crear el usuario
					
					String hash = texMD5(pass);
					
					
					Date today = new Date();
					Users userNew = new Users();
					userNew.setUsername(user.toUpperCase());
					userNew.setName(nombres);
					userNew.setPassword(hash);
					userNew.setEmail(user);
					userNew.setStatus(1);
					userNew.setCreated(today);
					
					businessDelegatorView.saveUsers(userNew);
					
					//Buscar el perfil para usuarios
					List<Profile> profileList = businessDelegatorView.findByCriteriaInProfile(new Object[]{"profileName",true, "ENTRENADORES_REGISTRADOS", "="},
							                                                              null,
							                                                              null);
					
					int profileId = 0;
					
					for(Profile perfil: profileList){
						profileId = perfil.getIdprofile();
					}
					
					
					if(profileId == 0){
						log.info("Pefil de usuarios no configurado, usuarios no pueden loguearse");
					}else{
						
						
						Profile perfil = businessDelegatorView.getProfile(profileId);
					
						List<Users> listUser = new ArrayList<Users>();
						listUser.add(userNew);
						
						//Crear su cuenta de usuario
						
						Account cuenta = new Account();
						cuenta.setAccountCreated(today);
						cuenta.setAccountDefault(1);
						cuenta.setAccountStatus(1);
						cuenta.setAccountName(user);
						cuenta.setAccountUserCreated("selfregister");
						cuenta.setProfile(perfil);
						cuenta.setUserses(new LinkedHashSet<Users>(listUser));
						
						businessDelegatorView.saveAccount(cuenta);
						
						String url = ("sucess.xhtml");
		    	    	FacesContext fc = FacesContext.getCurrentInstance();
		    	    	ExternalContext ec = fc.getExternalContext();
		    	    	ec.redirect(url);
		    	    	
					}//
					
					
					
					
				}//end if-else
    	}
		
		} catch (Exception e) {
			log.info("Error al registrar el usaurio: "+e.toString());
		}
    }
	
	public String texMD5(String cadena) {
		String hash=cadena;
       	 byte[] defaultBytes =cadena.getBytes();	        	
       	 MessageDigest algorithm;
		try {
			algorithm = MessageDigest.getInstance("MD5");		
			algorithm.reset();
			algorithm.update(defaultBytes);
			byte messageDigest[] = algorithm.digest();	        		            
			StringBuffer hexString = new StringBuffer();
			for (int i=0;i<messageDigest.length;i++) {
				 int val = 0xff &  messageDigest[i];
				  if (val < 16)
                     hexString.append("0");
                 hexString.append(Integer.toHexString(val));
                 //hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
			}	        			        			
			hash=hexString+"";	
		return hash;
		} catch (NoSuchAlgorithmException e) {
			return null;
		}	
	}

	public InputText getNames() {
		return names;
	}

	public void setNames(InputText names) {
		this.names = names;
	}

	public InputText getUsername() {
		return username;
	}

	public void setUsername(InputText username) {
		this.username = username;
	}

	public InputText getPassword() {
		return password;
	}

	public void setPassword(InputText password) {
		this.password = password;
	}
    
    
    

}
