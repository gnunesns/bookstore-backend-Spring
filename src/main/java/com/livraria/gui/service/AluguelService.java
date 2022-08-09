package com.livraria.gui.service;



import com.livraria.gui.model.Aluguel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AluguelService {

    void delete(Aluguel aluguel);

    Aluguel save(Aluguel aluguel);

    Aluguel saveDevolution(Aluguel aluguel);

    Optional<Aluguel> getId(Long id);

    Page<Aluguel> getAll(Pageable pageable);

}
