package com.wagnerrmorais.mvc.mudi.controller;

import com.wagnerrmorais.mvc.mudi.dto.RequisicaoNovoPedido;
import com.wagnerrmorais.mvc.mudi.model.Pedido;
import com.wagnerrmorais.mvc.mudi.model.User;
import com.wagnerrmorais.mvc.mudi.repository.PedidoRepository;
import com.wagnerrmorais.mvc.mudi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("pedido")
public class PedidoController {

  @Autowired
  private PedidoRepository repository;

  @Autowired
  private UserRepository userRepository;

  @GetMapping("formulario")
  public String formulario(RequisicaoNovoPedido requisicaoNovoPedido) {
    return "pedido/formulario";
  }

  @PostMapping("novo")
  public String novo(@Valid RequisicaoNovoPedido requisicaoNovoPedido, BindingResult bindingResult) {
    if(bindingResult.hasErrors()){
      return "pedido/formulario";
    }

    String username = SecurityContextHolder.getContext().getAuthentication().getName();
    User user = userRepository.findByUsername(username);

    Pedido pedido = requisicaoNovoPedido.toPedido();
    pedido.setUser(user);
    repository.save(pedido);
    return "redirect:/home";
  }
}
