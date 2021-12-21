package com.telltale.main.controlador;

import com.telltale.main.entidad.Rol;
import com.telltale.main.entidad.Usuario;
import com.telltale.main.excepcion.MiExcepcion;
import com.telltale.main.servicio.RolServicio;
import com.telltale.main.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/usuario")
public class UsuarioControlador {

    private Usuario usuario;

    @Autowired
    private UsuarioServicio usuarioServicio;
    @Autowired
    private RolServicio rolServicio;

    @GetMapping("/crear")
    public ModelAndView crearUsuario() throws Exception {
        ModelAndView modelAndView = new ModelAndView("usuario-formulario");
        modelAndView.addObject("usuario", new Usuario());
        modelAndView.addObject("title", "Crear Usuario");
        modelAndView.addObject("action", "guardar");
        modelAndView.addObject("roles", rolServicio.verTodosRol());
        return modelAndView;
    }

    @PostMapping("/guardar")
    public RedirectView guardarUsuario(@RequestParam String username, @RequestParam String email, @RequestParam String password, @RequestParam Rol rol, RedirectAttributes a) throws Exception {
        RedirectView redirectView = new RedirectView("/usuario");
        try {
            usuarioServicio.crearUsuario(username, email, password, rol);
            a.addFlashAttribute("success", "Usuario guardado exitosamente.");
        } catch (Exception exception) {
            a.addFlashAttribute("error", "Error --> Guardando usuario --> " + exception.getMessage());
            redirectView.setUrl("/usuario/crear");
        }
        return redirectView;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','SUPER','MODER')")
    public ModelAndView obtenerUsuarios(HttpServletRequest request, @RequestParam(required = false) String error) {
        ModelAndView modelAndView = new ModelAndView("admin-usuarios");
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
        if (flashMap != null) {
            modelAndView.addObject("success", flashMap.get("success"));
            modelAndView.addObject("error", flashMap.get("error"));
            modelAndView.addObject("listaUsuarios", null);
        }
        if (error != null) {
            modelAndView.addObject("error", error);
            modelAndView.addObject("listaUsuarios", null);
        } else {
            try {
                modelAndView.addObject("listaUsuarios", usuarioServicio.verTodosUsuario());
            } catch (Exception excepcion) {
                modelAndView.addObject("error", excepcion.getMessage());
                modelAndView.setViewName("redirect:/usuario");
            }
        }
        return modelAndView;
    }

    @PostMapping("/modificar")
    @PreAuthorize("hasAnyRole('USER','ADMIN','SUPER','MODER')")
    public RedirectView modificarUsuario(@RequestParam Integer id_usuario, @RequestParam String email, @RequestParam String password, RedirectAttributes a, HttpSession session) throws Exception {
        try {
            usuarioServicio.modificarUsuario(id_usuario, email, password);
            a.addFlashAttribute("success", "Usuario modificado exitosamente.");
            usuario=usuarioServicio.buscarUsuarioPorId(id_usuario);
            session.setAttribute("email",usuario.getEmail());
            session.setAttribute("password",usuario.getPassword());
        } catch (Exception exception) {
            a.addFlashAttribute("error", "Error --> Modificando usuario--> " + exception.getMessage());
/*            if (session.getAttribute("rol").equals("ADMIN")) {
                return new RedirectView("/usuario/modificar/" + id_usuario);
            }*/
        }
        return new RedirectView("/usuario");
    }

    @GetMapping("/modificar/{id_usuario}")
    @PreAuthorize("hasAnyRole('USER','ADMIN','SUPER','MODER')")
    public ModelAndView modificarUsuario(@PathVariable Integer id_usuario) throws Exception {
        ModelAndView modelAndView = new ModelAndView("usuario-formulario");
        modelAndView.addObject("usuario", usuarioServicio.verTodosUsuario());
        modelAndView.addObject("title", "Modificar Usuario.");
        modelAndView.addObject("action", "modificar");
        modelAndView.addObject("roles", rolServicio.verTodosRol());
        return modelAndView;
    }

    @PostMapping("/baja/{id_usuario}")
    @PreAuthorize("hasAnyRole('USER','ADMIN','SUPER','MODER')")
    public RedirectView bajaUsuario(@PathVariable Integer id_usuario) {
        usuarioServicio.bajaUsuario(id_usuario);
        return new RedirectView("/usuario");
    }

    @PostMapping("/alta/{id_usuario}")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER','MODER')")
    public RedirectView habilitarRol(@PathVariable Integer id_usuario, RedirectAttributes redirectAttributes) {
        RedirectView redirectView = new RedirectView("/usuario");
        try {
            String aux = "";
            Usuario usuario = usuarioServicio.buscarUsuarioPorId(id_usuario);
            usuario.setAlta(!usuario.getAlta());
            if (usuario.getAlta()) {
                aux = "habilitado";
                usuarioServicio.altaUsuario(id_usuario);
            } else {
                aux = "deshabilitado";
                usuarioServicio.bajaUsuario(id_usuario);
            }
            redirectAttributes.addFlashAttribute("success", "El usuario sido " + aux + " exitosamente!");
        } catch (Exception exception) {
            redirectAttributes.addFlashAttribute("error", exception.getMessage());
        }
        return redirectView;
    }
}
