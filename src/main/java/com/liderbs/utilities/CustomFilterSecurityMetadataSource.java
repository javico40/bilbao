package com.liderbs.utilities;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import com.liderbs.modelo.Account;
import com.liderbs.modelo.Options;
import com.liderbs.modelo.Profile;
import com.liderbs.presentation.businessDelegate.IBusinessDelegatorView;

public class CustomFilterSecurityMetadataSource implements FilterInvocationSecurityMetadataSource  {
	
	@Autowired
	private IBusinessDelegatorView businessDelegatorView;

	@Override
	public Collection<ConfigAttribute> getAttributes(Object filter) throws IllegalArgumentException {

		FilterInvocation filterInvocation = (FilterInvocation) filter;
		String url = filterInvocation.getRequestUrl();
		
		//get the roles for requested page from the db
		StringBuilder rolesStringBuilder = new StringBuilder();
		String urlPropsValue ="";			
		String[] urlParts = url.split("/");	
		url=urlParts[2];	
		
		try {
			
			List<Options> listOpt = businessDelegatorView.findByCriteriaInOptions(new Object[]{"optionsUrl",true, url, "=","optionsStatus",false, 1, "="},
                    null,
                    null);
			
			if(listOpt.size() == 0){
				throw new AccessDeniedException("Esta opcion no se encuentra configurada en su perfil");
			}
			
			for (Options option : listOpt) {
  				
				 Hibernate.initialize(option.getProfiles());
            	 Set<Profile> listProfiles = option.getProfiles();
            	 
            	 for(Profile profile: listProfiles){
            		 urlPropsValue+=(urlPropsValue.equals("")?profile.getProfileName():","+profile.getProfileName());
            	 }
  			}
			
			

			
    		//Map<String, Object> mapparameter=new HashMap<String, Object>();
    		//mapparameter.put("seguridadjci", "SEGURIDADJCI%");
    		//List<Object[]> rolesObject;
    		
			/*
    		if(url.contains("inicio")||url.contains("images")){
    			
    			List<Options> listOpt = businessDelegatorView.findByCriteriaInOptions(new Object[]{"optionsUrl",true, url, "=","optionsStatus",false, 1, "="},
    					                                                              null,
    					                                                              null);
    			
    			if(listOpt.size() == 0){
    				throw new AccessDeniedException("Esta opcion no se encuentra configurada");
    			}
    			
    			//rolesObject=businessDelegatorView.findConsultaNombre("consultaRolesAppIni", mapparameter);
    		}else{
    		  //mapparameter.put("menupathjci", url);
    		  //mapparameter.put("opcenlacev", urlParts[2]);
    		  //rolesObject=businessDelegatorView.findConsultaNombre("consultaRolesApp", mapparameter);    		  
    		}*/	
    		
    		/*
    		for (Object[] objects : rolesObject) {
  				urlPropsValue+=(urlPropsValue.equals("")?objects[1].toString():","+objects[1].toString());
  			}*/
			
    		
		}catch (Exception e) {
			throw new AccessDeniedException(e.toString());
		}		
		
		rolesStringBuilder.append(urlPropsValue);
		
		if(rolesStringBuilder.length() == 0) {
			throw new AccessDeniedException("No existe configuracion permitida para esta opción.");
		}	
		
		return SecurityConfig.createListFromCommaDelimitedString(rolesStringBuilder.toString());
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return true;
	}

}
