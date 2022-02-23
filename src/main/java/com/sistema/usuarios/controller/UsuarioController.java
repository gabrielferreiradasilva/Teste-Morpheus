package com.sistema.usuarios.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.usuarios.models.Usuario;
import com.sistema.usuarios.repository.UsuarioRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/usuarios")
@Api(value = "Api Usuários")
@CrossOrigin(origins = "*")
public class UsuarioController {

	private UsuarioRepository usuarioRepository;
	
	@GetMapping("/listar")
	@ApiOperation(value = "Retorna uma lista de usuários")
	public List<Usuario> listar() {
		return usuarioRepository.findAll();
	}
	
	@GetMapping("/listar/{nome}")
	@ApiOperation(value = "Retorna uma consulta na lista de usuários")
	public ResponseEntity<List<Usuario>> listar(@PathVariable("nome") String nome) {
		List<Usuario> usuario = usuarioRepository.findByNomeContaining(nome);
		if (usuario.isEmpty() == false) {
			return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.NO_CONTENT);
		}
		
	}
	
	@PostMapping("/adicionar")
	@ResponseStatus(HttpStatus.CREATED)
	@Transactional
	@ApiOperation(value = "Adiciona um usuário na lista")
	public Usuario adicionar(@Valid @RequestBody Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
}
