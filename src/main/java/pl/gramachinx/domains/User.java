package pl.gramachinx.domains;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
@Entity
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
	private long specialNumber;
	
	@NotNull
	private boolean isActive;
	
	@NotNull
	private boolean isConfig;
	@NotNull
	private boolean enabled;

	
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserData getUserData() {
		return userData;
	}

	public void setUserData(UserData userData) {
		this.userData = userData;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public boolean isConfig() {
		return isConfig;
	}

	public void setConfig(boolean isConfig) {
		this.isConfig = isConfig;
	}

	public long getSpecialNumber() {
		return specialNumber;
	}

	public void setSpecialNumber(long specialNumber) {
		this.specialNumber = specialNumber;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", name=" + name + ", email="
				+ email + ", userData=" + userData + ", role=" + role + ", specialNumber=" + specialNumber
				+ ", isActive=" + isActive + ", isConfig=" + isConfig + "]";
	}
	
	
	
	
	
	

}
