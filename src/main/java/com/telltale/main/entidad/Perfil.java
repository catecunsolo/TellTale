package com.telltale.main.entidad;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "perfil", schema = "telltale")
@EntityListeners(AuditingEntityListener.class)
public class Perfil implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_perfil;

    @Column(name = "nombre", nullable = false,  columnDefinition = "VARCHAR(50)")
    private String nombre;

    @Column(name = "apellido", columnDefinition = "VARCHAR(50)")
    private String apellido;

    @Column(name = "redes", columnDefinition = "VARCHAR(255)")
    private String redes;

    @OneToOne
    private Imagen avatar;

    @Column(name = "descripcion", columnDefinition = "VARCHAR(255)")
    private String descripcion;

    @ManyToOne
    private Categoria categoriaDelDia;

    @OneToOne
    private Usuario usuario;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDate fechaCreacion;

    @LastModifiedDate
    private LocalDate fechaUltModificacion;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean alta;

    @OneToMany(mappedBy = "perfil")
    private List<Historia> historias;

    @OneToMany
    private List<Historia> historiasFav;

    @OneToMany(mappedBy = "perfil")
    private List<Comentario> comentarios;

}
