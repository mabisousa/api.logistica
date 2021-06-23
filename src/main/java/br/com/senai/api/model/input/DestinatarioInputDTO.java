package br.com.senai.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class DestinatarioInputDTO {

    @NotBlank
    public String nome;

    @NotBlank
    public String logradouro;

    @NotBlank
    public String numero;

    public String complemento;

    @NotBlank
    public String bairro;
}
