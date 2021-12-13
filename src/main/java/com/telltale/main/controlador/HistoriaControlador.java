/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.telltale.main.controlador;

import com.telltale.main.entidad.Categoria;
import com.telltale.main.entidad.Perfil;
import com.telltale.main.servicio.CategoriaServicio;
import com.telltale.main.servicio.HistoriaServicio;
import com.telltale.main.servicio.PerfilServicio;
import com.telltale.main.servicio.UsuarioServicio;
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
    @GetMapping("/crear")
    public ModelAndView crear(HttpSession session) {
        ModelAndView mv = new ModelAndView("crearHistoriaPrueba");
        //Acá hay que implementar el método que te devuelve las categorías del día
        //mv.addObject("categorias", categoriaServicio.categoriasDelDia);
        return mv;
    }

    //Implementar Session
    @PostMapping("/guardar")
    public RedirectView guardar(@RequestParam("titulo") String titulo, @RequestParam("historia") String historia,
            @RequestParam("id_categoria") int id_categoria, RedirectAttributes attributes, HttpSession session) {
        RedirectView rv = new RedirectView("/historia");

        try {
            historiaServicio.crearHistoria(titulo, historia,
                    perfilServicio.buscarPerfilPorIdUsuario(Integer.parseInt(session.getId())),
                    categoriaServicio.buscarCategoriaPorId(id_categoria));
            attributes.addFlashAttribute("exito-name", "Historia guardada");
        } catch (Exception e) {
            attributes.addFlashAttribute("error-name", e.getMessage());
            rv.setUrl("/historia/crear");
        }

        return rv;
    }

    
    @GetMapping()
    public ModelAndView historia(HttpSession session) {
        ModelAndView mv = new ModelAndView("");
        try {
            Perfil perfil = perfilServicio.buscarPerfilPorIdUsuario(Integer.parseInt(session.getId()));
            Categoria categoria = perfil.getCategoriaDelDia();
            if (categoria== null) {
//                mv.addObject("Historia1",
//                        historiaServicio.verHistoriasPorCategoria(categoriaServicio.categoriasDelDia.get(0)).get(0));
//                mv.addObject("Historia2",
//                        historiaServicio.verHistoriasPorCategoria(categoriaServicio.categoriasDelDia.get(1)).get(0));
//                mv.addObject("Historia3",
//                        historiaServicio.verHistoriasPorCategoria(categoriaServicio.categoriasDelDia.get(2)).get(0));
            }else{
                mv.addObject("historias", historiaServicio.verHistoriasPorCategoria(categoria));
                mv.addObject("categoria",categoria);
            }
        } catch (Exception e) {
            mv.addObject("error-name", e.getMessage());
        }
        
        return mv;

    }

}
