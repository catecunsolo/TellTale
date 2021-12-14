package com.telltale.main.entidad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.*;


@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categoria", schema = "telltale")
@EntityListeners(AuditingEntityListener.class)
public class Categoria implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_categoria;

    @Column(name = "nombre", nullable = false, unique = true, columnDefinition = "VARCHAR(20)")
    private String nombre;

    @Column(name = "voto", nullable = false, columnDefinition = "INT")
    private Integer voto;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDate fechaCreacion;

    @LastModifiedDate
    private LocalDate fechaUltModificacion;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean alta;

    @OneToMany(mappedBy = "categoria" )
    private List <Historia> historias;

}
