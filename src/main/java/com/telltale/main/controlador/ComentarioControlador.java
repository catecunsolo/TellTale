/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.telltale.main.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import com.telltale.main.entidad.*;
import com.telltale.main.servicio.ComentarioServicio;
import com.telltale.main.servicio.PerfilServicio;
import java.util.logging.Level;
import java.util.logging.Logger;
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
}
