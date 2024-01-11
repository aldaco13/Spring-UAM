package com.crud.administradorpedidos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.administradorpedidos.entidades.ItemPedido;

public interface RepositorioPedidoItemPedio  extends JpaRepository<ItemPedido, Integer>{
	
}
