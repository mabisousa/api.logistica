package br.com.senai.api.controller;

import br.com.senai.api.model.RoleUsuariosDTO;
import br.com.senai.domain.model.RoleUsuarios;
import br.com.senai.domain.service.RoleUsuariosService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/role_usuarios")
public class RoleUsuariosController {

    private RoleUsuariosService roleUsuariosService;


    @GetMapping
    public List<RoleUsuariosDTO> listar(){

        return roleUsuariosService.listar();
    }

    @PutMapping("/{roleUsuarioId}")
    public ResponseEntity<RoleUsuariosDTO> editar(@Valid @PathVariable Long roleUsuarioId,
                                                 @RequestBody RoleUsuarios roleUsuario){

        return roleUsuariosService.editar(roleUsuarioId, roleUsuario);
    }

}
