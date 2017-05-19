package pl.gramachinx.controllers;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import pl.gramachinx.domains.UserData;
import pl.gramachinx.repository.UserRepository;
import pl.gramachinx.services.StatisticService;
@ControllerAdvice() //TODO LOGIN NAD APCNFIG nowa paczka
public class ModelController {
	@Autowired
	private StatisticService statServ;
	@Autowired
	UserRepository userRepo;
	
	@ModelAttribute("saldo")
	public double saldoGetter(HttpServletRequest req)
	{
	
		return 5.5;
		
	}
	
	@ModelAttribute("userDebets")
	public double debetsGetter()
	{
		return 5.5;
	}

}
