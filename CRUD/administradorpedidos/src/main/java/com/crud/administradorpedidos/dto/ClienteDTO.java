package com.crud.administradorpedidos.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTO {
	
	private Long cliente;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String correo;
	private String calle;
	private Long numero;
	private String colonia;
	private String delegacion;
	private String cp;
	private String ciudad;
}
