package com.telltale.main.controlador;

import com.telltale.main.entidad.Categoria;
import com.telltale.main.servicio.CategoriaServicio;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/categorias")
public class CategoriaControlador {

    @Autowired
    CategoriaServicio categoriaServicio;

    @GetMapping
    public ModelAndView verTodosCategoria(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView("categorias");
        Map<String, ?> map = RequestContextUtils.getInputFlashMap(request);
        if (map != null) {
            mav.addObject("error", map.get("exito-name"));
        }
        mav.addObject("categorias", categoriaServicio.verTodosCategoria());
        return mav;
    }

    /**
     * *
     * redireccionar en un get mav.setViewName("redirect:/usuario");
     */
    @GetMapping("/crear")
    public ModelAndView crearCategoria(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("categoria-formulario");
        Map<String, ?> map = RequestContextUtils.getInputFlashMap(request);
        if (map != null) {
            mav.addObject("error", map.get("error-name"));
            mav.addObject("categoria", map.get("categoria"));
        } else {
            mav.addObject("categoria", new Categoria());
        }
        mav.addObject("tittle", "Crear Categoria");
        mav.addObject("action", "guardar");
        return mav;
    }

    @GetMapping("/editar/ {id_categoria}")
    public ModelAndView editarCategoria(@PathVariable Integer id_categoria,
            HttpServletRequest request,RedirectAttributes attributes) {
        ModelAndView mav = new ModelAndView("autor-formulario");
        Map<String, ?> map = RequestContextUtils.getInputFlashMap(request);
        try {
            if (map != null) {
                mav.addObject("error", map.get("error-name"));
                mav.addObject("categoria", map.get("categoria"));
            } else {
                mav.addObject("categoria", categoriaServicio.buscarCategoriaPorId(id_categoria));
            }

            mav.addObject("tittle", "Editar Categoria");
            mav.addObject("action", "modificar");
        } catch (Exception e) {
             attributes.addFlashAttribute("error", e.getMessage());
             mav.setViewName("redirect:/categorias");
        }
        return mav;
    }

    @PostMapping("/guardar")
    public RedirectView guardar(@RequestParam String nombre, @RequestParam Integer id_categoria,
            @RequestParam Integer voto, RedirectAttributes ra) {
        RedirectView rv = new RedirectView("/categorias");
        try {
            categoriaServicio.crearCategoria(nombre, id_categoria, voto);
            ra.addFlashAttribute("exito-name", "Se ha creado la categoria con exito");
        } catch (Exception e) {
            ra.addFlashAttribute("error-name", e.getMessage());
            rv.setUrl("/categorias/crear");
        }

        return rv;
    }

    @PostMapping("/modificar")
    public RedirectView modificar(@ModelAttribute Categoria categoria, @RequestParam String nombre,
            @RequestParam Integer id_categoria,
            @RequestParam Integer voto,
            RedirectAttributes attributes) {
        RedirectView rv = new RedirectView("/categorias");
        try {
            categoriaServicio.modificarCategoria(id_categoria, nombre, voto);
            attributes.addFlashAttribute("exito name", "Se ha modificado la categoria exitosamente");
        } catch (Exception e) {
            attributes.addFlashAttribute("categoria", categoria);
            attributes.addFlashAttribute("error-name", e.getMessage());
            rv.setUrl("/categorias/editar/" + categoria.getId_categoria());
        }

        return rv;
    }

    @PostMapping("/eliminar/{id}")
    public RedirectView eliminar(@PathVariable Integer id_categoria) throws Exception {
        categoriaServicio.eliminarCategoria(id_categoria);
        return new RedirectView("/categorias");
    }
}
