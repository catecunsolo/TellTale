
package com.telltale.main.repositorio;

import com.telltale.main.entidad.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoriaRepositorio  extends JpaRepository<Categoria, Integer>{
    
}
