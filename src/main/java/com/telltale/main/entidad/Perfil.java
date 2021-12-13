package com.telltale.main.entidad;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_perfil;
    
    @Column(nullable = false)
    private String nombre;
    
    @Column(nullable = false)
    private String apellido;
    
    @Column(nullable = false)
    private String descripcion;
    
    @ManyToOne
    private Categoria categoriaDelDia;
    @OneToOne
    private Usuario usuario;
    
    @LastModifiedDate
    private LocalDate fechaModificacion;
    
    @OneToMany(mappedBy = "perfil")
    private List<Historia> historias;

    public Integer getId_perfil() {
        return id_perfil;
    }

    public void setId_perfil(Integer id_perfil) {
        this.id_perfil = id_perfil;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Categoria getCategoriaDelDia() {
        return categoriaDelDia;
    }

    public void setCategoriaDelDia(Categoria categoriaDelDia) {
        this.categoriaDelDia = categoriaDelDia;
    }
    

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDate getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(LocalDate fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public List<Historia> getHistorias() {
        return historias;
    }

    public void setHistorias(List<Historia> historias) {
        this.historias = historias;
    }

    public Perfil() {
    }

    public Perfil(String nombre, String apellido, String descripcion, Boolean alta, Usuario usuario, LocalDate fechaModificacion, List<Historia> historias) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.descripcion = descripcion;
        this.usuario = usuario;
        this.fechaModificacion = fechaModificacion;
        this.historias = historias;
    }

   
}
