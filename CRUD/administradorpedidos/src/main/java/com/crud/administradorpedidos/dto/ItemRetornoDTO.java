package com.crud.administradorpedidos.dto;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class ItemRetornoDTO {
	
	private String nombre;
	private String descripcion;
	private Long precio;
	private Long idItem;

}
