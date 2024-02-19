package com.crud.administradorpedidos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.crud.administradorpedidos.entidades.ItemPedido;
import com.crud.administradorpedidos.entidades.Rol;
import com.crud.administradorpedidos.entidades.Usuario;
import com.crud.administradorpedidos.repositorio.RepositorioItems;
import com.crud.administradorpedidos.repositorio.RepositorioRol;
import com.crud.administradorpedidos.repositorio.RepositorioUsuario;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class AdministradorPedidosApplication {

	@Autowired
	RepositorioItems repositorioItems;
	
	@Autowired
	RepositorioUsuario repositorioUsuario;
	
	@Autowired
	RepositorioRol repositorioRol;
	
	public static void main(String[] args) {
		SpringApplication.run(AdministradorPedidosApplication.class, args);
		System.out.println("Server levantado");
	}
	
	@PostConstruct
	public void inicia() {
		
		inicializaItems();
		
	}
	
	private static final int ADMINISTRADOR = 1;
	private static final int USUARIO = 2;
	private static final int CLIENTE = 3;
	
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
		
		Rol rol = new Rol();
		rol.setRol(1);
		rol.setNombre("Administrador");
		
		repositorioRol.save(rol);
		
		rol = new Rol();
		rol.setRol(2);
		rol.setNombre("Usuario");
		
		repositorioRol.save(rol);
		
		rol = new Rol();
		rol.setRol(3);
		rol.setNombre("Cliente");
		
		repositorioRol.save(rol);
		
		List<Rol> roles = repositorioRol.findAll();
		
		Rol admin = null;
		Rol user = null;
		Rol client = null;
		
		for(Rol r : roles) {
			if(r.getRol() == ADMINISTRADOR) {
				admin = r;
			}else if(r.getRol() == USUARIO){
				user = r;
			}else if(r.getRol() == CLIENTE) {
				client = r;
			}
		}
		
		Usuario usuario = new Usuario();
		
		usuario.setUsuario("oaldacom");
		usuario.setContrasenia("{noop}pass");
		usuario.setEstatus(1);
		usuario.setRol(admin);
		usuario.setNombre("Omar");
		usuario.setApellidoPaterno("Aldaco");
		usuario.setApellidoMaterno("Montalvo");
		usuario.setCorreo("omar.aldaco.m@gmail.com");
		usuario.setTelefono("5564871618");
		
		repositorioUsuario.save(usuario);
		
		usuario = new Usuario();
		
		usuario.setUsuario("aldaco");
		usuario.setContrasenia("{noop}pass");
		usuario.setEstatus(1);
		usuario.setRol(user);
		usuario.setNombre("Omar");
		usuario.setApellidoPaterno("Aldaco");
		usuario.setApellidoMaterno("Montalvo");
		usuario.setCorreo("omar.aldaco.m@gmail.com");
		usuario.setTelefono("5564871618");
		
		repositorioUsuario.save(usuario);
		
		
	}

}
