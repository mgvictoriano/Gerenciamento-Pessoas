package tech.mGaia.GerenciamentoPessoas.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<?> criarPessoa(@RequestBody Pessoa pessoa) {
        try {
            pessoaService.criarPessoa(pessoa);
            return ResponseEntity.ok().build();
        } catch (PessoaException.PessoaInvalidaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public List<Pessoa> buscarTodos() {
        return pessoaService.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            PessoaDTO pessoaDTO = pessoaService.buscarPorId(id);
            return ResponseEntity.ok().body(pessoaDTO);
        } catch (PessoaException.PessoaNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
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
    public ResponseEntity<Void> definirEnderecoPrincipal(
            @PathVariable Long idPessoa,
            @PathVariable Long idEnderecoPrincipal) {
        pessoaService.definirEnderecoPrincipal(idPessoa, idEnderecoPrincipal);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removerPorId(@PathVariable Long id) {
        try {
            pessoaService.removerPorId(id);
            return ResponseEntity.accepted().build();
        } catch (PessoaException.PessoaNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
