package com.crud.administradorpedidos.modelo.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.administradorpedidos.dto.ClienteDTO;
import com.crud.administradorpedidos.entidades.Cliente;
import com.crud.administradorpedidos.repositorio.RepositorioCliente;

@Service
public class ServicioCliente {
	
	@Autowired
	private RepositorioCliente repositorioCliente;
	
	public boolean existeCliente(String correo) {
		
		if(correo != null && !correo.equals("")) {
			Cliente clienteAux = repositorioCliente.findByCorreo(correo);
			
			if(clienteAux != null) {
				System.out.println("El correo: " + correo +" ya fue registrado");
				return true;
			} else {
				return false;
			}
		}else {
			return false;
		}
	}

	public boolean registraCliente(Cliente cliente) {
		
		if(cliente != null) {
			repositorioCliente.save(cliente);
			System.out.println("Se registró el cliente: " + cliente.getCliente());
			return true;
		} else  {
			return false;
		}
	}
	
	public Cliente consultaClientePorNumero(Long numCliente) {
		
		Cliente cliente = repositorioCliente.findByCliente(numCliente);
		System.out.println(cliente);
		return cliente;
	}

	public boolean actualizaCliente(ClienteDTO clienteDTO) {
		
		Cliente cliente = repositorioCliente.findByCliente(clienteDTO.getCliente());
		
		if(cliente != null) {
			
			if(clienteDTO.getNombre() != null) {
				cliente.setNombre(clienteDTO.getNombre());
			}
			
			if(clienteDTO.getApellidoPaterno() != null) {
				cliente.setApellidoPaterno(clienteDTO.getApellidoPaterno());
			}
			
			if(clienteDTO.getApellidoMaterno() != null) {
				cliente.setApellidoMaterno(clienteDTO.getApellidoMaterno());
			}
			
			if(clienteDTO.getCorreo() != null) {
				cliente.setCorreo(clienteDTO.getCorreo());
			}
			
			if(clienteDTO.getCalle() != null) {
				cliente.setCalle(clienteDTO.getCalle());
			}
			
			if(clienteDTO.getNumero() != null) {
				cliente.setNumero(clienteDTO.getNumero());
			}
			
			if(clienteDTO.getColonia() != null) {
				cliente.setColonia(clienteDTO.getColonia());
			}
			
			if(clienteDTO.getDelegacion() != null) {
				cliente.setDelegacion(clienteDTO.getDelegacion());
			}
			
			if(clienteDTO.getCp() != null) {
				cliente.setCp(clienteDTO.getCp());
			}
			
			if(clienteDTO.getCiudad() != null) {
				cliente.setCiudad(clienteDTO.getCiudad());
			}
			
			repositorioCliente.save(cliente);
			System.out.println("Cliente: " + clienteDTO.getCliente() + " actualizado");
			return true;
		} else {
			System.out.println("Error");
			return false;
		}
	}

	public boolean eliminaCliente(Long numCliente) {
		
		Cliente cliente = repositorioCliente.findByCliente(numCliente);
		
		if(cliente != null) {
			repositorioCliente.delete(cliente);
			System.out.println("Cliente: " + numCliente + " eliminado");
			return true;
		}else {
			System.out.println("Error");
			return false;
		}
	}

}
