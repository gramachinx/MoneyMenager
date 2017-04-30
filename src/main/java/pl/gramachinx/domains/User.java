package pl.gramachinx.domains;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class User {
	
	@Id
	@GeneratedValue
	private long id;
	
	@NotEmpty
	@Size(min=3, max=25)
	private String username;
	
	@NotEmpty
	@Size(min=6)
	private String password;
	
	@NotEmpty
	@Size(max=15)
	private String name;
	
	@Email
	@NotEmpty
	private String email;
	
	@OneToOne
	private UserData userData;
	
	@NotEmpty
	private String role;
	
	@NotNull
	private boolean isActive;

}
