package br.com.senai.api.model.input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PessoaInputDTO {

    @NotBlank
    private String nome;

    @NotNull
    private UsuarioInputDTO usuario;

    @NotBlank
    private String telefone;
}
