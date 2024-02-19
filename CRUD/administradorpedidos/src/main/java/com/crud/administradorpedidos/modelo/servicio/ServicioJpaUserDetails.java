package com.crud.administradorpedidos.modelo.servicio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crud.administradorpedidos.entidades.Usuario;
import com.crud.administradorpedidos.repositorio.RepositorioUsuario;

@Service
public class ServicioJpaUserDetails implements UserDetailsService{

	@Autowired
	private RepositorioUsuario repositorioUsuario;
	
	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
		
		Optional<Usuario> u = repositorioUsuario.findByUsuario(nombreUsuario);
		
		if(u.isEmpty()) {
			throw new UsernameNotFoundException(String.format("No existe el usuario %s", nombreUsuario));
		}
		
		Usuario usuario = u.orElseThrow();
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(usuario.getRol().getNombre()));
		
		boolean estatus = (usuario.getEstatus() == 1);
		
		return new User(usuario.getUsuario(), usuario.getContrasenia(), estatus, true, true, true, authorities);
	}

}
