package br.com.william.ot6.proposta.controller;

import br.com.william.ot6.proposta.dto.PropostaRequest;
import br.com.william.ot6.proposta.modelo.Proposta;
import br.com.william.ot6.proposta.repositories.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/propostas")
public class PropostaController {

    @Autowired
    PropostaRepository propostaRepository;


     @PostMapping
    public ResponseEntity<?>criarProposta(@RequestBody @Valid PropostaRequest request){

        Proposta proposta = request.conversor();

            propostaRepository.save(proposta);

         URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(proposta.getId()).toUri();

            return ResponseEntity.created(uri).body(proposta);




        }
    }





