package hr.java.web.vesliga.moneyapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    DataSource dataSource;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*auth

                .inMemoryAuthentication()
                .withUser("admin")
                .password(passwordEncoder().encode("adminpass"))
                .roles("ADMIN","USER")
                .and()
                .withUser("user")
                .password(passwordEncoder().encode("userpass"))
                .roles("USER");*/
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username, password, enabled from Users where username=?")
                .authoritiesByUsernameQuery("select username, authority from Authorities where username=?")
                .passwordEncoder(new BCryptPasswordEncoder(12));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                /*.antMatchers("/login")
                .permitAll()
                .antMatchers("/**")
                .hasRole("USER")
                .antMatchers("/expenses/**")
                .hasRole("USER")
                .and()*/
                .antMatchers("/expenses/newUser/**")
                .permitAll()
                .antMatchers("/expenses/**")
                .hasRole("USER")
                .antMatchers("/**").permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/expenses/new", true)
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .csrf().ignoringAntMatchers("/api/**","/login","/logout");

        http.csrf().disable();
        http.headers().frameOptions().disable();

    }
}
