package pl.gramachinx.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pl.gramachinx.domains.User;
import pl.gramachinx.repository.UserRepository;
import pl.gramachinx.services.PasswordService;

@Service
public class PasswordServiceImpl implements PasswordService {
	@Autowired
    private BCryptPasswordEncoder b;
	@Autowired
	private UserRepository userRepo;



	public void changePass(String newpass, User user) {
		user.setPassword(b.encode(newpass));
		userRepo.flush();
	}



	public boolean passwordMatch(String oldpass, User user) {
		return b.matches(oldpass, user.getPassword());
	}

}
