package tech.mGaia.GerenciamentoPessoas.provider;

import tech.mGaia.GerenciamentoPessoas.auxiliar.ValoresAleatorios;
import tech.mGaia.GerenciamentoPessoas.model.entidades.Endereco;

public class EnderecoProvider {

    private Endereco endereco;

    public EnderecoProvider criar() {
        endereco = new Endereco();
        endereco.setId(ValoresAleatorios.getLong());
        endereco.setLogradouro("Rua Teste");
        endereco.setNumero(ValoresAleatorios.getInteiroPositivo(0, 9999));
        endereco.setBairro("Bairro Teste");
        endereco.setCidade("Cidade Teste");
        endereco.setEstado("Estado Teste");
        endereco.setCep("12345678");

        return this;
    }

    public Endereco retornar() {
        return endereco;
    }
}
