package com.livraria.gui.model.DTO;

import com.livraria.gui.model.Editora;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditoraDTO {


    @NotBlank
    private String nomeEditora;
    @NotBlank
    private String cidade;

    public EditoraDTO(Editora editora){
        this.nomeEditora = editora.getNomeEditora();
        this.cidade = editora.getCidade();
    }

}
