package com.telltale.main;

import com.telltale.main.entidad.Rol;
import com.telltale.main.repositorio.RolRepositorio;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class ApplicationTests {

	@Resource
	private RolRepositorio rolRepositorio;

	@Test
	void contextLoads() {
	}

	@Test
	public void testRolRepositorio_deleteAll() { //método para borrar, desde RolRepositorio, todos los datos cargados en la tabla ROL de la DB
		rolRepositorio.deleteAll();
	}

	@Test
	public void testRolRepositorio_save() { //método para cargar datos, desde RolRepositorio, a la tabla ROL de la DB
		rolRepositorio.save(new Rol(1, "SUPER"));
		rolRepositorio.save(new Rol(2, "ADMIN"));
		rolRepositorio.save(new Rol(3, "MODER"));
		rolRepositorio.save(new Rol(4, "USER"));
	}

}
