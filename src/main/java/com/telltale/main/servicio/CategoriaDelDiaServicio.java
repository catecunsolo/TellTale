package com.telltale.main.servicio;

import com.telltale.main.entidad.Categoria;
import com.telltale.main.entidad.CategoriaDelDia;
import com.telltale.main.repositorio.CategoriaDelDiaRepositorio;
import com.telltale.main.repositorio.CategoriaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriaDelDiaServicio {

    @Autowired
    private CategoriaRepositorio categoriaRepositorio;

    @Autowired
    private CategoriaDelDiaRepositorio categoriaDelDiaRepositorio;

    public void crearCategoriasDelDia() {
        List<Categoria> todasSegunVotosDesc = this.categoriaRepositorio.findByOrderByVotoDesc();
        CategoriaDelDia categoriaDelDia;
        for (int i = 0; i < 3; i++) {
            categoriaDelDia = new CategoriaDelDia();
            categoriaDelDia.setCategoria(todasSegunVotosDesc.get(i));
            categoriaDelDiaRepositorio.save(categoriaDelDia);
        }
        this.setearVotos(todasSegunVotosDesc);
    }

    public void setearVotos(List<Categoria> todasSegunVotosDesc) {
        for(Categoria categoria : todasSegunVotosDesc) {
            categoria.setVoto((int)((Math.random()*100)+1));
            categoriaRepositorio.save(categoria);
        }
    }

    public List<Categoria> buscarLasUltimasTresMasVotadas() {
        this.crearCategoriasDelDia();
        List<CategoriaDelDia> todasCategoriaDelDia = this.categoriaDelDiaRepositorio.findAll();
        List<Categoria> categorias = new ArrayList<Categoria>();
        for (int i = todasCategoriaDelDia.size()-1; i > todasCategoriaDelDia.size()-4; i--) {
            categorias.add(todasCategoriaDelDia.get(i).getCategoria());
        }
        return categorias;
    }

}
