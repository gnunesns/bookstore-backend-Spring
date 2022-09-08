package com.livraria.gui.apiSwagger;

import com.livraria.gui.model.Aluguel;
import com.livraria.gui.model.DTO.AluguelDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;

public interface AluguelControllerApi {


    @ApiOperation(value = "Renting creation operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success Renting creation"),
            @ApiResponse(code = 400, message = "Missing required fields, wrong field range value or " +
                    "Renting already registered on system")
    })
    ResponseEntity<AluguelDTO> createRenting(AluguelDTO aluguelDTO);


    @ApiOperation(value = "Renting find by id and user operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success Renting found"),
            @ApiResponse(code = 404, message = "Renting not found error")
    })
    ResponseEntity<Object> findById(Long id);


    @ApiOperation(value = "Renting delete operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Renting by user successfully deleted"),
            @ApiResponse(code = 404, message = "Renting not found error")
    })
    Object deleteRenting(Long id);


    @ApiOperation(value = "Renting devolution operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Renting by user successfully updated"),
            @ApiResponse(code = 404, message = "Renting not found error"),
            @ApiResponse(code = 400, message = "Missing required fields, wrong field range value " +
                    "or renting already registered on system")
    })
    ResponseEntity<Object> devolution(Long id, Aluguel aluguel);
}
