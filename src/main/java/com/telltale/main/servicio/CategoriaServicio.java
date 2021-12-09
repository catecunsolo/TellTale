
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
    public void crearCategoria(String nombre) throws Exception{
        Categoria categoria = new Categoria();
        
        if (categoriaRepositorio.buscarCategoriaPorNombre(nombre)!= null){
            throw new Exception("Categoria existente en el servidor");
        }
        categoria.setNombre(nombre);
        categoria.setVoto(0);
        categoriaRepositorio.save(categoria);
    }
    
    @Transactional
    public void modificarCategoria(Integer id_categoria, String nombre){
        categoriaRepositorio.modificarCategoria(id_categoria, nombre);
    }
    
    @Transactional(readOnly = true)
    public Categoria buscarCategoriaPorId(Integer id_categoria){
        Categoria categoria = categoriaRepositorio.buscarCategoriaPorId(id_categoria);
        return categoria;
    }
    @Transactional(readOnly = true)
    public Categoria buscarCategoriaPorNombre(String nombre){
        Categoria categoria = categoriaRepositorio.buscarCategoriaPorNombre(nombre);
        return categoria;
    }
    
    @Transactional(readOnly = true)
    public List<Categoria> verTodosCategoria(){
        return categoriaRepositorio.findAll();
    }
  
    @Transactional
    public void eliminarCategoria(Integer id_categoria){
       categoriaRepositorio.deleteById(id_categoria);
    }
    
    
}
