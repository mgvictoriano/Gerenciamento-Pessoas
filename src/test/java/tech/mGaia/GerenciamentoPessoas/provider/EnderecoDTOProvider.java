package tech.mGaia.GerenciamentoPessoas.provider;

import tech.mGaia.GerenciamentoPessoas.auxiliar.ValoresAleatorios;
import tech.mGaia.GerenciamentoPessoas.model.dtos.EnderecoDTO;

public class EnderecoDTOProvider {

    private EnderecoDTO enderecoDTO;

    public EnderecoDTOProvider criar() {
        enderecoDTO = new EnderecoDTO();
        enderecoDTO.setId(ValoresAleatorios.getLong());
        enderecoDTO.setNumero(ValoresAleatorios.getInteiroPositivo(0, 9999));
        enderecoDTO.setLogradouro("Rua Teste");
        enderecoDTO.setBairro("Bairro Teste");
        enderecoDTO.setCidade("Cidade Teste");
        enderecoDTO.setCep("12345678");
        return this;
    }

    public EnderecoDTO retornar() {
        return criar().enderecoDTO;
    }

}
