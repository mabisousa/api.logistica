package br.com.senai.domain.service;

import br.com.senai.api.assembler.RoleUsuariosAssembler;
import br.com.senai.api.model.RoleUsuariosDTO;
import br.com.senai.domain.exception.NegocioException;
import br.com.senai.domain.model.RoleUsuarios;
import br.com.senai.domain.repository.RoleUsuariosRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@Service
public class RoleUsuariosService {

    private RoleUsuariosRepository roleUsuarioRepository;
    private RoleUsuariosAssembler roleUsuarioAssembler;

    public List<RoleUsuariosDTO> listar(){

        return roleUsuarioAssembler.toCollectionModel(roleUsuarioRepository.findAll());
    }

    @Transactional
    public RoleUsuarios cadastrar(RoleUsuarios roleUsuario) {

        return roleUsuarioRepository.save(roleUsuario);
    }

    public ResponseEntity<RoleUsuariosDTO> editar(Long roleUsuarioId, RoleUsuarios roleUsuario){

        if(!roleUsuarioRepository.existsById(roleUsuarioId)){
            throw new NegocioException("Id não encontrado");
        }

        roleUsuario.setId(roleUsuarioId);
        roleUsuario.setRole_nome_role(roleUsuario.getRole_nome_role());
        roleUsuarioRepository.save(roleUsuario);

        return ResponseEntity.ok(roleUsuarioAssembler.toModel(roleUsuario));
    }
}
