package pl.gramachinx;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.omg.CORBA.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import pl.gramachinx.domains.User;
import pl.gramachinx.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MoneyMenagerApplicationTests {

	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;
	@Mock
	private UserRepository repo;
	@Autowired
	private User testUser;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		repo.saveAndFlush(testUser);
	}

	@Test
	public void should_redirect_to_profile() throws Exception {
		this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk());
		
	}
	
	@Test
	public void register_test() throws Exception {
			
		this.mockMvc.perform(get("/").principal(new java.security.Principal() {
			
			@Override
			public String getName() {
				
				return "test";
			}
		}))
		.andDo(print())
		.andExpect(status().isFound())
		.andExpect(redirectedUrl("/user"));
		
		
		
	}
	
	@Test
	@WithMockUser(username="test", password="test2")
	public void main_acces_test() throws Exception{
			
	
	}
	

}
