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

    @Resource
    private ComentarioRepositorio comentarioRepositorio;

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
    public void cargaAutomaticaDeDatos() {
        /* INSERCIÓN DE CATEGORIAS A LA DB */

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

        /* INSERCIÓN AUTOMÁTICA DE ROLES A LA DB */

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

        /* INSERCIÓN AUTOMÁTICA DE USUARIOS A LA DB */

        Usuario usuario = new Usuario();

        usuario.setUsername("adminadmin");
        usuario.setEmail("admin@mail.com");
        usuario.setPassword(encoder.encode("adminadmin"));
        usuario.setFechaCreacion(LocalDate.now());
        usuario.setFechaUltModificacion(LocalDate.now());
        usuario.setRol(rolRepositorio.findByNombreIgnoreCase("ADMIN"));
        usuario.setAlta(true);
        usuarioRepositorio.save(usuario);

        usuario = new Usuario();
        usuario.setUsername("supersuper");
        usuario.setEmail("super@mail.com");
        usuario.setPassword(encoder.encode("supersuper"));
        usuario.setFechaCreacion(LocalDate.now());
        usuario.setFechaUltModificacion(LocalDate.now());
        usuario.setRol(rolRepositorio.findByNombreIgnoreCase("SUPER"));
        usuario.setAlta(true);
        usuarioRepositorio.save(usuario);

        usuario = new Usuario();
        usuario.setUsername("modermoder");
        usuario.setEmail("moder@mail.com");
        usuario.setPassword(encoder.encode("modermoder"));
        usuario.setFechaCreacion(LocalDate.now());
        usuario.setFechaUltModificacion(LocalDate.now());
        usuario.setRol(rolRepositorio.findByNombreIgnoreCase("MODER"));
        usuario.setAlta(true);
        usuarioRepositorio.save(usuario);

        /* INSERCIÓN AUTOMÁTICA DE PERFILES EN LA DB */

        Perfil perfil = new Perfil();
        perfil.setNombre("Usuario");
        perfil.setApellido("Administrador");
        perfil.setDescripcion("Usuario administrador del proyecto TellTale");
        perfil.setCategoriaDelDia(null);
        perfil.setUsuario(usuarioRepositorio.findByEmail("admin@mail.com").get());
        perfil.setFechaCreacion(LocalDate.now());
        perfil.setFechaUltModificacion(LocalDate.now());
        perfil.setAlta(true);
        perfilRepositorio.save(perfil);

        perfil = new Perfil();
        perfil.setNombre("Usuario");
        perfil.setApellido("Superadministrador");
        perfil.setDescripcion("Usuario superadministrador del proyecto TellTale");
        perfil.setCategoriaDelDia(null);
        perfil.setUsuario(usuarioRepositorio.findByEmail("super@mail.com").get());
        perfil.setFechaCreacion(LocalDate.now());
        perfil.setFechaUltModificacion(LocalDate.now());
        perfil.setAlta(true);
        perfilRepositorio.save(perfil);

        perfil = new Perfil();
        perfil.setNombre("Usuario");
        perfil.setApellido("Moderador");
        perfil.setDescripcion("Usuario moderador del proyecto TellTale");
        perfil.setCategoriaDelDia(null);
        perfil.setUsuario(usuarioRepositorio.findByEmail("moder@mail.com").get());
        perfil.setFechaCreacion(LocalDate.now());
        perfil.setFechaUltModificacion(LocalDate.now());
        perfil.setAlta(true);
        perfilRepositorio.save(perfil);

        /* INSERCIÓN AUTOMÁTICA DE HISTORIAS EN LA DB */

        Historia historia = new Historia();
        historia.setTitulo("Mi primer amor");
        historia.setHistoria("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
        historia.setMeGusta((int) ((Math.random()) * 100) + 1);
        historia.setNoMeGusta((int) ((Math.random()) * 100) + 1);
        historia.setCategoria(categoriaRepositorio.buscarCategoriaPorNombre("AMOR"));
        historia.setPerfil(perfilRepositorio.buscarPerfilPorIdUsuario(usuarioRepositorio.findByUsername("adminadmin").get()));
        historia.setFechaCreacion(LocalDate.now());
        historia.setFechaUltModificacion(LocalDate.now());
        historia.setAlta(true);
        historiaRepositorio.save(historia);

        historia = new Historia();
        historia.setTitulo("Un amor de verano");
        historia.setHistoria("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
        historia.setMeGusta((int) ((Math.random()) * 100) + 1);
        historia.setNoMeGusta((int) ((Math.random()) * 100) + 1);
        historia.setCategoria(categoriaRepositorio.buscarCategoriaPorNombre("AMOR"));
        historia.setPerfil(perfilRepositorio.buscarPerfilPorIdUsuario(usuarioRepositorio.findByUsername("supersuper").get()));
        historia.setFechaCreacion(LocalDate.now());
        historia.setFechaUltModificacion(LocalDate.now());
        historia.setAlta(true);
        historiaRepositorio.save(historia);

        historia = new Historia();
        historia.setTitulo("Trabajo finalizado");
        historia.setHistoria("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
        historia.setMeGusta((int) ((Math.random()) * 100) + 1);
        historia.setNoMeGusta((int) ((Math.random()) * 100) + 1);
        historia.setCategoria(categoriaRepositorio.buscarCategoriaPorNombre("TRABAJO"));
        historia.setPerfil(perfilRepositorio.buscarPerfilPorIdUsuario(usuarioRepositorio.findByUsername("modermoder").get()));
        historia.setFechaCreacion(LocalDate.now());
        historia.setFechaUltModificacion(LocalDate.now());
        historia.setAlta(true);
        historiaRepositorio.save(historia);

        historia = new Historia();
        historia.setTitulo("Mi primer empleo IT");
        historia.setHistoria("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
        historia.setMeGusta((int) ((Math.random()) * 100) + 1);
        historia.setNoMeGusta((int) ((Math.random()) * 100) + 1);
        historia.setCategoria(categoriaRepositorio.buscarCategoriaPorNombre("TRABAJO"));
        historia.setPerfil(perfilRepositorio.buscarPerfilPorIdUsuario(usuarioRepositorio.findByUsername("adminadmin").get()));
        historia.setFechaCreacion(LocalDate.now());
        historia.setFechaUltModificacion(LocalDate.now());
        historia.setAlta(true);
        historiaRepositorio.save(historia);

        historia = new Historia();
        historia.setTitulo("All inclusive");
        historia.setHistoria("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
        historia.setMeGusta((int) ((Math.random()) * 100) + 1);
        historia.setNoMeGusta((int) ((Math.random()) * 100) + 1);
        historia.setCategoria(categoriaRepositorio.buscarCategoriaPorNombre("CRUCERO"));
        historia.setPerfil(perfilRepositorio.buscarPerfilPorIdUsuario(usuarioRepositorio.findByUsername("supersuper").get()));
        historia.setFechaCreacion(LocalDate.now());
        historia.setFechaUltModificacion(LocalDate.now());
        historia.setAlta(true);
        historiaRepositorio.save(historia);

        historia = new Historia();
        historia.setTitulo("Naufragio");
        historia.setHistoria("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
        historia.setMeGusta((int) ((Math.random()) * 100) + 1);
        historia.setNoMeGusta((int) ((Math.random()) * 100) + 1);
        historia.setCategoria(categoriaRepositorio.buscarCategoriaPorNombre("CRUCERO"));
        historia.setPerfil(perfilRepositorio.buscarPerfilPorIdUsuario(usuarioRepositorio.findByUsername("modermoder").get()));
        historia.setFechaCreacion(LocalDate.now());
        historia.setFechaUltModificacion(LocalDate.now());
        historia.setAlta(true);
        historiaRepositorio.save(historia);

        historia = new Historia();
        historia.setTitulo("Mar muerto");
        historia.setHistoria("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
        historia.setMeGusta((int) ((Math.random()) * 100) + 1);
        historia.setNoMeGusta((int) ((Math.random()) * 100) + 1);
        historia.setCategoria(categoriaRepositorio.buscarCategoriaPorNombre("MAR"));
        historia.setPerfil(perfilRepositorio.buscarPerfilPorIdUsuario(usuarioRepositorio.findByUsername("adminadmin").get()));
        historia.setFechaCreacion(LocalDate.now());
        historia.setFechaUltModificacion(LocalDate.now());
        historia.setAlta(true);
        historiaRepositorio.save(historia);

        historia = new Historia();
        historia.setTitulo("Mar rojo");
        historia.setHistoria("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
        historia.setMeGusta((int) ((Math.random()) * 100) + 1);
        historia.setNoMeGusta((int) ((Math.random()) * 100) + 1);
        historia.setCategoria(categoriaRepositorio.buscarCategoriaPorNombre("MAR"));
        historia.setPerfil(perfilRepositorio.buscarPerfilPorIdUsuario(usuarioRepositorio.findByUsername("supersuper").get()));
        historia.setFechaCreacion(LocalDate.now());
        historia.setFechaUltModificacion(LocalDate.now());
        historia.setAlta(true);
        historiaRepositorio.save(historia);

        historia = new Historia();
        historia.setTitulo("Secretos");
        historia.setHistoria("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
        historia.setMeGusta((int) ((Math.random()) * 100) + 1);
        historia.setNoMeGusta((int) ((Math.random()) * 100) + 1);
        historia.setCategoria(categoriaRepositorio.buscarCategoriaPorNombre("MONTAÑA"));
        historia.setPerfil(perfilRepositorio.buscarPerfilPorIdUsuario(usuarioRepositorio.findByUsername("modermoder").get()));
        historia.setFechaCreacion(LocalDate.now());
        historia.setFechaUltModificacion(LocalDate.now());
        historia.setAlta(true);
        historiaRepositorio.save(historia);

        historia = new Historia();
        historia.setTitulo("Mar muerto");
        historia.setHistoria("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
        historia.setMeGusta((int) ((Math.random()) * 100) + 1);
        historia.setNoMeGusta((int) ((Math.random()) * 100) + 1);
        historia.setCategoria(categoriaRepositorio.buscarCategoriaPorNombre("MONTAÑA"));
        historia.setPerfil(perfilRepositorio.buscarPerfilPorIdUsuario(usuarioRepositorio.findByUsername("adminadmin").get()));
        historia.setFechaCreacion(LocalDate.now());
        historia.setFechaUltModificacion(LocalDate.now());
        historia.setAlta(true);
        historiaRepositorio.save(historia);

        historia = new Historia();
        historia.setTitulo("Picaflor");
        historia.setHistoria("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
        historia.setMeGusta((int) ((Math.random()) * 100) + 1);
        historia.setNoMeGusta((int) ((Math.random()) * 100) + 1);
        historia.setCategoria(categoriaRepositorio.buscarCategoriaPorNombre("COLIBRÍ"));
        historia.setPerfil(perfilRepositorio.buscarPerfilPorIdUsuario(usuarioRepositorio.findByUsername("supersuper").get()));
        historia.setFechaCreacion(LocalDate.now());
        historia.setFechaUltModificacion(LocalDate.now());
        historia.setAlta(true);
        historiaRepositorio.save(historia);

        historia = new Historia();
        historia.setTitulo("1200 RPM");
        historia.setHistoria("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
        historia.setMeGusta((int) ((Math.random()) * 100) + 1);
        historia.setNoMeGusta((int) ((Math.random()) * 100) + 1);
        historia.setCategoria(categoriaRepositorio.buscarCategoriaPorNombre("COLIBRÍ"));
        historia.setPerfil(perfilRepositorio.buscarPerfilPorIdUsuario(usuarioRepositorio.findByUsername("modermoder").get()));
        historia.setFechaCreacion(LocalDate.now());
        historia.setFechaUltModificacion(LocalDate.now());
        historia.setAlta(true);
        historiaRepositorio.save(historia);

        historia = new Historia();
        historia.setTitulo("A orillas del río");
        historia.setHistoria("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
        historia.setMeGusta((int) ((Math.random()) * 100) + 1);
        historia.setNoMeGusta((int) ((Math.random()) * 100) + 1);
        historia.setCategoria(categoriaRepositorio.buscarCategoriaPorNombre("SAUCE"));
        historia.setPerfil(perfilRepositorio.buscarPerfilPorIdUsuario(usuarioRepositorio.findByUsername("adminadmin").get()));
        historia.setFechaCreacion(LocalDate.now());
        historia.setFechaUltModificacion(LocalDate.now());
        historia.setAlta(true);
        historiaRepositorio.save(historia);

        historia = new Historia();
        historia.setTitulo("Como lágrimas");
        historia.setHistoria("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
        historia.setMeGusta((int) ((Math.random()) * 100) + 1);
        historia.setNoMeGusta((int) ((Math.random()) * 100) + 1);
        historia.setCategoria(categoriaRepositorio.buscarCategoriaPorNombre("SAUCE"));
        historia.setPerfil(perfilRepositorio.buscarPerfilPorIdUsuario(usuarioRepositorio.findByUsername("supersuper").get()));
        historia.setFechaCreacion(LocalDate.now());
        historia.setFechaUltModificacion(LocalDate.now());
        historia.setAlta(true);
        historiaRepositorio.save(historia);

        historia = new Historia();
        historia.setTitulo("Gótico");
        historia.setHistoria("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
        historia.setMeGusta((int) ((Math.random()) * 100) + 1);
        historia.setNoMeGusta((int) ((Math.random()) * 100) + 1);
        historia.setCategoria(categoriaRepositorio.buscarCategoriaPorNombre("GÁRGOLA"));
        historia.setPerfil(perfilRepositorio.buscarPerfilPorIdUsuario(usuarioRepositorio.findByUsername("modermoder").get()));
        historia.setFechaCreacion(LocalDate.now());
        historia.setFechaUltModificacion(LocalDate.now());
        historia.setAlta(true);
        historiaRepositorio.save(historia);

        historia = new Historia();
        historia.setTitulo("La fuente principal");
        historia.setHistoria("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
        historia.setMeGusta((int) ((Math.random()) * 100) + 1);
        historia.setNoMeGusta((int) ((Math.random()) * 100) + 1);
        historia.setCategoria(categoriaRepositorio.buscarCategoriaPorNombre("GÁRGOLA"));
        historia.setPerfil(perfilRepositorio.buscarPerfilPorIdUsuario(usuarioRepositorio.findByUsername("adminadmin").get()));
        historia.setFechaCreacion(LocalDate.now());
        historia.setFechaUltModificacion(LocalDate.now());
        historia.setAlta(true);
        historiaRepositorio.save(historia);

        historia = new Historia();
        historia.setTitulo("Mafaldas");
        historia.setHistoria("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
        historia.setMeGusta((int) ((Math.random()) * 100) + 1);
        historia.setNoMeGusta((int) ((Math.random()) * 100) + 1);
        historia.setCategoria(categoriaRepositorio.buscarCategoriaPorNombre("MEDIALUNAS"));
        historia.setPerfil(perfilRepositorio.buscarPerfilPorIdUsuario(usuarioRepositorio.findByUsername("supersuper").get()));
        historia.setFechaCreacion(LocalDate.now());
        historia.setFechaUltModificacion(LocalDate.now());
        historia.setAlta(true);
        historiaRepositorio.save(historia);

        historia = new Historia();
        historia.setTitulo("Saladas o de grasa");
        historia.setHistoria("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
        historia.setMeGusta((int) ((Math.random()) * 100) + 1);
        historia.setNoMeGusta((int) ((Math.random()) * 100) + 1);
        historia.setCategoria(categoriaRepositorio.buscarCategoriaPorNombre("MEDIALUNAS"));
        historia.setPerfil(perfilRepositorio.buscarPerfilPorIdUsuario(usuarioRepositorio.findByUsername("modermoder").get()));
        historia.setFechaCreacion(LocalDate.now());
        historia.setFechaUltModificacion(LocalDate.now());
        historia.setAlta(true);
        historiaRepositorio.save(historia);

        historia = new Historia();
        historia.setTitulo("Se dio vuelta");
        historia.setHistoria("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
        historia.setMeGusta((int) ((Math.random()) * 100) + 1);
        historia.setNoMeGusta((int) ((Math.random()) * 100) + 1);
        historia.setCategoria(categoriaRepositorio.buscarCategoriaPorNombre("PANQUEQUE"));
        historia.setPerfil(perfilRepositorio.buscarPerfilPorIdUsuario(usuarioRepositorio.findByUsername("supersuper").get()));
        historia.setFechaCreacion(LocalDate.now());
        historia.setFechaUltModificacion(LocalDate.now());
        historia.setAlta(true);
        historiaRepositorio.save(historia);

        historia = new Historia();
        historia.setTitulo("Con o sin dulce de leche");
        historia.setHistoria("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
        historia.setMeGusta((int) ((Math.random()) * 100) + 1);
        historia.setNoMeGusta((int) ((Math.random()) * 100) + 1);
        historia.setCategoria(categoriaRepositorio.buscarCategoriaPorNombre("PANQUEQUE"));
        historia.setPerfil(perfilRepositorio.buscarPerfilPorIdUsuario(usuarioRepositorio.findByUsername("modermoder").get()));
        historia.setFechaCreacion(LocalDate.now());
        historia.setFechaUltModificacion(LocalDate.now());
        historia.setAlta(true);
        historiaRepositorio.save(historia);

        /* INSERCIÓN AUTOMÁTICA DE COMENTARIOS EN LA DB */

        Comentario comentario = new Comentario();
        comentario.setComentario("Genial!");
        comentario.setMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setNoMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setPerfil(perfilRepositorio.buscarPerfilPorIdUsuario(usuarioRepositorio.findByUsername("adminadmin").get()));
        comentario.setHistoria(historiaRepositorio.findById(1).get());
        comentario.setFechaCreacion(LocalDate.now());
        comentario.setFechaUltModificacion(LocalDate.now());
        comentario.setAlta(true);
        comentarioRepositorio.save(comentario);

        comentario = new Comentario();
        comentario.setComentario("Me pasó lo mismo xD");
        comentario.setMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setNoMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setPerfil(perfilRepositorio.buscarPerfilPorIdUsuario(usuarioRepositorio.findByUsername("supersuper").get()));
        comentario.setHistoria(historiaRepositorio.findById(1).get());
        comentario.setFechaCreacion(LocalDate.now());
        comentario.setFechaUltModificacion(LocalDate.now());
        comentario.setAlta(true);
        comentarioRepositorio.save(comentario);

        comentario = new Comentario();
        comentario.setComentario("Increible!!");
        comentario.setMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setNoMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setPerfil(perfilRepositorio.buscarPerfilPorIdUsuario(usuarioRepositorio.findByUsername("modermoder").get()));
        comentario.setHistoria(historiaRepositorio.findById(2).get());
        comentario.setFechaCreacion(LocalDate.now());
        comentario.setFechaUltModificacion(LocalDate.now());
        comentario.setAlta(true);
        comentarioRepositorio.save(comentario);

        comentario = new Comentario();
        comentario.setComentario("Tristísimo!!!");
        comentario.setMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setNoMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setPerfil(perfilRepositorio.buscarPerfilPorIdUsuario(usuarioRepositorio.findByUsername("adminadmin").get()));
        comentario.setHistoria(historiaRepositorio.findById(2).get());
        comentario.setFechaCreacion(LocalDate.now());
        comentario.setFechaUltModificacion(LocalDate.now());
        comentario.setAlta(true);
        comentarioRepositorio.save(comentario);

        comentario = new Comentario();
        comentario.setComentario("¿Y cómo sigue?");
        comentario.setMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setNoMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setPerfil(perfilRepositorio.buscarPerfilPorIdUsuario(usuarioRepositorio.findByUsername("supersuper").get()));
        comentario.setHistoria(historiaRepositorio.findById(3).get());
        comentario.setFechaCreacion(LocalDate.now());
        comentario.setFechaUltModificacion(LocalDate.now());
        comentario.setAlta(true);
        comentarioRepositorio.save(comentario);

        comentario = new Comentario();
        comentario.setComentario("Hay parte 2!?");
        comentario.setMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setNoMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setPerfil(perfilRepositorio.buscarPerfilPorIdUsuario(usuarioRepositorio.findByUsername("modermoder").get()));
        comentario.setHistoria(historiaRepositorio.findById(3).get());
        comentario.setFechaCreacion(LocalDate.now());
        comentario.setFechaUltModificacion(LocalDate.now());
        comentario.setAlta(true);
        comentarioRepositorio.save(comentario);

        comentario = new Comentario();
        comentario.setComentario("No me gusta para nada =(");
        comentario.setMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setNoMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setPerfil(perfilRepositorio.buscarPerfilPorIdUsuario(usuarioRepositorio.findByUsername("adminadmin").get()));
        comentario.setHistoria(historiaRepositorio.findById(4).get());
        comentario.setFechaCreacion(LocalDate.now());
        comentario.setFechaUltModificacion(LocalDate.now());
        comentario.setAlta(true);
        comentarioRepositorio.save(comentario);

        comentario = new Comentario();
        comentario.setComentario("Me pasó lo mismo xD");
        comentario.setMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setNoMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setPerfil(perfilRepositorio.buscarPerfilPorIdUsuario(usuarioRepositorio.findByUsername("supersuper").get()));
        comentario.setHistoria(historiaRepositorio.findById(4).get());
        comentario.setFechaCreacion(LocalDate.now());
        comentario.setFechaUltModificacion(LocalDate.now());
        comentario.setAlta(true);
        comentarioRepositorio.save(comentario);

        comentario = new Comentario();
        comentario.setComentario("Increible!!");
        comentario.setMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setNoMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setPerfil(perfilRepositorio.buscarPerfilPorIdUsuario(usuarioRepositorio.findByUsername("modermoder").get()));
        comentario.setHistoria(historiaRepositorio.findById(5).get());
        comentario.setFechaCreacion(LocalDate.now());
        comentario.setFechaUltModificacion(LocalDate.now());
        comentario.setAlta(true);
        comentarioRepositorio.save(comentario);

        comentario = new Comentario();
        comentario.setComentario("Genial!");
        comentario.setMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setNoMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setPerfil(perfilRepositorio.buscarPerfilPorIdUsuario(usuarioRepositorio.findByUsername("adminadmin").get()));
        comentario.setHistoria(historiaRepositorio.findById(5).get());
        comentario.setFechaCreacion(LocalDate.now());
        comentario.setFechaUltModificacion(LocalDate.now());
        comentario.setAlta(true);
        comentarioRepositorio.save(comentario);

        comentario = new Comentario();
        comentario.setComentario("Creo que no era por ahí");
        comentario.setMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setNoMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setPerfil(perfilRepositorio.buscarPerfilPorIdUsuario(usuarioRepositorio.findByUsername("supersuper").get()));
        comentario.setHistoria(historiaRepositorio.findById(6).get());
        comentario.setFechaCreacion(LocalDate.now());
        comentario.setFechaUltModificacion(LocalDate.now());
        comentario.setAlta(true);
        comentarioRepositorio.save(comentario);

        comentario = new Comentario();
        comentario.setComentario("Zafaron?!");
        comentario.setMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setNoMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setPerfil(perfilRepositorio.buscarPerfilPorIdUsuario(usuarioRepositorio.findByUsername("modermoder").get()));
        comentario.setHistoria(historiaRepositorio.findById(6).get());
        comentario.setFechaCreacion(LocalDate.now());
        comentario.setFechaUltModificacion(LocalDate.now());
        comentario.setAlta(true);
        comentarioRepositorio.save(comentario);

        comentario = new Comentario();
        comentario.setComentario("Yo hubiera hecho otra cosa");
        comentario.setMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setNoMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setPerfil(perfilRepositorio.buscarPerfilPorIdUsuario(usuarioRepositorio.findByUsername("adminadmin").get()));
        comentario.setHistoria(historiaRepositorio.findById(7).get());
        comentario.setFechaCreacion(LocalDate.now());
        comentario.setFechaUltModificacion(LocalDate.now());
        comentario.setAlta(true);
        comentarioRepositorio.save(comentario);

        comentario = new Comentario();
        comentario.setComentario("Creo que no era la solución");
        comentario.setMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setNoMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setPerfil(perfilRepositorio.buscarPerfilPorIdUsuario(usuarioRepositorio.findByUsername("supersuper").get()));
        comentario.setHistoria(historiaRepositorio.findById(7).get());
        comentario.setFechaCreacion(LocalDate.now());
        comentario.setFechaUltModificacion(LocalDate.now());
        comentario.setAlta(true);
        comentarioRepositorio.save(comentario);

        comentario = new Comentario();
        comentario.setComentario("Increible!!");
        comentario.setMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setNoMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setPerfil(perfilRepositorio.buscarPerfilPorIdUsuario(usuarioRepositorio.findByUsername("modermoder").get()));
        comentario.setHistoria(historiaRepositorio.findById(8).get());
        comentario.setFechaCreacion(LocalDate.now());
        comentario.setFechaUltModificacion(LocalDate.now());
        comentario.setAlta(true);
        comentarioRepositorio.save(comentario);

        comentario = new Comentario();
        comentario.setComentario("Genial!");
        comentario.setMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setNoMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setPerfil(perfilRepositorio.buscarPerfilPorIdUsuario(usuarioRepositorio.findByUsername("adminadmin").get()));
        comentario.setHistoria(historiaRepositorio.findById(8).get());
        comentario.setFechaCreacion(LocalDate.now());
        comentario.setFechaUltModificacion(LocalDate.now());
        comentario.setAlta(true);
        comentarioRepositorio.save(comentario);

        comentario = new Comentario();
        comentario.setComentario("Comparto, pero no me animo");
        comentario.setMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setNoMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setPerfil(perfilRepositorio.buscarPerfilPorIdUsuario(usuarioRepositorio.findByUsername("supersuper").get()));
        comentario.setHistoria(historiaRepositorio.findById(9).get());
        comentario.setFechaCreacion(LocalDate.now());
        comentario.setFechaUltModificacion(LocalDate.now());
        comentario.setAlta(true);
        comentarioRepositorio.save(comentario);

        comentario = new Comentario();
        comentario.setComentario("Es en serio?");
        comentario.setMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setNoMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setPerfil(perfilRepositorio.buscarPerfilPorIdUsuario(usuarioRepositorio.findByUsername("modermoder").get()));
        comentario.setHistoria(historiaRepositorio.findById(9).get());
        comentario.setFechaCreacion(LocalDate.now());
        comentario.setFechaUltModificacion(LocalDate.now());
        comentario.setAlta(true);
        comentarioRepositorio.save(comentario);

        comentario = new Comentario();
        comentario.setComentario("Genial!");
        comentario.setMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setNoMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setPerfil(perfilRepositorio.buscarPerfilPorIdUsuario(usuarioRepositorio.findByUsername("adminadmin").get()));
        comentario.setHistoria(historiaRepositorio.findById(10).get());
        comentario.setFechaCreacion(LocalDate.now());
        comentario.setFechaUltModificacion(LocalDate.now());
        comentario.setAlta(true);
        comentarioRepositorio.save(comentario);

        comentario = new Comentario();
        comentario.setComentario("Me pasó lo mismo xD");
        comentario.setMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setNoMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setPerfil(perfilRepositorio.buscarPerfilPorIdUsuario(usuarioRepositorio.findByUsername("supersuper").get()));
        comentario.setHistoria(historiaRepositorio.findById(10).get());
        comentario.setFechaCreacion(LocalDate.now());
        comentario.setFechaUltModificacion(LocalDate.now());
        comentario.setAlta(true);
        comentarioRepositorio.save(comentario);

        comentario = new Comentario();
        comentario.setComentario("Increible!!");
        comentario.setMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setNoMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setPerfil(perfilRepositorio.buscarPerfilPorIdUsuario(usuarioRepositorio.findByUsername("modermoder").get()));
        comentario.setHistoria(historiaRepositorio.findById(11).get());
        comentario.setFechaCreacion(LocalDate.now());
        comentario.setFechaUltModificacion(LocalDate.now());
        comentario.setAlta(true);
        comentarioRepositorio.save(comentario);

        comentario = new Comentario();
        comentario.setComentario("Genial!");
        comentario.setMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setNoMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setPerfil(perfilRepositorio.buscarPerfilPorIdUsuario(usuarioRepositorio.findByUsername("adminadmin").get()));
        comentario.setHistoria(historiaRepositorio.findById(11).get());
        comentario.setFechaCreacion(LocalDate.now());
        comentario.setFechaUltModificacion(LocalDate.now());
        comentario.setAlta(true);
        comentarioRepositorio.save(comentario);

        comentario = new Comentario();
        comentario.setComentario("Pero creo que era por otro lado");
        comentario.setMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setNoMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setPerfil(perfilRepositorio.buscarPerfilPorIdUsuario(usuarioRepositorio.findByUsername("supersuper").get()));
        comentario.setHistoria(historiaRepositorio.findById(12).get());
        comentario.setFechaCreacion(LocalDate.now());
        comentario.setFechaUltModificacion(LocalDate.now());
        comentario.setAlta(true);
        comentarioRepositorio.save(comentario);

        comentario = new Comentario();
        comentario.setComentario("Y como terminó todo!?");
        comentario.setMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setNoMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setPerfil(perfilRepositorio.buscarPerfilPorIdUsuario(usuarioRepositorio.findByUsername("modermoder").get()));
        comentario.setHistoria(historiaRepositorio.findById(12).get());
        comentario.setFechaCreacion(LocalDate.now());
        comentario.setFechaUltModificacion(LocalDate.now());
        comentario.setAlta(true);
        comentarioRepositorio.save(comentario);

        comentario = new Comentario();
        comentario.setComentario("Genial!");
        comentario.setMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setNoMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setPerfil(perfilRepositorio.buscarPerfilPorIdUsuario(usuarioRepositorio.findByUsername("adminadmin").get()));
        comentario.setHistoria(historiaRepositorio.findById(13).get());
        comentario.setFechaCreacion(LocalDate.now());
        comentario.setFechaUltModificacion(LocalDate.now());
        comentario.setAlta(true);
        comentarioRepositorio.save(comentario);

        comentario = new Comentario();
        comentario.setComentario("Me pasó lo mismo xD");
        comentario.setMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setNoMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setPerfil(perfilRepositorio.buscarPerfilPorIdUsuario(usuarioRepositorio.findByUsername("supersuper").get()));
        comentario.setHistoria(historiaRepositorio.findById(13).get());
        comentario.setFechaCreacion(LocalDate.now());
        comentario.setFechaUltModificacion(LocalDate.now());
        comentario.setAlta(true);
        comentarioRepositorio.save(comentario);

        comentario = new Comentario();
        comentario.setComentario("Increible!!");
        comentario.setMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setNoMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setPerfil(perfilRepositorio.buscarPerfilPorIdUsuario(usuarioRepositorio.findByUsername("modermoder").get()));
        comentario.setHistoria(historiaRepositorio.findById(14).get());
        comentario.setFechaCreacion(LocalDate.now());
        comentario.setFechaUltModificacion(LocalDate.now());
        comentario.setAlta(true);
        comentarioRepositorio.save(comentario);

        comentario = new Comentario();
        comentario.setComentario("Quizás hubiera hecho más");
        comentario.setMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setNoMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setPerfil(perfilRepositorio.buscarPerfilPorIdUsuario(usuarioRepositorio.findByUsername("adminadmin").get()));
        comentario.setHistoria(historiaRepositorio.findById(14).get());
        comentario.setFechaCreacion(LocalDate.now());
        comentario.setFechaUltModificacion(LocalDate.now());
        comentario.setAlta(true);
        comentarioRepositorio.save(comentario);

        comentario = new Comentario();
        comentario.setComentario("Me pasó lo mismo xD");
        comentario.setMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setNoMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setPerfil(perfilRepositorio.buscarPerfilPorIdUsuario(usuarioRepositorio.findByUsername("supersuper").get()));
        comentario.setHistoria(historiaRepositorio.findById(15).get());
        comentario.setFechaCreacion(LocalDate.now());
        comentario.setFechaUltModificacion(LocalDate.now());
        comentario.setAlta(true);
        comentarioRepositorio.save(comentario);

        comentario = new Comentario();
        comentario.setComentario("Increible!!");
        comentario.setMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setNoMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setPerfil(perfilRepositorio.buscarPerfilPorIdUsuario(usuarioRepositorio.findByUsername("modermoder").get()));
        comentario.setHistoria(historiaRepositorio.findById(15).get());
        comentario.setFechaCreacion(LocalDate.now());
        comentario.setFechaUltModificacion(LocalDate.now());
        comentario.setAlta(true);
        comentarioRepositorio.save(comentario);

        comentario = new Comentario();
        comentario.setComentario("Genial!");
        comentario.setMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setNoMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setPerfil(perfilRepositorio.buscarPerfilPorIdUsuario(usuarioRepositorio.findByUsername("adminadmin").get()));
        comentario.setHistoria(historiaRepositorio.findById(16).get());
        comentario.setFechaCreacion(LocalDate.now());
        comentario.setFechaUltModificacion(LocalDate.now());
        comentario.setAlta(true);
        comentarioRepositorio.save(comentario);

        comentario = new Comentario();
        comentario.setComentario("Entonces, pudiste resolverlo??");
        comentario.setMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setNoMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setPerfil(perfilRepositorio.buscarPerfilPorIdUsuario(usuarioRepositorio.findByUsername("supersuper").get()));
        comentario.setHistoria(historiaRepositorio.findById(16).get());
        comentario.setFechaCreacion(LocalDate.now());
        comentario.setFechaUltModificacion(LocalDate.now());
        comentario.setAlta(true);
        comentarioRepositorio.save(comentario);

        comentario = new Comentario();
        comentario.setComentario("Increible!!");
        comentario.setMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setNoMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setPerfil(perfilRepositorio.buscarPerfilPorIdUsuario(usuarioRepositorio.findByUsername("modermoder").get()));
        comentario.setHistoria(historiaRepositorio.findById(17).get());
        comentario.setFechaCreacion(LocalDate.now());
        comentario.setFechaUltModificacion(LocalDate.now());
        comentario.setAlta(true);
        comentarioRepositorio.save(comentario);

        comentario = new Comentario();
        comentario.setComentario("Genial!");
        comentario.setMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setNoMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setPerfil(perfilRepositorio.buscarPerfilPorIdUsuario(usuarioRepositorio.findByUsername("adminadmin").get()));
        comentario.setHistoria(historiaRepositorio.findById(17).get());
        comentario.setFechaCreacion(LocalDate.now());
        comentario.setFechaUltModificacion(LocalDate.now());
        comentario.setAlta(true);
        comentarioRepositorio.save(comentario);

        comentario = new Comentario();
        comentario.setComentario("Dejalo ir!");
        comentario.setMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setNoMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setPerfil(perfilRepositorio.buscarPerfilPorIdUsuario(usuarioRepositorio.findByUsername("supersuper").get()));
        comentario.setHistoria(historiaRepositorio.findById(18).get());
        comentario.setFechaCreacion(LocalDate.now());
        comentario.setFechaUltModificacion(LocalDate.now());
        comentario.setAlta(true);
        comentarioRepositorio.save(comentario);

        comentario = new Comentario();
        comentario.setComentario("Me pasó casi casi pero casi lo mismo");
        comentario.setMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setNoMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setPerfil(perfilRepositorio.buscarPerfilPorIdUsuario(usuarioRepositorio.findByUsername("modermoder").get()));
        comentario.setHistoria(historiaRepositorio.findById(18).get());
        comentario.setFechaCreacion(LocalDate.now());
        comentario.setFechaUltModificacion(LocalDate.now());
        comentario.setAlta(true);
        comentarioRepositorio.save(comentario);

        comentario = new Comentario();
        comentario.setComentario("Genial!");
        comentario.setMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setNoMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setPerfil(perfilRepositorio.buscarPerfilPorIdUsuario(usuarioRepositorio.findByUsername("adminadmin").get()));
        comentario.setHistoria(historiaRepositorio.findById(19).get());
        comentario.setFechaCreacion(LocalDate.now());
        comentario.setFechaUltModificacion(LocalDate.now());
        comentario.setAlta(true);
        comentarioRepositorio.save(comentario);

        comentario = new Comentario();
        comentario.setComentario("Uffff te la debo -_-");
        comentario.setMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setNoMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setPerfil(perfilRepositorio.buscarPerfilPorIdUsuario(usuarioRepositorio.findByUsername("supersuper").get()));
        comentario.setHistoria(historiaRepositorio.findById(19).get());
        comentario.setFechaCreacion(LocalDate.now());
        comentario.setFechaUltModificacion(LocalDate.now());
        comentario.setAlta(true);
        comentarioRepositorio.save(comentario);

        comentario = new Comentario();
        comentario.setComentario("Increible!!");
        comentario.setMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setNoMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setPerfil(perfilRepositorio.buscarPerfilPorIdUsuario(usuarioRepositorio.findByUsername("modermoder").get()));
        comentario.setHistoria(historiaRepositorio.findById(20).get());
        comentario.setFechaCreacion(LocalDate.now());
        comentario.setFechaUltModificacion(LocalDate.now());
        comentario.setAlta(true);
        comentarioRepositorio.save(comentario);

        comentario = new Comentario();
        comentario.setComentario("Genial!");
        comentario.setMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setNoMeGusta((int) ((Math.random()) * 100) + 1);
        comentario.setPerfil(perfilRepositorio.buscarPerfilPorIdUsuario(usuarioRepositorio.findByUsername("adminadmin").get()));
        comentario.setHistoria(historiaRepositorio.findById(20).get());
        comentario.setFechaCreacion(LocalDate.now());
        comentario.setFechaUltModificacion(LocalDate.now());
        comentario.setAlta(true);
        comentarioRepositorio.save(comentario);
    }

/*
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

	@Test
	public void testCategoriaRepositorio_deleteAll() { //método para borrar, desde CategoriaRepositorio, todos los datos cargados en la tabla CATEGORIA de la DB
		categoriaRepositorio.deleteAll();
	}

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

    @Test
    public void testUsuarioRepositorio_deleteAll() { //método para borrar, desde UsuarioRepositorio, todos los datos cargados en la tabla USUARIO de la DB
        usuarioRepositorio.deleteAll();
    }
*/

/*
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
*/

}