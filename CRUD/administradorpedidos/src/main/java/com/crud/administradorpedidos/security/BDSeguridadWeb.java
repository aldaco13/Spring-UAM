package com.crud.administradorpedidos.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@Configuration
@EnableWebSecurity
public class BDSeguridadWeb {
	
	private final DataSource dataSource;
	private final JdbcTemplate jdbcTemplate;

	public BDSeguridadWeb(DataSource dataSource, JdbcTemplate jdbcTemplate) {
	    this.dataSource = dataSource;
	    this.jdbcTemplate = jdbcTemplate;
	}
	
	
	@Bean
	UserDetailsManager usuarios(DataSource dataSource) {
		JdbcUserDetailsManager usuarios = new JdbcUserDetailsManager();
		usuarios.setDataSource(dataSource);
		usuarios.setJdbcTemplate(jdbcTemplate);
		usuarios.setUsersByUsernameQuery("SELECT U.usuario, U.contrasenia, U.estatus FROM Usuario U WHERE usuario=?");
		usuarios.setAuthoritiesByUsernameQuery("SELECT U.usuario, R.descripcion FROM usuario U JOIN Rol R ON U.id_rol = R.id WHERE U.usuario=?");
		
		//usuarios.setAuthoritiesByUsernameQuery("SELECT R.descripcion FROM usuario U JOIN Rol R where rol = U.id_rol");
		
		return usuarios;
	}
	
	
	@Bean
	public SecurityFilterChain filtroVistas(HttpSecurity http) throws Exception {
		
		return http.authorizeHttpRequests((authorize) -> authorize
				.requestMatchers(HttpMethod.GET, "/usuario/**").permitAll()
				.requestMatchers(HttpMethod.POST, "/usuario/registraUsuario").permitAll()
				.anyRequest().authenticated())
				.csrf(config -> config.disable())
				.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.build();
				
		/*http
			.authorizeHttpRequests(authorize -> authorize
				.requestMatchers("/").permitAll()
				.anyRequest().authenticated()
			)
			.formLogin(form -> form.permitAll());

			return http.build();*/

	}
	
	 @Bean
	 PasswordEncoder passwordEncoder(){
	    
		 return new BCryptPasswordEncoder();
	 }
	 
	 
	/*@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .authorizeHttpRequests(requests -> requests
	            .requestMatchers("/").hasAnyRole("SCHUELER", "LEHRER", "VERWALTUNG")
	            .anyRequest().authenticated()
	        )
	        .formLogin(form -> form
	            .loginPage("/login")
	            .permitAll()
	        )
	        .logout(logout -> logout
	            .permitAll());
	    
	    return http.build();
	}
	 @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http
	            .authorizeRequests(authorize -> authorize
	                .requestMatchers("/").permitAll() // Permitir acceso sin autenticación
	                .anyRequest().authenticated()) // Requerir autenticación para otras URLs
	            .formLogin(form -> form
	                    .permitAll()); // Configurar formulario de inicio de sesión

	        return http.build();
	    }
	
	/*@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                .anyRequest().permitAll()) // Permitir acceso sin autenticación a todas las URLs
            .formLogin(form -> form
                .permitAll()); // Permitir acceso sin autenticación al formulario de inicio de sesión

        return http.build();
    }
    
    @Bean
    PasswordEncoder passwordEncoder(){
    
    	return new BCryptPasswordEncoder();
    }*/
    
	
	
}
