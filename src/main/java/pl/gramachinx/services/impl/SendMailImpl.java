package pl.gramachinx.services.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pl.gramachinx.domains.User;
import pl.gramachinx.repository.UserRepository;
import pl.gramachinx.services.SendMail;
@Service
public class SendMailImpl implements SendMail {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
    private BCryptPasswordEncoder b;
	
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
	
	//TODO rozdzielic to na 2 serwisy.
	public void passRecorvery(String email, User user) {
		String str = UUID.randomUUID().toString();
		user.setPassword(b.encode(str));
		userRepo.flush();
		System.out.println(user.getName());
		SimpleMailMessage message = new SimpleMailMessage();
		message.setText("Twoje nowe hasło to: " + str);
		message.setSubject("Zapomniane hasło.");
		message.setTo(email);
		emailSender.send(message);
		System.out.println("email dziala");
		
		
	}

}
