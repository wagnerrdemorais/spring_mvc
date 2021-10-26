package com.wagnerrmorais.mvc.mudi.controller;

import com.wagnerrmorais.mvc.mudi.model.Pedido;
import com.wagnerrmorais.mvc.mudi.model.StatusPedido;
import com.wagnerrmorais.mvc.mudi.repository.PedidoRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping({"/home", "", "/"})
public class HomeController {

  private final PedidoRepository repository;

  public HomeController(PedidoRepository pedidoRepository) {
    this.repository = pedidoRepository;
  }

  @GetMapping
  public String home(Model model) {

    Sort sort = Sort.by("dataEntrega").ascending();

    PageRequest pageRequest = PageRequest.of(0, 10, sort);

    List<Pedido> pedidos = repository.findByStatus(StatusPedido.ENTREGUE, pageRequest);
    model.addAttribute("pedidos", pedidos);
    return "home";
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public String onError() {
    return "redirect:/home";
  }
}
