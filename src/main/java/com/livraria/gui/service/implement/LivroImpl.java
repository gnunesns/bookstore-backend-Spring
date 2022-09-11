package com.livraria.gui.service.implement;

import com.livraria.gui.exception.DeleteExceptionBooks;
import com.livraria.gui.exception.DeleteExceptionUser;
import com.livraria.gui.model.Livro;
import com.livraria.gui.model.enums.AluguelStatus;
import com.livraria.gui.repository.LivroRepository;
import com.livraria.gui.service.LivroService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroImpl implements LivroService {

    private final LivroRepository livroRepository;

    public LivroImpl(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    @Override
    public void delete(Livro livro) {
        /*
        if (!livro.getAlugueis().isEmpty()){
            throw new DeleteExceptionBooks();
        }
         */
        livro.getAlugueis().forEach(aluguel -> {
            if (aluguel.getStatus() == AluguelStatus.IN_PROGRESS){
                throw new DeleteExceptionUser();
            }
        });

        livroRepository.delete(livro);
    }

    @Override
    public Livro save(Livro livro) {
        return livroRepository.save(livro);
    }

    @Override
    public Optional<Livro> getId(Long id) {
        return livroRepository.findById(id);
    }

    @Override
    public Page<Livro> getAll(Pageable pageable) {
        return livroRepository.findAll(pageable);
    }


    @Override
    public List<Livro> mostRented() {
        List<Livro> livros = livroRepository.findAll();
        livros.sort(this::getAllmostRented);
        return livros;
    }

    private Integer getAllmostRented(Livro livro1, Livro livro2){
        return livro1.getTotalAlugado().compareTo(livro2.getTotalAlugado());
    }

}
