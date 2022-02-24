package com.gestion.usuarios.controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.usuarios.excepciones.ResourceNotFoundException;
import com.gestion.usuarios.modelo.Usuario;
import com.gestion.usuarios.repositorio.UsuarioRepositorio;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioControlador {

	@Autowired
	private UsuarioRepositorio repositorio;

	//este metodo sirve para listar todos los Usuario
	@GetMapping("/usuarios")
	public List<Usuario> listarTodosLosUsuarios() {
		return repositorio.findAll();
	}
	

	//este metodo sirve para guardar el Usuario
	@PostMapping("/usuarios")
	public Usuario guardarUsuario(@RequestBody Usuario usuario) {
		return repositorio.save(usuario);
	}
    
	//este metodo sirve para buscar un Usuario
	@GetMapping("/usuarios/{id}")
	public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Long id){
		Usuario usuario = repositorio.findById(id)
					            .orElseThrow(() -> new ResourceNotFoundException("No existe el Usuario con el ID : " + id));
			return ResponseEntity.ok(usuario);
	}
	
	//este metodo sirve para actualizar empleado
		@PutMapping("/empleados/{id}")
		public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id,@RequestBody Usuario detallesUsuario){
			Usuario usuario = repositorio.findById(id)
					            .orElseThrow(() -> new ResourceNotFoundException("No existe el usuario con el ID : " + id));
			
			usuario.setNombre(detallesUsuario.getNombre());
			usuario.setApellido(detallesUsuario.getApellido());
			usuario.setEmail(detallesUsuario.getEmail());
			
			Usuario usuarioActualizado = repositorio.save(usuario);
			return ResponseEntity.ok(usuarioActualizado);
	    }
	
	//este metodo sirve para eliminar un Usuario
	@DeleteMapping("/usuarios/{id}")
	public ResponseEntity<Map<String,Boolean>> eliminarUsuario(@PathVariable Long id){
		Usuario usuario = repositorio.findById(id)
				            .orElseThrow(() -> new ResourceNotFoundException("No existe el Usuario con el ID : " + id));
		
		repositorio.delete(usuario);
		Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminar",Boolean.TRUE);
		return ResponseEntity.ok(respuesta);
    }
}














