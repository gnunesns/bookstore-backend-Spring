package com.livraria.gui.repository;

import com.livraria.gui.model.Editora;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EditoraRepository extends JpaRepository<Editora, Long> {

    Page<Editora> findAll(Pageable Pageable);
}
