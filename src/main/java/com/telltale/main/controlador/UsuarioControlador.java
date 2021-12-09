package com.telltale.main.controlador;

import com.telltale.main.entidad.Rol;
import com.telltale.main.entidad.Usuario;
import com.telltale.main.excepcion.MiExcepcion;
import com.telltale.main.servicio.RolServicio;
import com.telltale.main.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/usuario")
public class UsuarioControlador {
    @Autowired
    private UsuarioServicio usuarioServicio;
    @Autowired
    private RolServicio rolServicio;

@GetMapping("/crear")
    public ModelAndView crearUsuario() throws Exception {
    ModelAndView modelAndView = new ModelAndView("usuario-formulario");
    modelAndView.addObject("usuario", new Usuario());
    modelAndView.addObject("title", "Crear Cliente");
    modelAndView.addObject("action","guardar");
    modelAndView.addObject("roles", rolServicio.verTodosRol());
    return modelAndView;
}

@PostMapping("/guardar")
    public RedirectView guardarUsuario(@RequestParam String username, @RequestParam String email, @RequestParam String password, @RequestParam Rol rol, RedirectAttributes a) throws Exception {
    RedirectView redirectView = new RedirectView("/usuario");
    try{
        usuarioServicio.crearUsuario(username,email,password,rol);
a.addFlashAttribute("success","Usuario guardado exitosamente.");
    } catch (MiExcepcion miExcepcion){
        a.addFlashAttribute("error", "Error --> Guardando usuario.");
       redirectView.setUrl("/usuario/crear");
    }
    return redirectView;
}

@GetMapping("/ver-todos")
public ModelAndView obtenerUsuarios(){
    ModelAndView modelAndView = new ModelAndView("usuario");
    modelAndView.addObject("usuarios",usuarioServicio.verTodosUsuario());
    return modelAndView;
}

@PostMapping("/modificar")
    public RedirectView modificarUsuario(@RequestParam Integer id_usuario, @RequestParam String email, @RequestParam String password,RedirectAttributes a) throws Exception {
try{
    usuarioServicio.modificarUsuario(id_usuario, email, password);
    a.addFlashAttribute("success","Usuario modificado exitosamente.");
}catch(MiExcepcion miExcepcion){
   a.addFlashAttribute("error","Error --> Modificando usuario.");
}
return new RedirectView("/usuario");
}

@GetMapping("/modificar/{id_usuario}")
public ModelAndView modificarUsuario(@PathVariable Integer id_usuario) throws Exception {
    ModelAndView modelAndView = new ModelAndView("usuario-formulario");
    modelAndView.addObject("usuario",usuarioServicio.verTodosUsuario());
    modelAndView.addObject("title","Modificar Usuario.");
    modelAndView.addObject("action","modificar");
    modelAndView.addObject("roles",rolServicio.verTodosRol());
    return modelAndView;
}

@PostMapping("/baja/{id_usuario}")
    public RedirectView bajaUsuario(@PathVariable Integer id_usuario){
    usuarioServicio.bajaUsuario(id_usuario);
    return new RedirectView("/usuario");
}

}
