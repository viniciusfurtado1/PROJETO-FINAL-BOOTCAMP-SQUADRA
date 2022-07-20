package br.com.squadra.bootcamp.projetofinal.repository;

import br.com.squadra.bootcamp.projetofinal.model.UFModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@AllArgsConstructor
public class UFCustomRepository {

    private final EntityManager entityManager;

    public List<UFModel> find(Long codigoUF, String nome, String sigla, Integer status) {

        String query = "select u from UFModel as u ";
        String condicao = "where";

        if (codigoUF != null) {
            query += condicao + " u.codigoUF = :codigoUF";
            condicao = " and";
        }
        if (nome != null) {
            query += condicao + " u.nome = :nome";
            condicao = " and";
        }
        if (sigla != null) {
            query += condicao + " u.sigla = :sigla";
            condicao = " and";
        }
        if (status != null) {
            query += condicao + " u.status = :status";
        }

        var q = entityManager.createQuery(query, UFModel.class);

        if (codigoUF != null) {
            q.setParameter("codigoUF", codigoUF);
        }
        if (nome != null) {
            q.setParameter("nome", nome);
        }
        if (sigla != null) {
            q.setParameter("sigla", sigla);
        }
        if (status != null) {
            q.setParameter("status", status);
        }

        return q.getResultList();
    }
}
