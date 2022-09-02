package com.livraria.gui.service.implement;

import com.livraria.gui.model.Usuario;
import com.livraria.gui.repository.UsuarioRepository;
import com.livraria.gui.service.UsuarioService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UsuarioImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void delete(Usuario usuario) {
        usuarioRepository.delete(usuario);
    }

    @Override
    public Usuario save(Usuario user) {
        return usuarioRepository.save(user);
    }

    @Override
    public Optional<Usuario> getId(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Page<Usuario> getAll(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }

}
