package com.livraria.gui.service;

import com.livraria.gui.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UsuarioService {


    void delete(Usuario usuario);

    Usuario save(Usuario user);

    Optional<Usuario> getId(Long id);

    Page<Usuario> getAll(Pageable pageable);


}
