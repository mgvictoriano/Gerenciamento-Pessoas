package tech.mGaia.GerenciamentoPessoas.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.mGaia.GerenciamentoPessoas.exceptions.EnderecoException;
import tech.mGaia.GerenciamentoPessoas.model.dtos.EnderecoDTO;
import tech.mGaia.GerenciamentoPessoas.model.entidades.Endereco;
import tech.mGaia.GerenciamentoPessoas.model.entidades.Pessoa;
import tech.mGaia.GerenciamentoPessoas.provider.EnderecoDTOProvider;
import tech.mGaia.GerenciamentoPessoas.provider.EnderecoProvider;
import tech.mGaia.GerenciamentoPessoas.repository.EnderecoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@Tag("TESTE_UNITARIO")
@ExtendWith(MockitoExtension.class)
public class EnderecoServiceTest {

    @Mock
    private EnderecoRepository enderecoRepository;
    @InjectMocks
    private EnderecoService enderecoService;
    private final EnderecoProvider enderecoProvider = new EnderecoProvider();
    private final EnderecoDTOProvider enderecoDTOProvider = new EnderecoDTOProvider();

    @Test
    public void testInserirEndereco() {
        Endereco endereco = enderecoProvider.criar().retornar();
        when(enderecoRepository
                        .save(Mockito.any(Endereco.class)))
                .thenReturn(new Endereco());

        enderecoService.inserirEndereco(endereco);

        Mockito.verify(enderecoRepository).save(endereco);
    }

    @Test
    void testAtualizarEndereco() {
        EnderecoDTO enderecoDTO = enderecoDTOProvider.criar().retornar();
        enderecoDTO.setId(1L);

        Endereco endereco = enderecoProvider.criar().retornar();
        endereco.setId(1L);

        Mockito.lenient().when(enderecoRepository.findById(1L))
                .thenReturn(Optional.of(endereco));
        Mockito.lenient().when(enderecoRepository.save(Mockito.any(Endereco.class)))
                .thenReturn(endereco);

        enderecoService.atualizarEndereco(enderecoDTO.getId(), enderecoDTO);

        verify(enderecoRepository, times(1)).findById(1L);
        verify(enderecoRepository, times(1)).save(Mockito.any(Endereco.class));
    }

    @Test
    void testAtualizarEnderecoEnderecoNaoEncontrado() {
        when(enderecoRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EnderecoException.EnderecoNaoEncontradoException.class,
                () -> enderecoService.atualizarEndereco(1L, new EnderecoDTO()));
    }

    @Test
    void testBuscarEnderecoPorId() {
        Endereco endereco = enderecoProvider.criar().retornar();
        when(enderecoRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(endereco));
        EnderecoDTO enderecoRetornado = enderecoService.buscarEnderecoPorId(1L);

        assertNotNull(enderecoRetornado);
        Mockito.verify(enderecoRepository).findById(1L);
    }

    @Test
    void testBuscarEnderecoPorIdNaoEncontrado() {
        when(enderecoRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EnderecoException.EnderecoNaoEncontradoException.class,
                () -> enderecoService.buscarEnderecoPorId(1L));
    }

    @Test
    void testBuscarEnderecoPorCep() {
        EnderecoDTO enderecoDTO = enderecoDTOProvider.criar().retornar();
        enderecoDTO.setCep("12345678");
        Endereco endereco = enderecoProvider.criar().retornar();

        Mockito.lenient().when(enderecoRepository.findByCep(Mockito.anyString()))
                .thenReturn(Optional.of(endereco));

        EnderecoDTO result = enderecoService.buscarEnderecoPorCep("12345678");

        assertNotNull(result);
        Mockito.verify(enderecoRepository).findByCep("12345678");
    }

    @Test
    void testBuscarEnderecoPorCepNaoEncontrado() {
        when(enderecoRepository.findByCep(anyString())).thenReturn(Optional.empty());

        assertThrows(EnderecoException.EnderecoNaoEncontradoException.class,
                () -> enderecoService.buscarEnderecoPorCep("12345678"));
    }

    @Test
    void testRemoverEnderecoPorId() {
        Endereco endereco = enderecoProvider.criar().retornar();
        when(enderecoRepository.findById(anyLong())).thenReturn(Optional.of(endereco));

        enderecoService.removerEnderecoPorId(1L);

        Mockito.verify(enderecoRepository).findById(1L);
        Mockito.verify(enderecoRepository).deleteById(1L);
    }

    @Test
    void testRemoverEnderecoPorIdNaoEncontrado() {
        when(enderecoRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EnderecoException.EnderecoNaoEncontradoException.class,
                () -> enderecoService.removerEnderecoPorId(1L));

        Mockito.verify(enderecoRepository, Mockito.never()).deleteById(anyLong());
    }


}
