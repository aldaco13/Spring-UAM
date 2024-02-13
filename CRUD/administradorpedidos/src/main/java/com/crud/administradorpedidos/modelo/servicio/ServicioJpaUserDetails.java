package com.crud.administradorpedidos.modelo.servicio;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.crud.administradorpedidos.entidades.Usuario;
import com.crud.administradorpedidos.repositorio.RepositorioUsuario;

@Service
public class ServicioJpaUserDetails implements UserDetailsService{

	@Autowired
	private RepositorioUsuario repositorioUsuario;
	
	@Override
	public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
		
		Optional<Usuario> u = repositorioUsuario.findByUsuario(nombreUsuario);
		
		if(u.isEmpty()) {
			throw new UsernameNotFoundException(String.format("No existe el usuario %s", nombreUsuario));
		}
		
		Usuario usuario = u.orElseThrow();
		
		return null;
	}

}
