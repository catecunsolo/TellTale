package com.telltale.main.servicio;

import com.telltale.main.entidad.Categoria;
import com.telltale.main.repositorio.CategoriaRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoriaServicio {

    @Autowired
    private CategoriaRepositorio categoriaRepositorio;
    
    @Transactional
    public void crearCategoria(String nombre, Integer id_categoria, Integer voto) throws Exception {
        Categoria categoria = new Categoria();


        if (categoriaRepositorio.buscarCategoriaPorNombre(nombre) != null) {
            throw new Exception("Categoria existente en el servidor");
        }
        if (nombre.trim().isEmpty() || nombre==null) {
            throw new Exception("El nombre de la categoria se encuentra vacio");
        }
        if (categoria==null) {
            throw new Exception("la categoria se encuentra vacia");

        }
        categoria.setNombre(nombre);
        categoria.setVoto(0);
        categoriaRepositorio.save(categoria);
    }

    @Transactional
    public void modificarCategoria(Integer id_categoria, String nombre, Integer voto) throws Exception {
         Categoria categoria =categoriaRepositorio.findById(id_categoria).orElse(null);
         if(categoria==null){
            throw new Exception("La categoria esta vacia");
        }
        if(nombre==null){
            throw new Exception("El nombre de la categoria esta vacio");
        }
         if(id_categoria==null){
            throw new Exception("El id de la categoria esta vacio");
        }
        if(voto==null){
            throw new Exception("El voto de la categoria esta vacio");
        }
       categoriaRepositorio.modificarCategoria(id_categoria, nombre);
    }


    @Transactional(readOnly = true)
    public Categoria buscarCategoriaPorId(Integer id_categoria) throws Exception {
        if (id_categoria == null) {
            throw new Exception("el id al que intenta acceder esta vacío");
        }
        Categoria categoria = categoriaRepositorio.findById(id_categoria).orElse(null);
        if (categoria == null) {
            throw new Exception("No se encontró la categoria");
        }
        return categoria;

    }

    @Transactional(readOnly = true)
    public Categoria buscarCategoriaPorNombre(String nombre) throws Exception {
          if (nombre==null) {
              throw new Exception("El nombre de la categoria esta vacio");
        }
          Categoria categoria = categoriaRepositorio.buscarCategoriaPorNombre(nombre);
          if (categoria == null) {
            throw new Exception("No se encontró la categoria");
        }

        return categoria;
    }

    @Transactional(readOnly = true)

    public List<Categoria> verTodosCategoria() throws Exception {
        List<Categoria> categorias = categoriaRepositorio.findAll();
        if (categorias.isEmpty()) {
            throw new Exception("No existen categorias para mostrar");
        }
        return categorias;
    }

    @Transactional
    public void eliminarCategoria(Integer id_categoria) throws Exception {
         if (id_categoria == null) {
            throw new Exception("el id al que intenta Eliminar esta vacío");
        }
        Categoria categoria = categoriaRepositorio.findById(id_categoria).orElse(null);
        if (categoria == null) {
            throw new Exception("No se encontró la categoria que intenta eliminar");
        }
        categoriaRepositorio.deleteById(id_categoria);
    }
    
        }
