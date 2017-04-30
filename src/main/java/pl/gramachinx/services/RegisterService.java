package pl.gramachinx.services;

import pl.gramachinx.domains.UserRegister;

public interface RegisterService {
	boolean addUser(UserRegister user);
}
