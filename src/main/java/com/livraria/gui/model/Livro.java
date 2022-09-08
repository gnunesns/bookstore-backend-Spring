package com.livraria.gui.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.livraria.gui.auditable.Auditable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "livro")
public class Livro extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 60)
    @Column(nullable = false, length = 100)
    private String nome;

    @NotBlank
    @Size(min = 3, max = 25)
    @Column(nullable = false, length = 100)
    private String autor;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Column(nullable = false)
    private LocalDate lancamento;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    private Integer totalAlugado;

    // para não vir os dados de livros junto
    //@JsonBackReference
    @JsonIgnoreProperties("livros")
    @OneToMany(mappedBy = "livro", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Aluguel> alugueis;

    @JsonIgnoreProperties("livros")
    @ManyToOne
    @JoinColumn(name = "editora")
    private Editora editora;


}
