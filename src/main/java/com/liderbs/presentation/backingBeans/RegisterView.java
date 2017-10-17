package com.liderbs.presentation.backingBeans;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.password.Password;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liderbs.utilities.FacesUtils;
import com.liderbs.modelo.Academiclevel;
import com.liderbs.modelo.Account;
import com.liderbs.modelo.Cities;
import com.liderbs.modelo.Identification;
import com.liderbs.modelo.Options;
import com.liderbs.modelo.Place;
import com.liderbs.modelo.Profile;
import com.liderbs.modelo.Trainer;
import com.liderbs.modelo.Users;
import com.liderbs.modelo.Vprofile;
import com.liderbs.presentation.businessDelegate.IBusinessDelegatorView;

@ManagedBean
@ViewScoped
public class RegisterView implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(RegisterView.class);

	@ManagedProperty(value = "#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;
	private InputText username;
	private InputText userlogin;
	private InputText correo;
	private Password password;
	private InputText centroDeportivo;
	private Integer city;
	private List<SelectItem> cities;
	
	

	public RegisterView() {
		super();
	}

	public IBusinessDelegatorView getBusinessDelegatorView() {
		return businessDelegatorView;
	}

	public void setBusinessDelegatorView(IBusinessDelegatorView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}

	public void register() {

		try {

			if (FacesUtils.checkString(userlogin) == null) {
				FacesUtils.addErrorMessage("Ingrese un nombre de usuario valido");
			} else if (FacesUtils.checkString(correo) == null) {
				FacesUtils.addErrorMessage("Ingrese un correo valido");
			} else if (FacesUtils.checkString(password) == null) {
				FacesUtils.addErrorMessage("Ingrese una contraseña valida");
			} else {

				String usern = FacesUtils.checkString(userlogin);
				String email = FacesUtils.checkString(correo);
				String pass = FacesUtils.checkString(password);

				// Buscar el si el nombre de usuario ya se encuentra registrado

				List<Users> list = businessDelegatorView
						.findByCriteriaInUsers(new Object[] { "username", true, usern.toUpperCase(), "=" }, null, null);

				if (list.size() > 0) {
					FacesUtils.addErrorMessage("El nombre de usuario ya se encuentra registrado");
				} else {

					list = businessDelegatorView.findByCriteriaInUsers(new Object[] { "email", true, email, "=" }, null,
							null);

					if (list.size() > 0) {
						FacesUtils.addErrorMessage("El correo ya se encuentra registrado");
					} else {

						// Crear el usuario
						
						Cities city = businessDelegatorView.getCities(1);

						String hash = texMD5(pass);

						Date today = new Date();
						Users userNew = new Users();
						userNew.setUsername(usern.toUpperCase());
						userNew.setPassword(hash);
						userNew.setEmail(email);
						userNew.setStatus(1);
						userNew.setIstrainer(1);
						userNew.setCities(city);
						userNew.setCreated(today);

						businessDelegatorView.saveUsers(userNew);

						// Buscar el perfil para usuarios
						List<Profile> profileList = businessDelegatorView.findByCriteriaInProfile(
								new Object[] { "profileName", true, "ENTRENADORES_REGISTRADOS", "=" }, null, null);

						int profileId = 0;

						for (Profile perfil : profileList) {
							profileId = perfil.getIdprofile();
						}

						if (profileId == 0) {
							log.info("Pefil de usuarios no configurado, usuarios no pueden loguearse");
						} else {

							Profile perfil = businessDelegatorView.getProfile(profileId);

							List<Users> listUser = new ArrayList<Users>();
							listUser.add(userNew);

							// Crear su cuenta de usuario

							Account cuenta = new Account();
							cuenta.setAccountCreated(today);
							cuenta.setAccountDefault(1);
							cuenta.setAccountStatus(1);
							cuenta.setAccountName(usern.toUpperCase());
							cuenta.setAccountUserCreated("selfregister");
							cuenta.setProfile(perfil);
							cuenta.setUserses(new LinkedHashSet<Users>(listUser));

							businessDelegatorView.saveAccount(cuenta);
							
							//Crear su perfil de entrenador
							
							Academiclevel academic = businessDelegatorView.getAcademiclevel(6);
							Identification identi = businessDelegatorView.getIdentification(5);
							
							Trainer trainer = new Trainer();
							trainer.setUsersIdusers(userNew.getIdusers());
							trainer.setAcademiclevel(academic);
							trainer.setIdentification(identi);
							trainer.setTrainerProfStatus(0);
							
							businessDelegatorView.saveTrainer(trainer);
							
							
							//EMAIL SEND
							
							/*Mail parameters*/
							
							final String FROM = "noreply@govirfit.com";
						    final String FROMNAME = "	Govirfit";
							final String TO = "rubenzportilla@hotmail.com";
						    final String SMTP_USERNAME = "AKIAJNZAR6WYMWWR75NA";
						    final String SMTP_PASSWORD = "AtsODaP/MZPQ2W+xpBpI9TR9sK3RHSl4sWjH0G0eeIbl";
						    final String HOST = "email-smtp.us-west-2.amazonaws.com";
						    final int PORT = 465;
						    final String SUBJECT = "Nuevo Usuario registrado en Govirfit";
						    
						    final String BODY = ""+
						    	    "<h1>Registro de Usuarios Govirfit</h1>"+
						    	    "<p>El usuario "+userNew.getUsername()+" "+
						    	    "con el correo "+userNew.getEmail()+" "+
						    	    "se ha registrado en la plataforma";
							
							 // Create a Properties object to contain connection configuration information.
					    	Properties props = System.getProperties();
					    	props.put("mail.transport.protocol", "smtp");
					    	props.put("mail.smtp.port", PORT); 
					    	props.put("mail.smtp.ssl.enable", "true");
					    	props.put("mail.smtp.auth", "true");
					    	
					    	// Create a Session object to represent a mail session with the specified properties. 
					    	Session session = Session.getDefaultInstance(props);

					        // Create a message with the specified information. 
					        MimeMessage msg = new MimeMessage(session);
					        msg.setFrom(new InternetAddress(FROM,FROMNAME));
					        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(TO));
					        msg.setSubject(SUBJECT);
					        msg.setContent(BODY,"text/html");
					        
					        // Add a configuration set header. Comment or delete the 
					        // next line if you are not using a configuration set
					        //msg.setHeader("X-SES-CONFIGURATION-SET", CONFIGSET);
					            
					        // Create a transport.
					        Transport transport = session.getTransport();
					        
					        // Send the message.
					        try
					        {
					            // Connect to Amazon SES using the SMTP username and password you specified above.
					            transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);
					        	
					            // Send the email.
					            transport.sendMessage(msg, msg.getAllRecipients());
					           
					        }
					        catch (Exception ex) {
					        	log.info("Correo no enviado "+ex.toString());
					        }
					        finally
					        {
					            // Close and terminate the connection.
					            transport.close();
					        }
					        
					        /*
							
							businessDelegatorView.sendMail("noreply@govirfit.com", "fortaleza40@gmail.com", "Bienvenido a Govirfit",
		        							  "<style type=\"text/css\"></style>" + "<blockquote> "
		        							+ "<p  align=\"left\"><font style=\" color: #505050; font-size: 13px; font-weight: bold; \">"
		        							+ "Alcanza tus metas con Govirfit, entrena a otros y diviertete en el proceso" + "<br>" + "<br>"
		        							+ "Somos una plataforma diseñada por personas que aman el fitness y el deporte<br>"
		        							+ "por eso trabajamos constantemente para que nuestros afiliados mejoren sus ganancias<br>"
		        							+ "<br>"
		        							+ "<br>"
		        							+ "<p>&nbsp;</p>"
		        							+ "Agradecemos que hagas parte de nuestro equipo "
		        							+ "<br>" + "Atentamente,<br>" + " Govirfit Team<br>" + "<br>"
		        							+ "Si tienes alguna duda escribe a nuestro correo info@govirfit.com"
		        							+ "<br>" + " </font> </p>" + " </blockquote>",
		        					null);
							
							*/
							
							FacesUtils.addInfoMessage("Te has registrado satisfactoriamente, ingresa con tu usuario y contraseña.");
							
							//Send email welcome govirfit
							
							//End Send email

							String url = ("login.xhtml");
							FacesContext fc = FacesContext.getCurrentInstance();
							ExternalContext ec = fc.getExternalContext();
							ec.redirect(url);

						} //

					} // end if-else

				} // end user name validation

			}

		} catch (Exception e) {
			log.info("Error al registrar el usaurio: " + e.toString());
		}
	}

	public void registerCenpro() {

		try {

			if (FacesUtils.checkString(userlogin) == null) {
				FacesUtils.addErrorMessage("Ingrese su nombre de usuario");
			} else if (FacesUtils.checkString(correo) == null) {
				FacesUtils.addErrorMessage("Ingrese un correo valido");
			} else if (FacesUtils.checkString(password) == null) {
				FacesUtils.addErrorMessage("Ingrese una contraseña valida");
			} else if (FacesUtils.checkString(centroDeportivo) == null) {
				FacesUtils.addErrorMessage("Ingrese el nombre del centro deportivo");
			} else {

				String centro = FacesUtils.checkString(centroDeportivo);
				String usuario = FacesUtils.checkString(userlogin);
				String email = FacesUtils.checkString(correo);
				String pass = FacesUtils.checkString(password);

				List<Users> list = businessDelegatorView
						.findByCriteriaInUsers(new Object[] { "username", true, usuario, "=" }, null, null);

				if (list.size() > 0) {
					FacesUtils.addErrorMessage("El nombre de usuario ya se encuentra registrado");
				} else {
					
					list.clear();
					
					list = businessDelegatorView
							.findByCriteriaInUsers(new Object[] { "email", true, email, "=" }, null, null);
					
					if (list.size() > 0) {
						FacesUtils.addErrorMessage("El correo ya se encuentra registrado");
					} else {
						
						// Crear el usuario
						
						Cities city = businessDelegatorView.getCities(1);


						String hash = texMD5(pass);

						Date today = new Date();
						Users userNew = new Users();
						userNew.setUsername(usuario.toUpperCase());
						userNew.setPassword(hash);
						userNew.setEmail(email);
						userNew.setStatus(1);
						userNew.setIstrainer(0);
						userNew.setCreated(today);
						userNew.setCities(city);
						

						businessDelegatorView.saveUsers(userNew);

						// Buscar el perfil para usuarios
						List<Profile> profileList = businessDelegatorView.findByCriteriaInProfile(
								new Object[] { "profileName", true, "CENTROS DEPORTIVOS", "=" }, null, null);

						int profileId = 0;

						for (Profile perfil : profileList) {
							profileId = perfil.getIdprofile();
						}

						if (profileId == 0) {
							log.info("Pefil de usuarios no configurado, usuarios no pueden loguearse");
						} else {

							Profile perfil = businessDelegatorView.getProfile(profileId);

							List<Users> listUser = new ArrayList<Users>();
							listUser.add(userNew);

							// Crear su cuenta de usuario

							Account cuenta = new Account();
							cuenta.setAccountCreated(today);
							cuenta.setAccountDefault(1);
							cuenta.setAccountStatus(1);
							cuenta.setAccountName(usuario.toUpperCase());
							cuenta.setAccountUserCreated("selfregister");
							cuenta.setProfile(perfil);
							cuenta.setUserses(new LinkedHashSet<Users>(listUser));

							businessDelegatorView.saveAccount(cuenta);

							// Crear el centro deportivo

							Place place = new Place();
							place.setPlaceName(centro);
							place.setPlaceStatus(1);
							place.setPlaceOwner(userNew.getIdusers());
							place.setPlaceIsVzone(0);
							place.setAccountID(cuenta.getIdAccount());

							businessDelegatorView.savePlace(place);

							String url = ("login.xhtml");
							FacesContext fc = FacesContext.getCurrentInstance();
							ExternalContext ec = fc.getExternalContext();
							ec.redirect(url);

						}//
					}//end if-else
				} // end if-else
			}

		} catch (Exception e) {
			log.info("Error al registrar el usaurio: " + e.toString());
		}
	}

	public String texMD5(String cadena) {
		String hash = cadena;
		byte[] defaultBytes = cadena.getBytes();
		MessageDigest algorithm;
		try {
			
			algorithm = MessageDigest.getInstance("MD5");
			algorithm.update(cadena.getBytes());
	        BigInteger hashi = new BigInteger(1, algorithm.digest());
	        hash = hashi.toString(16);
	        
	        while(hash.length() < 32) { //40 for SHA-1
	            hash = "0" + hash;
	        }
			
			/*
			algorithm.reset();
			algorithm.update(defaultBytes);
			byte messageDigest[] = algorithm.digest();
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < messageDigest.length; i++) {
				int val = 0xff & messageDigest[i];
				if (val < 16)
					hexString.append("0");
				hexString.append(Integer.toHexString(val));
				// hexString.append(Integer.toHexString(0xFF &
				// messageDigest[i]));
			}
			hash = hexString + "";
			*/
			
			return hash;
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}


	public InputText getUsername() {
		return correo;
	}

	public void setUsername(InputText username) {
		this.correo = username;
	}

	public Password getPassword() {
		return password;
	}

	public void setPassword(Password password) {
		this.password = password;
	}

	public InputText getCentroDeportivo() {
		return centroDeportivo;
	}

	public void setCentroDeportivo(InputText centroDeportivo) {
		this.centroDeportivo = centroDeportivo;
	}
	
	

	public InputText getCorreo() {
		return correo;
	}

	public void setCorreo(InputText correo) {
		this.correo = correo;
	}

	public Integer getCity() {
		return city;
	}

	public void setCity(Integer city) {
		this.city = city;
	}

	public List<SelectItem> getCities() {
		
		if (cities == null) {
			try {

				cities = new ArrayList<SelectItem>();

				List<Cities> list = businessDelegatorView
						.findByCriteriaInCities(new Object[] { "idcities", false, 1, "!=" }, null, null);
				if (list.size() > 0) {

					for (Cities city : list) {
						cities.add(new SelectItem(city.getIdcities(), city.getCitiesName()));
					}

				}

			} catch (Exception e) {
				log.info(e.toString());
				FacesUtils.addErrorMessage("Error al consultar las ciudades, por favor utilice la opcion ayuda.");
			}
		}
		
		return cities;
	}

	public void setCities(List<SelectItem> cities) {
		this.cities = cities;
	}

	public InputText getUserlogin() {
		return userlogin;
	}

	public void setUserlogin(InputText userlogin) {
		this.userlogin = userlogin;
	}
	
	

}
