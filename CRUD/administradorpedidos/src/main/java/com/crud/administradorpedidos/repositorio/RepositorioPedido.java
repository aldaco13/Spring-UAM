package com.crud.administradorpedidos.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.administradorpedidos.entidades.Pedido;

public interface RepositorioPedido extends JpaRepository<Pedido, Integer>{
	
	public Optional<Pedido> findById(Long id);

	public Optional<Pedido> findByIdentificador(Long identificador);
	
}
