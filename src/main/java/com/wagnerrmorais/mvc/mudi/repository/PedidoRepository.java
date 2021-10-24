package com.wagnerrmorais.mvc.mudi.repository;

import com.wagnerrmorais.mvc.mudi.model.Pedido;
import com.wagnerrmorais.mvc.mudi.model.StatusPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByStatus(StatusPedido status);

    @Query("select p from Pedido p join p.user u where u.username = :username")
    List<Pedido> findAllByUser(@Param("username")String username);
}
