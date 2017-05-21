package pl.gramachinx;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import pl.gramachinx.domains.User;
import pl.gramachinx.repository.UserRepository;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

public class UserLoginAndRegisterTest extends ControllerIntegrationTest{
	
	 @Autowired
	 protected UserRepository userRepo;
	   @Test
	    public void showRegisterUserFormShouldBeOk() throws Exception {
	        mockMvc.perform(get("/register"))
	                .andExpect(status().isOk())
	                .andExpect(view().name("registerPage"))
	                .andExpect(model().attributeExists("userRegister"));
	    }

	   @Test
	    public void registerUserWithValidDataShouldBeDoneSuccessfully() throws Exception {
	        final String USERNAME = "username";

	        mockMvc.perform(post("/register").with(csrf())
	                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
	                .param("name", "Test123")
	                .param("username", USERNAME)
	                .param("password", "password")
	                .param("repassword", "password")
	                .param("email", "valid@email.com"))
	        		.andExpect(status().isFound())
	                .andExpect(redirectedUrl("/user"));
	            

	      User user = userRepo.findByUsername(USERNAME);
	       assertEquals(USERNAME, user.getUsername());
	    }

		@Test
		public void shouldRedirectUserToMainInterface() throws Exception
		{
			
		       mockMvc.perform(get("/user").with(user("test").roles("CONFIGUSER")))
	           .andExpect(status().isFound())
	           .andExpect(redirectedUrl("/bills"));
		}
		
		@Test
		public void shouldRedirectNewUserToAuthorizationPage() throws Exception
		{
				
		       mockMvc.perform(get("/user").with(user("testNoAuth").roles("USER")))
	           .andExpect(status().isFound())
	           .andExpect(redirectedUrl("/authorize"));
		}
}
