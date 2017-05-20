package pl.gramachinx.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No such Object")
public class ObjectNotFoundException extends Exception{
	
	public ObjectNotFoundException(String mess) {
		super(mess);
	}

}
