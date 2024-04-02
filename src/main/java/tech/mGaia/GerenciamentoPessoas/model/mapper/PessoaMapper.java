package tech.mGaia.GerenciamentoPessoas.model.mapper;

import org.springframework.web.bind.annotation.Mapping;
import tech.mGaia.GerenciamentoPessoas.model.dtos.PessoaDTO;
import tech.mGaia.GerenciamentoPessoas.model.entidades.Pessoa;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PessoaMapper {

    PessoaMapper INSTANCE = Mappers.getMapper(PessoaMapper.class);

    Pessoa toObject(PessoaDTO pessoaDTO);
    PessoaDTO toDTO(Pessoa pessoa);
}
