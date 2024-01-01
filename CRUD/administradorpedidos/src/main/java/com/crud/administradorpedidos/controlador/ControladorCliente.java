package com.crud.administradorpedidos.controlador;

import org.springframework.beans.factory.annotation.Autowired;
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
	public boolean registraCliente(@RequestBody Cliente cliente) {
		
		boolean registrado = servicioCliente.registraCliente(cliente);
		
		return registrado;
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
