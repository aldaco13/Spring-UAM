package com.crud.administradorpedidos.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class PedidoDTO{
	
	private Long identificador;
	private Long numeroCliente;
	private String domicilio;
	private String fechaPedido;
	private String estado;
	private List<Long> items;
}
