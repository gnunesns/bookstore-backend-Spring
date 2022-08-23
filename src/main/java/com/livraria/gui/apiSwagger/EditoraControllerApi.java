package com.livraria.gui.apiSwagger;

import com.livraria.gui.model.DTO.EditoraDTO;
import com.livraria.gui.model.DTO.LivroDTO;
import com.livraria.gui.model.Editora;
import com.livraria.gui.model.Livro;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;

public interface EditoraControllerApi {


    @ApiOperation(value = "Publisher creation operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success publisher creation"),
            @ApiResponse(code = 400, message = "Missing required fields, wrong field range value or " +
                    "publisher already registered on system")
    })
    ResponseEntity<Editora> createPublisher(EditoraDTO editoraDTO);


    @ApiOperation(value = "Publisher update operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Publisher by user successfully updated"),
            @ApiResponse(code = 404, message = "Publisher not found error"),
            @ApiResponse(code = 400, message = "Missing required fields, wrong field range value " +
                    "or publisher already registered on system")
    })
    ResponseEntity<Object> updatePublisher(Long id, EditoraDTO editoraDTO);


    @ApiOperation(value = "Publisher find by id and user operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success publisher found"),
            @ApiResponse(code = 404, message = "Publisher not found error")
    })
    ResponseEntity<Object> findById(Long id);



    @ApiOperation(value = "Publisher delete operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Publisher by user successfully deleted"),
            @ApiResponse(code = 404, message = "Publisher not found error")
    })
    Object deletePublisher(Long id);
}
