package com.crud.administradorpedidos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.crud.administradorpedidos.entidades.ItemPedido;
import com.crud.administradorpedidos.entidades.Usuario;
import com.crud.administradorpedidos.repositorio.RepositorioItems;
import com.crud.administradorpedidos.repositorio.RepositorioUsuario;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class AdministradorPedidosApplication {

	@Autowired
	RepositorioItems repositorioItems;
	
	@Autowired
	RepositorioUsuario repositorioUsuario;
	
	public static void main(String[] args) {
		SpringApplication.run(AdministradorPedidosApplication.class, args);
		System.out.println("Server levantado");
	}
	
	@PostConstruct
	public void inicia() {
		
		inicializaItems();
		
	}
	
	public void inicializaItems() {
		ItemPedido item = new ItemPedido();
		item.setCodigo(1L);
		item.setNombre("Pizza");
		item.setDescripcion("Pizza al horno");
		item.setPrecio(300L);
		
		repositorioItems.save(item);
		
		item = new ItemPedido();
		item.setCodigo(2L);
		item.setNombre("Hamburguesa");
		item.setDescripcion("Hamburguesa Hawaiana");
		item.setPrecio(100L);
		
		repositorioItems.save(item);
		
		item = new ItemPedido();
		item.setCodigo(3L);
		item.setNombre("Burrito");
		item.setDescripcion("Burrito de arrachera");
		item.setPrecio(150L);
		
		repositorioItems.save(item);
		
		Usuario usuario = new Usuario();
		
		usuario.setUsuario("oaldacom");
		usuario.setContrasenia("Aldaco");
		usuario.setRol(1);
		usuario.setNombre("Omar");
		usuario.setApellidoPaterno("Aldaco");
		usuario.setApellidoMaterno("Montalvo");
		usuario.setCorreo("omar.aldaco.m@gmail.com");
		
		repositorioUsuario.save(usuario);
		
	}

}
