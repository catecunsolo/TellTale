package com.telltale.main;

import com.telltale.main.entidad.*;
import com.telltale.main.repositorio.*;
import org.apache.tomcat.jni.Local;
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

/*
	@Test
	public void testRolRepositorio_deleteAll() { //método para borrar, desde RolRepositorio, todos los datos cargados en la tabla ROL de la DB
		rolRepositorio.deleteAll();
	}
*/

	@Test
	public void testRolRepositorio_save() { //método para cargar datos, desde RolRepositorio, a la tabla ROL de la DB
		Rol rol;

		rol = new Rol();
		rol.setNombre("SUPER");
		rol.setFechaCreacion(LocalDate.now());
		rol.setFechaUltModificacion(LocalDate.now());
		rol.setAlta(true);
		rolRepositorio.save(rol);

		rol = new Rol();
		rol.setNombre("ADMIN");
		rol.setFechaCreacion(LocalDate.now());
		rol.setFechaUltModificacion(LocalDate.now());
		rol.setAlta(true);
		rolRepositorio.save(rol);

		rol = new Rol();
		rol.setNombre("MODER");
		rol.setFechaCreacion(LocalDate.now());
		rol.setFechaUltModificacion(LocalDate.now());
		rol.setAlta(true);
		rolRepositorio.save(rol);
		rol = new Rol();

		rol.setNombre("USER");
		rol.setFechaCreacion(LocalDate.now());
		rol.setFechaUltModificacion(LocalDate.now());
		rol.setAlta(true);
		rolRepositorio.save(rol);

	}

/*
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
    */


	@Test
	public void testCategoriaRepositorio_save() { //método para cargar datos, desde CategoriaRepositorio, a la tabla CATEGORIA de la DB
		Categoria categoria = new Categoria();
		categoria.setNombre("PANQUEQUE");
		categoria.setFechaCreacion(LocalDate.now());
		categoria.setFechaUltModificacion(LocalDate.now());
		categoria.setAlta(true);
		categoria.setVoto((int) ((Math.random())*100)+1);
		categoriaRepositorio.save(categoria);

		categoria = new Categoria();
		categoria.setNombre("MEDIALUNAS");
		categoria.setFechaCreacion(LocalDate.now());
		categoria.setFechaUltModificacion(LocalDate.now());
		categoria.setAlta(true);
		categoria.setVoto((int) ((Math.random())*100)+1);
		categoriaRepositorio.save(categoria);

		categoria = new Categoria();
		categoria.setNombre("GÁRGOLA");
		categoria.setFechaCreacion(LocalDate.now());
		categoria.setFechaUltModificacion(LocalDate.now());
		categoria.setAlta(true);
		categoria.setVoto((int) ((Math.random())*100)+1);
		categoriaRepositorio.save(categoria);

		categoria = new Categoria();
		categoria.setNombre("SAUCE");
		categoria.setFechaCreacion(LocalDate.now());
		categoria.setFechaUltModificacion(LocalDate.now());
		categoria.setAlta(true);
		categoria.setVoto((int) ((Math.random())*100)+1);
		categoriaRepositorio.save(categoria);

		categoria = new Categoria();
		categoria.setNombre("COLIBRÍ");
		categoria.setFechaCreacion(LocalDate.now());
		categoria.setFechaUltModificacion(LocalDate.now());
		categoria.setAlta(true);
		categoria.setVoto((int) ((Math.random())*100)+1);
		categoriaRepositorio.save(categoria);

		categoria = new Categoria();
		categoria.setNombre("MONTAÑA");
		categoria.setFechaCreacion(LocalDate.now());
		categoria.setFechaUltModificacion(LocalDate.now());
		categoria.setAlta(true);
		categoria.setVoto((int) ((Math.random())*100)+1);
		categoriaRepositorio.save(categoria);

		categoria = new Categoria();
		categoria.setNombre("MAR");
		categoria.setFechaCreacion(LocalDate.now());
		categoria.setFechaUltModificacion(LocalDate.now());
		categoria.setAlta(true);
		categoria.setVoto((int) ((Math.random())*100)+1);
		categoriaRepositorio.save(categoria);

		categoria = new Categoria();
		categoria.setNombre("CRUCERO");
		categoria.setFechaCreacion(LocalDate.now());
		categoria.setFechaUltModificacion(LocalDate.now());
		categoria.setAlta(true);
		categoria.setVoto((int) ((Math.random())*100)+1);
		categoriaRepositorio.save(categoria);

		categoria = new Categoria();
		categoria.setNombre("TRABAJO");
		categoria.setFechaCreacion(LocalDate.now());
		categoria.setFechaUltModificacion(LocalDate.now());
		categoria.setAlta(true);
		categoria.setVoto((int) ((Math.random())*100)+1);
		categoriaRepositorio.save(categoria);

		categoria = new Categoria();
		categoria.setNombre("AMOR");
		categoria.setFechaCreacion(LocalDate.now());
		categoria.setFechaUltModificacion(LocalDate.now());
		categoria.setAlta(true);
		categoria.setVoto((int) ((Math.random())*100)+1);
		categoriaRepositorio.save(categoria);

	}

/*
	@Test
	public void testHistoriaRepositorio_deleteAll() { //método para borrar, desde HistoriaRepositorio, todos los datos cargados en la tabla HISTORIA de la DB
		historiaRepositorio.deleteAll();
	}

	@Test
	public void testHistoriaRepositorio_save() { //método para cargar datos, desde HistoriaRepositorio, a la tabla HISTORIA de la DB
		Historia historia1 = new Historia();
		historia1.setTitulo("Titulo de Historia 1");
		historia1.setHistoria("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's.");
		historia1.setMeGusta(7);
		historia1.setNoMeGusta(1);
		historia1.setCategoria(categoriaRepositorio.findById(1).get());
		historia1.setPerfil(perfilRepositorio.findById(1).get());
		historia1.setFechaCreacion(LocalDate.now());
		historia1.setFechaUltModificacion(LocalDate.now());
		historia1.setAlta(true);
		historiaRepositorio.save(historia1);

		Historia historia2 = new Historia();
		historia2.setTitulo("Titulo de Historia 2");
		historia2.setHistoria("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's.");
		historia2.setMeGusta(6);
		historia2.setNoMeGusta(2);
		historia2.setCategoria(categoriaRepositorio.findById(2).get());
		historia2.setPerfil(perfilRepositorio.findById(2).get());
		historia2.setFechaCreacion(LocalDate.now());
		historia2.setFechaUltModificacion(LocalDate.now());
		historia2.setAlta(true);
		historiaRepositorio.save(historia2);

		Historia historia3 = new Historia();
		historia3.setTitulo("Titulo de Historia 3");
		historia3.setHistoria("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's.");
		historia3.setMeGusta(5);
		historia3.setNoMeGusta(3);
		historia3.setCategoria(categoriaRepositorio.findById(3).get());
		historia3.setPerfil(perfilRepositorio.findById(3).get());
		historia3.setFechaCreacion(LocalDate.now());
		historia3.setFechaUltModificacion(LocalDate.now());
		historia3.setAlta(true);
		historiaRepositorio.save(historia3);

		Historia historia4 = new Historia();
		historia4.setTitulo("Titulo de Historia 4");
		historia4.setHistoria("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's.");
		historia4.setMeGusta(4);
		historia4.setNoMeGusta(4);
		historia4.setCategoria(categoriaRepositorio.findById(4).get());
		historia4.setPerfil(perfilRepositorio.findById(4).get());
		historia4.setFechaCreacion(LocalDate.now());
		historia4.setFechaUltModificacion(LocalDate.now());
		historia4.setAlta(true);
		historiaRepositorio.save(historia4);

		Historia historia5 = new Historia();
		historia5.setTitulo("Titulo de Historia 5");
		historia5.setHistoria("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's.");
		historia5.setMeGusta(3);
		historia5.setNoMeGusta(5);
		historia5.setCategoria(categoriaRepositorio.findById(5).get());
		historia5.setPerfil(perfilRepositorio.findById(5).get());
		historia5.setFechaCreacion(LocalDate.now());
		historia5.setFechaUltModificacion(LocalDate.now());
		historia5.setAlta(true);
		historiaRepositorio.save(historia5);

		Historia historia6 = new Historia();
		historia6.setTitulo("Titulo de Historia 6");
		historia6.setHistoria("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's.");
		historia6.setMeGusta(2);
		historia6.setNoMeGusta(6);
		historia6.setCategoria(categoriaRepositorio.findById(6).get());
		historia6.setPerfil(perfilRepositorio.findById(6).get());
		historia6.setFechaCreacion(LocalDate.now());
		historia6.setFechaUltModificacion(LocalDate.now());
		historia6.setAlta(true);
		historiaRepositorio.save(historia6);

		Historia historia7 = new Historia();
		historia7.setTitulo("Titulo de Historia 7");
		historia7.setHistoria("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's.");
		historia7.setMeGusta(1);
		historia7.setNoMeGusta(7);
		historia7.setCategoria(categoriaRepositorio.findById(7).get());
		historia7.setPerfil(perfilRepositorio.findById(7).get());
		historia7.setFechaCreacion(LocalDate.now());
		historia7.setFechaUltModificacion(LocalDate.now());
		historia7.setAlta(true);
		historiaRepositorio.save(historia7);
	}
*/

}