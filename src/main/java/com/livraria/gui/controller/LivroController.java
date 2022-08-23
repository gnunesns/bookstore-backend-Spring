package com.livraria.gui.controller;


import com.livraria.gui.apiSwagger.LivroControllerApi;
import com.livraria.gui.model.DTO.LivroDTO;
import com.livraria.gui.model.Livro;
import com.livraria.gui.service.LivroService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/books")
public class LivroController implements LivroControllerApi {

    private final LivroService livroService;

    public LivroController(LivroService livroService){
        this.livroService = livroService;
    }


    @PostMapping()
    public ResponseEntity<Livro> createBook(@RequestBody @Valid LivroDTO livroDTO){
        Livro livro = new Livro();
        BeanUtils.copyProperties(livroDTO, livro);
        livro.setTotalAlugado(0);
        livro.setLocalDateTime(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(livroService.save(livro));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateBook(@PathVariable("id") Long id,
                                             @RequestBody @Valid LivroDTO livroDTO){

        Optional<Livro> livroOptional = livroService.getId(id);
        if (!livroOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
        }
        Livro livro = new Livro();
        BeanUtils.copyProperties(livroDTO, livro);
        livro.setLastModifiedDate(LocalDateTime.now(ZoneId.of("UTC")));
        livro.setId(livroOptional.get().getId());
        livro.setLocalDateTime(livroOptional.get().getLocalDateTime());

        return ResponseEntity.status(HttpStatus.OK).body(livroService.save(livro));
    }


    @GetMapping()
    public ResponseEntity<Page<Livro>> getAllBook(@PageableDefault(page = 0, size = 10, sort = "id",
            direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(livroService.getAll(pageable));

    }


    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") Long id){
        Optional<Livro> livroOptional = livroService.getId(id);
        return livroOptional.<ResponseEntity<Object>>map(livro -> ResponseEntity.status(HttpStatus.OK).body(livro)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found"));
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable Long id){
        Optional<Livro> livroOptional = livroService.getId(id);
        if (!livroOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
        }
        livroService.delete(livroOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Book deleted sucessfully.");

    }


    @GetMapping("/orderly")
    public ResponseEntity<List<Livro>> getAllBookMostRented(@PageableDefault(page = 0, size = 10, sort = "id",
            direction = Sort.Direction.ASC) Livro livro){
        return ResponseEntity.status(HttpStatus.OK).body(livroService.mostRented());

    }

}
