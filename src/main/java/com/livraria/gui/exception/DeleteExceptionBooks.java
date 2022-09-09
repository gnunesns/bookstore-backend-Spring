package com.livraria.gui.exception;

import javax.persistence.EntityExistsException;

public class DeleteExceptionBooks extends EntityExistsException {

    public DeleteExceptionBooks(){
        super("Erro ao deletar! Este livro está vinculada a um Aluguel!");
    }
}
