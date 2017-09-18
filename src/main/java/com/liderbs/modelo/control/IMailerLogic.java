package com.liderbs.modelo.control;

import java.util.List;

public interface IMailerLogic {
	
	public void sendMail(String from,String to,String subject,String text,List<Object[]> listAttachment)throws Exception;
}
