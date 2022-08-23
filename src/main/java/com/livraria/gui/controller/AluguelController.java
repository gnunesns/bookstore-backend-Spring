package com.livraria.gui.controller;


import com.livraria.gui.apiSwagger.AluguelControllerApi;
import com.livraria.gui.model.Aluguel;
import com.livraria.gui.model.DTO.AluguelDTO;
import com.livraria.gui.model.enums.AluguelStatus;
import com.livraria.gui.repository.LivroRepository;
import com.livraria.gui.service.AluguelService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/renting")
public class AluguelController implements AluguelControllerApi {


    private final AluguelService aluguelService;
    private final LivroRepository livroRepository;

    public AluguelController(AluguelService aluguelService, LivroRepository livroRepository) {
        this.aluguelService = aluguelService;
        this.livroRepository = livroRepository;
    }


    @PostMapping()
    public ResponseEntity<AluguelDTO> createRenting(@RequestBody @Valid AluguelDTO aluguelDTO){

            Aluguel aluguel = new Aluguel();
            BeanUtils.copyProperties(aluguelDTO, aluguel);
            aluguel.setDataAluguel(LocalDate.now(ZoneId.of("UTC"))); // data do aluguel
            aluguel.setLocalDateTime(LocalDateTime.now(ZoneId.of("UTC")));

            aluguel.setStatus(AluguelStatus.IN_PROGRESS);
            Aluguel aluguelCadastrado = aluguelService.save(aluguel);
            AluguelDTO aluguelCadastradoDTO = new AluguelDTO();
            BeanUtils.copyProperties(aluguelCadastrado, aluguelCadastradoDTO);

            return ResponseEntity.status(HttpStatus.CREATED).body(aluguelCadastradoDTO);

    }


    @GetMapping()
    public ResponseEntity<Page<Aluguel>> getAllRenting(@PageableDefault(page = 0, size = 10, sort = "id",
            direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(aluguelService.getAll(pageable));

    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") Long id){
        Optional<Aluguel> aluguelOptional = aluguelService.getId(id);
        if (!aluguelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Renting not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(aluguelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRenting(@PathVariable Long id){
        Optional<Aluguel> aluguelOptional = aluguelService.getId(id);
        if (!aluguelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Renting not found");
        }
        aluguelService.delete(aluguelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Renting deleted sucessfully.");

    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> devolution(@PathVariable("id") Long id,
                                                 @RequestBody @Valid Aluguel aluguel){
        Optional<Aluguel> aluguelOptional = aluguelService.getId(id);
        Aluguel aluguel1 = aluguelOptional.get();
        aluguel1.setLastModifiedDate(LocalDateTime.now(ZoneId.of("UTC")));
        aluguel1.setId(aluguelOptional.get().getId());
        aluguel1.setDataDevolucao(aluguel.getDataDevolucao());

        if (aluguel1.getDataDevolucao().isAfter(aluguel1.getDataPrevisao())){
            aluguel1.setStatus(AluguelStatus.DELAYED);
        } else {
            aluguel1.setStatus(AluguelStatus.ON_TIME);
        }

        return ResponseEntity.status(HttpStatus.OK).body(aluguelService.saveDevolution(aluguel1));

    }

}
