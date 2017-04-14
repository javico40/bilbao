package com.liderbs.utilities;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.stereotype.Component;

import com.liderbs.modelo.Account;
import com.liderbs.modelo.Users;
import com.liderbs.presentation.businessDelegate.IBusinessDelegatorView;

@Scope("singleton")
@Component("CustomAuthenticationProviderLDAP")
public class CustomAuthenticationProviderLDAP implements AuthenticationProvider  {
		
	@Autowired
	private IBusinessDelegatorView businessDelegatorView;
	
	@Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		
		String username = authentication.getName();
        String password = (String) authentication.getCredentials();  
       
        Object[] busqueda=new Object[8];
        busqueda[0]="username";
        busqueda[1]=true;
        busqueda[2]=username.toUpperCase();
        busqueda[3]="=";
        busqueda[4]="status";
        busqueda[5]=false;
        busqueda[6]=1;
        busqueda[7]="=";        
        
        try {
        	
			Users user =businessDelegatorView.findByCriteriaInUsers(new Object[]{"username",true, username.toUpperCase(), "=","status",false, 1, "="},
																	null,
																	null).get(0);
	
			if(password==null||password.trim().equals("")){
				throw new BadCredentialsException("Contraseña invalida");	
			}else if (user == null){
				throw new BadCredentialsException("Usuario no valido");	        	           
	        }else{	
	        	
	        	 String hash = password;
	        	
	        	 byte[] defaultBytes = password.getBytes();	
	        	 
	        			MessageDigest algorithm = MessageDigest.getInstance("MD5");
	        			algorithm.reset();
	        			algorithm.update(defaultBytes);
	        			byte messageDigest[] = algorithm.digest();	        		            
	        			StringBuffer hexString = new StringBuffer();
	        			
	        			for (int i=0;i<messageDigest.length;i++) {
	        				 int val = 0xff &  messageDigest[i];
	        				  if (val < 16)
	                              hexString.append("0");
	                          hexString.append(Integer.toHexString(val));
	        			}	        			        			
	        	
	        			hash=hexString+"";	        		

	             if (user.getPassword().equalsIgnoreCase(hash)!= true){
	            	 throw new BadCredentialsException("Contraseña invalida");      
	             } else {
	            	 
	            	 user.setLastlogin(new Date());
	            	 //user.setNumentradas(user.getNumentradas()!=null?new Long(user.getNumentradas().intValue()+1):0L);
	            	 
	            	 businessDelegatorView.updateUsers(user);
	            	 ArrayList<GrantedAuthority> x = new ArrayList<GrantedAuthority>();	
	            	 
	            	 
	            	 Hibernate.initialize(user.getAccounts());
	            	 Set<Account> list = user.getAccounts();
	            	 
	            	 
	            	 /*
	            	 for(Account act: list){
	            		 Hibernate.initialize(act.getIdAccount());
	            	 }*/
	            	 
	            	 if(list.size() == 0){
	            		 throw new BadCredentialsException("Su usuario no tiene una cuenta asociada");     
	            	 }else{
	            		 
	            		 String profileDescription = "";
		            	 
		            	 for (Iterator<Account> it = list.iterator(); it.hasNext(); ) {
		            		 
		            		 	Account act = it.next();
		            	        
		            		 	//Si tiene cuenta activa
		            		 	if(act.getAccountStatus() == 1){
		            		 		if(act.getAccountDefault() == 1){
		            		 			Hibernate.initialize(act.getProfile());
		            		 			profileDescription = act.getProfile().getProfileName();
		            		 		}
		            		 	}
		            	    }
		            	 
		            	 GrantedAuthority g = new GrantedAuthorityImpl(profileDescription);
		 	        	 
		            	 //se agregan los roles que tiene el usuario autrizado
		 	        	 
		            	 x.add(g);
		            	 
		            	 return new UsernamePasswordAuthenticationToken(user, hash, x);
		            	 
	            	 }//end
	             }//validador contraseña
            }//end if-else	        
		} catch (Exception e) {
			System.out.println(e.toString());
			throw new BadCredentialsException("Authentication Fail.");
		}//end try-catch	
    } 
	
    @Override
	public boolean supports(Class<?> arg0) {
	     return true;
	}
         
		
}
