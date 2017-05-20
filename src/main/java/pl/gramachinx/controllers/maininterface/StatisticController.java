package pl.gramachinx.controllers.maininterface;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.securityContext;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import pl.gramachinx.domains.Stats;
import pl.gramachinx.domains.UserData;
import pl.gramachinx.repository.UserRepository;
import pl.gramachinx.services.StatisticService;

@Controller
public class StatisticController {
	@Autowired
	StatisticService statServ;
	@Autowired
	UserRepository repoUser;
	
	@GetMapping("/stats")
	public String statisticsPage(Model model, Principal principal)
	{
	
		UserData userData = repoUser.findByUsername(principal.getName()).getUserData();
		model.addAttribute("stats", new Stats(statServ, userData));
		
		return "statsPage";
	}

}
