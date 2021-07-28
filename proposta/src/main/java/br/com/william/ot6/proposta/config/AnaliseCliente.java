package br.com.william.ot6.proposta.config;

import br.com.william.ot6.proposta.dto.AnaliseFinanceiraRequest;
import br.com.william.ot6.proposta.dto.AnaliseFinanceiraResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import org.springframework.stereotype.Component;

@Component
public class AnaliseCliente {

    private ClienteAnaliseFinanceira clienteAnaliseFinanceira;
    private ObjectMapper objectMapper;

    public AnaliseCliente(ClienteAnaliseFinanceira clienteAnaliseFinanceira, ObjectMapper objectMapper) {
        this.clienteAnaliseFinanceira = clienteAnaliseFinanceira;
        this.objectMapper = objectMapper;
    }

    public AnaliseFinanceiraResponse consultaAnalise(AnaliseFinanceiraRequest analiseFinanceiraRequest) {

        try {
            return clienteAnaliseFinanceira.solicitaAnaliseFinanceira(analiseFinanceiraRequest);
        } catch (FeignException.UnprocessableEntity exception) {
            try {
                String body = exception.contentUTF8();
                return objectMapper.readValue(body, AnaliseFinanceiraResponse.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}