package com.telltale.main.seguridad;

import com.telltale.main.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

/* //DESCOMENTAR >> cuando UsuarioServicio implemente UserDetailsService
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(usuarioServicio).passwordEncoder(passwordEncoder);
    }
*/

/* //DESCOMENTAR SOLO SI ES NECESARIO >> este método se usa para habilitar los elementos visuales antes de estar logueado en la webapp
    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity.ignoring()
                .antMatchers("/resources/**", "/static/**", "/webjars/**");
    }
*/

    @Override
    protected void configure(HttpSecurity security) throws Exception {
        security
                .authorizeRequests()
                .antMatchers("/img/*", "/js/*").permitAll() //REVISAR >> hay que agregar "/usuario/crear", "/usuario/guardar" para que los usuarios puedan registrarse sin estar logueados
                .antMatchers("/**").authenticated() //.permitAll() o .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/logincheck")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/", true)
                .failureUrl("/login?error=true")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout=true")
                .permitAll()
                .deleteCookies("JSESSIONID")
                .and()
                .csrf()
                .disable();
    }

}
