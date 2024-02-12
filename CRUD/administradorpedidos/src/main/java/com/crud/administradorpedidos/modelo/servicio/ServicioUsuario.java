package com.crud.administradorpedidos.modelo.servicio;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.crud.administradorpedidos.dto.UsuarioDTO;
import com.crud.administradorpedidos.dto.UsuarioRetornoDTO;
import com.crud.administradorpedidos.entidades.Rol;
import com.crud.administradorpedidos.entidades.Usuario;
import com.crud.administradorpedidos.repositorio.RepositorioRol;
import com.crud.administradorpedidos.repositorio.RepositorioUsuario;

@Service
public class ServicioUsuario {
	

	@Autowired
	private RepositorioUsuario repositorioUsuario;
	
	@Autowired
	private UsuarioRetornoDTO usuarioRetornoDTO;
	
	@Autowired
	private RepositorioRol repositorioRol;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	

	public boolean existeUsuario(String dato) {
		if(dato != null && !dato.equals("")) {
			
			if(dato.contains("@")) {
				Optional<Usuario> u = repositorioUsuario.findByCorreo(dato);
				
				if(u.isPresent()) {
					System.out.println("Ya existe un cliente con el correo " + dato);
					return true;
				} else {
					return false;
				}
			}else {
				Optional<Usuario> u = repositorioUsuario.findByUsuario(dato);
				
				if(u.isPresent()) {
					return true;
				} else {
					return false;
				}
			}
		}else {
			return false;
		}
	}

	public Boolean registraUsuario(Usuario usuario) {
		if(usuario != null) {
			
			boolean existe = existeUsuario(usuario.getCorreo());
			
			if(existe) {
				return false;
			}else{
				Optional<Rol> r = repositorioRol.findByNombre(usuario.getNombreRol());
				
				if(r.isPresent()) {
					
					Rol rol = r.get();
					String nombreUsuario = generaUsuario(usuario.getApellidoPaterno(), usuario.getNombre() ,usuario.getApellidoMaterno());
					
					if(existeUsuario(nombreUsuario)) {
						nombreUsuario = generaUsuario(usuario.getNombre(), usuario.getApellidoPaterno() ,usuario.getApellidoMaterno());
					}
					
					usuario.setUsuario(nombreUsuario);
					usuario.setRol(rol);
					usuario.setContrasenia(passwordEncoder.encode(usuario.getContrasenia()));
					
					repositorioUsuario.save(usuario);
					
					System.out.println("Se registró el usuario: " + usuario.getUsuario());
					return true;
				}else {
					return false;
				}
			}
			
		} else  {
			return null;
		}
	}

	public UsuarioRetornoDTO consultaUsuarioPorNumero(String nombreUsuario) {
		Optional<Usuario> u = repositorioUsuario.findByUsuario(nombreUsuario);
		
		if(u.isPresent()){
			
			usuarioRetornoDTO.setUsuario(u.get().getUsuario());
			usuarioRetornoDTO.setContrasenia(u.get().getContrasenia());
			usuarioRetornoDTO.setNombre(u.get().getNombre());
			usuarioRetornoDTO.setAPaterno(u.get().getApellidoPaterno());
			usuarioRetornoDTO.setAMaterno(u.get().getApellidoMaterno());
			usuarioRetornoDTO.setPuesto(u.get().getPuesto());
			usuarioRetornoDTO.setRol(u.get().getRol().getNombre());
			
			System.out.println("Usuario: " + usuarioRetornoDTO.getUsuario());
			System.out.println("Nombre: " + usuarioRetornoDTO.getNombre());
			System.out.println("Apellidos: " + usuarioRetornoDTO.getAPaterno() + " " + usuarioRetornoDTO.getAMaterno());
			System.out.println("Puesto: " + usuarioRetornoDTO.getPuesto());
			
			return usuarioRetornoDTO;
		}else {
			System.out.println("El cliente " + nombreUsuario + " no existe");
			return null;
		}
	}

	public boolean actualizaUsuario(UsuarioDTO usuarioDTO) {
		
		return false;
	}

	public boolean eliminaUsuario(String nombreUsuario) {
		Optional<Usuario> u = repositorioUsuario.findByUsuario(nombreUsuario);
		
		if(u.isPresent()) {
			
			Usuario usuario = u.get();
			repositorioUsuario.delete(usuario);
			System.out.println("Usuario: " + nombreUsuario + " eliminado");
			return true;
		}else {
			System.out.println("Error");
			return false;
		}
	}
	
	private String generaUsuario(String apellidoPaterno, String nombre, String apellidoMaterno) {
		String nombreUsuario = "";
		
		nombreUsuario = apellidoPaterno.substring(0, 1) + nombre + apellidoMaterno.substring(0, 1);
		
		return nombreUsuario.toLowerCase();
	}

}
