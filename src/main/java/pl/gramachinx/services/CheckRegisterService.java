package pl.gramachinx.services;

public interface CheckRegisterService {
	public boolean usernameExist(String username);
	public boolean emailExist(String email);
}
