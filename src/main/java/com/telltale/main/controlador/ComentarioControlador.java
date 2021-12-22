/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.telltale.main.controlador;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;
import com.telltale.main.entidad.*;
import com.telltale.main.servicio.ComentarioServicio;
import com.telltale.main.servicio.PerfilServicio;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
/**
 *
 * @author silvia
 */
@Controller
@RequestMapping("/comentario")
public class ComentarioControlador {
    
    @Autowired
    private ComentarioServicio comentarioServicio;
    @Autowired
    private PerfilServicio perfilServicio;
    
    @PostMapping("/guardar")
    public RedirectView guardarComentario(@RequestParam("comentario") String comentarioPropio,
            @RequestParam("historia") Historia historia,HttpSession session,RedirectAttributes ra ){
        try {
            Perfil perfil = perfilServicio.buscarPerfilPorIdUsuario((int) session.getAttribute("id_usuario"));
            comentarioServicio.crearComentario(comentarioPropio, perfil, historia);
            return new RedirectView("/historia/"+historia.getId_historia());
        } catch (Exception e) {
            if (historia==null) {
                ra.addFlashAttribute("error-name", e.getMessage());
                return new RedirectView("/historia");
            }else{
                return new RedirectView("/historia/"+ historia.getId_historia());
            }
            
        }
        
    }

    @GetMapping("/todos")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER','MODER')")
    public ModelAndView obtenerComentarios(HttpServletRequest request, @RequestParam(required = false) String error) {
        ModelAndView modelAndView = new ModelAndView("admin-comentarios");
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
        if (flashMap != null) {
            modelAndView.addObject("success", flashMap.get("success"));
            modelAndView.addObject("error", flashMap.get("error"));
            modelAndView.addObject("listaComentarios", null);
        }
        if (error != null) {
            modelAndView.addObject("error", error);
            modelAndView.addObject("listaComentarios", null);
        } else {
            try {
                modelAndView.addObject("listaComentarios", comentarioServicio.verTodosComentario());
            } catch (Exception excepcion) {
                modelAndView.addObject("error", excepcion.getMessage());
                modelAndView.setViewName("redirect:/comentario/todos");
            }
        }
        return modelAndView;
    }

    @PostMapping("/alta/{id_comentario}")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER','MODER')")
    public RedirectView habilitarRol(@PathVariable Integer id_comentario, RedirectAttributes redirectAttributes) {
        RedirectView redirectView = new RedirectView("/comentario/todos");
        try {
            String aux = "";
            Comentario comentario = comentarioServicio.buscarComentarioPorId(id_comentario);
            comentario.setAlta(!comentario.getAlta());
            if (comentario.getAlta()) {
                aux = "habilitado";
                comentarioServicio.altaComentario(id_comentario);
            } else {
                aux = "deshabilitado";
                comentarioServicio.bajaComentario(id_comentario);
            }
            redirectAttributes.addFlashAttribute("success", "El comentario ha sido " + aux + " exitosamente!");
        } catch (Exception exception) {
            redirectAttributes.addFlashAttribute("error", exception.getMessage());
        }
        return redirectView;
    }
}
