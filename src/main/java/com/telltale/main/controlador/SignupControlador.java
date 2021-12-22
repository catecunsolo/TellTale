package com.telltale.main.controlador;

import com.telltale.main.servicio.PerfilServicio;
import com.telltale.main.servicio.RolServicio;
import com.telltale.main.servicio.UsuarioServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping(value = {"/signup"})
public class SignupControlador {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PerfilServicio perfilServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private RolServicio rolServicio;

    @GetMapping
    public ModelAndView signup(HttpServletRequest request, Principal principal) {
        ModelAndView modelAndView = new ModelAndView("signup");
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
        if (flashMap != null) {
            modelAndView.addObject("success", flashMap.get("success"));
            modelAndView.addObject("error", flashMap.get("error"));
            modelAndView.addObject("username", flashMap.get("username"));
            modelAndView.addObject("mail", flashMap.get("mail"));
            modelAndView.addObject("password", flashMap.get("password"));
            modelAndView.addObject("password2", flashMap.get("password2"));
        }
        if (principal != null) {
            modelAndView.setViewName("redirect:/");
        }
        modelAndView.addObject("action", "register");
        return modelAndView;
    }

    @PostMapping("/register")
    public RedirectView register(@RequestParam String username, @RequestParam String mail, 
            @RequestParam String password, @RequestParam String password2,
            RedirectAttributes redirectAttributes,HttpServletRequest req) {
        RedirectView redirectView = new RedirectView("/login");
        try {
            perfilServicio.crearPerfil(username,null,null, usuarioServicio.crearUsuario(username, mail, password, rolServicio.buscarRolPorNombre("USER")));
            redirectAttributes.addFlashAttribute("success", "El usuario ha sido creado exitosamente!");
            req.login(mail,password);
        } catch (Exception exception) {
            redirectAttributes.addFlashAttribute("error", exception.getMessage());
            redirectAttributes.addFlashAttribute("username", username);
            redirectAttributes.addFlashAttribute("mail", mail);
            redirectAttributes.addFlashAttribute("password", password);
            redirectAttributes.addFlashAttribute("password2", password2);
            redirectView.setUrl("/signup");
        }
        return redirectView;
    }
}
