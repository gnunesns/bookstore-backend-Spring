package com.livraria.gui.model.DTO;


import com.livraria.gui.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    @NotBlank
    private String nome;
    @NotBlank
    private String cidade;
    @NotBlank
    private String endereco;
    @NotBlank
    private String email;

    public UsuarioDTO(Usuario usuario){
        this.nome = usuario.getNome();
        this.cidade = usuario.getCidade();
        this.endereco = usuario.getEndereco();
        this.email = usuario.getEmail();
    }
}
