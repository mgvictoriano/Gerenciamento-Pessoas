package tech.mGaia.GerenciamentoPessoas.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

@Data
@EqualsAndHashCode(callSuper = true)
public class PessoaException extends RuntimeException {

    private HttpStatusCode code;
    private String message;
    private String description;

    public PessoaException(HttpStatusCode code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public PessoaException(HttpStatusCode code, String message, String description) {
        super(description);
        this.code = code;
        this.message = message;
        this.description = description;
    }

    public PessoaException() {

    }

    public ResponseEntity<Object> toHttpResponse() {
        return ResponseEntity
                .status(code)
                .body(new ErrorResponse(message, description));
    }

    private static class ErrorResponse {
        public ErrorResponse(String message, String description) {
        }
    }

    public static class PessoaNaoEncontradaException extends PessoaException {
        public PessoaNaoEncontradaException(Long idPessoa) {
            super(HttpStatus.NOT_FOUND, "Pessoa com ID " + idPessoa + " não encontrada");
        }
    }

    public static class PessoaInvalidaException extends PessoaException {
        public PessoaInvalidaException(String message) {
            super(HttpStatus.BAD_REQUEST, message);
        }
    }
}
