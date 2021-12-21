package com.telltale.main.controlador;

import com.telltale.main.entidad.Categoria;
import com.telltale.main.servicio.*;
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
    private PerfilServicio perfilServicio;
    @Autowired
    private CategoriaDelDiaServicio categoriaDelDiaServicio;
    @Autowired
    private HistoriaServicio historiaServicio;
    @Autowired
    private ComentarioServicio comentarioServicio;
    @Autowired
    private UsuarioServicio usuarioServicio;

    private List<Categoria> topicos;

    @GetMapping
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("topicos", topicos);
        modelAndView.addObject("cantidadHistorias",historiaServicio.cantidadDeHistorias());
        modelAndView.addObject("cantidadUsuarios",usuarioServicio.cantidadDeUsuarios());
        modelAndView.addObject("cantidadComentarios",comentarioServicio.cantidadDeComentarios());

        return modelAndView;
    }

    @Scheduled(fixedRate = 300000)
    public void rellenarTopicos() {
        topicos = categoriaDelDiaServicio.buscarLasUltimasTresMasVotadas();
        perfilServicio.bajaCateDelDia();

    }

}
