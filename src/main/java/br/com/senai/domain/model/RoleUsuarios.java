package br.com.senai.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "role_usuarios")
public class RoleUsuarios {

    private long usuarios_id;

    private String role_nome_role;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

}