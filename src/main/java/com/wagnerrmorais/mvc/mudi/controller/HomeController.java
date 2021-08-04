package com.wagnerrmorais.mvc.mudi.controller;

import com.wagnerrmorais.mvc.mudi.model.Pedido;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Controller
public class HomeController {

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/home")
    public String home(Model model) {
        String sql = "select p from Pedido p";
        Query query = entityManager.createQuery(sql, Pedido.class);
        List<Pedido> pedidos = query.getResultList();

        model.addAttribute("pedidos",pedidos);
        return "home";
    }
}
