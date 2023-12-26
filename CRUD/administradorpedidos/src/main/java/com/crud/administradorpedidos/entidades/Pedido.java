package com.crud.administradorpedidos.entidades;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;


@Data
@Entity
public class Pedido {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	private Long identificador;
	
	@ManyToOne
	@JoinColumn(name = "idPedidoCliente")
	private Cliente cliente;
	
	private String domicilio;
	private String fechaPedido;
	private String estado;
	
	@OneToMany(targetEntity = ItemPedido.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "itemsPedido")
	private final List<ItemPedido> items = new ArrayList<>();
	
	public void agregaItem(List<ItemPedido> item) {
		items.addAll(item);
	}
}
