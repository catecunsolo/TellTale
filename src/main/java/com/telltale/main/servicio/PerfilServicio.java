package com.telltale.main.servicio;

import com.telltale.main.entidad.Historia;
import com.telltale.main.entidad.Perfil;
import com.telltale.main.entidad.Usuario;
import com.telltale.main.repositorio.PerfilRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PerfilServicio {

    @Autowired
    private PerfilRepositorio perfilRepositorio;

    @Transactional
    public void crearPerfil(String nombre, String apellido, String descripcion, Usuario usuario) {

        Perfil perfil = new Perfil();
        perfil.setNombre(nombre);
        perfil.setApellido(apellido);
        perfil.setUsuario(usuario);
        perfil.setDescripcion(descripcion);
        perfilRepositorio.save(perfil);
    }

    @Transactional(readOnly = true)
    public List<Perfil> verTodosPerfil() {
        return perfilRepositorio.findAll();
    }

    @Transactional(readOnly = true)
    public Perfil buscarPerfilPorId(Integer id_perfil) {
        Optional<Perfil> optionalPerfil = perfilRepositorio.findById(id_perfil);
        return optionalPerfil.orElse(null);
    }

    @Transactional
    public void modificarPerfil(Integer id_perfil, String nombre, String apellido, String descripcion) {
        perfilRepositorio.modificarPerfil(id_perfil, nombre, apellido, descripcion);

    }

    @Transactional
    public void eliminarPerfil(Integer id_perfil) {
        perfilRepositorio.deleteById(id_perfil);

    }

}
