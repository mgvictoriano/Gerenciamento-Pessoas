package tech.mGaia.GerenciamentoPessoas.service;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.mGaia.GerenciamentoPessoas.exceptions.PessoaException;
import tech.mGaia.GerenciamentoPessoas.model.dtos.PessoaDTO;
import tech.mGaia.GerenciamentoPessoas.model.entidades.Pessoa;
import tech.mGaia.GerenciamentoPessoas.provider.PessoaDTOProvider;
import tech.mGaia.GerenciamentoPessoas.provider.PessoaProvider;
import tech.mGaia.GerenciamentoPessoas.repository.PessoaRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@Tag("TESTE_UNITARIO")
@ExtendWith(MockitoExtension.class)
public class PessoaServiceTest {

    @Mock
    private PessoaRepository pessoaRepository;
    @InjectMocks
    private PessoaService pessoaService;
    private final PessoaProvider pessoaProvider = new PessoaProvider();
    private final PessoaDTOProvider pessoaDTOProvider = new PessoaDTOProvider();

    @Test
    public void testCriarPessoa() {
        Pessoa pessoa = pessoaProvider.criar().retornar();
        Mockito.when(pessoaRepository
                        .save(Mockito.any(Pessoa.class)))
                .thenReturn(new Pessoa());

        pessoaService.criarPessoa(pessoa);

        Mockito.verify(pessoaRepository).save(pessoa);
    }

    @Test
    void testAtualizarPessoa() {
        PessoaDTO pessoaDTO = pessoaDTOProvider.criar().retornar();
        pessoaDTO.setId(1L);

        Pessoa pessoa = pessoaProvider.criar().retornar();
        pessoa.setId(1L);
        pessoa.setNome("Teste");

        Mockito.lenient().when(pessoaRepository.findById(1L))
                .thenReturn(Optional.of(pessoa));
        Mockito.lenient().when(pessoaRepository.save(Mockito.any(Pessoa.class)))
                .thenReturn(pessoa);

        pessoaService.atualizarPessoa(pessoaDTO.getId(), pessoaDTO);

        verify(pessoaRepository, times(1)).findById(1L);
        verify(pessoaRepository, times(1)).save(Mockito.any(Pessoa.class));
    }

    @Test
    public void testAtualizarPessoaPessoaNaoEncontrada() {
        Mockito.when(pessoaRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());

        PessoaDTO pessoaDTO = new PessoaDTO();

        assertThrows(PessoaException.PessoaNaoEncontradaException.class,
                () -> pessoaService.atualizarPessoa(1L, pessoaDTO));
    }

    @Test
    public void testBuscarTodos() {
        List<Pessoa> mockPessoas = Arrays.asList(new Pessoa(), new Pessoa());

        Mockito.when(pessoaRepository.findAll()).thenReturn(mockPessoas);

        List<Pessoa> pessoas = pessoaService.buscarTodos();

        assertFalse(pessoas.isEmpty());
        assertEquals(mockPessoas.size(), pessoas.size());
        Mockito.verify(pessoaRepository).findAll();
    }

    @Test
    public void testBuscarPorId() {
        Pessoa pessoa = pessoaProvider.criar().retornar();

        Mockito.when(pessoaRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(pessoa));
        PessoaDTO pessoaRetornada = pessoaService.buscarPorId(1L);

        assertNotNull(pessoaRetornada);
        Mockito.verify(pessoaRepository).findById(1L);
    }

    @Test
    public void testBuscarPorIdPessoaNaoEncontrada() {
        Mockito.when(pessoaRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());

        assertThrows(PessoaException.PessoaNaoEncontradaException.class,
                () -> pessoaService.buscarPorId(1L));
    }

    @Test
    public void testRemoverPorId() {
        Mockito.when(pessoaRepository.findById(anyLong()))
                .thenReturn(Optional.of(new Pessoa()));

        pessoaService.removerPorId(1L);
    }

    @Test
    public void testRemoverPorIdPessoaNaoEncontrada() {
        Mockito.when(pessoaRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());

        assertThrows(PessoaException.PessoaNaoEncontradaException.class,
                () -> pessoaService.removerPorId(1L));

        Mockito.verify(pessoaRepository, never()).deleteById(anyLong());
    }


}

