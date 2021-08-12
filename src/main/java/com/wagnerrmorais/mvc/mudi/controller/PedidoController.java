package com.wagnerrmorais.mvc.mudi.controller;

import com.wagnerrmorais.mvc.mudi.dto.RequisicaoNovoPedido;
import com.wagnerrmorais.mvc.mudi.model.Pedido;
import com.wagnerrmorais.mvc.mudi.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

  @GetMapping("formulario")
  public String formulario(RequisicaoNovoPedido requisicaoNovoPedido) {
    return "pedido/formulario";
  }

  @PostMapping("novo")
  public String novo(@Valid RequisicaoNovoPedido requisicaoNovoPedido, BindingResult bindingResult) {
    if(bindingResult.hasErrors()){
      return "pedido/formulario";
    }

    Pedido pedido = requisicaoNovoPedido.toPedido();
    repository.save(pedido);
    return "redirect:/home";
  }
}
