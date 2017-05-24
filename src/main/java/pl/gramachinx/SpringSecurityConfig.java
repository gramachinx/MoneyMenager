package pl.gramachinx;

import javax.annotation.security.RolesAllowed;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled=true)
@EnableAutoConfiguration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private DataSource dataSource;
	
	@Value("${spring.queries.users-query}")
	private String usersQuery;
	
	@Value("${spring.queries.roles-query}")
	private String rolesQuery;
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/", "/webjars/**", "/css/**", "/resources/**","/register", "/tester","/license", "/main", "/password/rec").permitAll()
                .anyRequest().authenticated() 
                .and()
            .formLogin()
            .defaultSuccessUrl("/user")
            .usernameParameter("username").passwordParameter("password")
                .loginPage("/")
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/403")
                .and()
                .logout()
                .permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.
    			jdbcAuthentication()
    				.usersByUsernameQuery(usersQuery)
    				.authoritiesByUsernameQuery(rolesQuery)
    				.dataSource(dataSource)
    				.passwordEncoder(bCryptPasswordEncoder);
        
       //.inMemoryAuthentication()
       //.withUser("test").password("test").roles("USERC");
    }
}