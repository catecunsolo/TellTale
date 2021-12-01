package com.telltale.main.repositorio;

import com.telltale.main.entidad.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolRepositorio extends JpaRepository<Rol, Integer> {

    List<Rol> findByOrderByNombreAsc(); //retorna un listado de "Roles" ordenados alfabéticamente por nombre

}
