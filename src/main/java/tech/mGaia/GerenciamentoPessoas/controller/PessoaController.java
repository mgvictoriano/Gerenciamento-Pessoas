package tech.mGaia.GerenciamentoPessoas.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.mGaia.GerenciamentoPessoas.exceptions.PessoaException;
import tech.mGaia.GerenciamentoPessoas.model.dtos.PessoaDTO;
import tech.mGaia.GerenciamentoPessoas.model.entidades.Pessoa;
import tech.mGaia.GerenciamentoPessoas.service.PessoaService;

import java.util.List;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @PostMapping
    @Operation(
            summary = "Criar Pessoa",
            description = "Este endpoint permite criar uma nova pessoa."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoa criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    })
    public ResponseEntity<?> criarPessoa(@RequestBody Pessoa pessoa) {
        try {
            pessoaService.criarPessoa(pessoa);
            return ResponseEntity.ok().build();
        } catch (PessoaException.PessoaInvalidaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    @Operation(
            summary = "Buscar todas as Pessoas",
            description = "Este endpoint permite buscar todas as pessoas cadastradas."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoas encontradas com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    })
    public ResponseEntity<List<Pessoa>> buscarTodos() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(pessoaService.buscarTodos());
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar Pessoa por ID",
            description = "Este endpoint permite buscar uma pessoa cadastrada pelo seu ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoa encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    })
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            PessoaDTO pessoaDTO = pessoaService.buscarPorId(id);
            return ResponseEntity.ok().body(pessoaDTO);
        } catch (PessoaException.PessoaNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Atualizar Pessoa",
            description = "Este endpoint permite atualizar os dados de uma pessoa existente pelo seu ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoa atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    })
    public ResponseEntity<?> atualizarPessoa(@PathVariable Long id,
                                             @RequestBody PessoaDTO pessoaDTO) {
        try {
            pessoaService.atualizarPessoa(id, pessoaDTO);
            return ResponseEntity.ok().build();
        } catch (PessoaException.PessoaNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        } catch (PessoaException.PessoaInvalidaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{idPessoa}/enderecoPrincipal/{idEnderecoPrincipal}")
    @Operation(
            summary = "Definir Endereço Principal",
            description = "Este endpoint permite definir o endereço principal de uma pessoa pelo ID da pessoa e ID do endereço."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Endereço principal definido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pessoa ou endereço não encontrado", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    })
    public ResponseEntity<Void> definirEnderecoPrincipal(
            @PathVariable Long idPessoa,
            @PathVariable Long idEnderecoPrincipal) {
        pessoaService.definirEnderecoPrincipal(idPessoa, idEnderecoPrincipal);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Remover Pessoa por ID",
            description = "Este endpoint permite remover uma pessoa existente pelo seu ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Pessoa removida com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    })
    public ResponseEntity<?> removerPorId(@PathVariable Long id) {
        try {
            pessoaService.removerPorId(id);
            return ResponseEntity.accepted().build();
        } catch (PessoaException.PessoaNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
