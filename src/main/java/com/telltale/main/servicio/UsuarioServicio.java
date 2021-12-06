package com.telltale.main.servicio;

import com.telltale.main.entidad.Rol;
import com.telltale.main.entidad.Usuario;
import com.telltale.main.excepcion.MiExcepcion;
import com.telltale.main.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicio {

    private Usuario usuario;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Transactional
    public Usuario crearUsuario(String username, String email, String password, Rol rol) throws MiExcepcion {
        validar(username, email, password);
        usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setEmail(email);
        usuario.setPassword(encoder.encode(password));
        usuario.setRol(rol);
        usuario.setFechaCreacion(LocalDate.now());
        usuario.setFechaUltModificacion(LocalDate.now());
        usuario.setAlta(true);
        return usuarioRepositorio.save(usuario);
    }

    @Transactional
    public void modificarUsuario(Integer id_usuario, String email, String password) throws MiExcepcion {
        validarEmail(email);
        validarPassword(password);
        try {
        usuario = buscarUsuarioPorId(id_usuario);
        }catch(Exception exception){
            exception.printStackTrace();
            throw new MiExcepcion("Error--> Ocurrió un error al buscar usuario por id.");
    }
usuario.setEmail(email);
usuario.setPassword(password);
usuarioRepositorio.save(usuario);
    }

    @Transactional
    public void altaUsuario(Integer id_usuario){usuarioRepositorio.altaUsuario(id_usuario,true);
    }

    @Transactional
    public void bajaUsuario(Integer id_usuario){usuarioRepositorio.altaUsuario(id_usuario,false);
    }

    @Transactional(readOnly = true)
    public List<Usuario> buscarUsuarioTodos(){return usuarioRepositorio.findAll();
    }

    @Transactional(readOnly = true)
    public Usuario buscarUsuarioPorId(Integer id_usuario){
        Optional<Usuario>usuarioOptional=usuarioRepositorio.findById(id_usuario);
        return usuarioOptional.orElse(null);
    }

    public void validar(String username, String email, String password) throws MiExcepcion {
        validarUsername(username);
        validarPassword(password);
        validarEmail(email);
    }

    //después hacer interfaz para evitar los métodos repetitivos
    public void validarUsername(String username) throws MiExcepcion {
        if (username == null) {
            throw new MiExcepcion("Error--> El nombre de usuario no puede estar vacío.");
        }
        if (username.length() > 12) {
            throw new MiExcepcion("Error--> El nombre de usuario no puede contener más de 12 carácteres.");
        } else {
            if (username.length() < 7) {
                throw new MiExcepcion("Error--> El nombre de usuario debe contener al menos 8 caracteres.");
            }
        }
    }

    public void validarEmail(String email) throws MiExcepcion {
        if (email == null) {
            throw new MiExcepcion("Error--> El email no puede estar vacío.");
        }
    }

    public void validarPassword(String password) throws MiExcepcion {
        if (password == null) {
            throw new MiExcepcion("Error--> La contraseña no puede estar vacía.");
        }
        if (password.length() <7) {
            throw new MiExcepcion("Error--> La contraseña no puede contener menos de 8 carácteres.");
        }
    }
}
