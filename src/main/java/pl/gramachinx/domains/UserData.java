package pl.gramachinx.domains;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.springframework.test.context.support.DefaultBootstrapContext;

@Entity
public class UserData {

	public UserData() {
		bills = new ArrayList<Bill>();
		debt = new ArrayList<Debt>();
	}

	@Id
	@GeneratedValue
	private long id;

	@OneToOne
	private User user;

	@NotNull
	private double wallet;

	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
	private FirstConfig firstConfig; // TODO przeniesc wallet do DATA USER i go
										// caly czas aktualizowac.

	@NotNull
	@OneToMany(cascade = CascadeType.ALL)
	private List<Bill> bills;

	@NotNull
	@OneToMany(cascade = CascadeType.ALL)
	private List<Debt> debt;

	public double getWallet() {
		return wallet;
	}

	public void setWallet(double wallet) {
		this.wallet = wallet;
	}

	public List<Debt> getDebt() {
		return debt;
	}

	public void setDebt(List<Debt> debt) {
		this.debt = debt;
	}

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

	public List<Bill> getBills() {
		return bills;
	}

	public void setBills(List<Bill> bills) {
		this.bills = bills;
	}

}
