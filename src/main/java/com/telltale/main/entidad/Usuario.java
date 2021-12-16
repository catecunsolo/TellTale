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

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuario", schema = "telltale")
@EntityListeners(AuditingEntityListener.class)
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_usuario;

    @Column(name = "username", nullable = false, unique = true, columnDefinition = "VARCHAR(20)")
    private String username;

    @Column(name = "email", nullable = false, unique = true, columnDefinition = "VARCHAR(50)")
    private String email;

    @Column(name = "password", nullable = false, columnDefinition = "VARCHAR(255)")
    private String password;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDate fechaCreacion;

    @LastModifiedDate
    private LocalDate fechaUltModificacion;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean alta;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Rol rol;

}
