package tech.mGaia.GerenciamentoPessoas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.mGaia.GerenciamentoPessoas.exceptions.EnderecoException;
import tech.mGaia.GerenciamentoPessoas.model.dtos.EnderecoDTO;
import tech.mGaia.GerenciamentoPessoas.model.entidades.Endereco;
import tech.mGaia.GerenciamentoPessoas.service.EnderecoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @PostMapping
    @Operation(
            summary = "Inserir Endereço",
            description = "Este endpoint permite inserir um novo endereço."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço inserido com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content)
    })
    public ResponseEntity<?> inserirEndereco(@RequestBody Endereco endereco) {
        try {
            enderecoService.inserirEndereco(endereco);
            return ResponseEntity.ok().build();
        } catch (EnderecoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Atualizar Endereço",
            description = "Este endpoint permite atualizar um endereço existente."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content),
            @ApiResponse(responseCode = "404", description = "Endereço não encontrado", content = @Content)
    })
    public ResponseEntity<EnderecoDTO> atualizarEndereco(@PathVariable Long id,
                                                         @RequestBody EnderecoDTO enderecoDTO) {
        try {
            EnderecoDTO enderecoAtualizado = enderecoService.atualizarEndereco(id, enderecoDTO);
            return ResponseEntity.ok(enderecoAtualizado);
        } catch (EnderecoException ex) {
            return ResponseEntity.status(ex.getCode()).build();
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar Endereço por ID",
            description = "Este endpoint permite buscar um endereço pelo seu ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço encontrado com sucesso", content = @Content(schema = @Schema(implementation = EnderecoDTO.class))),
            @ApiResponse(responseCode = "404", description = "Endereço não encontrado", content = @Content)
    })
    public ResponseEntity<?> buscarEnderecoPorId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(enderecoService.buscarEnderecoPorId(id));
    }

    @GetMapping("/por-cep/{cep}")
    @Operation(
            summary = "Buscar Endereço por CEP",
            description = "Este endpoint permite buscar um endereço pelo seu CEP."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço encontrado com sucesso", content = @Content(schema = @Schema(implementation = EnderecoDTO.class)))
    })
    public EnderecoDTO buscarEnderecoPorCep(@PathVariable String cep) {
        return enderecoService.buscarEnderecoPorCep(cep);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Remover Endereço por ID",
            description = "Este endpoint permite remover um endereço pelo seu ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Endereço removido com sucesso")
    })
    public ResponseEntity<Void> removerEnderecoPorId(@PathVariable Long id) {
        enderecoService.removerEnderecoPorId(id);
        return ResponseEntity.noContent().build();
    }
}
