package com.livraria.gui.model;


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
@Table(name = "editora")
public class Editora  extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigoEditora;

    @NotBlank
    @Size(min = 3, max = 80)
    @Column(nullable = false)
    private String nomeEditora;

    @NotBlank
    @Size(min = 3, max = 50)
    @Column(nullable = false)
    private String cidade;

    //@JsonIgnoreProperties("editora")
    @OneToMany(mappedBy = "editora", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Livro> livros;
}
