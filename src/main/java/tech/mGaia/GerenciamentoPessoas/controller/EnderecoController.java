package tech.mGaia.GerenciamentoPessoas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.mGaia.GerenciamentoPessoas.exceptions.EnderecoException;
import tech.mGaia.GerenciamentoPessoas.model.dtos.EnderecoDTO;
import tech.mGaia.GerenciamentoPessoas.model.entidades.Endereco;
import tech.mGaia.GerenciamentoPessoas.service.EnderecoService;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @PostMapping
    public ResponseEntity<?> inserirEndereco(@RequestBody Endereco endereco) {
        try {
            enderecoService.inserirEndereco(endereco);
            return ResponseEntity.ok().build();
        } catch (EnderecoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
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
    public ResponseEntity<?> buscarEnderecoPorId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(enderecoService.buscarEnderecoPorId(id));
    }

    @GetMapping("/por-cep/{cep}")
    public EnderecoDTO buscarEnderecoPorCep(@PathVariable String cep) {
        return enderecoService.buscarEnderecoPorCep(cep);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerEnderecoPorId(@PathVariable Long id) {
        enderecoService.removerEnderecoPorId(id);
        return ResponseEntity.noContent().build();
    }
}
