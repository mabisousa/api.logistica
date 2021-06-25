package br.com.senai.api.assembler;

import br.com.senai.api.model.RoleUsuariosDTO;
import br.com.senai.api.model.input.RoleUsuariosInputDTO;
import br.com.senai.domain.model.RoleUsuarios;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class RoleUsuariosAssembler {

    private ModelMapper modelMapper;

    public RoleUsuariosDTO toModel(RoleUsuarios roleUsuario){
        return modelMapper.map(roleUsuario, RoleUsuariosDTO.class);
    }

    public List<RoleUsuariosDTO> toCollectionModel(List<RoleUsuarios> roles){
        return roles.stream().map(this::toModel).collect(Collectors.toList());
    }

    public  RoleUsuarios toEntity(RoleUsuariosInputDTO roleUsuarioInputDTo){
        return modelMapper.map(roleUsuarioInputDTo, RoleUsuarios.class);
    }
}
