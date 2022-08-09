package com.livraria.gui.model.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.livraria.gui.model.Editora;
import com.livraria.gui.model.Livro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LivroDTO {

    @NotBlank
    private String nome;
    @NotBlank
    private String autor;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate lancamento;
    private Integer quantidade;
    private Editora editora;

    public LivroDTO(Livro livro){
        this.nome = livro.getNome();
        this.autor = livro.getAutor();
        this.lancamento = livro.getLancamento();
        this.quantidade = livro.getQuantidade();
        this.editora = livro.getEditora();

    }

}
