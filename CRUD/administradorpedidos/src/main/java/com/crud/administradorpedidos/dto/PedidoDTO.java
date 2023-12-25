package com.crud.administradorpedidos.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class PedidoDTO{
	
	private Long identificador;
	private String nombreCliente;
	private String item;
	private String domicilio;
	private String fechaPedido;
	private String estado;
}
