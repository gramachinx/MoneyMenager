package pl.gramachinx.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import pl.gramachinx.domains.User;
import pl.gramachinx.repository.UserRepository;
import pl.gramachinx.services.SendMail;
@Service
public class SendMailImpl implements SendMail {

	@Autowired
	UserRepository userRepo;
	
	 @Autowired
	 public JavaMailSender emailSender;
	public boolean sendMail(String username) {
		User user = userRepo.findByUsername(username);

		SimpleMailMessage message = new SimpleMailMessage();
		message.setText("Twoj unikalny kod to: " + user.getSpecialNumber());
		message.setSubject("Kod do autoryzacji na Money Menager");
		message.setTo(user.getEmail());
		emailSender.send(message);
		
		return false;
	}

}
