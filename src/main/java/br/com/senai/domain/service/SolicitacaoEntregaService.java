package br.com.senai.domain.service;

import br.com.senai.api.assembler.EntregaAssembler;
import br.com.senai.api.model.EntregaDTO;
import br.com.senai.domain.model.Entrega;
import br.com.senai.domain.model.Pessoa;
import br.com.senai.domain.model.StatusEntrega;
import br.com.senai.domain.repository.EntregaRepository;
import br.com.senai.domain.repository.PessoaRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class SolicitacaoEntregaService {

    private PessoaService pessoaService;
    private EntregaRepository entregaRepository;
    private PessoaRepository pessoaRepository;
    private EntregaAssembler entregaAssembler;

    @Transactional
    public Entrega solicitar(Entrega entrega){
        Pessoa pessoa = pessoaService.buscarId(entrega.getPessoa().getId());
        entrega.setPessoa(pessoa);

        entrega.setStatus(StatusEntrega.PENDENTE);
        entrega.setDataPedido(LocalDateTime.now());

        return entregaRepository.save(entrega);
    }

    public List<EntregaDTO> listar(){
        return entregaAssembler.toCollection(entregaRepository.findAll());
    }


    public ResponseEntity<EntregaDTO> buscar(Long entregaId){
        return entregaRepository.findById(entregaId)
                .map(entrega -> {
//                    EntregaDTO entregaModel = new EntregaDTO();
//
//                    entregaModel.setId(entrega.getId());
//                    entregaModel.setNomePessoa(entrega.getPessoa().getNome());
//                    entregaModel.setDestinatario(new DestinatarioDTO());
//                    entregaModel.getDestinatario().setNome(entrega.getDestinatario().getNome());
//                    entregaModel.getDestinatario().setLogradouro(entrega.getDestinatario().getLogradouro());
//                    entregaModel.getDestinatario().setNumero(entrega.getDestinatario().getNumero());
//                    entregaModel.getDestinatario().setComplemento(entrega.getDestinatario().getComplemento());
//                    entregaModel.getDestinatario().setBairro(entrega.getDestinatario().getBairro());
//                    entregaModel.setTaxa(entrega.getTaxa());
//                    entregaModel.setDataPedido(entrega.getDataPedido());
//                    entregaModel.setDataFinalizacao(entrega.getDataFinalizacao());

                    return ResponseEntity.ok(entregaAssembler.toModel(entrega));

                })

                .orElse(ResponseEntity.notFound().build());
    }


}
