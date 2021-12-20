package com.telltale.main.repositorio;

import com.telltale.main.entidad.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario,Integer> {
    @Modifying
    @Query("UPDATE Usuario u SET u.alta = :alta WHERE u.id_usuario = :id_usuario")
    void altaUsuario(@Param("id_usuario") Integer id_usuario, @Param("alta") Boolean alta);

    @Modifying
    @Query("UPDATE Usuario u SET u.alta = :alta WHERE u.id_usuario = :id_usuario")
    void bajaUsuario(@Param("id_usuario") Integer id_usuario, @Param("alta") Boolean alta);

    Optional<Usuario> findByUsername(String username);

    Optional<Usuario>findByEmail(String email);

    boolean existsUsuarioByUsername(String username);

    boolean existsUsuarioByEmail(String email);
}
