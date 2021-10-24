package com.wagnerrmorais.mvc.mudi.controller;

import com.wagnerrmorais.mvc.mudi.model.Pedido;
import com.wagnerrmorais.mvc.mudi.model.StatusPedido;
import com.wagnerrmorais.mvc.mudi.repository.PedidoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping({"/home", "", "/"})
public class HomeController {

  private final PedidoRepository repository;

  public HomeController(PedidoRepository pedidoRepository) {
    this.repository = pedidoRepository;
  }

  @GetMapping
  public String home(Model model, Principal principal) {
    List<Pedido> pedidos = repository.findAllByUser(principal.getName());
    model.addAttribute("pedidos", pedidos);
    return "home";
  }

  @GetMapping("/{status}")
  public String porStatus(@PathVariable("status") String status, Model model) {
    List<Pedido> pedidos = repository.findByStatus(StatusPedido.valueOf(status.toUpperCase()));
    model.addAttribute("pedidos", pedidos);
    model.addAttribute("status", status);
    return "home";
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public String onError() {
    return "redirect:/home";
  }
}
