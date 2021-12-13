/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.telltale.main.entidad;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 *
 * @author silvia
 */
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Historia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_historia;
    @Column(nullable = false)
    private String titulo;
    //Imagen portada
    @Column(nullable = false,columnDefinition = "text")
    private String historia;
    private Integer meGusta;
    private Integer noMeGusta;
    @ManyToOne
    @JoinColumn(nullable=false)
    private Categoria categoria;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Perfil perfil;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDate fechaCreacion;
    @LastModifiedDate
    private LocalDate fechaUltModificacion;
    @Column(columnDefinition = "TINYINT(1)")
    private Boolean alta;

   
    
    public Historia() {
    }
  
 
}
