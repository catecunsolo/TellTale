
package com.telltale.main.repositorio;

import com.telltale.main.entidad.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepositorio  extends JpaRepository<Categoria, Integer>{
    
     @Modifying
     @Query("UPDATE Categoria c SET c.nombre = :nombre WHERE c.id_categoria = :id_categoria")
     void modificarCategoria(@Param("id_categoria") Integer id_categoria ,
             @Param("nombre") String nombre);
    
      @Query("SELECT c FROM Categoria c WHERE c.nombre = :nombre")
      Categoria buscarCategoriaPorNombre(@Param ("nombre") String nombre);
      
      @Query("SELECT c FROM Categoria c WHERE c.id_categoria = :id_categoria")
      Categoria buscarCategoriaPorId(@Param ("id_categoria") Integer id_categoria);
}