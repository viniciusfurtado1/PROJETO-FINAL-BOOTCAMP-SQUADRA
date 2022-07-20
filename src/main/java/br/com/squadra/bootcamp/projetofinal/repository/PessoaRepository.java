package br.com.squadra.bootcamp.projetofinal.repository;

import br.com.squadra.bootcamp.projetofinal.model.PessoaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<PessoaModel, Long> {
    boolean existsByLogin(String login);
}
