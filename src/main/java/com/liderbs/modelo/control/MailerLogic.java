package com.liderbs.modelo.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liderbs.dataaccess.dao.IMailerDAO;


@Scope("singleton")
@Service("MailerLogic")
public class MailerLogic implements IMailerLogic {
	
	 @Autowired
	 private IMailerDAO mailerDAO;
	
	public void sendMail(String from,String to,String subject,String text,List<Object[]> listAttachment)throws Exception{
		mailerDAO.sendMail(from, to, subject, text,listAttachment);
	}
	
}
