
package com.telltale.main.entidad;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Categoria {
    
   @Id  
   @GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id_categoria;
 
   @Column(nullable = false, unique = true)
private String nombre;
   
private Integer voto;

@OneToMany
private List <Historia> historias;

    public Categoria() {
    }

    public Categoria(Integer id_categoria, 
            String nombre, Integer voto, List<Historia> historias) {
        this.id_categoria = id_categoria;
        this.nombre = nombre;
        this.voto = voto;
        this.historias = historias;
    }

    public Integer getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(Integer id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getVoto() {
        return voto;
    }

    public void setVoto(Integer voto) {
        this.voto = voto;
    }

    public List<Historia> getHistorias() {
        return historias;
    }

    public void setHistorias(List<Historia> historias) {
        this.historias = historias;
    }

    

}
