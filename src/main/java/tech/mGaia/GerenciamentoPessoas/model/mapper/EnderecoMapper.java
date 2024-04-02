package tech.mGaia.GerenciamentoPessoas.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tech.mGaia.GerenciamentoPessoas.model.dtos.EnderecoDTO;
import tech.mGaia.GerenciamentoPessoas.model.entidades.Endereco;

@Mapper
public interface EnderecoMapper {

    EnderecoMapper INSTANCE = Mappers.getMapper(EnderecoMapper.class);

    EnderecoDTO toDTO(Endereco endereco);
    Endereco toObject(EnderecoDTO enderecoDTO);

}
