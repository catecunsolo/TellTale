package com.telltale.main;

import com.telltale.main.entidad.Rol;
import com.telltale.main.entidad.Usuario;
import com.telltale.main.repositorio.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.LocalDate;

@SpringBootTest
class ApplicationTests {

	@Resource
	private RolRepositorio rolRepositorio;

	@Resource
	private UsuarioRepositorio usuarioRepositorio;

	@Resource
	private PerfilRepositorio perfilRepositorio;

	@Resource
	private CategoriaRepositorio categoriaRepositorio;

	@Resource
	private HistoriaRepositorio historiaRepositorio;

	@Test
	void contextLoads() {
	}

	@Test
	public void testRolRepositorio_deleteAll() { //método para borrar, desde RolRepositorio, todos los datos cargados en la tabla ROL de la DB
		rolRepositorio.deleteAll();
	}

	@Test
	public void testRolRepositorio_save() { //método para cargar datos, desde RolRepositorio, a la tabla ROL de la DB
		rolRepositorio.save(new Rol(null, "SUPER"));
		rolRepositorio.save(new Rol(null, "ADMIN"));
		rolRepositorio.save(new Rol(null, "MODER"));
		rolRepositorio.save(new Rol(null, "USER"));
	}

	@Test
	public void testUsuarioRepositorio_deleteAll() { //método para borrar, desde UsuarioRepositorio, todos los datos cargados en la tabla USUARIO de la DB
		usuarioRepositorio.deleteAll();
	}

	@Test
	public void testUsuarioRepositorio_save() { //método para cargar datos, desde UsuarioRepositorio, a la tabla USUARIO de la DB
		Usuario usuario1 = new Usuario();
		usuario1.setUsername("catecunsolo");
		usuario1.setEmail("catecunsolo@mail.com");
		usuario1.setPassword("catecunsolo");
		usuario1.setFechaCreacion(LocalDate.now());
		usuario1.setFechaUltModificacion(LocalDate.now());
		usuario1.setRol(rolRepositorio.findByNombreIgnoreCase("USER"));
		usuario1.setAlta(true);
		usuarioRepositorio.save(usuario1);

		Usuario usuario2 = new Usuario();
		usuario2.setUsername("mirnadiaz");
		usuario2.setEmail("mirnadiaz@mail.com");
		usuario2.setPassword("mirnadiaz");
		usuario2.setFechaCreacion(LocalDate.now());
		usuario2.setFechaUltModificacion(LocalDate.now());
		usuario2.setRol(rolRepositorio.findByNombreIgnoreCase("USER"));
		usuario2.setAlta(true);
		usuarioRepositorio.save(usuario2);

		Usuario usuario3 = new Usuario();
		usuario3.setUsername("biandente");
		usuario3.setEmail("biandente@mail.com");
		usuario3.setPassword("biandente");
		usuario3.setFechaCreacion(LocalDate.now());
		usuario3.setFechaUltModificacion(LocalDate.now());
		usuario3.setRol(rolRepositorio.findByNombreIgnoreCase("USER"));
		usuario3.setAlta(true);
		usuarioRepositorio.save(usuario3);

		Usuario usuario4 = new Usuario();
		usuario4.setUsername("vilmagarcia");
		usuario4.setEmail("vilmagarcia@mail.com");
		usuario4.setPassword("vilmagarcia");
		usuario4.setFechaCreacion(LocalDate.now());
		usuario4.setFechaUltModificacion(LocalDate.now());
		usuario4.setRol(rolRepositorio.findByNombreIgnoreCase("USER"));
		usuario4.setAlta(true);
		usuarioRepositorio.save(usuario4);

		Usuario usuario5 = new Usuario();
		usuario5.setUsername("sebagimenez");
		usuario5.setEmail("sebagimenez@mail.com");
		usuario5.setPassword("sebagimenez");
		usuario5.setFechaCreacion(LocalDate.now());
		usuario5.setFechaUltModificacion(LocalDate.now());
		usuario5.setRol(rolRepositorio.findByNombreIgnoreCase("USER"));
		usuario5.setAlta(true);
		usuarioRepositorio.save(usuario5);

		Usuario usuario6 = new Usuario();
		usuario6.setUsername("juanferomero");
		usuario6.setEmail("juanferomero@mail.com");
		usuario6.setPassword("juanferomero");
		usuario6.setFechaCreacion(LocalDate.now());
		usuario6.setFechaUltModificacion(LocalDate.now());
		usuario6.setRol(rolRepositorio.findByNombreIgnoreCase("USER"));
		usuario6.setAlta(true);
		usuarioRepositorio.save(usuario6);

		Usuario usuario7 = new Usuario();
		usuario7.setUsername("marcosfrites");
		usuario7.setEmail("marcosfrites@mail.com");
		usuario7.setPassword("marcosfrites");
		usuario7.setFechaCreacion(LocalDate.now());
		usuario7.setFechaUltModificacion(LocalDate.now());
		usuario7.setRol(rolRepositorio.findByNombreIgnoreCase("USER"));
		usuario7.setAlta(true);
		usuarioRepositorio.save(usuario7);
	}

}
