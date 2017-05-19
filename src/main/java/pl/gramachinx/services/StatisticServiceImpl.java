package pl.gramachinx.services;

import org.springframework.stereotype.Service;

import pl.gramachinx.domains.Bill;
import pl.gramachinx.domains.Debt;
import pl.gramachinx.domains.UserData;

@Service
public class StatisticServiceImpl implements StatisticService {

	@Override
	public double getSaldo(UserData userData) {
		return userData.getWallet(); //TODO changessss
	}

	@Override
	public double getPayments(UserData userData) {
		double value = 0;
		for(Bill b : userData.getBills())
		{
			if(b.isPayBill() == true)
			{
				value+=b.getMoney(); //TODO moze byc ujemne
			}
		}
		return value;
	}

	@Override
	public double getIncomeMoney(UserData userData) {
		double value = 0;
		for(Bill b : userData.getBills())
		{
			if(b.isPayBill() == false)
			{
				value+=b.getMoney(); //TODO moze byc ujemne
			}
		}
		return value;
	}

	@Override
	public double getMyDebts(UserData userData) {
		double value = 0;
		for(Debt b : userData.getDebt())
		{
			if(b.isUserDebt() == true)
			{
				value+=b.getMoney(); 
			}
		}
		return value;
	}

	@Override
	public double getDebts(UserData userData) {
		double value = 0;
		for(Debt b : userData.getDebt())
		{
			if(b.isUserDebt() == false)
			{
				value+=b.getMoney(); 
			}
		}
		return value;
	}

	@Override
	public double getSaldoWithUserDebts(UserData userData) {	
		return getSaldo(userData) - getMyDebts(userData);
		
	}

	@Override
	public double getSaldoWithNotUserDebts(UserData userData) {
		return getSaldo(userData) + getDebts(userData);
	}

	@Override
	public double getSaldoWithDebts(UserData userData) {
		return getSaldoWithNotUserDebts(userData) + getDebts(userData);
	}

}
