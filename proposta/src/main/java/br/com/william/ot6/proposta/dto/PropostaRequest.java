package br.com.william.ot6.proposta.dto;

import br.com.william.ot6.proposta.modelo.Proposta;
import br.com.william.ot6.proposta.validacao.CPF_CNPJ;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class PropostaRequest {

    @NotBlank
    @CPF_CNPJ
    private String documento;

     @NotBlank
     @Email
    private String email;

     @NotBlank
    private String nome;

      @NotBlank
    private String endereco;

    @Positive
    @NotNull
    private BigDecimal salario;

    public PropostaRequest(String documento, String email, String nome, String endereco, BigDecimal salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }


    public Proposta conversor() {

     return new Proposta(this.documento, this.email, this.nome, this.endereco, this.salario);


    }
}
