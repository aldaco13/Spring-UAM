package com.crud.administradorpedidos.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


@Data
@Entity
public class Pedido {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	private Long identificador;
	private String nombreCliente;
	private String item;
	private String domicilio;
	private String fechaPedido;
	private String estado;
}
