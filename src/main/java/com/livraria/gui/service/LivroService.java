package com.livraria.gui.service;

import com.livraria.gui.model.Livro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface LivroService {


    void delete(Livro livro);

    Livro save(Livro livro);

    Optional<Livro> getId(Long id);

    Page<Livro> getAll(Pageable pageable);

    List<Livro> mostRented();



}
