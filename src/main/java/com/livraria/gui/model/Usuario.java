package com.livraria.gui.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.livraria.gui.auditable.Auditable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 40)
    @Column(nullable = false)
    private String nome;

    @NotBlank
    @Size(min = 3, max = 20)
    @Column(nullable = false)
    private String cidade;

    @NotBlank
    @Size(min = 3, max = 25)
    @Column(nullable = false)
    private String endereco;

    @NotBlank
    @Column(nullable = false)
    private String email;

    @JsonIgnoreProperties("usuario") //
    //@JsonManagedReference
    //@JsonBackReference
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Aluguel> alugueis;

}
