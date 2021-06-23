package br.com.senai.api.model;

import br.com.senai.domain.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class PessoaDTO {

    private String nome;
    private String telefone;
    private UsuarioDTO usuario;

}
