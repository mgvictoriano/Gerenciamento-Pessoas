package tech.mGaia.GerenciamentoPessoas.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

@Data
@EqualsAndHashCode(callSuper = true)
public class EnderecoException extends PessoaException {

    public EnderecoException() {
        super(); }

    public EnderecoException(HttpStatusCode code, String message) {
        super(code, message);
    }

    public EnderecoException(HttpStatusCode code, String message, String description) {
        super(code, message, description);
    }

    public static class EnderecoNaoEncontradoException extends EnderecoException {
        public EnderecoNaoEncontradoException(Long id) {
            super(HttpStatus.NOT_FOUND, "Endereço com ID " + id + " não encontrado");
        }

        public EnderecoNaoEncontradoException(String cep) { // Nova exceção específica para CEP
            super(HttpStatus.NOT_FOUND, "Endereço com CEP " + cep + " não encontrado");
        }
    }
}

