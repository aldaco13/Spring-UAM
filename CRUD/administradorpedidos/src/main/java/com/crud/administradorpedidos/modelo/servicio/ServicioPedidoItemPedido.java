package com.crud.administradorpedidos.modelo.servicio;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.administradorpedidos.entidades.ItemPedido;
import com.crud.administradorpedidos.repositorio.RepositorioItems;

@Service
public class ServicioPedidoItemPedido {

	@Autowired
	RepositorioItems repositorioItems;
	
	public boolean eliminaItem(Long id) {
		
		Optional<ItemPedido> i = repositorioItems.findById(id);
		
		if(i.isPresent()) {
			
			ItemPedido itemPedido = i.get();
			repositorioItems.delete(itemPedido);
			return true;
		}else {
			return false;
		}
	}
}
