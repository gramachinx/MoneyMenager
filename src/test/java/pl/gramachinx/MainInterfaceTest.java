package pl.gramachinx;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import pl.gramachinx.domains.User;
import pl.gramachinx.repository.UserRepository;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
public class MainInterfaceTest extends ControllerIntegrationTest{
	
	    @Autowired
	    protected UserRepository userRepo;
	
		
   @Test
    public void showBillsShouldBeOk() throws Exception {
        mockMvc.perform(get("/bills").with(user("test").roles("CONFIGUSER")))
                .andExpect(status().isOk())
                .andExpect(view().name("userInterfacePage"))
                .andExpect(model().attributeExists("bills"));
     
    }
    
    
    @Test
    public void addBillsShoudBeOK() throws Exception {
    	
        mockMvc.perform(post("/bills/bill/wages/add").with(csrf())
        		.with(user("test").roles("CONFIGUSER"))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("money", "1000")
                .param("cathegory", "Pensja"));
      
        assertEquals(3, userRepo.findByUsername("test").getUserData().getBills().size());
      
    }
    
    
    

    @Test
    public void showStatsShouldBeOk() throws Exception {
    	
        mockMvc.perform(get("/stats").with(user("test").roles("CONFIGUSER")))
                .andExpect(status().isOk())
                .andExpect(view().name("statsPage"))
                .andExpect(model().attributeExists("stats"));
       
    }
    

    
    @Test
    public void showDebetsShouldBeOk() throws Exception {
    	
        mockMvc.perform(get("/debets").with(user("test").roles("CONFIGUSER")))
                .andExpect(status().isOk())
                .andExpect(view().name("debtListPage"))
                .andExpect(model().attributeExists("debets"));
       
    }
    
    @Test
    public void addDebtsShoudBeOK() throws Exception {
    	
        mockMvc.perform(post("/debets/debt/add").with(csrf())
        		.with(user("test").roles("CONFIGUSER"))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("creditor", "Jank")
                .param("money", "100")
                .param("isUserDebt", "false"));
      
        assertEquals(3, userRepo.findByUsername("test").getUserData().getBills().size());
      
    }


}
