package com.telltale.main.controlador;

import com.telltale.main.entidad.Categoria;
import com.telltale.main.servicio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

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
    public ModelAndView home(HttpServletRequest request, RedirectAttributes attributes) {
        ModelAndView modelAndView = new ModelAndView("index");
        Map<String, ?> map = RequestContextUtils.getInputFlashMap(request);
        if (map != null) {
            modelAndView.addObject("error", map.get("error-name"));
            modelAndView.addObject("success", map.get("success-name"));
        }
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
