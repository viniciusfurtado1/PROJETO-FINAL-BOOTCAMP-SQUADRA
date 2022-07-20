package br.com.squadra.bootcamp.projetofinal.repository;

import br.com.squadra.bootcamp.projetofinal.model.MunicipioModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@AllArgsConstructor
public class MunicipioCustomRepository {

    private final EntityManager entityManager;

    public List<MunicipioModel> find(Long codigoMunicipio, Long codigoUF, String nome, Integer status) {

        String query = "select m from MunicipioModel as m ";
        String condicao = "where";

        if (codigoMunicipio != null) {
            query += condicao + " m.codigoMunicipio = :codigoMunicipio";
            condicao = " and";
        }
        if (codigoUF != null) {
            query += condicao + " m.codigoUF = :codigoUF";
            condicao = " and";
        }
        if (nome != null) {
            query += condicao + " m.nome = :nome";
            condicao = " and";
        }
        if (status != null) {
            query += condicao + " m.status = :status";
        }

        var q = entityManager.createQuery(query, MunicipioModel.class);

        if (codigoMunicipio != null) {
            q.setParameter("codigoMunicipio", codigoMunicipio);
        }
        if (codigoUF != null) {
            q.setParameter("codigoUF", codigoUF);
        }
        if (nome != null) {
            q.setParameter("nome", nome);
        }
        if (status != null) {
            q.setParameter("status", status);
        }
        return q.getResultList();
    }
}