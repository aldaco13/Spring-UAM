package com.crud.administradorpedidos.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.administradorpedidos.dto.PedidoDTO;
import com.crud.administradorpedidos.dto.PedidoRetornoDTO;
import com.crud.administradorpedidos.modelo.servicio.ServicioPedido;

@RestController
@RequestMapping("/pedidos")
public class ControladorPedido {
	
	@Autowired
	private ServicioPedido servicioPedido;
	
	@PostMapping("/enviaPedido")
	public boolean pedidoRecibido(@RequestBody PedidoDTO pedidoDto) {
		
		//System.out.println(pedidoDto);
		
		boolean recibido = servicioPedido.pedidoRecibido(pedidoDto);
		
		return recibido;
	}
	
	@PostMapping("/modificaPedido")
	public boolean modificaPedido(@RequestBody PedidoDTO pedidoDto) {
		
		boolean modificado = servicioPedido.modificaPedido(pedidoDto);
		
		return modificado;
	}
	
	@GetMapping("/pedido")
	public PedidoRetornoDTO pedido(@RequestBody Long identificador) {
		
		PedidoRetornoDTO pedido = servicioPedido.consultaPedido(identificador);
		return pedido;
	}
	
	@PostMapping("/eliminaPedido")
	public boolean eliminaPedido(@RequestBody Long identificador) {
		
		boolean eliminado = servicioPedido.eliminaPedido(identificador);
		
		return eliminado;
	}
}
