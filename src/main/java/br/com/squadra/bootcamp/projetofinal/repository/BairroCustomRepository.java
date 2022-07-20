package br.com.squadra.bootcamp.projetofinal.repository;

import br.com.squadra.bootcamp.projetofinal.model.BairroModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@AllArgsConstructor
public class BairroCustomRepository {

    private final EntityManager entityManager;

    public List<BairroModel> find(Long codigoBairro, Long codigoMunicipio, String nome, Integer status) {

        String query = "select b from BairroModel as b ";
        String condicao = "where";

        if (codigoBairro != null) {
            query += condicao + " b.codigoBairro = :codigoBairro";
            condicao = " and";
        }
        if (codigoMunicipio != null) {
            query += condicao + " b.codigoMunicipio = :codigoMunicipio";
            condicao = " and";
        }
        if (nome != null) {
            query += condicao + " b.nome = :nome";
            condicao = " and";
        }
        if (status != null) {
            query += condicao + " b.status = :status";
        }

        var q = entityManager.createQuery(query, BairroModel.class);

        if (codigoBairro != null) {
            q.setParameter("codigoBairro", codigoBairro);
        }
        if (codigoMunicipio != null) {
            q.setParameter("codigoMunicipio", codigoMunicipio);
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