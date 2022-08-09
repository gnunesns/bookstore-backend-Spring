package com.livraria.gui.exception.errors;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MensagemDeErro {

    private String titulo;
    private Integer status;
    private String mensagem;
}
