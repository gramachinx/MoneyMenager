package pl.gramachinx.controllers.maininterface;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.securityContext;

import java.security.Principal;

import javax.xml.crypto.Data;

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
import pl.gramachinx.services.DataInterface;
import pl.gramachinx.services.StatisticService;

@Controller
@Secured("ROLE_CONFIGUSER")
public class StatisticController {

	private StatisticService statServ;

	private DataInterface dataServ;

	@Autowired
	public StatisticController(StatisticService statServ, DataInterface dataServ) {
		super();
		this.statServ = statServ;
		this.dataServ = dataServ;
	}

	@GetMapping("/stats")
	public String statisticsPage(Model model, Principal principal) {

		UserData userData = dataServ.getUserData(principal);
		model.addAttribute("stats", new Stats(statServ, userData));

		return "statsPage";
	}

}
