package com.crud.administradorpedidos.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.administradorpedidos.dto.ClienteDTO;
import com.crud.administradorpedidos.dto.ClienteRetornoDTO;
import com.crud.administradorpedidos.dto.UsuarioDTO;
import com.crud.administradorpedidos.dto.UsuarioRetornoDTO;
import com.crud.administradorpedidos.entidades.Cliente;
import com.crud.administradorpedidos.entidades.Usuario;
import com.crud.administradorpedidos.modelo.servicio.ServicioUsuario;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/usuario")
public class ControladorUsuario {
	
	@Autowired
	private ServicioUsuario servicioUsuario;
	
	@GetMapping("/validaExisteUsuario")
	public boolean existeUsuario(@RequestBody String correo) {
		
		if(servicioUsuario.existeUsuario(correo)) {
			return true;
		}else {
			return false;
		}
	}
	
	
	@PostMapping("/registraUsuario")
	public ResponseEntity<String> registraUsuario(@RequestBody Usuario usuario) {
		
		Boolean registrado = servicioUsuario.registraUsuario(usuario);
		
		if(registrado == null){
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error en datos enviados");
		}else if(registrado) {
			return ResponseEntity.ok("ok");
		}else{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El usuario ya existe");
		}
	}
	
	@GetMapping("/consultaUsuarioNumero")
	public UsuarioRetornoDTO consultaUsuarioPorNumero(@RequestBody String nombreUsuario) {
		 
		UsuarioRetornoDTO usuario = servicioUsuario.consultaUsuarioPorNumero(nombreUsuario);
		
		return usuario;
	}
	
	@PostMapping("/actualizaUsuario")
	public boolean actualizaUsuario(@RequestBody UsuarioDTO usuarioDTO) {
		
		boolean actualizado = servicioUsuario.actualizaUsuario(usuarioDTO);
		
		return actualizado;
	}
	
	@PostMapping("/eliminaUsuario")
	public boolean eliminaCliente(@RequestBody String nombreUsuario) {
		
		boolean eliminado = servicioUsuario.eliminaUsuario(nombreUsuario);
		
		return eliminado;
	}
}
