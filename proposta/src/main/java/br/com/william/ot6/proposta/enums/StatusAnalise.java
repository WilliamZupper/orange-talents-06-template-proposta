package br.com.william.ot6.proposta.enums;

public enum StatusAnalise {


    COM_RESTRICAO {
        @Override
        public PropostaStatus converte() {
            return PropostaStatus.NAO_ELEGIVEL;
        }
    },
    SEM_RESTRICAO {
        @Override
        public PropostaStatus converte() {
            return PropostaStatus.ELEGIVEL;
        }
    };

    public abstract PropostaStatus converte();
}
