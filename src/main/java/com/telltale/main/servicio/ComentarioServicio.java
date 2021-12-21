/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.telltale.main.servicio;

import com.telltale.main.entidad.Comentario;
import com.telltale.main.entidad.Historia;
import com.telltale.main.entidad.Perfil;
import com.telltale.main.entidad.Usuario;
import com.telltale.main.repositorio.ComentarioRepositorio;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author silvia
 */
@Service
public class ComentarioServicio {

    @Autowired
    private ComentarioRepositorio comentarioRepositorio;

    @Transactional
    public void crearComentario(String comentarioTexto, Perfil perfil, Historia historia) throws Exception {
        if (comentarioTexto.trim().isEmpty() || comentarioTexto == null) {
            throw new Exception("El comentario no puede ser vacío");
        }
        if (perfil == null) {
            throw new Exception("El perfil no puede ser vacío");

        }
        if (historia == null) {
            throw new Exception("La historia no puede ser vacía");

        }
        Comentario comentario = new Comentario();
        comentario.setComentario(comentarioTexto);
        comentario.setMeGusta(0);
        comentario.setNoMeGusta(0);
        comentario.setPerfil(perfil);
        comentario.setHistoria(historia);
        comentario.setAlta(true);
        
        comentarioRepositorio.save(comentario);
    }
    
    @Transactional(readOnly=true)
    public int cantidadDeComentarios(){
        List<Comentario> comentarios = comentarioRepositorio.findAll();
        if (comentarios==null) {
            return 0;
        }else{
            return comentarios.size();
        }
    }

    @Transactional(readOnly=true)
    public List<Comentario> verTodosComentario(){
        return comentarioRepositorio.findAll();
    }

    @Transactional(readOnly = true)
    public Comentario buscarComentarioPorId(Integer id_comentario) {
        Optional<Comentario> comentarioOptional = comentarioRepositorio.findById(id_comentario);
        return comentarioOptional.orElse(null);
    }

    @Transactional
    public void altaComentario(Integer id) throws Exception{
        if (id==null) {
            throw new Exception("id vacío");
        }

        Comentario comentario= comentarioRepositorio.findById(id).orElse(null);
        if (comentario==null) {
            throw new Exception("No hay un comentario con ese id");
        }
        comentario.setAlta(true);
        comentarioRepositorio.save(comentario);
    }

    @Transactional
    public void bajaComentario(Integer id) throws Exception{
        if (id==null) {
            throw new Exception("id vacío");
        }

        Comentario comentario= comentarioRepositorio.findById(id).orElse(null);
        if (comentario==null) {
            throw new Exception("No hay un comentario con ese id");
        }
        comentario.setAlta(false);
        comentarioRepositorio.save(comentario);
    }
}
