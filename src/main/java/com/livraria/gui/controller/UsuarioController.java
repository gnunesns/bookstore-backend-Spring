package com.livraria.gui.controller;

import com.livraria.gui.apiSwagger.UserControllerApi;
import com.livraria.gui.model.DTO.UsuarioDTO;
import com.livraria.gui.model.Usuario;
import com.livraria.gui.service.UsuarioService;
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
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
public class UsuarioController implements UserControllerApi {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    @PostMapping()
    public ResponseEntity<Usuario> createUser(@RequestBody @Valid UsuarioDTO usuarioDTO){
        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioDTO, usuario);
        usuario.setLocalDateTime(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario));
    }



    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable("id") Long id,
                                             @RequestBody @Valid UsuarioDTO usuarioDTO){

        Optional<Usuario> usuarioOptional = usuarioService.getId(id);
        if (!usuarioOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioDTO, usuario);
        usuario.setLastModifiedDate(LocalDateTime.now(ZoneId.of("UTC")));
        usuario.setId(usuarioOptional.get().getId());
        usuario.setLocalDateTime(usuarioOptional.get().getLocalDateTime());

        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.save(usuario));
    }

    @GetMapping()
    public ResponseEntity<Page<Usuario>> getAllUser(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC)
                                                        Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.getAll(pageable));
    }


    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") Long id){
        Optional<Usuario> usuarioOptional = usuarioService.getId(id);
        return usuarioOptional.<ResponseEntity<Object>>map(usuario -> ResponseEntity.status(HttpStatus.OK).body(usuario)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id){
        Optional<Usuario> usuarioOptional = usuarioService.getId(id);
        if (!usuarioOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        usuarioService.delete(usuarioOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("User deleted sucessfully.");

    }


}
