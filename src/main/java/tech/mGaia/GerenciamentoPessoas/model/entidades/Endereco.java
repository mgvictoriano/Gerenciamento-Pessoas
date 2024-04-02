package tech.mGaia.GerenciamentoPessoas.model.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "endereco")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true)
    private Long id;

    @Column(name = "LOGRADOURO", length = 100, nullable = false)
    private String logradouro;

    @Column(name = "NUMERO", length = 15)
    private String numero;

    @Column(name = "BAIRRO", length = 100)
    private String bairro;

    @Column(name = "CIDADE", length = 150)
    private String cidade;

    @Column(name = "CEP", length = 10)
    private String cep;

}
