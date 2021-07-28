package br.com.william.ot6.proposta.modelo;

import br.com.william.ot6.proposta.config.AnaliseCliente;
import br.com.william.ot6.proposta.dto.AnaliseFinanceiraRequest;
import br.com.william.ot6.proposta.dto.AnaliseFinanceiraResponse;
import br.com.william.ot6.proposta.enums.PropostaStatus;
import br.com.william.ot6.proposta.enums.StatusAnalise;
import br.com.william.ot6.proposta.repositories.PropostaRepository;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String documento;

    @Column(nullable = false)
    private String email;


    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String endereco;



   @Column(nullable = false)
    private BigDecimal salario;

    @Enumerated(value = EnumType.STRING)
    private PropostaStatus status;


    public Proposta(String documento, String email, String nome, String endereco, BigDecimal salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    @Deprecated
    public Proposta() {
    }

    public Long getId() {
        return id;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void atualiza(PropostaRepository repository, AnaliseCliente analiseCliente) {

        AnaliseFinanceiraRequest analiseFinanceiraRequest = new AnaliseFinanceiraRequest(documento, nome, id.toString());
        AnaliseFinanceiraResponse analiseFinanceiraResponse = analiseCliente.consultaAnalise(analiseFinanceiraRequest);

        StatusAnalise statusAnalise = analiseFinanceiraResponse.getResultadoSolicitacao();
        this.status = statusAnalise.converte();

        repository.save(this);
    }
}
