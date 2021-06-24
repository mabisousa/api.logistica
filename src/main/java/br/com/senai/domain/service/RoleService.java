package br.com.senai.domain.service;

import br.com.senai.api.assembler.RoleAssembler;
import br.com.senai.api.model.RoleDTO;
import br.com.senai.domain.model.Role;
import br.com.senai.domain.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@Service
public class RoleService extends RoleDTO{

    private RoleAssembler roleAssembler;
    private RoleRepository roleRepository;

    public List<RoleDTO> listar(){
        return roleAssembler.toCollection(roleRepository.findAll());
    }

    @Transactional
    public Role cadastrar(@Valid Role role) {
        return roleRepository.save(role);
    }

    public void excluir(String role) {
        roleRepository.deleteById(role);
    }

    public Role editar(Role role, String nomeRole){
        role.setNomeRole(nomeRole);
        return roleRepository.save(role);
    }
}
