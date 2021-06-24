package br.com.senai.api.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RoleDTO {

    private String nomeRole;
    private List<UsuarioDTO> usuarios;
}
