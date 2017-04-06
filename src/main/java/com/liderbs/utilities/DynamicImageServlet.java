package com.liderbs.utilities;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpRequestHandler;

import com.liderbs.modelo.Users;
import com.liderbs.modelo.UsersId;
import com.liderbs.presentation.backingBeans.MenusView;
import com.liderbs.presentation.businessDelegate.IBusinessDelegatorView;

/**
 * Servlet implementation class DynamicImageServlet
 */

@Component("DynamicImageServlet")
public class DynamicImageServlet implements HttpRequestHandler {
	private static final long serialVersionUID = 1L;
	
	@Autowired
    private IBusinessDelegatorView businessDelegatorView;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DynamicImageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	@Override
	public void handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		InputStream my_banner_image=null;
		
		try { 
			
            String id = request.getParameter("id");
            String manual = request.getParameter("manual");
            
            if(manual!=null&&manual.equals("jci")){
            	
            	my_banner_image = getClass().getClassLoader().getResourceAsStream("manualSEGURIDAD.pdf");
				
            	try {
					byte[] bytes = IOUtils.toByteArray(my_banner_image);
					response.getOutputStream().write(bytes);
				} catch (IOException e1) {					
					e1.printStackTrace();
				}	
            }else{
            	
            	Users usuario = businessDelegatorView.getUsers(new Long(id));
            	
				if(usuario!=null){
				   //byte[] bytes =usuario.getLogo(); 
				   //response.getOutputStream().write(bytes);
				}else{
					my_banner_image = getClass().getClassLoader().getResourceAsStream("avatar1.jpg");
					try {
						byte[] bytes = IOUtils.toByteArray(my_banner_image);
						response.getOutputStream().write(bytes);
					} catch (IOException e1) {					
						e1.printStackTrace();
					}				
				}
		   }
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(my_banner_image!=null){
				try {
					my_banner_image.close();
				} catch (IOException e) {						
					e.printStackTrace();
				}
			}
		}
		
	}

	

}
