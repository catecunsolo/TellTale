package com.telltale.main.repositorio;

import com.telltale.main.entidad.CategoriaDelDia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaDelDiaRepositorio extends JpaRepository<CategoriaDelDia, Integer> {



}
