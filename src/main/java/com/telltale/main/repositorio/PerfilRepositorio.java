
package com.telltale.main.repositorio;

import com.telltale.main.entidad.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilRepositorio extends JpaRepository<Perfil, Integer>  {
    
    
     @Modifying
    @Query("UPDATE Perfil p SET p.nombre = :nombre, p.apellido = :apellido, p.descripcion =:descripcion ,p.alta = :alta WHERE p.id_perfil = :id_perfil")
    void modificarPerfil( @Param("id_perfil")Integer id_perfil, @Param("nombre") String nombre,@Param("apellido") String apellido,@Param("descripcion") String descripcion,@Param("alta")Boolean alta);
 
    
    @Modifying
    @Query("UPDATE Perfil p SET p.alta= true WHERE p.id_perfil =:id_perfil")
 void altaPerfil(@Param("id_perfil")Integer id_perfil);
    }

