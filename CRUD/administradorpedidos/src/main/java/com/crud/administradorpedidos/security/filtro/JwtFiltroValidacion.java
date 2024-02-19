package com.crud.administradorpedidos.security.filtro;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.crud.administradorpedidos.security.SimpleGrantedAuthorityJsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.lang.Arrays;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtFiltroValidacion extends BasicAuthenticationFilter{
	
	private static final String HADER_AUTORIZACION = "Authorization";
	private static final String PREFIJO_TOKEN = "Bearer ";
	private static final SecretKey LLAVE_SECRETA = Jwts.SIG.HS256.key().build();
	private static final String CONTENT_TYPE = "application/json";

	public JwtFiltroValidacion(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		String header = request.getHeader(HADER_AUTORIZACION);
		
		if(header == null || !header.startsWith(PREFIJO_TOKEN)) {
			chain.doFilter(request, response);
			return;
		}
		
		String token = header.replace(PREFIJO_TOKEN, "");
		
		try {
			Claims claims = Jwts.parser().verifyWith(LLAVE_SECRETA).build().parseSignedClaims(token).getPayload();
			String usuario = claims.getSubject();
			Object authoritiesClaims = claims.get("authorities");
			
			Collection<? extends GrantedAuthority> authorities = Arrays.asList(
					new ObjectMapper()
					.addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthorityJsonCreator.class)
					.readValue(authoritiesClaims.toString()
					.getBytes(), 
					SimpleGrantedAuthority[].class));
			
			UsernamePasswordAuthenticationToken tokenAutenticacion = new UsernamePasswordAuthenticationToken(usuario, null, authorities);
			
			SecurityContextHolder.getContext().setAuthentication(tokenAutenticacion);
			
			chain.doFilter(request, response);
			
		} catch (JwtException e) {
			Map<String, String> body = new HashMap<>();
			body.put("error", e.getMessage());
			body.put("mensaje", "Token invalido");
			
			response.getWriter().write(new ObjectMapper().writeValueAsString(body));
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			response.setContentType(CONTENT_TYPE);
		}
	}

}
