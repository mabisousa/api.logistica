package br.com.senai.api.controller;

import br.com.senai.api.assembler.PessoaAssembler;
import br.com.senai.api.model.PessoaModel;
import br.com.senai.api.model.input.PessoaInput;
import br.com.senai.domain.model.Pessoa;
import br.com.senai.domain.repository.PessoaRepository;
import br.com.senai.domain.service.PessoaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public List<PessoaModel> listar(){
        return pessoaService.listar();
    }

    @GetMapping("/nome/{pessoaNome}")
    public List<PessoaModel> listarPorNome(@PathVariable String pessoaNome) {
        return pessoaService.listarPorNome(pessoaNome);
    }

    @GetMapping("/nome/containing/{nomeContaining}")
    public List<PessoaModel> listarNomeContaining(@PathVariable String nomeContaining){
        return pessoaService.listarPorNomeContaining(nomeContaining);
    }

    @GetMapping("{pessoaId}")
    public ResponseEntity<PessoaModel> buscar(@PathVariable Long pessoaId){
//        Optional<Pessoa> pessoa = pessoaRepository.findById(pessoaId);

//        if(pessoa.isPresent()){
//            return ResponseEntity.ok(pessoa.get());
//        }
//        return ResponseEntity.notFound().build();

        return pessoaService.buscar(pessoaId);
    }

    @PostMapping
    public PessoaModel cadastrar(@Valid @RequestBody PessoaInput pessoaInput){
        Pessoa novaPessoa = pessoaAssembler.toEntity(pessoaInput);
        Pessoa pessoa = pessoaService.cadastrar(novaPessoa);

        return pessoaAssembler.toModel(pessoa);
    }

    @PutMapping("/{pessoaId}")
    public ResponseEntity<PessoaModel> editar(@Valid @PathVariable  Long pessoaId, @RequestBody PessoaInput pessoaInput){

        if(!pessoaRepository.existsById(pessoaId)){
            return ResponseEntity.notFound().build();
        }

        Pessoa pessoa = pessoaAssembler.toEntity(pessoaInput);
        pessoa.setId(pessoaId);
        pessoaService.editar(pessoa);

        PessoaModel pessoaModel = pessoaAssembler.toModel(pessoa);

        return ResponseEntity.ok(pessoaModel);
    }

    @DeleteMapping("/{pessoaId}")
    public ResponseEntity<PessoaModel> remover(@PathVariable Long pessoaId){
        if(!pessoaRepository.existsById(pessoaId)){
            return ResponseEntity.notFound().build();
        }
        pessoaService.excluir(pessoaId);
        return ResponseEntity.noContent().build();
    }
}
