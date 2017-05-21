package pl.gramachinx.services;

import pl.gramachinx.domains.User;

public interface PasswordService {
	boolean passwordMatch(String oldpass, User user);
	void changePass(String newpass, User user);
}
