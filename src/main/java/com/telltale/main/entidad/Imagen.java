package com.telltale.main.entidad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "imagen", schema = "telltale")
@EntityListeners(AuditingEntityListener.class)
public class Imagen implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_imagen;

    @Column(name = "url", nullable = false, columnDefinition = "VARCHAR(255)")
    private String url;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDate fechaCreacion;

    @LastModifiedDate
    private LocalDate fechaUltModificacion;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean alta;

    @OneToMany(mappedBy = "portada" )
    private List<Historia> historias;

    @OneToMany(mappedBy = "avatar" )
    private List<Perfil> perfiles;

}