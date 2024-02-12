package com.crud.administradorpedidos.modelo.servicio;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.administradorpedidos.dto.UsuarioDTO;
import com.crud.administradorpedidos.dto.UsuarioRetornoDTO;
import com.crud.administradorpedidos.entidades.Usuario;
import com.crud.administradorpedidos.repositorio.RepositorioUsuario;

@Service
public class ServicioUsuario {
	

	@Autowired
	private RepositorioUsuario repositorioUsuario;
	
	@Autowired
	private UsuarioRetornoDTO usuarioRetornoDTO;

	public boolean existeUsuario(String correo) {
		if(correo != null && !correo.equals("")) {
			Optional<Usuario> u = repositorioUsuario.findByCorreo(correo);
			
			if(u.isPresent()) {
				System.out.println("Ya existe un cliente con el correo " + correo);
				return true;
			} else {
				return false;
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
				String nombreUsuario = "";// generaUsuario();
				usuario.setUsuario(nombreUsuario);
				repositorioUsuario.save(usuario);
				System.out.println("Se registró el usuario: " + usuario.getUsuario());
				return true;
			}
			
		} else  {
			return null;
		}
	}

	public UsuarioRetornoDTO consultaUsuarioPorNumero(String nombreUsuario) {
		Optional<Usuario> u = repositorioUsuario.findByUsuario(nombreUsuario);
		
		if(u.isPresent()){
			
			usuarioRetornoDTO.setUsuario(u.get().getUsuario());
			usuarioRetornoDTO.setNombre(u.get().getNombre());
			usuarioRetornoDTO.setAPaterno(u.get().getApellidoPaterno());
			usuarioRetornoDTO.setAMaterno(u.get().getApellidoMaterno());
			usuarioRetornoDTO.setPuesto(u.get().getPuesto());
			
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
		// TODO Auto-generated method stub
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

}
