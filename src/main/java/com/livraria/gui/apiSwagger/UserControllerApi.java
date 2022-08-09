package com.livraria.gui.apiSwagger;


import com.livraria.gui.model.DTO.UsuarioDTO;
import com.livraria.gui.model.Usuario;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;

import java.awt.print.Pageable;


@Api("Users module management")
public interface UserControllerApi {

    @ApiOperation(value = "User creation operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success User creation"),
            @ApiResponse(code = 400, message = "Missing required fields, wrong field range value or " +
                    "user already registered on system")
    })
    ResponseEntity<Usuario> createUser(UsuarioDTO usuarioDTO);


    @ApiOperation(value = "user update operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "user by user successfully updated"),
            @ApiResponse(code = 404, message = "user not found error"),
            @ApiResponse(code = 400, message = "Missing required fields, wrong field range value " +
                    "or user already registered on system")
    })
    ResponseEntity<Object> updateUser(Long id, UsuarioDTO usuarioDTO);


    /*
    @ApiOperation(value = "List all user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User list found ")
    })
    ResponseEntity<Page<Usuario>> getAllUser(java.awt.print.Pageable pageable);
    */


    @ApiOperation(value = "Book find by id and user operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success user found"),
            @ApiResponse(code = 404, message = "user not found error")
    })
    ResponseEntity<Object> findById(Long id);



    @ApiOperation(value = "User delete operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User by user successfully deleted"),
            @ApiResponse(code = 404, message = "User not found error")
    })
    Object deleteUser(Long id);

}
