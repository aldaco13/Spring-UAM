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
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Cliente {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
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
	
	@OneToMany(targetEntity = Pedido.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "idPedidoCliente")
	private final List<Pedido> pedidos = new ArrayList<>();
	
	public void agregaItem(Pedido pedido) {
		pedidos.add(pedido);
	}
}
