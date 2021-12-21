/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.telltale.main.controlador;

import com.telltale.main.entidad.Categoria;
import com.telltale.main.entidad.Comentario;
import com.telltale.main.entidad.Historia;
import com.telltale.main.entidad.Perfil;
import com.telltale.main.servicio.CategoriaServicio;
import com.telltale.main.servicio.HistoriaServicio;
import com.telltale.main.servicio.PerfilServicio;
import com.telltale.main.servicio.UsuarioServicio;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author silvia
 */
@Controller
@RequestMapping("/historia")
public class HistoriaControlador {

    @Autowired
    private HistoriaServicio historiaServicio;
    @Autowired
    private CategoriaServicio categoriaServicio;
    @Autowired
    private PerfilServicio perfilServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/crear")
    public ModelAndView crear(HttpSession session, @RequestParam("categoria") Integer id_categoria) {
        ModelAndView mv = new ModelAndView("historias");
        try {
            Perfil perfil = perfilServicio.buscarPerfilPorIdUsuario((int) session.getAttribute("id_usuario"));
            mv.addObject("perfilLogueado", perfil);
            Categoria categoriaElegida = categoriaServicio.buscarCategoriaPorId(id_categoria);
            mv.addObject("categoriaElegida", categoriaElegida);
            Historia historia = new Historia();
            historia.setPerfil(perfil);
            historia.setCategoria(categoriaElegida);
            mv.addObject("historia", historia);

            if (perfil.getCategoriaDelDia() != null) {
                mv.setViewName("redirect:/historia");
            }
        } catch (Exception e) {
            mv.setViewName("redirect:/");
        }

        mv.addObject("action", "crear");

        return mv;
    }

    @PostMapping("/guardar")
    public RedirectView guardar(@RequestParam("titulo") String titulo, @RequestParam("historia") String historia,
            RedirectAttributes attributes, HttpSession session, @RequestParam("categoria") Categoria categoria) {
        RedirectView rv = new RedirectView("/historia");

        try {
            Perfil perfil = perfilServicio.buscarPerfilPorIdUsuario((int) session.getAttribute("id_usuario"));
            historiaServicio.crearHistoria(titulo, historia, perfil, categoria);
            perfilServicio.setearCategoriaDelDia(perfil.getId_perfil(), categoria);
            attributes.addFlashAttribute("success-name", "Historia guardada");
        } catch (Exception e) {
            attributes.addFlashAttribute("error-name", e.getMessage());
            rv.setUrl("/historia/crear");
        }

        return rv;
    }

    @GetMapping
    public ModelAndView historia(HttpSession session, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("historias");
        Map<String, ?> map = RequestContextUtils.getInputFlashMap(request);
        if (map != null) {
            mv.addObject("error", map.get("error-name"));
            mv.addObject("success", map.get("success-name"));
        }

        try {
            Perfil perfil = perfilServicio.buscarPerfilPorIdUsuario((int) session.getAttribute("id_usuario"));
            Categoria categoria = perfil.getCategoriaDelDia();
            if (categoria == null) {
                mv.setViewName("redirect:/");
            } else {
                List<Historia> historias = historiaServicio.verHistoriasPorCategoria(categoria);
                List<Historia> historiasCortadas = new ArrayList();
                for (Historia historia : historias) {
                    String history = historia.getHistoria();
                    if (history.length() > 90) {
                        history = history.substring(0, 89);
                        historia.setHistoria(history);
                    }
                    historiasCortadas.add(historia);
                }
                mv.addObject("listaHistorias", historiasCortadas);
                mv.addObject("categoria", categoria);
            }
        } catch (Exception e) {
            mv.setViewName("redirect:/perfil");
        }

        mv.addObject("action", "historias");
        return mv;
    }

    @GetMapping("/{id}")
    public ModelAndView historiaEnParticular(@PathVariable int id, RedirectAttributes ra, HttpSession session) {
        ModelAndView mv = new ModelAndView("historias");
        mv.addObject("action", "id");
        try {
            Perfil perfil = perfilServicio.buscarPerfilPorIdUsuario((int) session.getAttribute("id_usuario"));
            Historia historia = historiaServicio.buscarHistoriaPorId(id);
            if (perfil.getId_perfil() != historia.getPerfil().getId_perfil()) {
                mv.addObject("noEsMiHistoria", true);
            } else {
                mv.addObject("noEsMiHistoria", false);

            }
            if ((perfil.getCategoriaDelDia() == null
                    || perfil.getCategoriaDelDia().getId_categoria() != historia.getCategoria().getId_categoria())
                    && historia.getPerfil().getId_perfil() != perfil.getId_perfil()
                    && !perfil.getHistoriasFav().contains(historia)) {
                mv.setViewName("redirect:/historia");
            }
            mv.addObject("historia", historia);
            mv.addObject("listaComentarios", historia.getComentarios());
            mv.addObject("comentario_propio", new Comentario());
            mv.addObject("isFav", perfil.getHistoriasFav().contains(historia));
        } catch (Exception e) {
            ra.addFlashAttribute("error-name", e.getMessage());
            mv.setViewName("redirect:/historia");
        }

        return mv;

    }

    //CONTROLADORES PARA IMPLEMENTAR DESPUES
    @GetMapping("/misHistorias")
    public ModelAndView misHistorias(HttpSession session, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("historias");
        Map<String, ?> map = RequestContextUtils.getInputFlashMap(request);
        if (map != null) {
            mv.addObject("error", map.get("error-name"));
        }
        mv.addObject("action", "misHistorias");
        try {
            Perfil perfil = perfilServicio.buscarPerfilPorIdUsuario((int) session.getAttribute("id_usuario"));
            List<Historia> historias = perfil.getHistorias();
            List<Historia> historiasCortadas = new ArrayList();
            for (Historia historia : historias) {
                String history = historia.getHistoria();
                if (history.length() > 90) {
                    history = history.substring(0, 89);
                    historia.setHistoria(history);
                }
                historiasCortadas.add(historia);
            }
            mv.addObject("listaHistorias", historiasCortadas);
            mv.addObject("action", "misHistorias");
        } catch (Exception e) {
            mv.addObject("error", e.getMessage());
        }
        return mv;
    }

    @GetMapping("/misHistorias/{id}")
    public ModelAndView misHistorias(@PathVariable int id, HttpSession session, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("historias");
        if (id == (int) session.getAttribute("id_usuario")) {
            mv.setViewName("redirect:/historia/misHistorias");
        } else {
            Map<String, ?> map = RequestContextUtils.getInputFlashMap(request);
            if (map != null) {
                mv.addObject("error", map.get("error-name"));
            }
            mv.addObject("action", "otrasHistorias");
            mv.addObject("nombreUsuario", usuarioServicio.buscarUsuarioPorId(id).getUsername());
            try {
                Perfil perfil = perfilServicio.buscarPerfilPorIdUsuario(id);
                List<Historia> historias = perfil.getHistorias();
                List<Historia> historiasCortadas = new ArrayList();
                for (Historia historia : historias) {
                    String history = historia.getHistoria();
                    if (history.length() > 90) {
                        history = history.substring(0, 89);
                        historia.setHistoria(history);
                    }
                    historiasCortadas.add(historia);
                }
                mv.addObject("listaHistorias", historiasCortadas);
                //mv.addObject("action", "misHistorias");
            } catch (Exception e) {
                mv.addObject("error", e.getMessage());
            }
        }
        return mv;
    }

    @PostMapping("/fav")
    public RedirectView favoritos(@RequestParam("historia") Historia historia, HttpSession session, RedirectAttributes ra) {
        try {
            Perfil perfil = perfilServicio.buscarPerfilPorIdUsuario((int) session.getAttribute("id_usuario"));
            perfilServicio.favorita(perfil, historia);
        } catch (Exception e) {
            ra.addFlashAttribute("error-name", e.getMessage());
        }

        return new RedirectView("/historia/" + historia.getId_historia());
    }

    @GetMapping("/favoritas")
    public ModelAndView favoritas(HttpSession session) {
        ModelAndView mv = new ModelAndView("historias");
        mv.addObject("action", "favoritas");
        try {
            Perfil perfil = perfilServicio.buscarPerfilPorIdUsuario((int) session.getAttribute("id_usuario"));
            mv.addObject("listaHistorias", perfil.getHistoriasFav());
        } catch (Exception e) {
            mv.setViewName("redirect:/");
        }
        return mv;
    }
//    @PostMapping("/baja/{id}")
//    public RedirectView baja(@PathVariable("id") int id, HttpSession session,RedirectAttributes ra) {
//        RedirectView rv = new RedirectView("/historia/misHistorias");
//        try {
//            Perfil perfil = perfilServicio.buscarPerfilPorIdUsuario((int) session.getAttribute("id_usuario"));
//            Historia historia = historiaServicio.buscarHistoriaPorId(id);
//            if (historia.getPerfil().getId_perfil() == perfil.getId_perfil()) {
//                historiaServicio.baja(id);
//
//            }
//        } catch (Exception e) {
//            ra.addFlashAttribute("error-name", e.getMessage());
//        }
//        return rv;
//    }
}
