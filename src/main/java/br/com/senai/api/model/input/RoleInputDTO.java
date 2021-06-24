package br.com.senai.api.model.input;

import br.com.senai.domain.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoleInputDTO {

    private String nomeRole;

    private List<Usuario> usuarios;
}
