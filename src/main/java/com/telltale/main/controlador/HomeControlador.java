package com.telltale.main.controlador;

import com.telltale.main.entidad.Categoria;
import com.telltale.main.servicio.CategoriaDelDiaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/")
public class HomeControlador {

    @Autowired
    private CategoriaDelDiaServicio categoriaDelDiaServicio;

    private List<Categoria> topicos;

    @GetMapping
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("topicos", topicos);
        return modelAndView;
    }

    @Scheduled(fixedRate = 20000)
    public void rellenarTopicos() {
        topicos = categoriaDelDiaServicio.buscarLasUltimasTresMasVotadas();
    }

}
