package com.telltale.main;

import com.telltale.main.entidad.*;
import com.telltale.main.repositorio.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;
import java.time.LocalDate;

@SpringBootTest
class ApplicationTests {

	@Resource
	private BCryptPasswordEncoder encoder;

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
	public void testCategoriaRepositorio_deleteAll() { //método para borrar, desde CategoriaRepositorio, todos los datos cargados en la tabla CATEGORIA de la DB
		categoriaRepositorio.deleteAll();
	}
*/


    @Test
    public void testCategoriaRepositorio_save() { //método para cargar datos, desde CategoriaRepositorio, a la tabla CATEGORIA de la DB
        Categoria categoria = new Categoria();
        categoria.setNombre("PANQUEQUE");
        categoria.setVoto((int) ((Math.random()) * 100) + 1);
        categoriaRepositorio.save(categoria);

        categoria = new Categoria();
        categoria.setNombre("MEDIALUNAS");
        categoria.setVoto((int) ((Math.random()) * 100) + 1);
        categoriaRepositorio.save(categoria);

        categoria = new Categoria();
        categoria.setNombre("GÁRGOLA");
        categoria.setVoto((int) ((Math.random()) * 100) + 1);
        categoriaRepositorio.save(categoria);

        categoria = new Categoria();
        categoria.setNombre("SAUCE");
        categoria.setVoto((int) ((Math.random()) * 100) + 1);
        categoriaRepositorio.save(categoria);

        categoria = new Categoria();
        categoria.setNombre("COLIBRÍ");
        categoria.setVoto((int) ((Math.random()) * 100) + 1);
        categoriaRepositorio.save(categoria);

        categoria = new Categoria();
        categoria.setNombre("MONTAÑA");
        categoria.setVoto((int) ((Math.random()) * 100) + 1);
        categoriaRepositorio.save(categoria);

        categoria = new Categoria();
        categoria.setNombre("MAR");
        categoria.setVoto((int) ((Math.random()) * 100) + 1);
        categoriaRepositorio.save(categoria);

        categoria = new Categoria();
        categoria.setNombre("CRUCERO");
        categoria.setVoto((int) ((Math.random()) * 100) + 1);
        categoriaRepositorio.save(categoria);

        categoria = new Categoria();
        categoria.setNombre("TRABAJO");
        categoria.setVoto((int) ((Math.random()) * 100) + 1);
        categoriaRepositorio.save(categoria);

        categoria = new Categoria();
        categoria.setNombre("AMOR");
        categoria.setVoto((int) ((Math.random()) * 100) + 1);
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

    @Test
    public void testUsuarioRepositorio_deleteAll() { //método para borrar, desde UsuarioRepositorio, todos los datos cargados en la tabla USUARIO de la DB
        usuarioRepositorio.deleteAll();
    }

    @Test
    public void testUsuarioRepositorio_save() { //método para cargar datos, desde UsuarioRepositorio, a la tabla USUARIO de la DB
        Usuario usuario1 = new Usuario();
        usuario1.setUsername("adminadmin");
        usuario1.setEmail("admin@mail.com");
        usuario1.setPassword(encoder.encode("adminadmin"));
        usuario1.setFechaCreacion(LocalDate.now());
        usuario1.setFechaUltModificacion(LocalDate.now());
        usuario1.setRol(rolRepositorio.findByNombreIgnoreCase("ADMIN"));
        usuario1.setAlta(true);
        usuarioRepositorio.save(usuario1);

        Usuario usuario2 = new Usuario();
        usuario2.setUsername("supersuper");
        usuario2.setEmail("super@mail.com");
        usuario2.setPassword(encoder.encode("supersuper"));
        usuario2.setFechaCreacion(LocalDate.now());
        usuario2.setFechaUltModificacion(LocalDate.now());
        usuario2.setRol(rolRepositorio.findByNombreIgnoreCase("SUPER"));
        usuario2.setAlta(true);
        usuarioRepositorio.save(usuario2);

        Usuario usuario3 = new Usuario();
        usuario3.setUsername("modermoder");
        usuario3.setEmail("moder@mail.com");
        usuario3.setPassword(encoder.encode("modermoder"));
        usuario3.setFechaCreacion(LocalDate.now());
        usuario3.setFechaUltModificacion(LocalDate.now());
        usuario3.setRol(rolRepositorio.findByNombreIgnoreCase("MODER"));
        usuario3.setAlta(true);
        usuarioRepositorio.save(usuario3);
    }

    @Test
    public void testPerfilRepositorio_deleteAll() { //método para borrar, desde PerfilRepositorio, todos los datos cargados en la tabla PERFIL de la DB
        perfilRepositorio.deleteAll();
    }

    @Test
    public void testPerfilRepositorio_save() { //método para cargar datos, desde PerfilRepositorio, a la tabla PERFIL de la DB
        Perfil perfil1 = new Perfil();
        perfil1.setNombre("Usuario");
        perfil1.setApellido("Administrador");
        perfil1.setDescripcion("Usuario administrador del proyecto TellTale");
        perfil1.setCategoriaDelDia(null);
        perfil1.setUsuario(usuarioRepositorio.findByEmail("admin@mail.com").get());
        perfil1.setFechaCreacion(LocalDate.now());
        perfil1.setFechaUltModificacion(LocalDate.now());
		perfil1.setAlta(true);
        perfilRepositorio.save(perfil1);

        Perfil perfil2 = new Perfil();
        perfil2.setNombre("Usuario");
        perfil2.setApellido("Superadministrador");
        perfil2.setDescripcion("Usuario superadministrador del proyecto TellTale");
        perfil2.setCategoriaDelDia(null);
        perfil2.setUsuario(usuarioRepositorio.findByEmail("super@mail.com").get());
        perfil2.setFechaCreacion(LocalDate.now());
        perfil2.setFechaUltModificacion(LocalDate.now());
		perfil2.setAlta(true);
        perfilRepositorio.save(perfil2);

        Perfil perfil3 = new Perfil();
        perfil3.setNombre("Usuario");
        perfil3.setApellido("Moderador");
        perfil3.setDescripcion("Usuario moderador del proyecto TellTale");
        perfil3.setCategoriaDelDia(null);
        perfil3.setUsuario(usuarioRepositorio.findByEmail("moder@mail.com").get());
        perfil3.setFechaCreacion(LocalDate.now());
        perfil3.setFechaUltModificacion(LocalDate.now());
		perfil3.setAlta(true);
        perfilRepositorio.save(perfil3);
    }

}