package br.com.squadra.bootcamp.projetofinal.repository;

import br.com.squadra.bootcamp.projetofinal.model.MunicipioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MunicipioRepository extends JpaRepository<MunicipioModel, Long> {
    boolean existsByNomeAndCodigoUF(String nomeMunicipio, Long codigoUF);
}
