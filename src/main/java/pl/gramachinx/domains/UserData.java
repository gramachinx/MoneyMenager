package pl.gramachinx.domains;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class UserData {
	
	@Id
	@GeneratedValue
	private long id;
	
	@OneToOne 
	private User user;
	
	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
	private FirstConfig firstConfig;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public FirstConfig getFirstConfig() {
		return firstConfig;
	}

	public void setFirstConfig(FirstConfig firstConfig) {
		this.firstConfig = firstConfig;
	}
	
	

}
