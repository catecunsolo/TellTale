package com.telltale.main.servicio;

import com.telltale.main.entidad.Rol;
import com.telltale.main.excepcion.MiExcepcion;
import com.telltale.main.repositorio.RolRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RolServicio {

    @Autowired
    private RolRepositorio rolRepositorio;

    @Transactional
    public Rol crearRol(String nombre) throws Exception {
        try {
            Rol rol = new Rol();
            rol.setNombre(nombre.toUpperCase());
            return rolRepositorio.save(rol);
        } catch (Exception excepcion) {
            throw new Exception("Algo ha sucedido y no se ha podido procesar la creación de Rol en la base de datos." +
                    "\n - Descripción del error: " + excepcion.getMessage());
        }
    }

    public boolean existeRol(String nombre) throws Exception {
        try {
            return (rolRepositorio.findByNombreIgnoreCase(nombre) != null) ? true : false;
        } catch (Exception excepcion) {
            throw new Exception("Algo ha sucedido y no se ha podido verificar la existencia de Rol en la base de datos." +
                    "\n - Descripción del error: " + excepcion.getMessage());
        }
    }

    public Rol validarFormularioYCrear(String nombre) throws Exception {
        if (this.existeRol(nombre)) {
            throw new Exception("Ya se ha registrado un Rol con el mismo nombre.");
        }
        return this.crearRol(nombre);
    }

}
