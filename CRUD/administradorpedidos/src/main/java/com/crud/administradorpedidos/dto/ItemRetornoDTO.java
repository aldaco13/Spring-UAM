package com.crud.administradorpedidos.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class ItemRetornoDTO {
	
	private String nombre;
	private String descripcion;
	private Long precio;
	private Long identificadorPedido;

}
