package com.crud.administradorpedidos.dto;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class PedidoRetornoDTO {
	private Long identificador;
	private String nombreCliente;
	private String item;
	private String domicilio;
	private String fechaPedido;
	private String estado;
}
