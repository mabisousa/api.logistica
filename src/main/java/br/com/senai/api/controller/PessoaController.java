package br.com.senai.api.controller;

import br.com.senai.api.assembler.PessoaAssembler;
import br.com.senai.api.model.PessoaDTO;
import br.com.senai.api.model.input.PessoaInputDTO;
import br.com.senai.domain.model.Pessoa;
import br.com.senai.domain.repository.PessoaRepository;
import br.com.senai.domain.service.PessoaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    private PessoaRepository pessoaRepository;
    private PessoaService pessoaService;
    private PessoaAssembler pessoaAssembler;

    @GetMapping
    public List<PessoaDTO> listar(){
        return pessoaService.listar();
    }

    @GetMapping("/nome/{pessoaNome}")
    public List<PessoaDTO> listarPorNome(@PathVariable String pessoaNome) {
        return pessoaService.listarPorNome(pessoaNome);
    }

    @GetMapping("/nome/containing/{nomeContaining}")
    public List<PessoaDTO> listarNomeContaining(@PathVariable String nomeContaining){
        return pessoaService.listarPorNomeContaining(nomeContaining);
    }

    @GetMapping("{pessoaId}")
    public ResponseEntity<PessoaDTO> buscar(@PathVariable Long pessoaId){
//        Optional<Pessoa> pessoa = pessoaRepository.findById(pessoaId);

//        if(pessoa.isPresent()){
//            return ResponseEntity.ok(pessoa.get());
//        }
//        return ResponseEntity.notFound().build();

        return pessoaService.buscar(pessoaId);
    }

    @PostMapping
    public PessoaDTO cadastrar(@Valid @RequestBody PessoaInputDTO pessoaInputDTO){
        Pessoa novaPessoa = pessoaAssembler.toEntity(pessoaInputDTO);
        novaPessoa.getUsuario().setSenha(new BCryptPasswordEncoder().encode(pessoaInputDTO.getUsuario().getSenha()));
        Pessoa pessoa = pessoaService.cadastrar(novaPessoa);

        return pessoaAssembler.toModel(pessoa);
    }

    @PutMapping("/{pessoaId}")
    public ResponseEntity<PessoaDTO> editar(@Valid @PathVariable Long pessoaId, @RequestBody PessoaInputDTO pessoaInputDTO){

        if(!pessoaRepository.existsById(pessoaId)){
            return ResponseEntity.notFound().build();
        }

        Pessoa pessoa = pessoaAssembler.toEntity(pessoaInputDTO);
        pessoa.setId(pessoaId);
        pessoaService.editar(pessoa);

        PessoaDTO pessoaDTO = pessoaAssembler.toModel(pessoa);

        return ResponseEntity.ok(pessoaDTO);
    }

    @DeleteMapping("/{pessoaId}")
    public ResponseEntity<PessoaDTO> remover(@PathVariable Long pessoaId){
        if(!pessoaRepository.existsById(pessoaId)){
            return ResponseEntity.notFound().build();
        }
        pessoaService.excluir(pessoaId);
        return ResponseEntity.noContent().build();
    }
}
