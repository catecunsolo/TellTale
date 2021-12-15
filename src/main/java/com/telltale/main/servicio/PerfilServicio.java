package com.telltale.main.servicio;

import com.telltale.main.entidad.Categoria;
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
    @Autowired
    private UsuarioServicio usuarioServicio;

    @Transactional
    public void crearPerfil(String nombre, String apellido, String descripcion, Usuario usuario) throws Exception {
        //validarNulo(nombre, apellido, descripcion);
        Perfil perfil = new Perfil();
        perfil.setNombre(nombre);
        perfil.setApellido(apellido);
        perfil.setRedes(null);
        perfil.setAvatar(null);
        perfil.setDescripcion(descripcion);
        perfil.setCategoriaDelDia(null);
        perfil.setUsuario(usuario);
        perfil.setAlta(true);
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
    public void modificarPerfil(Integer id_perfil, String nombre, String apellido, String descripcion)throws Exception {
        validarNulo(nombre,apellido,descripcion);
        perfilRepositorio.modificarPerfil(id_perfil, nombre, apellido, descripcion);

    }

    @Transactional(readOnly=true)
    public Perfil buscarPerfilPorIdUsuario(int id) throws Exception{
        Usuario usuario = usuarioServicio.buscarUsuarioPorId(id);
        Perfil perfil= perfilRepositorio.buscarPerfilPorIdUsuario(usuario);
        if (perfil==null) {
            throw new Exception("No existe un perfil asociado a ese usuario");
        }
        return perfil;
    }
    
    @Transactional
    public void setearCategoriaDelDia(int id_perfil,Categoria categoria) throws Exception{
        Perfil perfil = perfilRepositorio.findById(id_perfil).orElse(null);
        if (perfil==null) {
            throw new Exception("No existe un perfil asociado a ese usuario");
        }
        perfil.setCategoriaDelDia(categoria);
        perfilRepositorio.save(perfil);
    }
    
    @Transactional
    public void bajaCateDelDia(){
        List<Perfil> perfiles = perfilRepositorio.findAll();
        for (Perfil perfil : perfiles) {
            perfil.setCategoriaDelDia(null);
            perfilRepositorio.save(perfil);
        }
    }















    public void validarNulo(String nombre, String apellido, String descripcion) throws Exception {
        if (nombre.trim().isEmpty() || nombre == null) {
            throw new Exception("El nombre es obligatorio");
        }
        if (apellido.trim().isEmpty() || apellido == null) {
            throw new Exception("El apellido es obligatorio");
        }
        if (descripcion.trim().isEmpty() || descripcion == null) {
            throw new Exception("La descripcion es obligatoria");

        }
    }

    @Transactional
    public void eliminarPerfil(Integer id_perfil) {
        perfilRepositorio.deleteById(id_perfil);

    }

}