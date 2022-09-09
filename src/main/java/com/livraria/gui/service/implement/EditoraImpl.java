package com.livraria.gui.service.implement;

import com.livraria.gui.exception.DeleteExceptionPublisher;
import com.livraria.gui.exception.DeleteExceptionUser;
import com.livraria.gui.model.Editora;
import com.livraria.gui.repository.EditoraRepository;
import com.livraria.gui.service.EditoraService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.websocket.DecodeException;
import java.util.Optional;

@Service
public class EditoraImpl implements EditoraService {

    private final EditoraRepository editoraRepository;

    public EditoraImpl(EditoraRepository editoraRepository) {
        this.editoraRepository = editoraRepository;
    }

    @Override
    public void delete(Editora editora) {

        editora.getLivros().forEach((livro -> {
            if (!livro.getAlugueis().isEmpty()){
                throw new DeleteExceptionPublisher();
            }
        }));

        editoraRepository.delete(editora);
    }


    @Override
    public Editora save(Editora editora) {
        return editoraRepository.save(editora);
    }

    @Override
    public Optional<Editora> getId(Long id) {
        return editoraRepository.findById(id);
    }

    // paginação
    @Override
    public Page<Editora> getAll(Pageable pageable) {
        return editoraRepository.findAll(pageable);
    }

}
