package com.crud.administradorpedidos.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.administradorpedidos.entidades.Rol;

public interface RepositorioRol extends JpaRepository<Rol, Integer> {

	Optional<Rol> findByNombre(String nombre);
}
