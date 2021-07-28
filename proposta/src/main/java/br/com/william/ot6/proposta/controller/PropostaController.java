package br.com.william.ot6.proposta.controller;

import br.com.william.ot6.proposta.config.AnaliseCliente;
import br.com.william.ot6.proposta.dto.PropostaRequest;
import br.com.william.ot6.proposta.modelo.Proposta;
import br.com.william.ot6.proposta.repositories.PropostaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;


@RestController
@RequestMapping("/propostas")
public class PropostaController {


    private PropostaRepository propostaRepository;
    private AnaliseCliente analiseCliente;

    public PropostaController(PropostaRepository propostaRepository, AnaliseCliente analiseCliente) {
        this.propostaRepository = propostaRepository;
        this.analiseCliente = analiseCliente;
    }


    @PostMapping
    public ResponseEntity<?>criarProposta(@RequestBody @Valid PropostaRequest request, UriComponentsBuilder uriComponentsBuilder ){

        Proposta proposta = request.conversor();

        Proposta propostaSalva = propostaRepository.save(proposta);
        propostaSalva.atualiza(propostaRepository, analiseCliente);


        return ResponseEntity.created(uriComponentsBuilder.path("/proposta/{id}")
                .buildAndExpand(proposta.getId()).toUri()).build();



        }
    }





