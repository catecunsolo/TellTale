package com.telltale.main.servicio;

import com.telltale.main.entidad.Rol;
import com.telltale.main.entidad.Usuario;
import com.telltale.main.excepcion.MiExcepcion;
import com.telltale.main.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicio implements UserDetailsService {

    private Usuario usuario;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private RolServicio rolServicio;

    private final String MENSAJE = "Este usuario no existe. %s";

    @Transactional
    public Usuario crearUsuario(String username, String email, String password, Rol rol) throws Exception {
        validar(username, email, password,rol);
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
    public void modificarUsuario(Integer id_usuario, String email, String password) throws Exception {
        validarEmail(email);
        validarPassword(password);
        try {
            usuario = buscarUsuarioPorId(id_usuario);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new Exception("Error--> Ocurrió un error al buscar usuario por id.");
        }
        usuario.setEmail(email);
        usuario.setPassword(password);
        usuarioRepositorio.save(usuario);
    }

    @Transactional
    public void modificarPassword(Integer id_usuario, String password) throws Exception{
        validarPassword(password);
        try {
            usuario = buscarUsuarioPorId(id_usuario);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new Exception("Error--> Ocurrió un error al buscar usuario por id.");
        }
        usuario.setPassword(password);
    }

    @Transactional
    public void altaUsuario(Integer id_usuario) {
        usuarioRepositorio.altaUsuario(id_usuario, true);
    }

    @Transactional
    public void bajaUsuario(Integer id_usuario) {
        usuarioRepositorio.altaUsuario(id_usuario, false);
    }

    @Transactional(readOnly = true)
    public List<Usuario> verTodosUsuario() {
        return usuarioRepositorio.findAll();
    }

    @Transactional(readOnly = true)
    public Usuario buscarUsuarioPorId(Integer id_usuario) {
        Optional<Usuario> usuarioOptional = usuarioRepositorio.findById(id_usuario);
        return usuarioOptional.orElse(null);
    }

    @Transactional(readOnly = true)
    public Usuario buscarUsuarioPorEmail(String email){
        Optional<Usuario>usuarioOptional=usuarioRepositorio.findByEmail(email);
        return usuarioOptional.orElse(null);
    }

/*    //ESTO ES UN SIMULACRO DE CAMBIO DE CLAVE. A DEFINIR.
    @Transactional
    public void recuperoPassword(String username, String email) throws Exception {
     try{
        usuario=buscarUsuarioPorEmail(email);
        if(usuario.getUsername().equals(username)&&usuario.getEmail().equals(email)){
            modificarPassword(usuario.getId_usuario(),);
        }
     }catch (Exception exception){
         throw new Exception("Error --> Error al buscar usuario por email al recuperar contraseña.");
     }
    }*/


    public void validar(String username, String email, String password,Rol rol) throws Exception {
        validarUsername(username);
        validarPassword(password);
        validarEmail(email);
        validarRol(rol);
    }

    //después hacer interfaz para evitar los métodos repetitivos
    public void validarUsername(String username) throws Exception {
        if (username == null) {
            throw new Exception("Error--> El nombre de usuario no puede estar vacío.");
        }
        if (username.length() > 16) {
            throw new Exception("Error--> El nombre de usuario no puede contener más de 16 carácteres.");
        } else {
            if (username.length() <= 7) {
                throw new Exception("Error--> El nombre de usuario debe contener al menos 8 caracteres.");
            }
        }
        if (usuarioRepositorio.existsUsuarioByUsername(username)) {
            throw new Exception("Error --> El nombre de usuario ya existe.");
        }
    }

    public void validarEmail(String email) throws Exception {
        if (email == null) {
            throw new Exception("Error--> El email no puede estar vacío.");
        }
    }

    public void validarPassword(String password) throws Exception {
        if (password == null) {
            throw new Exception("Error--> La contraseña no puede estar vacía.");
        }
        if (password.length() <= 7) {
            throw new Exception("Error--> La contraseña no puede contener menos de 8 carácteres.");
        }

        if (password.length() > 16) {
            throw new Exception("Error--> La contraseña no puede contener mas de 16 carácteres.");
        }
    }

    public void validarRol(Rol rol) throws Exception{
        if(rol == null){
            throw new Exception("Error--> El rol no puede estar vacío/nulo.");
        }
        rolServicio.existeRol(rol.getNombre());
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        usuario = new Usuario();
        if (username.contains("@")) {
            usuario = usuarioRepositorio.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(String.format(MENSAJE, username)));
        } else {
            usuario = usuarioRepositorio.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format(MENSAJE, username)));
        }
        GrantedAuthority autorizacion = new SimpleGrantedAuthority("ROL_" + usuario.getRol().getNombre());
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = requestAttributes.getRequest().getSession(true);
        session.setAttribute("id_usuario",usuario.getId_usuario());
        session.setAttribute("username",usuario.getUsername());
        session.setAttribute("email",usuario.getEmail());
        session.setAttribute("rol",usuario.getRol().getNombre());
        return new User(usuario.getUsername(), usuario.getPassword(), Collections.singletonList(autorizacion));
    }
    
    @Transactional(readOnly=true)
    public int cantidadDeUsuarios(){
        List<Usuario> usuarios = usuarioRepositorio.findAll();
        if (usuarios==null) {
            return 0;
        }else{
            return usuarios.size();
        }
    }
}
