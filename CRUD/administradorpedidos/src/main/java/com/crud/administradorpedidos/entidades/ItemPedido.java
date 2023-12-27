package com.crud.administradorpedidos.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class ItemPedido {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	private Long codigo;
	private String nombre;
	private String descripcion;
	private Long precio;
	
	@ManyToOne
	@JoinColumn(name = "idPedido")
	private Pedido pedido;
}
