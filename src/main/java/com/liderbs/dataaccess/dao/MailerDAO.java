package com.liderbs.dataaccess.dao;


import java.util.List;
import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Repository;


@Repository("MailerDAO")
public class MailerDAO implements IMailerDAO {
	
	@Resource
    private JavaMailSender mailSender;
	
	public void sendMail(final String from, final String to, final String subject, final String text, final List<Object[]> listAttachment)throws Exception{
		   /* SimpleMailMessage message = new SimpleMailMessage();
		    message.setContent(message, "text/html");
	        message.setTo(to);
	        message.setFrom(from);
	        message.setSubject(subject);
	        message.setText(text);
	        mailSender.send(message);*/
	        
	        mailSender.send(new MimeMessagePreparator() {
	        	   public void prepare(MimeMessage mimeMessage) throws MessagingException {
	        	     MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");	        	     
	        	     message.setFrom(from);
	        	     message.setTo(to.split(","));
	        	     message.setSubject(subject);
	        	     //message.setText("<img src='cid:myLogo'>"+text, true);
	        	     message.setText(text, true);
	        	     //message.addInline("myLogo", new ClassPathResource("logoxls.png",getClass().getClassLoader()));
	        	     if(listAttachment!=null)
	        	     for (Object[] objects : listAttachment) {
	        	    	 String nameFile=(String)objects[0];
	        	    	 byte[] bytes=(byte[])objects[1];
	        	    	 InputStreamSource attachSource = new ByteArrayResource(bytes);
		        	     message.addAttachment(nameFile, attachSource);
					 }
	        	     
	        	   }
	        	 });
	
	}
}
