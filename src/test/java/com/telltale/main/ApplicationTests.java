package com.telltale.main;

import com.telltale.main.entidad.Categoria;
import com.telltale.main.entidad.Perfil;
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

	@Test
	public void testPerfilRepositorio_deleteAll() { //método para borrar, desde PerfilRepositorio, todos los datos cargados en la tabla PERFIL de la DB
		perfilRepositorio.deleteAll();
	}

	@Test
	public void testPerfilRepositorio_save() { //método para cargar datos, desde PerfilRepositorio, a la tabla PERFIL de la DB
		Perfil perfil1 = new Perfil();
		perfil1.setNombre("Caterina");
		perfil1.setApellido("Cunsolo");
		perfil1.setDescripcion("Integrante backend del proyecto TellTale");
		perfil1.setCategoriaDelDia(null);
		perfil1.setUsuario(usuarioRepositorio.findByEmail("catecunsolo@mail.com").get());
		perfil1.setFechaModificacion(LocalDate.now());
		perfilRepositorio.save(perfil1);

		Perfil perfil2 = new Perfil();
		perfil2.setNombre("Mirna");
		perfil2.setApellido("Diaz");
		perfil2.setDescripcion("Integrante backend del proyecto TellTale");
		perfil2.setCategoriaDelDia(null);
		perfil2.setUsuario(usuarioRepositorio.findByEmail("mirnadiaz@mail.com").get());
		perfil2.setFechaModificacion(LocalDate.now());
		perfilRepositorio.save(perfil2);

		Perfil perfil3 = new Perfil();
		perfil3.setNombre("Bianca");
		perfil3.setApellido("Dente");
		perfil3.setDescripcion("Integrante frontend del proyecto TellTale");
		perfil3.setCategoriaDelDia(null);
		perfil3.setUsuario(usuarioRepositorio.findByEmail("biandente@mail.com").get());
		perfil3.setFechaModificacion(LocalDate.now());
		perfilRepositorio.save(perfil3);

		Perfil perfil4 = new Perfil();
		perfil4.setNombre("Vilma");
		perfil4.setApellido("Garcia");
		perfil4.setDescripcion("Integrante backend del proyecto TellTale");
		perfil4.setCategoriaDelDia(null);
		perfil4.setUsuario(usuarioRepositorio.findByEmail("vilmagarcia@mail.com").get());
		perfil4.setFechaModificacion(LocalDate.now());
		perfilRepositorio.save(perfil4);

		Perfil perfil5 = new Perfil();
		perfil5.setNombre("Sebastian");
		perfil5.setApellido("Gimenez");
		perfil5.setDescripcion("Integrante backend del proyecto TellTale");
		perfil5.setCategoriaDelDia(null);
		perfil5.setUsuario(usuarioRepositorio.findByEmail("sebagimenez@mail.com").get());
		perfil5.setFechaModificacion(LocalDate.now());
		perfilRepositorio.save(perfil5);

		Perfil perfil6 = new Perfil();
		perfil6.setNombre("Juanfe");
		perfil6.setApellido("Romero");
		perfil6.setDescripcion("Integrante frontend del proyecto TellTale");
		perfil6.setCategoriaDelDia(null);
		perfil6.setUsuario(usuarioRepositorio.findByEmail("juanferomero@mail.com").get());
		perfil6.setFechaModificacion(LocalDate.now());
		perfilRepositorio.save(perfil6);

		Perfil perfil7 = new Perfil();
		perfil7.setNombre("Marcos");
		perfil7.setApellido("Frites");
		perfil7.setDescripcion("Integrante backend del proyecto TellTale");
		perfil7.setCategoriaDelDia(null);
		perfil7.setUsuario(usuarioRepositorio.findByEmail("marcosfrites@mail.com").get());
		perfil7.setFechaModificacion(LocalDate.now());
		perfilRepositorio.save(perfil7);
	}

	@Test
	public void testCategoriaRepositorio_deleteAll() { //método para borrar, desde CategoriaRepositorio, todos los datos cargados en la tabla CATEGORIA de la DB
		categoriaRepositorio.deleteAll();
	}

	@Test
	public void testCategoriaRepositorio_save() { //método para cargar datos, desde CategoriaRepositorio, a la tabla CATEGORIA de la DB
		Categoria categoria1 = new Categoria();
		categoria1.setNombre("Categoria 1");
		categoria1.setVoto(0);
		categoriaRepositorio.save(categoria1);

		Categoria categoria2 = new Categoria();
		categoria2.setNombre("Categoria 2");
		categoria2.setVoto(0);
		categoriaRepositorio.save(categoria2);

		Categoria categoria3 = new Categoria();
		categoria3.setNombre("Categoria 3");
		categoria3.setVoto(0);
		categoriaRepositorio.save(categoria3);

		Categoria categoria4 = new Categoria();
		categoria4.setNombre("Categoria 4");
		categoria4.setVoto(0);
		categoriaRepositorio.save(categoria4);

		Categoria categoria5 = new Categoria();
		categoria5.setNombre("Categoria 5");
		categoria5.setVoto(0);
		categoriaRepositorio.save(categoria5);

		Categoria categoria6 = new Categoria();
		categoria6.setNombre("Categoria 6");
		categoria6.setVoto(0);
		categoriaRepositorio.save(categoria6);

		Categoria categoria7 = new Categoria();
		categoria7.setNombre("Categoria 7");
		categoria7.setVoto(0);
		categoriaRepositorio.save(categoria7);
	}

}
