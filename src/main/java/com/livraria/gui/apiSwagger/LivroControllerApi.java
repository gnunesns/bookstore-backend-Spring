package com.livraria.gui.apiSwagger;

import com.livraria.gui.model.DTO.LivroDTO;
import com.livraria.gui.model.Livro;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;

public interface LivroControllerApi {


    @ApiOperation(value = "Book creation operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success Book creation"),
            @ApiResponse(code = 400, message = "Missing required fields, wrong field range value or " +
                    "book already registered on system")
    })
    ResponseEntity<Livro> createBook(LivroDTO livroDTO);


    @ApiOperation(value = "Book update operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Book by user successfully updated"),
            @ApiResponse(code = 404, message = "Book not found error"),
            @ApiResponse(code = 400, message = "Missing required fields, wrong field range value " +
                    "or book already registered on system")
    })
    ResponseEntity<Object> updateBook(Long id, LivroDTO livroDTO);


    @ApiOperation(value = "Book find by id and user operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success book found"),
            @ApiResponse(code = 404, message = "Book not found error")
    })
    ResponseEntity<Object> findById(Long id);



    @ApiOperation(value = "Book delete operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Book by user successfully deleted"),
            @ApiResponse(code = 404, message = "Book not found error")
    })
    Object deleteBook(Long id);
}
