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
        historia.setHistoria("Tenía 18 años, la conocí en la universidad. Ella estudiaba arquitectura y yo contaduría, entonces la veía siempre que salíamos de clases porque teníamos el mismo horario. La primera vez que pude hablarle fue porque estábamos en la fila del Subway de la uni. Recuerdo que ella pidió un sandwich de roast beef de 15 centímetros con aceitunas negras. Después de eso, cada que la veía la saludaba, hasta que una vez un amigo en común me dijo que a ella le gustaba mucho fumar mariguana. Así que un día en la uni le saqué conversación y la invité a fumar, después de ahí me agregó a Messenger y bueno, empezamos a salir.");
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
        historia.setHistoria("Hace diez años. Mi papá tenía una heladería y yo lo ayudaba en las tardes muchísimas veces. Había un grupo de niñas que se juntaban a jugar cerca del local, y a veces mi papá me enviaba a regalarles algunos helados. Siempre hubo una que me llamó la atención: María Carlota.");
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
        historia.setHistoria("En una entrevista para un puesto de administrativa en un despacho de abogados, me preguntaron con qué animal me identificaba. Les dije que con un delfín. Fue el primer animal que me vino a la mente, porque me acordé de la canción de Bailar Pegados de Sergio Dalma y acerté. Estuve trabajando año y medio con ellos y me enteré después de que ese era el animal que más le gustaba a la de Recursos Humanos.");
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
        historia.setHistoria("Aún recuerdo a una entrevista a la que fui y en la que el entrevistador me miró raro desde que entré. Pasado un rato, con cara incrédula me preguntó si era verdad todo lo que ponía en el currículum y si había alguna manera de demostrar toda mi experiencia.\n" +
                "\n" +
                "¿Experiencia? – pregunté – ¡si acabo de terminar la carrera!\n" +
                "\n" +
                "Cuando me enseñó finalmente el CV yo no daba crédito. Me empecé a carcajear y cuando el entrevistador me miró extrañado le tuve que explicar que ese CV ¡era de mi padre!");
        historia.setMeGusta((int) ((Math.random()) * 100) + 1);
        historia.setNoMeGusta((int) ((Math.random()) * 100) + 1);
        historia.setCategoria(categoriaRepositorio.buscarCategoriaPorNombre("TRABAJO"));
        historia.setPerfil(perfilRepositorio.buscarPerfilPorIdUsuario(usuarioRepositorio.findByUsername("adminadmin").get()));
        historia.setFechaCreacion(LocalDate.now());
        historia.setFechaUltModificacion(LocalDate.now());
        historia.setAlta(true);
        historiaRepositorio.save(historia);

        historia = new Historia();
        historia.setTitulo("Naufragio");
        historia.setHistoria("Apenas una semana después otro hecho casi inaudito ocurría en el Caribe: Un crucerista de 22 años de edad, tuvo un dramático rescate en el mar cuando cayó por la borda del Oasis of the Seas, de Royal Caribbean, y fue rescatado por el barco Disney Magic de la Disney Cruise Line, 5 horas después. Un pasajero a bordo del crucero Disney dio la voz de alerta cuando escuchó gritos de auxilio en el mar alrededor de las 6:30 de la mañana. Fue entonces cuando se ordenó detener el barco y desplegar un bote para acudir a su rescate.");
        historia.setMeGusta((int) ((Math.random()) * 100) + 1);
        historia.setNoMeGusta((int) ((Math.random()) * 100) + 1);
        historia.setCategoria(categoriaRepositorio.buscarCategoriaPorNombre("CRUCERO"));
        historia.setPerfil(perfilRepositorio.buscarPerfilPorIdUsuario(usuarioRepositorio.findByUsername("supersuper").get()));
        historia.setFechaCreacion(LocalDate.now());
        historia.setFechaUltModificacion(LocalDate.now());
        historia.setAlta(true);
        historiaRepositorio.save(historia);

        historia = new Historia();
        historia.setTitulo("All inclusive");
        historia.setHistoria("Los nacimientos a bordo sí que suelen ser un tema recurrente en los viajes. Nace bebe a bordo del Queen Mary 2 durante su itinerario transatlántico de siete días entre Southampton y Nueva York. El bebé que se llamará Benjamin Brooklyn nació el pasado sábado por la noche a bordo de barco de la naviera Cunard. La madre, una crucerista de nacionalidad alemana, viajaba con su hermano cuando rompió aguas con tres semanas de adelanto. El capitán del Queen Mary 2, comentó que es la primera vez que un bebé había nacido en el buque bajo su mando.");
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
        historia.setHistoria("Se trata del punto con menor elevación en la Tierra, pues se encuentra a 420 metros, aproximadamente, por debajo del nivel del mar. Su agua contiene 10 veces más sal que los océanos, por lo que, a excepción de ciertas bacterias y microalgas, ningún organismo puede vivir en sus profundidades. Gracias a esta densidad de minerales, cuando te sumerges en el Mar Muerto flotas sin esfuerzo.");
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
        historia.setHistoria("Jehová ahora le dijo a Moisés que extendiera su palo sobre el mar Rojo. Cuando Moisés hizo esto, Jehová hizo que un viento fuerte del este soplara. Las aguas del mar se dividieron, y se quedaron aguantadas en los dos lados.\n" +
                "Jehová le dijo a Moisés que extendiera su palo sobre el mar Rojo, como viste en el cuadro. Entonces las paredes de agua empezaron a volver y a cubrir a los egipcios y sus carros. El ejército entero se había metido en el mar. ¡Y ni un solo egipcio salió vivo!\n");
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
        historia.setHistoria("Hace 2 días me fracturé la mano, yo dije que me había caído de las escaleras, por miedo de que me fueran a internar de nuevo. La verdadera razón fue que golpee durísimo a la pared en un momento de desesperación y ansiedad :(");
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
        historia.setHistoria("No metas la cara al agua: te provocará un terrible ardor en los ojos; no se te ocurra tallarlos, aunque sientas ansiedad, porque el problema se agravará y alguien más deberá ayudarte a salir. Tampoco la pruebes por curiosidad. Su sabor es muy desagradable y deja los labios resecos. Si tienes una herida reciente o te acabas de rasurar, ¡no querrás saber qué pasará!");
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
        historia.setHistoria("Poty (flor) se llamaba la hija de un Cacique de una tekoha (pueblo). Emanaba belleza y simpatía por todas partes y estaba enamorada de Mainumby. Si bien su amor era correspondido, el muchacho pertenecía a otra tekoha con la cual existían enfrentamientos desde antaño.\n" +
                "\n" +
                "Esto lo obligaba a escaparse y verse a escondidas solo con los montes como testigos que daban refugio a su amor. Esto fue así hasta que fueron descubiertos por una mujer que mucho envidiaba a Poty. Luego de las reprimendas del caso, cayó sobre la muchacha guaraní la prohibición de volver a ver a su amado.");
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
        historia.setHistoria("La leyenda del colibrí es una hermosa reflexión de la muerte y el corazón. Esta ave ha sido muy apreciada desde los mayas y mexicas, quienes lo consideraban el mensajero de los dioses por su agilidad para volar. Su corazón alcanza hasta 1,200 latidos por minuto y sus alas se baten hasta 90 veces por segundo.");
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
        historia.setHistoria("A orillas del río Uruguay existía una orgullosa tribu, la cual era gobernada por un hombre justo y valiente. Este guerrero tenía una hija llamada Isapí, a quien todos admiraban por ser la más bella de la región. En todo el lugar no había una sola persona que pudiera compararse con su belleza; sin embargo, así como grande era su hermosura, frío era su corazón, pues la princesa no tenía sentimientos por nadie.");
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
        historia.setHistoria("Jamás en su vida había derramado una sola lágrima, no tenía amigos ni sabía sonreír. Y aunque le sobraban los pretendientes a ninguno de ellos amaba, pues se decía que era incapaz de sentir cariño. Así era la hermosa Isapí, orgullosa y fría como el hielo.");
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
        historia.setHistoria("Si bien las gárgolas tienen un uso práctico desde su masificación a partir del siglo XIII como desagües decorativos, también tenían fines ideológicos-místicos. Para la Iglesia Medieval Católica, las figuras representaban la maldad, los demonios y el dolor para quienes tuvieran que pagar sus pecados en la vida post muerte. De esta forma, los creyentes verían el interior de la iglesia/catedral como la salvación.");
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
        historia.setHistoria("su origen como 'Gárgola' propiamente tal, está en Francia a partir de la palabra 'gargouille', que se suele asociar a 'garganta' y aquellas estatuas que expulsan agua por sus bocas. Sin embargo, su etimología nació de una antigua leyenda llamada La Gargouille, la historia de un dragón que aterrorizaba a los habitantes del pueblo Rouen.");
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
        historia.setHistoria("Recuerdo que una vez estaba leyendo historietas con mi madre a la hora de la merienda. Estabamos comiendo medialunas rellenas de queso. Terminamos tirando a Mafalda al carajo porque me ahogué. Fin. ");
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
        historia.setHistoria("Lo único bueno que trajo la cuarentena fue haberme dado tiempo para aprender a cocinar. Después de ver varios videos pude animarme a hacer las mejores medialunas que comí en mi vida. Cocinar es lo mejor que me pasó!");
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
        historia.setHistoria("Si hablamos de panqueque no podemos olvidarnos de mencionar al gran señor SERGIO MASSA. That's the tweet. PD: si no entienden la referencia, acudan al gran amigo Google. Cambio y fuera.");
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
        historia.setHistoria("Hay algo que no se puede discutir y es que PRIMERO el dulce de leche es ARGENTINO y segundo que los panqueques llevan DULCE DE LECHE  y nada más. Una vez me pegaron un bife por ponerle mermelada así que no lo hagan, adiós.");
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