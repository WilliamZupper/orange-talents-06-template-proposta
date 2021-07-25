package br.com.william.ot6.proposta.repositories;

import br.com.william.ot6.proposta.modelo.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long> {
}
