package com.crud.administradorpedidos.entidades;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Rol {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	private int rol;
	private String descripcion;
	
	@OneToMany(targetEntity = Usuario.class, fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name = "idRol")
	private final List<Usuario> usuarios = new ArrayList<>();
	
}
