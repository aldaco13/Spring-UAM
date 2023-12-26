package com.crud.administradorpedidos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.administradorpedidos.entidades.Pedido;

public interface RepositorioPedido extends JpaRepository<Pedido, Integer>{
	
	public Pedido findById(Long id);

	public Pedido findByIdentificador(Long identificador);
	
}
