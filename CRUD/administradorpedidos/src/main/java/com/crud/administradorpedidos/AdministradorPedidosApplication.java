package com.crud.administradorpedidos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.crud.administradorpedidos.entidades.ItemPedido;
import com.crud.administradorpedidos.repositorio.RepositorioItems;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class AdministradorPedidosApplication {

	@Autowired
	RepositorioItems repositorioItems;
	
	public static void main(String[] args) {
		SpringApplication.run(AdministradorPedidosApplication.class, args);
		System.out.println("Server levantado");
	}
	
	@PostConstruct
	public void inicia() {
		
		inicializaItems();
		
	}
	
	public void inicializaItems() {
		ItemPedido item1 = new ItemPedido();
		item1.setCodigo(1L);
		item1.setNombre("Pizza");
		item1.setDescripcion("Pizza al horno");
		item1.setPrecio(300L);
		
		repositorioItems.save(item1);
		
	}

}
