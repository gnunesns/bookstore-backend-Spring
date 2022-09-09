package com.livraria.gui.exception.errors;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MensagemDeErro {

    private Integer status;
    private String mensagem;

}
