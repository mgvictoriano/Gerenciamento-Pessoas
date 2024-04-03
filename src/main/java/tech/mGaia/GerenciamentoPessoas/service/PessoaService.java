package tech.mGaia.GerenciamentoPessoas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.mGaia.GerenciamentoPessoas.exceptions.EnderecoException;
import tech.mGaia.GerenciamentoPessoas.exceptions.PessoaException;
import tech.mGaia.GerenciamentoPessoas.model.dtos.PessoaDTO;
import tech.mGaia.GerenciamentoPessoas.model.entidades.Endereco;
import tech.mGaia.GerenciamentoPessoas.model.entidades.Pessoa;
import tech.mGaia.GerenciamentoPessoas.model.mapper.PessoaMapper;
import tech.mGaia.GerenciamentoPessoas.repository.PessoaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;
    private final PessoaMapper pessoaMapper = PessoaMapper.INSTANCE;


    public void criarPessoa(Pessoa pessoa) {
        pessoaRepository.save(pessoa);
    }

    public void atualizarPessoa(Long idPessoa, PessoaDTO pessoaDTO) {
        if (pessoaDTO == null) {
            throw new PessoaException.PessoaInvalidaException("PessoaDTO não pode ser nulo");
        }
        Optional<Pessoa> pessoaOptional = pessoaRepository.findById(idPessoa);
        if (pessoaOptional.isEmpty()) {
            throw new PessoaException.PessoaNaoEncontradaException(idPessoa);
        }
        Pessoa pessoa = pessoaMapper.toObject(pessoaDTO);
        pessoaRepository.save(pessoa);
    }

    public List<Pessoa> buscarTodos() {
        return pessoaRepository.findAll();
    }

    public PessoaDTO buscarPorId(Long idPessoa) {
        Optional<Pessoa> pessoaOptional = pessoaRepository.findById(idPessoa);
        if (pessoaOptional.isEmpty()) {
            throw new PessoaException.PessoaNaoEncontradaException(idPessoa);
        }
        return pessoaMapper.toDTO(pessoaOptional.get());
    }

    public void definirEnderecoPrincipal(Long idPessoa, Long idEnderecoPrincipal) {
        Pessoa pessoa = pessoaRepository.findById(idPessoa)
                .orElseThrow(() -> new PessoaException.PessoaNaoEncontradaException(idPessoa));

        Endereco enderecoPrincipal = pessoa.getEnderecos().stream()
                .filter(endereco -> endereco.getId().equals(idEnderecoPrincipal))
                .findFirst()
                .orElseThrow(() -> new EnderecoException.EnderecoNaoEncontradoException(idEnderecoPrincipal));

        pessoa.setIdEnderecoPrincipal(idEnderecoPrincipal);
        pessoaRepository.save(pessoa);
    }

    public void removerPorId(Long idPessoa) {
        Optional<Pessoa> pessoaOptional = pessoaRepository.findById(idPessoa);
        if (pessoaOptional.isEmpty()) {
            throw new PessoaException.PessoaNaoEncontradaException(idPessoa);
        }
        pessoaRepository.deleteById(idPessoa);
    }

}
