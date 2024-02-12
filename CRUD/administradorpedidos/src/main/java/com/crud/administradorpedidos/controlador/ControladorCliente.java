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
import com.crud.administradorpedidos.entidades.Cliente;
import com.crud.administradorpedidos.modelo.servicio.ServicioCliente;


@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/cliente")
public class ControladorCliente {
	
	@Autowired
	private ServicioCliente servicioCliente;
	
	@GetMapping("/validaExisteCliente")
	public boolean existeCliente(@RequestBody String correo) {
		
		if(servicioCliente.existeCliente(correo)) {
			return true;
		}else {
			return false;
		}
	}
	
	
	@PostMapping("/registraCliente")
	public ResponseEntity<String> registraCliente(@RequestBody Cliente cliente) {
		
		Boolean registrado = servicioCliente.registraCliente(cliente);
		
		if(registrado == null){
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error en datos enviados");
		}else if(registrado) {
			return ResponseEntity.ok("ok");
		}else{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El usuario ya existe");
		}
	}
	
	@GetMapping("/consultaClienteNumero")
	public ClienteRetornoDTO consultaClientePorNumero(@RequestBody Long numCliente) {
		 
		ClienteRetornoDTO cliente = servicioCliente.consultaClientePorNumero(numCliente);
		
		return cliente;
	}
	
	@PostMapping("/actualizaCliente")
	public boolean actualizaCliente(@RequestBody ClienteDTO clienteDTO) {
		
		boolean actualizado = servicioCliente.actualizaCliente(clienteDTO);
		
		return actualizado;
	}
	
	@PostMapping("/eliminaCliente")
	public boolean eliminaCliente(@RequestBody Long numCliente) {
		
		boolean eliminado = servicioCliente.eliminaCliente(numCliente);
		
		return eliminado;
	}
}
