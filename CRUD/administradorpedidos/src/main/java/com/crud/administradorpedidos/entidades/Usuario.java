package com.crud.administradorpedidos.entidades;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	//@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String usuario;
	private String contrasenia;
	private int estatus;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String correo;
	private String telefono;
	private String puesto;
	private String nombreRol;
	
	@ManyToOne
	@JoinColumn(name = "idRol")
	private Rol rol;
}
