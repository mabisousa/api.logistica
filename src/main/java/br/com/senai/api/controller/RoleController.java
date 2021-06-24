package br.com.senai.api.controller;

import br.com.senai.api.assembler.RoleAssembler;
import br.com.senai.api.model.RoleDTO;
import br.com.senai.api.model.input.RoleInputDTO;
import br.com.senai.domain.model.Role;
import br.com.senai.domain.repository.RoleRepository;
import br.com.senai.domain.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/roles")
public class RoleController {

    private RoleRepository roleRepository;
    private RoleService roleService;
    private RoleAssembler roleAssembler;

    @GetMapping
    public List<RoleDTO> listar(){
        return roleService.listar();
    }

    @PostMapping
    public RoleDTO cadastrar(@Valid @RequestBody RoleInputDTO roleInputDTO){
        Role novaRole = roleAssembler.toEntity(roleInputDTO);
        Role role = roleService.cadastrar(novaRole);

        return roleAssembler.toModel(role);
    }

    @PutMapping("/{nomeRole}")
    public ResponseEntity<RoleDTO> editar(@Valid @PathVariable String nomeRole, @RequestBody RoleInputDTO roleInputDTO){

        Role role = roleAssembler.toEntity(roleInputDTO);

        if(!roleRepository.existsById(nomeRole)){
            return ResponseEntity.notFound().build();
        }

        RoleDTO roleDTO = roleAssembler.toModel(roleService.editar(role, nomeRole));

        return ResponseEntity.ok(roleDTO);
    }

    @DeleteMapping("/{nomeRole}")
    public void deletar(@PathVariable String nomeRole) {
        roleService.excluir(nomeRole);
    }
}
