package br.com.william.ot6.proposta.dto;

import br.com.william.ot6.proposta.enums.StatusAnalise;

public class AnaliseFinanceiraResponse {

    private String documento;
    private String nome;
    private String idProposta;
    private StatusAnalise resultadoSolicitacao;

    public AnaliseFinanceiraResponse(String documento, String nome, String idProposta, StatusAnalise resultadoSolicitacao) {
        this.documento = documento;
        this.nome = nome;
        this.idProposta = idProposta;
        this.resultadoSolicitacao = resultadoSolicitacao;
    }

    public StatusAnalise getResultadoSolicitacao() {
        return resultadoSolicitacao;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getIdProposta() {
        return idProposta;
    }


}
