package com.wagnerrmorais.mvc.mudi.controller;

import com.wagnerrmorais.mvc.mudi.model.Pedido;
import com.wagnerrmorais.mvc.mudi.repository.PedidoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

  private final PedidoRepository repository;

  public HomeController(PedidoRepository pedidoRepository) {
    this.repository = pedidoRepository;
  }

  @GetMapping("/home")
  public String home(Model model) {

    List<Pedido> pedidos = repository.getPedidos();

    model.addAttribute("pedidos", pedidos);
    return "home";
  }
}
