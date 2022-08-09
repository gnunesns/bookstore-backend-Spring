package com.livraria.gui.handlers;

import com.livraria.gui.exception.errors.MensagemDeErro;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestHandlerException {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<MensagemDeErro> erroDataAluguel(RuntimeException exception) {
        var mensagem = new MensagemDeErro("Erro no aluguel", HttpStatus.NOT_ACCEPTABLE.value(),
                exception.getMessage());

        return new ResponseEntity<>(mensagem, HttpStatus.NOT_ACCEPTABLE);
    }



}
