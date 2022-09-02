package com.livraria.gui.controller;

import com.livraria.gui.apiSwagger.EditoraControllerApi;
import com.livraria.gui.model.DTO.EditoraDTO;
import com.livraria.gui.model.Editora;
import com.livraria.gui.service.EditoraService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/publisher")
public class EditoraController implements EditoraControllerApi {


    private final EditoraService editoraService;

    public EditoraController(EditoraService editoraService) {
        this.editoraService = editoraService;
    }

    @PostMapping()
    public ResponseEntity<Editora> createPublisher(@RequestBody @Valid EditoraDTO editoraDTO){
        Editora editora = new Editora();
        BeanUtils.copyProperties(editoraDTO, editora);
        editora.setLocalDateTime(LocalDateTime.now(ZoneId.of("GMT-3")));
        return ResponseEntity.status(HttpStatus.CREATED).body(editoraService.save(editora));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePublisher(@PathVariable("id") Long id,
                                             @RequestBody @Valid EditoraDTO editoraDTO){

        Optional<Editora> editoraOptional = editoraService.getId(id);
        if (!editoraOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("publisher not found");
        }
        Editora editora = new Editora();
        BeanUtils.copyProperties(editoraDTO, editora);
        editora.setLastModifiedDate(LocalDateTime.now(ZoneId.of("GMT-3")));
        editora.setCodigoEditora(editoraOptional.get().getCodigoEditora());
        editora.setLocalDateTime(editoraOptional.get().getLocalDateTime());
        return ResponseEntity.status(HttpStatus.OK).body(editoraService.save(editora));
    }

    @GetMapping()
    public ResponseEntity<Page<Editora>> getAllPublisher(@PageableDefault Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(editoraService.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") Long id){
        Optional<Editora> editoraOptional = editoraService.getId(id);
        if (!editoraOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("publisher  not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(editoraOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePublisher(@PathVariable Long id){
        Optional<Editora> editoraOptional = editoraService.getId(id);
        if (!editoraOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("publisher not found");
        }
        editoraService.delete(editoraOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("publisher deleted sucessfully.");

    }

}
