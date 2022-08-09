package com.livraria.gui.model.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.livraria.gui.model.Aluguel;
import com.livraria.gui.model.Livro;
import com.livraria.gui.model.Usuario;
import com.livraria.gui.model.enums.AluguelStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AluguelDTO {


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataAluguel;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataPrevisao;

    private AluguelStatus status;

    private Livro livro;

    private Usuario usuario;


    public AluguelDTO(Aluguel aluguel){
        this.dataPrevisao = aluguel.getDataPrevisao();
        this.livro = aluguel.getLivro();
        this.usuario = aluguel.getUsuario();
    }


}
