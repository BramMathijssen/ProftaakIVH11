package avans.ivh11.proftaak.Configuration;

import avans.ivh11.proftaak.CustomAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationEntryPoint authEntryPoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers( "/home" , "/api/v1/students/**" , "/s/delete/**" ).permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/v1/students/**").hasRole("ADMIN") // didnt solve the bug
                .and()
                .csrf().disable().authorizeRequests() //adding this line solved the authentication failed for http delete with admin role
                .anyRequest().authenticated()
                .and()
                .formLogin() //default login page
                //.loginPage("/login") custom page
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/403")
                .and().httpBasic()
                .authenticationEntryPoint(authEntryPoint);
                //.exceptionHandling().accessDeniedHandler(accessDeniedHandler());

    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("pass")
                        .roles("USER")
                        .build();

        UserDetails user2 =
                User.withDefaultPasswordEncoder()
                        .username("admin")
                        .password("admin")
                        .roles("ADMIN")
                        .build();

        return new InMemoryUserDetailsManager(user, user2);
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return new CustomAccessDeniedHandler();
    }
}

    //START DATABASE SECURITY

//    @Autowired
//    private UserDetailsServiceImpl userDetailsServiceImpl;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth)
//            throws Exception {
//        auth.authenticationProvider(authenticationProvider());
//    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("avans/ivh11/proftaak/resources/static/**");
//
//    }
//
//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authProvider
//                = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userDetailsServiceImpl);
//        authProvider.setPasswordEncoder(encoder());
//        return authProvider;
//    }
//
//    @Bean
//    public PasswordEncoder encoder() {
//        return new BCryptPasswordEncoder(11);
//    }
//
//    @Override
//    protected void configure(final HttpSecurity http) throws Exception {
//        //http.authorizeRequests().antMatchers("/resources/**").permitAll().anyRequest().permitAll();
//        // @formatter:off
//        http.authorizeRequests()
//                .antMatchers("/login").permitAll()
//                .antMatchers("/").permitAll()
//                .antMatchers("/index").permitAll()
//                .antMatchers("/admin").hasRole("ADMIN")
//                .anyRequest().authenticated()
//                .and().formLogin().permitAll()
//                .and().csrf().disable();
//        ;
//        // @formatter:on
//    }
    //END DATABASE SECURITY



