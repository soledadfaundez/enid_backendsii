package com.prueba.mantenedor.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.prueba.mantenedor.dto.UsuarioDto;
import com.prueba.mantenedor.model.Usuario;
import com.prueba.mantenedor.request.UsuarioRequest;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    // ESFC: user a userDTO
    UsuarioDto toDto(Usuario usuario);

    // ESFC: userDTO a user
    Usuario toEntity(UsuarioDto usuarioDto);

    // ESFC: UserRequest a User
    Usuario toEntity(UsuarioRequest usuarioRequest);

    // ESFC: List de usuarios a List de usuariosDTO
    List<UsuarioDto> toDtoList(List<Usuario> usuarios);

}
