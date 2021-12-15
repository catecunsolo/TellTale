/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.telltale.main.controlador;

import com.telltale.main.entidad.Categoria;
import com.telltale.main.entidad.Historia;
import com.telltale.main.entidad.Perfil;
import com.telltale.main.servicio.CategoriaServicio;
import com.telltale.main.servicio.HistoriaServicio;
import com.telltale.main.servicio.PerfilServicio;
import com.telltale.main.servicio.UsuarioServicio;
import java.util.Collections;
import java.util.List;
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

    //implementa las categorias del dia
    @PostMapping("/crear")
    public ModelAndView crear(HttpSession session) {
        ModelAndView mv = new ModelAndView("historias");
        mv.addObject("action", "crear");
        Perfil perfil = perfilServicio.buscarPerfilPorId(Integer.parseInt(session.getId()));
        mv.addObject("categoria",perfil.getCategoriaDelDia());
        return mv;
    }
    
    @PostMapping("/guardar")
    public RedirectView guardar(@RequestParam("titulo") String titulo, @RequestParam("historia") String historia,
        RedirectAttributes attributes, HttpSession session) {
        RedirectView rv = new RedirectView("/historia");
        
        try {
            Perfil perfil= perfilServicio.buscarPerfilPorIdUsuario(Integer.parseInt(session.getId()));
            historiaServicio.crearHistoria(titulo, historia,perfil,perfil.getCategoriaDelDia());
            attributes.addFlashAttribute("exito-name", "Historia guardada");
        } catch (Exception e) {
            attributes.addFlashAttribute("error-name", e.getMessage());
            rv.setUrl("/historia/crear");
        }
        
        return rv;
    }
    
    @GetMapping()
    public ModelAndView historia(HttpSession session) {
        ModelAndView mv = new ModelAndView("historia");
        mv.addObject("action", "historias");
        try {
            Perfil perfil = perfilServicio.buscarPerfilPorIdUsuario(Integer.parseInt(session.getId()));
            Categoria categoria = perfil.getCategoriaDelDia();
            if (categoria == null) {
                //aca tengo que poner la ruta del controlador de categorias
                mv.setViewName("redirect:/");
//                mv.addObject("Historia1",
//                        historiaServicio.verHistoriasPorCategoria(categoriaServicio.categoriasDelDia.get(0)).get(0));
//                mv.addObject("Historia2",
//                        historiaServicio.verHistoriasPorCategoria(categoriaServicio.categoriasDelDia.get(1)).get(0));
//                mv.addObject("Historia3",
//                        historiaServicio.verHistoriasPorCategoria(categoriaServicio.categoriasDelDia.get(2)).get(0));
            } else {
                List<Historia> historias = historiaServicio.verHistoriasPorCategoria(categoria);
                List<Historia> historiasCortadas = Collections.EMPTY_LIST;
                for (Historia historia : historias) {
                    String history = historia.getHistoria();
                    if (history.length() > 90) {
                        history = history.substring(0, 89);
                        historia.setHistoria(history);
                    }
                    historiasCortadas.add(historia);
                }
                mv.addObject("historias", historiasCortadas);
                mv.addObject("categoria", categoria);
            }
        } catch (Exception e) {
            mv.addObject("error-name", e.getMessage());
        }
        
        return mv;
        
    }
    
    @GetMapping("/{id}")
    public ModelAndView historiaEnParticular(@PathVariable int id,RedirectAttributes ra){
        ModelAndView mv = new ModelAndView("historia");
        mv.addObject("action",id);
        try{
            mv.addObject("historia", historiaServicio.buscarHistoriaPorId(id));
        }catch(Exception e){
            ra.addFlashAttribute("error-name", e.getMessage());
            mv.setViewName("redirect:/historia");
        }
        return mv;
        
    }
}
