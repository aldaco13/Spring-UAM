package com.crud.administradorpedidos.modelo.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.administradorpedidos.dto.PedidoDTO;
import com.crud.administradorpedidos.dto.PedidoRetornoDTO;
import com.crud.administradorpedidos.entidades.Pedido;
import com.crud.administradorpedidos.repositorio.RepositorioPedido;

@Service
public class ServicioPedido {
	
	@Autowired
	private RepositorioPedido repositorioPedido;
	
	@Autowired
	private PedidoRetornoDTO pedidoRetornoDto;
	
	public boolean pedidoRecibido(PedidoDTO pedidoDto) {
		
		if(pedidoDto != null) {
			
			Pedido pedido = repositorioPedido.findByIdentificador(pedidoDto.getIdentificador());
			if(pedido!=null) {
				System.out.println(pedido.toString() + " ya existe");
				return false;
			} else {
			
				pedido = new Pedido();
				pedido.setIdentificador(pedidoDto.getIdentificador());
				pedido.setNombreCliente(pedidoDto.getNombreCliente());
				pedido.setItem(pedidoDto.getItem());
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
				
				if(pedidoDto.getNombreCliente() != null) {
					pedido.setNombreCliente(pedidoDto.getNombreCliente());
				}
				
				if(pedidoDto.getItem() != null) {
					pedido.setItem(pedidoDto.getItem());
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
		
		Pedido pedido = repositorioPedido.findByIdentificador(identificador);
		if(pedido != null) {
			
			pedidoRetornoDto.setIdentificador(pedido.getIdentificador());
			pedidoRetornoDto.setNombreCliente(pedido.getNombreCliente());
			pedidoRetornoDto.setItem(pedido.getItem());
			pedidoRetornoDto.setDomicilio(pedido.getDomicilio());
			pedidoRetornoDto.setFechaPedido(pedido.getFechaPedido());
			pedidoRetornoDto.setEstado(pedido.getEstado());
			
			System.out.println(pedidoRetornoDto);
			return pedidoRetornoDto;
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

}
