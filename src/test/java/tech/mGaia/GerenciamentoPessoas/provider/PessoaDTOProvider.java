package tech.mGaia.GerenciamentoPessoas.provider;

import tech.mGaia.GerenciamentoPessoas.auxiliar.ValoresAleatorios;
import tech.mGaia.GerenciamentoPessoas.model.dtos.PessoaDTO;

public class PessoaDTOProvider {

    private PessoaDTO pessoa;

    public PessoaDTOProvider criar() {
        pessoa = new PessoaDTO();
        pessoa.setId(ValoresAleatorios.getLong());
        pessoa.setNome("Michelle");

        return this;
    }

    public PessoaDTO retornar() {
        return pessoa;
    }

}