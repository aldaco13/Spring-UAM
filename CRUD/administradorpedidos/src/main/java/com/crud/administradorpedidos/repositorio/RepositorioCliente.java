package com.crud.administradorpedidos.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.administradorpedidos.entidades.Cliente;

public interface RepositorioCliente extends JpaRepository<Cliente, Integer> {
	
	public Optional<Cliente> findById(Long id);
	
	public Optional<Cliente> findByCliente(Long cliente);
	
	public Optional<Cliente> findByCorreo(String correo);
	
}
