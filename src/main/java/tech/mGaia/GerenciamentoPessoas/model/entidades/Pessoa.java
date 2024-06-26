package tech.mGaia.GerenciamentoPessoas.model.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pessoa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pessoa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true)
    private Long id;

    @Column(name = "NOME", nullable = false, length = 100)
    private String nome;

    @Column(name = "DATA_NASCIMENTO", columnDefinition = "DATE")
    private LocalDate dataNascimento;

    @Column(name = "EMAIL", length = 100)
    private String email;

    @Column(name = "INSCRICAO_FEDERAL", length = 20)
    private String inscricaoFederal;

    @OneToMany(mappedBy = "idPessoa", cascade = CascadeType.ALL)
    private List<Endereco> enderecos = new ArrayList<>();

    @Column(name = "ENDERECO_PRINCIPAL_ID")
    private Long idEnderecoPrincipal;

}





