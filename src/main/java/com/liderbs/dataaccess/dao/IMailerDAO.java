package com.liderbs.dataaccess.dao;

import java.util.List;

public interface IMailerDAO {

	public void sendMail(String from,String to,String subject,String text,List<Object[]> listAttachment)throws Exception;
	
}
