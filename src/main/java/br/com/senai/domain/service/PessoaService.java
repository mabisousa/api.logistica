package br.com.senai.domain.service;

import br.com.senai.api.assembler.PessoaAssembler;
import br.com.senai.api.model.PessoaModel;
import br.com.senai.domain.exception.NegocioException;
import br.com.senai.domain.model.Pessoa;
import br.com.senai.domain.repository.PessoaRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@Service
public class PessoaService extends PessoaModel {

    private PessoaRepository pessoaRepository;
    private PessoaAssembler pessoaAssembler;

    public List<PessoaModel> listar(){
        return pessoaAssembler.toCollection(pessoaRepository.findAll());
    }

    public List<PessoaModel> listarPorNomeContaining(String nomeContaining){
        return pessoaAssembler.toCollection(pessoaRepository.findByNomeContaining(nomeContaining));
    }

    public List<PessoaModel> listarPorNome(String pessoaNome){
        return pessoaAssembler.toCollection(pessoaRepository.findByNome(pessoaNome));
    }

    public Pessoa buscarId(Long pessoaId){
        return pessoaRepository.findById(pessoaId)
                .orElseThrow(() -> new NegocioException("Pessoa não encontrada."));
    }

    public ResponseEntity<PessoaModel> buscar(Long pessoaId){
        return pessoaRepository.findById(pessoaId).map(pessoa -> {
            return ResponseEntity.ok(pessoaAssembler.toModel(pessoa));
        }).orElse(ResponseEntity.notFound().build());
    }

    @Transactional
    public Pessoa cadastrar(@Valid Pessoa pessoa) {

        boolean emailValidation = pessoaRepository.findByEmail(pessoa.getEmail()).isPresent();

        if (emailValidation) {
            throw new NegocioException("Já existe uma pessoa com este e-mail cadastrado");
        }
        return pessoaRepository.save(pessoa);
    }

    @Transactional
    public void excluir(Long pessoaId){
        pessoaRepository.deleteById(pessoaId);
    }

    public Pessoa editar(@Valid Pessoa pessoa){
        return pessoaRepository.save(pessoa);
    }
}
