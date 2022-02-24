package com.gestion.usuarios.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.usuarios.modelo.Usuario;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, String> {
	
	public Optional<Usuario> findByNombre(String nombre);
	public Optional<Usuario> findById(Long id);
}