package com.livraria.gui.service.implement;

import com.livraria.gui.model.Aluguel;
import com.livraria.gui.model.Livro;
import com.livraria.gui.repository.AluguelRepository;
import com.livraria.gui.repository.LivroRepository;
import com.livraria.gui.service.AluguelService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AluguelImpl implements AluguelService {

    private final AluguelRepository aluguelRepository;
    private final LivroRepository livroRepository;

    public AluguelImpl(AluguelRepository aluguelRepository, LivroRepository livroRepository) {
        this.aluguelRepository = aluguelRepository;
        this.livroRepository = livroRepository;
    }


    @Override
    public void delete(Aluguel aluguel) {
        aluguelRepository.delete(aluguel);

    }

    @Override
    public Aluguel save(Aluguel aluguel) {
        Optional<Livro> livroOptional = livroRepository.findById(aluguel.getLivro().getId());
        Livro livro = livroOptional.get();

        if (aluguel.getDataPrevisao().isBefore(aluguel.getDataAluguel())){
           throw new RuntimeException("The forecast date cannot be greater than the rental date");
        } else {
            if (livro.getQuantidade() > 0){
                livro.setQuantidade(livro.getQuantidade() - 1);
                livro.setTotalAlugado(livro.getTotalAlugado() + 1);

                Livro livroSalvo = livroRepository.save(livro); // salva os dados de livro
                aluguel.setLivro(livroSalvo); // traz os dados de livro

                livroRepository.save(livro);
                return aluguelRepository.save(aluguel);

            } else {
                throw new RuntimeException("This book is not available for rent.");
            }
        }
    }

    @Override
    public Aluguel saveDevolution(Aluguel aluguel) {
        Optional<Livro> livroOptional = livroRepository.findById(aluguel.getLivro().getId()); //
        Livro livro = livroOptional.get();
        livro.setQuantidade(livro.getQuantidade() + 1);
        livro.setTotalAlugado(livro.getTotalAlugado() - 1);
        livroRepository.save(livro);

        return aluguelRepository.save(aluguel);
    }


    @Override
    public Optional<Aluguel> getId(Long id) {
        return aluguelRepository.findById(id);
    }


    @Override
    public Page<Aluguel> getAll(Pageable pageable) {
        return aluguelRepository.findAll(pageable);
    }

}
