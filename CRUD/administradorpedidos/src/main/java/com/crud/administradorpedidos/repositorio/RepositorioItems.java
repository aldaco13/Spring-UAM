package com.crud.administradorpedidos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.administradorpedidos.entidades.ItemPedido;

public interface RepositorioItems extends JpaRepository<ItemPedido, Integer>{
	 public ItemPedido findByCodigo(Long codigo);

}
