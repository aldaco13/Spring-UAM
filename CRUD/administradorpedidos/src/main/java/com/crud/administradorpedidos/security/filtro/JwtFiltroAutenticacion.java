package com.crud.administradorpedidos.security.filtro;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.crud.administradorpedidos.entidades.Usuario;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class JwtFiltroAutenticacion extends UsernamePasswordAuthenticationFilter{
	
	private AuthenticationManager authenticationManager;
	
	private static final SecretKey LLAVE_SECRETA = Jwts.SIG.HS256.key().build();
	private static final String PREFIJO_TOKEN = "Bearer ";
	private static final String HADER_AUTORIZACION = "Authorization";
	private static final String CONTENT_TYPE = "application/json";
	
	public JwtFiltroAutenticacion(AuthenticationManager autenticador) {
		this.authenticationManager = autenticador;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		Usuario user = null;
		
        String username = null;
        String password = null;

        try {
            user = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);
            username = user.getUsuario();
            password = user.getContrasenia();
        } catch (StreamReadException e) {
            e.printStackTrace();
        } catch (DatabindException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
                password);
        
        return authenticationManager.authenticate(authenticationToken);
		
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		User usuario = (User)authResult.getPrincipal();
		String nombreUsuario = usuario.getUsername();
		Collection<? extends GrantedAuthority> roles = authResult.getAuthorities();
		
		Claims claims =Jwts.claims()
				.add("authorities", new ObjectMapper().writeValueAsString(roles))
				 .build();
		
		
		String token = Jwts.builder()
				.subject(nombreUsuario)
				.claims(claims)
				.expiration(new Date(System.currentTimeMillis() + 3600000))
				.issuedAt(new Date())
				.signWith(LLAVE_SECRETA)
				.compact();
		
		response.addHeader(HADER_AUTORIZACION, PREFIJO_TOKEN + token);
		
		Map<String, String> body = new HashMap<>();
		
		body.put("token", token);
		body.put("usuario", nombreUsuario);
		body.put("mensaje", String.format("Hola %s has iniciado sesion con exito!", nombreUsuario));
		
		response.getWriter().write(new ObjectMapper().writeValueAsString(body));
		response.setContentType(CONTENT_TYPE);
		response.setStatus(200);
	}
	
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		
		Map<String, String> body = new HashMap<>();
		
		body.put("mensaje", "Error en la autenticación, usuario o contraseña incorrecta");
		body.put("error", failed.getMessage());
		
		response.getWriter().write(new ObjectMapper().writeValueAsString(body));
		response.setStatus(401);
		response.setContentType(CONTENT_TYPE);
		
		
		super.unsuccessfulAuthentication(request, response, failed);
	}

}
