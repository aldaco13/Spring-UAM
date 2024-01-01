package com.crud.administradorpedidos.modelo.servicio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.administradorpedidos.dto.PedidoDTO;
import com.crud.administradorpedidos.dto.PedidoRetornoDTO;
import com.crud.administradorpedidos.entidades.Cliente;
import com.crud.administradorpedidos.entidades.ItemPedido;
import com.crud.administradorpedidos.entidades.Pedido;
import com.crud.administradorpedidos.repositorio.RepositorioCliente;
import com.crud.administradorpedidos.repositorio.RepositorioItems;
import com.crud.administradorpedidos.repositorio.RepositorioPedido;

@Service
public class ServicioPedido {
	
	@Autowired
	private RepositorioPedido repositorioPedido;
	
	@Autowired
	private RepositorioCliente repositorioCliente;
	
	@Autowired
	private RepositorioItems repositorioItems;
	
	@Autowired
	private PedidoRetornoDTO pedidoRetornoDto;
	
	public boolean pedidoRecibido(PedidoDTO pedidoDto) {
		
		if(pedidoDto != null) {
			
			Optional<Pedido> p = repositorioPedido.findByIdentificador(pedidoDto.getIdentificador());
			Pedido pedido = null;
			
			if(p.isPresent()) {
				
				pedido = p.get();
				System.out.println("El pedido: " + pedido.getIdentificador() + " ya existe");
				return false;
			} else {
			
				Optional<Cliente> c = repositorioCliente.findByCliente(pedidoDto.getNumeroCliente());
				
				if(c.isPresent()) {
					
					Cliente cliente = c.get();
					
					List<ItemPedido> items = obtenItemsPedido(pedidoDto.getItems());
					
					pedido = new Pedido();
					pedido.setIdentificador(pedidoDto.getIdentificador());
					pedido.setCliente(cliente);
					pedido.agregaItem(items);
					pedido.setDomicilio(pedidoDto.getDomicilio());
					pedido.setFechaPedido(pedidoDto.getFechaPedido());
					pedido.setEstado(pedidoDto.getEstado());
					
				
					repositorioPedido.save(pedido);
					System.out.println("Pedido " + pedido.getIdentificador() + " creado");
					return true;
				}else{
					System.out.println("No existe ningún cliente asociado a este pedido");
					return false;
				}
				
				
			}
		}else {
			return false;
		}
	}
	
	public boolean modificaPedido(PedidoDTO pedidoDto) {
		
		if(pedidoDto != null) {
			
			Optional<Pedido> pedidoOpt  = repositorioPedido.findByIdentificador(pedidoDto.getIdentificador());
			
			if(pedidoOpt.isPresent()) {
				
				Pedido pedido = pedidoOpt.get();
				
				if(pedidoDto.getItems() != null) {
					List<ItemPedido> items = obtenItemsPedido(pedidoDto.getItems());
					pedido.agregaItem(items);
				}

				if(pedidoDto.getDomicilio() != null) {
					pedido.setDomicilio(pedidoDto.getDomicilio());
				}
				
				repositorioPedido.save(pedido);
				
				System.out.println("Pedido modificado con exito");
				System.out.println(pedido.toString());
				
				return true;
			}else {
				System.out.println("Pedido: " + pedidoDto.getIdentificador() + " no existe");
				return false;
			}
		}else {
			System.out.println("Pedido no recibido");
			return false;
		}
	}

	public PedidoRetornoDTO consultaPedido(Long identificador) {
		
		Optional<Pedido> p = repositorioPedido.findByIdentificador(identificador);
		
		if(p.isPresent()) {
			
			Pedido pedido = p.get();
			Optional<Cliente> c = repositorioCliente.findByCliente(pedido.getCliente().getCliente());
			
			if(c.isPresent()) {
				Cliente cliente = c.get();
				
				pedidoRetornoDto.setIdentificador(pedido.getIdentificador());
				pedidoRetornoDto.setNombreCliente(cliente.getNombre() + " " + cliente.getApellidoPaterno() + " " + cliente.getApellidoMaterno());
				pedidoRetornoDto.setNumeroCliente(cliente.getCliente());
				pedidoRetornoDto.setItemsPedido(pedido.getItems());
				pedidoRetornoDto.setDomicilio(pedido.getDomicilio());
				pedidoRetornoDto.setFechaPedido(pedido.getFechaPedido());
				pedidoRetornoDto.setEstado(pedido.getEstado());
				
				System.out.println(pedidoRetornoDto.toString());
				return pedidoRetornoDto;
			}else {
				return null;
			}
			
		}else {
			System.out.println("Pedido no existe");
			return null;
		}	
	}
	
	public boolean eliminaPedido(Long identificador) {
		
		Optional<Pedido> pedidoOpt = repositorioPedido.findByIdentificador(identificador);
		
		if(pedidoOpt.isPresent()) {
			Pedido pedido = pedidoOpt.get();
			
			repositorioPedido.delete(pedido);
			System.out.println("Pedido: " + identificador + " eliminado");
			return true;
		}else {
			System.out.println("El pedido no existe");
			return false;
		}
		
	}
	
	public List<ItemPedido> obtenItemsPedido(List<Long> items){
		List<ItemPedido> itemsPedido = new ArrayList<ItemPedido>();
		
		for (Long id : items) {
			
			ItemPedido item = repositorioItems.findByCodigo(id);
			if(item != null) {
				itemsPedido.add(item);
			}
			
		}
		
		return itemsPedido;
	}

}
