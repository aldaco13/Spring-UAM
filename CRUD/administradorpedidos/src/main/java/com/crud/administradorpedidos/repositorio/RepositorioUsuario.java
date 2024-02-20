package com.crud.administradorpedidos.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.administradorpedidos.entidades.Usuario;

public interface RepositorioUsuario extends JpaRepository<Usuario, Integer> {

	public Optional<Usuario> findByCorreo(String correo);

	public Optional<Usuario> findByUsuario(String nombreUsuario);
}
