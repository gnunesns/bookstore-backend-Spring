package com.livraria.gui.exception;

import javax.persistence.EntityExistsException;

public class DeleteExceptionPublisher extends EntityExistsException {

    public DeleteExceptionPublisher(){
        super("Erro ao deletar! Este editora está vinculada a um livro em Aluguel!");
    }
}
