package tech.mGaia.GerenciamentoPessoas.provider;

import tech.mGaia.GerenciamentoPessoas.auxiliar.ValoresAleatorios;
import tech.mGaia.GerenciamentoPessoas.model.entidades.Pessoa;

public class PessoaProvider {

    private Pessoa pessoa;

    public PessoaProvider criar(){
        pessoa = new Pessoa();
        pessoa.setId(ValoresAleatorios.getLong());
        pessoa.setNome("Michelle");
        pessoa.setEmail("mgvictoriano@live.com");
        pessoa.setDataNascimento(ValoresAleatorios.getLocalDate().withYear(1991));
        pessoa.setInscricaoFederal("12345678995");

        return this;
    }

    public Pessoa retornar(){
        return pessoa;
    }

}

