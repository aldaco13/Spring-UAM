package com.crud.administradorpedidos.dto;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
@Component
public class UsuarioRetornoDTO {
	private String usuario;
	private String contrasenia;
	private String nombre;
	private String aPaterno;
	private String aMaterno;
	private String puesto;
	private String rol;
}
