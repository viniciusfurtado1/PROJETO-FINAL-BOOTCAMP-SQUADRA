package br.com.squadra.bootcamp.projetofinal.repository;

import br.com.squadra.bootcamp.projetofinal.model.PessoaModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@AllArgsConstructor
public class PessoaCustomRepository {

    private final EntityManager entityManager;

    public List<PessoaModel> find(Long codigoPessoa, String login, Integer status) {

        String query = "select p from PessoaModel as p ";
        String condicao = "where";

        if (codigoPessoa != null) {
            query += condicao + " p.codigoPessoa = :codigoPessoa";
            condicao = " and";
        }
        if (login != null) {
            query += condicao + " p.login = :login";
            condicao = " and";
        }
        if (status != null) {
            query += condicao + " p.status = :status";
        }

        var q = entityManager.createQuery(query, PessoaModel.class);


        if (codigoPessoa != null) {
            q.setParameter("codigoPessoa", codigoPessoa);
        }
        if (login != null) {
            q.setParameter("login", login);
        }
        if (status != null) {
            q.setParameter("status", status);
        }

        return q.getResultList();
    }
}
