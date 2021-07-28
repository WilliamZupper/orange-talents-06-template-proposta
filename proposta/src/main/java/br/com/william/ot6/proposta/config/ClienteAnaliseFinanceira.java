package br.com.william.ot6.proposta.config;

import br.com.william.ot6.proposta.dto.AnaliseFinanceiraRequest;
import br.com.william.ot6.proposta.dto.AnaliseFinanceiraResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "analise", url = "http://localhost:9999/api")
public interface ClienteAnaliseFinanceira {

    @PostMapping("/solicitacao")
    AnaliseFinanceiraResponse solicitaAnaliseFinanceira(@RequestBody AnaliseFinanceiraRequest analiseFinanceiraRequest);
}