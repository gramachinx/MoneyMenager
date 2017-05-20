package pl.gramachinx.controllers.maininterface;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import pl.gramachinx.domains.UserData;
import pl.gramachinx.repository.UserRepository;
import pl.gramachinx.services.DataInterface;
import pl.gramachinx.services.StatisticService;

@ControllerAdvice(basePackages = "pl.gramachinx.controllers.maininterface")
public class ModelController {

	private StatisticService statServ;

	private DataInterface dataServ;

	@Autowired
	public ModelController(StatisticService statServ, DataInterface dataServ) {
		super();
		this.statServ = statServ;
		this.dataServ = dataServ;
	}

	@ModelAttribute("saldo")
	public double saldoGetter(Principal princip) {
		return statServ.getSaldo(dataServ.getUserData(princip));

	}

	@ModelAttribute("userDebets")
	public double debetsGetter(Principal princip) {
		return statServ.getMyDebts(dataServ.getUserData(princip));
	}

}
