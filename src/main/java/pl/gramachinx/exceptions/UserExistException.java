package pl.gramachinx.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No such Object")
public class UserExistException extends Exception {
	
	public UserExistException(String mess) {
		super(mess);
	}

}
