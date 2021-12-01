package com.telltale.main.entidad;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_usuario;
    @Column(nullable = false,unique = true)
    private String username;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDate fechaCreacion;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDate fechaUltModificacion;
    @Column(columnDefinition = "TINYINT(1)")
    private Boolean alta;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Rol rol;

    public Usuario() {
    }

    public Usuario(String username, String email,String password, LocalDate fechaCreacion, LocalDate fechaUltModificacion, Boolean alta, Rol rol) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.fechaCreacion = fechaCreacion;
        this.fechaUltModificacion = fechaUltModificacion;
        this.alta = alta;
        this.rol = rol;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDate getFechaUltModificacion() {
        return fechaUltModificacion;
    }

    public void setFechaUltModificacion(LocalDate fechaUltModificacion) {
        this.fechaUltModificacion = fechaUltModificacion;
    }

    public Boolean getAlta() {
        return alta;
    }

    public void setAlta(Boolean alta) {
        this.alta = alta;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}
