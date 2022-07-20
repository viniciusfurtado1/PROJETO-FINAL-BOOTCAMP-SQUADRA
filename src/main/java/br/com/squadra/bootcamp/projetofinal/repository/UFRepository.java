package br.com.squadra.bootcamp.projetofinal.repository;

import br.com.squadra.bootcamp.projetofinal.model.UFModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UFRepository extends JpaRepository<UFModel, Long> {
    boolean existsBySigla(String sigla);

    boolean existsByNome(String nome);

    @Query("select u from UFModel u where (u.nome = :nome or u.sigla = :sigla) and u.codigoUF <> :codigoUF")
    List<UFModel> findByNomeAndSiglaAndCodigo(String nome, String sigla, Long codigoUF);

}
