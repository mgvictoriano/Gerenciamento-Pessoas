package tech.mGaia.GerenciamentoPessoas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.mGaia.GerenciamentoPessoas.model.entidades.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>  {


}
