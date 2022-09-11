package com.livraria.gui.service.implement;

import com.livraria.gui.exception.DeleteExceptionUser;
import com.livraria.gui.model.Aluguel;
import com.livraria.gui.model.Usuario;
import com.livraria.gui.model.enums.AluguelStatus;
import com.livraria.gui.repository.AluguelRepository;
import com.livraria.gui.repository.UsuarioRepository;
import com.livraria.gui.service.UsuarioService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;

import static com.livraria.gui.model.enums.AluguelStatus.DELAYED;
import static com.livraria.gui.model.enums.AluguelStatus.valueOf;


@Service
public class UsuarioImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final AluguelRepository aluguelRepository;

    public UsuarioImpl(UsuarioRepository usuarioRepository, AluguelRepository aluguelRepository) {
        this.usuarioRepository = usuarioRepository;
        this.aluguelRepository = aluguelRepository;
    }

    @Override
    public void delete(Usuario usuario) {
        /*
        if(!usuario.getAlugueis().isEmpty()){
            throw new DeleteExceptionUser();
        }
         */
        usuario.getAlugueis().forEach(aluguel ->{
            if (aluguel.getStatus() == AluguelStatus.IN_PROGRESS){
                throw new DeleteExceptionUser();
            }
        });
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
