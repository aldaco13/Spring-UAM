package com.crud.administradorpedidos.modelo.servicio;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.administradorpedidos.dto.ClienteDTO;
import com.crud.administradorpedidos.dto.ClienteRetornoDTO;
import com.crud.administradorpedidos.entidades.Cliente;
import com.crud.administradorpedidos.repositorio.RepositorioCliente;

@Service
public class ServicioCliente {
	
	@Autowired
	private RepositorioCliente repositorioCliente;
	
	@Autowired
	private ClienteRetornoDTO clienteRetornoDTO;
	
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
			
			Optional<Cliente> c = repositorioCliente.findByCliente(cliente.getCliente());
			
			if(c.isPresent()) {
				System.out.println("El cliente " + c.get().getCliente() + " ya fue registrado");
				return false;
			}else{
				repositorioCliente.save(cliente);
				System.out.println("Se registró el cliente: " + cliente.getCliente());
				return true;
			}
			
		} else  {
			return false;
		}
	}
	
	public ClienteRetornoDTO consultaClientePorNumero(Long numCliente) {
		
		Optional<Cliente> c = repositorioCliente.findByCliente(numCliente);
		
		if(c.isPresent()){
			clienteRetornoDTO.setCliente(c.get().getCliente());
			clienteRetornoDTO.setNombre(c.get().getNombre());
			clienteRetornoDTO.setApellidoPaterno(c.get().getApellidoPaterno());
			clienteRetornoDTO.setApellidoMaterno(c.get().getApellidoMaterno());
			clienteRetornoDTO.setCorreo(c.get().getCorreo());
			clienteRetornoDTO.setCalle(c.get().getCalle());
			clienteRetornoDTO.setNumero(c.get().getNumero());
			clienteRetornoDTO.setColonia(c.get().getColonia());
			clienteRetornoDTO.setDelegacion(c.get().getDelegacion());
			clienteRetornoDTO.setCp(c.get().getCp());
			clienteRetornoDTO.setCiudad(c.get().getCiudad());
			clienteRetornoDTO.setIdPedidos(c.get().getPedidos());
			
			System.out.println("Número de cliente: " + clienteRetornoDTO.getCliente());
			System.out.println("Nombre: " + clienteRetornoDTO.getNombre());
			System.out.println("Apellidos: " + clienteRetornoDTO.getApellidoPaterno() + " " + clienteRetornoDTO.getApellidoMaterno());
			System.out.println("Correo: " + clienteRetornoDTO.getCorreo());
			System.out.println("Domicilio:" + clienteRetornoDTO.getCalle() + ", " + clienteRetornoDTO.getNumero() + ", " + clienteRetornoDTO.getColonia() + ", " + clienteRetornoDTO.getDelegacion() + ", " + clienteRetornoDTO.getCp() + ", " + clienteRetornoDTO.getCiudad());
			System.out.println("Pedidos: " + clienteRetornoDTO.getIdPedidos());
			
			return clienteRetornoDTO;
		}else {
			System.out.println("El cliente " + numCliente + " no existe");
			return null;
		}
	}

	public boolean actualizaCliente(ClienteDTO clienteDTO) {
		
		Optional<Cliente> c = repositorioCliente.findByCliente(clienteDTO.getCliente());
		
		if(c.isPresent()) {
			
			Cliente cliente = c.get();
			
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
		
		Optional<Cliente> c = repositorioCliente.findByCliente(numCliente);
		
		if(c.isPresent()) {
			
			Cliente cliente = c.get();
			repositorioCliente.delete(cliente);
			System.out.println("Cliente: " + numCliente + " eliminado");
			return true;
		}else {
			System.out.println("Error");
			return false;
		}
	}

}
