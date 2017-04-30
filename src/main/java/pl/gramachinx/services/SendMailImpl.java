package pl.gramachinx.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import pl.gramachinx.domains.User;
import pl.gramachinx.repository.UserRepository;

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
