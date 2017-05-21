package pl.gramachinx.services;

public interface CheckAuthorize {
	boolean ifAuthorized(String username);
	boolean codeCorrect(String username, String code);

}
