package com.telltale.main.controlador;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

@RestController
public class ErrorControlador implements ErrorController {

    @RequestMapping(value = "/error", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView errors(HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView("error");
        String message = "";
        int codeResponse = response.getStatus();
        switch (codeResponse) {
            case 301:
                message = "¡Los recursos solicitados ya no se encuentran disponibles. Han sido removidos de manera permanente!";
                break;
            case 302:
                message = "¡Los recursos solicitados ya no se encuentran disponibles. Han sido removidos temporalmente!";
                break;
            case 403:
                message = "¡No cuenta con los permisos necesarios para acceder al recurso solicitado!";
                break;
            case 404:
                message = "¡No se ha encontrado el recurso solicitado!";
                break;
            case 500:
                message = "¡Algo ha ocurrido en el servidor!";
                break;
            case 503:
                message = "¡El servidor se encuentra temporalmente fuera de servicio!";
                break;
            case 504:
                message = "¡El tiempo de espera de respuesta ha superado el máximo permitido!";
                break;
            default:
                message = "¡Error inesperado! Contacte con su administrador.";
        }
        modelAndView.addObject("codigo", codeResponse);
        modelAndView.addObject("mensaje", message);
        return modelAndView;
    }

}