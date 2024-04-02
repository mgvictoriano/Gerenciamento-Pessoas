package tech.mGaia.GerenciamentoPessoas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.mGaia.GerenciamentoPessoas.model.entidades.Endereco;

import java.util.Optional;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    Optional<Endereco> findByCep(String cep);


}
