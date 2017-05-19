package pl.gramachinx.services;

import pl.gramachinx.domains.UserData;

public interface StatisticService {
	
	double getSaldo(UserData userData);
	double getPayments(UserData userData);
	double getIncomeMoney(UserData userData);
	double getMyDebts(UserData userData);
	double getDebts(UserData userData);
	double getSaldoWithUserDebts(UserData userData);
	double getSaldoWithNotUserDebts(UserData userData);
	double getSaldoWithDebts(UserData userData);
}
