package com.curssojava.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dao.UsuarioDao;
import com.models.Usuario;
import com.utils.JWTUtil;

@RestController
public class AuthController {

	@Autowired
	private UsuarioDao usuarioDao;

	@Autowired
	private JWTUtil jwtUtil;

	@PostMapping(value = "/api/login")
	public String login(@RequestBody Usuario usuario) {

		Usuario usuarioLogueado = usuarioDao.obtenerUsuarioPorCredenciales(usuario);
		if (usuarioLogueado != null) {
			String token = jwtUtil.create(String.valueOf(usuarioLogueado.getId()), usuarioLogueado.getEmail());
			return token;
		}
		return "FAIL";
	}
}
