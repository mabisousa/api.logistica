package br.com.senai.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class PessoaModel {

    long id;
    String nomePessoa;
    String email;
    String telefone;

}
