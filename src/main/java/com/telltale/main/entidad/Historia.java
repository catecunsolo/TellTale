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
@Table(name = "historia", schema = "telltale")
@EntityListeners(AuditingEntityListener.class)
public class Historia  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_historia;

    @Column(nullable = false)
    private String titulo;

    @OneToOne
    private Imagen portada;

    @Column(nullable = false,columnDefinition = "text")
    private String historia;

    @Column(name = "meGusta", nullable = false, columnDefinition = "INT")
    private Integer meGusta;

    @Column(name = "noMeGusta", nullable = false, columnDefinition = "INT")
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

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean alta;

    @OneToMany(mappedBy = "historia")
    private List<HistoriaFavorita> historiasFav;

    @OneToMany(mappedBy = "historia")
    private List<Comentario> comentarios;

}
