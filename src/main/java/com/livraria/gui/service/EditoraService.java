package com.livraria.gui.service;


import com.livraria.gui.model.Editora;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface EditoraService {


    void delete(Editora editora);

    Editora save(Editora editora);

    Optional<Editora> getId(Long id);

    Page<Editora> getAll(Pageable pageable);

}
