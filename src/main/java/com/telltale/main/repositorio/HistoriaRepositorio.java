/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.telltale.main.repositorio;

import com.telltale.main.entidad.Categoria;
import com.telltale.main.entidad.Historia;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author silvia
 */
@Repository
public interface HistoriaRepositorio extends JpaRepository<Historia, Integer> {
    
    @Query("select h from Historia h where h.categoria = :categoria ")
    List<Historia> buscarPorCategoria(@Param("categoria") Categoria categoria);
}
