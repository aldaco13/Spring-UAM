package com.crud.administradorpedidos.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class UsuarioRetornoDTO {
	private String usuario;
	private String nombre;
	private String aPaterno;
	private String aMaterno;
	private String puesto;
}
