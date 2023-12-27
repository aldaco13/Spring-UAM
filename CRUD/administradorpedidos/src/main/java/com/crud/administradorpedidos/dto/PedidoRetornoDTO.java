package com.crud.administradorpedidos.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.crud.administradorpedidos.entidades.ItemPedido;

import lombok.Data;

@Data
@Component
public class PedidoRetornoDTO {
	private Long identificador;
	private String nombreCliente;
	private Long numeroCliente;
	private String domicilio;
	private String fechaPedido;
	private String estado;
	private List<ItemRetornoDTO> itemsPedido = new ArrayList<ItemRetornoDTO>();
	
	public void setItemsPedido(List<ItemPedido>items) {
		
		for (ItemPedido itemPedido : items) {
			ItemRetornoDTO itemRetorno = new ItemRetornoDTO();
			itemRetorno.setIdentificadorPedido(itemPedido.getId());
			itemRetorno.setNombre(itemPedido.getNombre());
			itemRetorno.setDescripcion(itemPedido.getDescripcion());
			itemRetorno.setPrecio(itemPedido.getPrecio());
			itemsPedido.add(itemRetorno);
		}
		
	}
}
