package com.telltale.main.controlador;

import com.telltale.main.entidad.Historia;
import com.telltale.main.entidad.Perfil;
import com.telltale.main.entidad.Usuario;
import com.telltale.main.servicio.PerfilServicio;
import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/perfil")
public class PerfilControlador {

    @Autowired
    PerfilServicio perfilServicio;

    @GetMapping
    public ModelAndView verTodosPerfil() {
        ModelAndView mav = new ModelAndView("perfiles");
        mav.addObject("perfiles", perfilServicio.verTodosPerfil());
        return mav;
    }

    @GetMapping("/crear")
    public ModelAndView crearPerfil() {
        ModelAndView mav = new ModelAndView("perfil-formulario");
        mav.addObject("perfil", new Perfil());
        mav.addObject("titile", "Crear Perfil");
        mav.addObject("action", "guardae");
        return mav;

    }

    @PostMapping("/guardar")
    public RedirectView guardaPerfil(@RequestParam String nombre, @RequestParam String apellido,
            @RequestParam String descripcion, @RequestParam Usuario usuario)throws Exception {
        perfilServicio.crearPerfil(nombre, apellido, descripcion, usuario);
        return new RedirectView("/perfil");

    }

    @PostMapping("/modificar")
    public RedirectView modificarPerfil(@RequestParam Integer id_perfil, @RequestParam String nombre,
            @RequestParam String apellido, @RequestParam String descripcion)throws Exception {
        perfilServicio.modificarPerfil(id_perfil, nombre, apellido, descripcion);
        return new RedirectView("/perfil");
    }

    @PostMapping("/eliminar/{id_perfil}")
    public RedirectView elimiarPerfil(@PathVariable Integer id_perfil) {
        perfilServicio.eliminarPerfil(id_perfil);
        return new RedirectView("/perfil");
    }
}
