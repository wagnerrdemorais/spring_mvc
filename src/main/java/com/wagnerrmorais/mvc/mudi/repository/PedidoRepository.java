package com.wagnerrmorais.mvc.mudi.repository;

import com.wagnerrmorais.mvc.mudi.model.Pedido;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class PedidoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Pedido> getPedidos() {
        String sql = "select p from Pedido p";
        Query query = entityManager.createQuery(sql, Pedido.class);
        return query.getResultList();
    }
}
