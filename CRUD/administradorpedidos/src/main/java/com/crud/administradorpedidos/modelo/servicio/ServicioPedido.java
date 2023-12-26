package com.crud.administradorpedidos.modelo.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.administradorpedidos.dto.PedidoDTO;
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
	private PedidoDTO pedidoDto;
	
	public boolean pedidoRecibido(PedidoDTO pedidoDto) {
		
		if(pedidoDto != null) {
			
			Pedido pedido = repositorioPedido.findByIdentificador(pedidoDto.getIdentificador());
			
			if(pedido!=null) {
				System.out.println(pedido.toString() + " ya existe");
				return false;
			} else {
			
				Cliente cliente = repositorioCliente.findByCliente(pedidoDto.getNumeroCliente());
				List<ItemPedido> items = obtenItemsPedido(pedidoDto.getItems());
				
				pedido = new Pedido();
				pedido.setIdentificador(pedidoDto.getIdentificador());
				pedido.setCliente(cliente);
				pedido.agregaItem(items);
				pedido.setDomicilio(pedidoDto.getDomicilio());
				pedido.setFechaPedido(pedidoDto.getFechaPedido());
				pedido.setEstado(pedidoDto.getEstado());
				
			
				repositorioPedido.save(pedido);
				System.out.println(pedido.toString() + " creado");
				return true;
			}
		}else {
			return false;
		}
	}
	
	public boolean modificaPedido(PedidoDTO pedidoDto) {
		
		if(pedidoDto != null) {
			Pedido pedido = repositorioPedido.findByIdentificador(pedidoDto.getIdentificador());
			
			if(pedido != null) {
				
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

	public PedidoDTO consultaPedido(Long identificador) {
		
		Pedido pedido = repositorioPedido.findByIdentificador(identificador);
		
		if(pedido != null) {
			
			pedidoDto.setIdentificador(pedido.getIdentificador());
			pedidoDto.setNumeroCliente(pedido.getCliente().getCliente());
			//pedidoDto.setItems(pedido.getItems());
			pedidoDto.setDomicilio(pedido.getDomicilio());
			pedidoDto.setFechaPedido(pedido.getFechaPedido());
			pedidoDto.setEstado(pedido.getEstado());
			
			System.out.println(pedidoDto);
			return pedidoDto;
		}else {
			System.out.println("Pedido no existe");
			return null;
		}	
	}
	
	public boolean eliminaPedido(Long identificador) {
		
		Pedido pedido = repositorioPedido.findByIdentificador(identificador);
		
		if(pedido != null) {
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
