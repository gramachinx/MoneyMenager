package pl.gramachinx.domains;

import pl.gramachinx.services.StatisticService;

public class Stats {
	
	private double saldo;
	private double userDebt;
	private double debets;
	private double income;
	private double payments;
	private double completSaldo;
	private double saldoUserDebt;
	private double saldoDebt;

	//TODO itd.
	
	public Stats(StatisticService statServ, UserData userData)
	{
		saldo = statServ.getSaldo(userData);
		userDebt=statServ.getMyDebts(userData);
		debets = statServ.getDebts(userData);
		income = statServ.getIncomeMoney(userData);
		payments = statServ.getPayments(userData);
		completSaldo = statServ.getSaldoWithDebts(userData);
		saldoDebt = statServ.getSaldoWithNotUserDebts(userData);
		saldoUserDebt = statServ.getSaldoWithUserDebts(userData);
		
		
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public double getUserDebt() {
		return userDebt;
	}

	public void setUserDebt(double userDebt) {
		this.userDebt = userDebt;
	}

	public double getDebets() {
		return debets;
	}

	public void setDebets(double debets) {
		this.debets = debets;
	}

	public double getIncome() {
		return income;
	}

	public void setIncome(double income) {
		this.income = income;
	}

	public double getPayments() {
		return payments;
	}

	public void setPayments(double payments) {
		this.payments = payments;
	}

	public double getCompletSaldo() {
		return completSaldo;
	}

	public void setCompletSaldo(double completSaldo) {
		this.completSaldo = completSaldo;
	}

	public double getSaldoUserDebt() {
		return saldoUserDebt;
	}

	public void setSaldoUserDebt(double saldoUserDebt) {
		this.saldoUserDebt = saldoUserDebt;
	}

	public double getSaldoDebt() {
		return saldoDebt;
	}

	public void setSaldoDebt(double saldoDebt) {
		this.saldoDebt = saldoDebt;
	}
	
	

}
