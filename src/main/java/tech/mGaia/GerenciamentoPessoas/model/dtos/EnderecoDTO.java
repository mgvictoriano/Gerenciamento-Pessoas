package tech.mGaia.GerenciamentoPessoas.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDTO implements Serializable {

    private Long id;
    private String logradouro;
    private int numero;
    private String bairro;
    private String cidade;
    private String cep;

}
