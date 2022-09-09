package com.livraria.gui.exception;

import javax.persistence.EntityExistsException;

public class DeleteExceptionUser extends EntityExistsException {

    public DeleteExceptionUser(){
        super("Erro ao deletar! Este usuário está vinculado a um Aluguel!");
    }
}
