/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.telltale.main.servicio;

import com.telltale.main.entidad.Categoria;
import com.telltale.main.entidad.Historia;
import com.telltale.main.entidad.Perfil;
import com.telltale.main.repositorio.HistoriaRepositorio;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author silvia
 */
@Service
public class HistoriaServicio {
    @Autowired
    private HistoriaRepositorio repository;
    @Autowired
    private CategoriaServicio categoriaServicio;
    
    @Transactional
    public void crearHistoria(String titulo, String historia, Perfil perfil,Categoria categoria) throws Exception{
        Historia history = new Historia();
        if (titulo.trim().isEmpty() || titulo==null) {
            throw new Exception("Titulo vacío");
        }
        if (historia.trim().isEmpty() || historia==null) {
            throw new Exception("Historia vacío");
        }
        
        history.setAlta(true);
        history.setCategoria(categoria);
        history.setHistoria(historia);
        history.setMeGusta(0);
        history.setNoMeGusta(0);
        history.setPerfil(perfil);
        history.setTitulo(titulo);
        repository.save(history);
    }
    
    @Transactional
    public void modificarHistoria(Integer id,String titulo, String historia, Perfil perfil,
            Categoria categoria,Boolean alta) throws Exception{
        Historia history = repository.findById(id).orElse(null);
        if (history==null) {
            throw new Exception("No se encontró la historia");
        }
        if (titulo.trim().isEmpty() || titulo==null) {
            throw new Exception("Titulo vacío");
        }
        if (historia.trim().isEmpty() || historia==null) {
            throw new Exception("Historia vacío");
        }
        if(id==null){
            throw new Exception("Id vacío");
        }
        if(alta==null){
            throw new Exception("Alta vacía");
        }
        
        history.setTitulo(titulo);
        history.setCategoria(categoria);
        history.setPerfil(perfil);
        history.setHistoria(historia);
        history.setAlta(alta);
        repository.save(history);
    }
    
    @Transactional
    public void alta(Integer id) throws Exception{
        if (id==null) {
            throw new Exception("id vacío");
        }
        
        Historia history= repository.findById(id).orElse(null);
        if (history==null) {
            throw new Exception("No hay una historia con ese id");
        }
        history.setAlta(true);
        repository.save(history);
    }
    
    @Transactional
    public void baja(Integer id) throws Exception{
        if (id==null) {
            throw new Exception("id vacío");
        }
        
        Historia history= repository.findById(id).orElse(null);
        if (history==null) {
            throw new Exception("No hay una historia con ese id");
        }
        history.setAlta(false);
        repository.save(history);
    }
    
    @Transactional(readOnly=true)
    public Historia buscarHistoriaPorId(Integer id) throws Exception{
        if (id==null) {
            throw new Exception("id vacío");
        }
        Historia history = repository.findById(id).orElse(null);
        if (history==null) {
            throw new Exception("No se encontró la historia");
        }
        return history;
    }
    
    @Transactional(readOnly=true)
    public List<Historia> verTodosHistoria() throws Exception{
        List<Historia> historias = repository.findAll();
        if (historias.isEmpty()) {
            throw new Exception("No hay historias");
        }
        return historias;
    }
    
    @Transactional(readOnly=true)
    public List<Historia> verHistoriasPorCategoria(Categoria categoria) throws Exception{
        List<Historia> historias = repository.buscarPorCategoria(categoria);
        if (historias.isEmpty()) {
            throw new Exception("No hay historias en esta categoría");
        }
        return historias;
    }
}
