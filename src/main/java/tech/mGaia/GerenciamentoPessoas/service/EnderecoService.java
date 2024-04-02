package tech.mGaia.GerenciamentoPessoas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.mGaia.GerenciamentoPessoas.exceptions.EnderecoException;
import tech.mGaia.GerenciamentoPessoas.model.dtos.EnderecoDTO;
import tech.mGaia.GerenciamentoPessoas.model.entidades.Endereco;
import tech.mGaia.GerenciamentoPessoas.model.mapper.EnderecoMapper;
import tech.mGaia.GerenciamentoPessoas.repository.EnderecoRepository;

import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;
    private final EnderecoMapper enderecoMapper = EnderecoMapper.INSTANCE;

    public void inserirEndereco(Endereco endereco) {
        enderecoRepository.save(endereco);
    }

    public EnderecoDTO atualizarEndereco(Long id, EnderecoDTO enderecoDTO) {
        Optional<Endereco> enderecoOptional = enderecoRepository.findById(id);
        if (enderecoOptional.isEmpty()) {
            throw new EnderecoException.EnderecoNaoEncontradoException(id);
        }

        Endereco existingEndereco = enderecoOptional.get();
        existingEndereco.setLogradouro(enderecoDTO.getLogradouro());
        existingEndereco.setNumero(enderecoDTO.getNumero());
        existingEndereco.setBairro(enderecoDTO.getBairro());
        existingEndereco.setCidade(enderecoDTO.getCidade());
        existingEndereco.setCep(enderecoDTO.getCep());

        Endereco enderecoSalvo = enderecoRepository.save(existingEndereco);

        return enderecoMapper.toDTO(enderecoSalvo);
    }

    public EnderecoDTO buscarEnderecoPorId(Long id) {
        Optional<Endereco> enderecoOptional = enderecoRepository.findById(id);
        if (enderecoOptional.isEmpty()) {
            throw new EnderecoException.EnderecoNaoEncontradoException(id);
        }
        return enderecoMapper.toDTO(enderecoOptional.get());
    }

    public EnderecoDTO buscarEnderecoPorCep(String cep) {
        Optional<Endereco> enderecoOptional = enderecoRepository.findByCep(cep);
        if (enderecoOptional.isEmpty()) {
            throw new EnderecoException.EnderecoNaoEncontradoException(cep);
        }
        return enderecoMapper.toDTO(enderecoOptional.get());
    }

    public void removerEnderecoPorId(Long id) {
        Optional<Endereco> enderecoOptional = enderecoRepository.findById(id);
        if (enderecoOptional.isEmpty()) {
            throw new EnderecoException.EnderecoNaoEncontradoException(id);
        }
        enderecoRepository.deleteById(id);
    }


}
