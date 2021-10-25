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
@RequestMapping("usuario")
public class UsuarioController {

    private final PedidoRepository repository;

    public UsuarioController(PedidoRepository pedidoRepository) {
        this.repository = pedidoRepository;
    }

    @GetMapping("pedido")
    public String home(Model model, Principal principal) {
        List<Pedido> pedidos = repository.findAllByUser(principal.getName());
        model.addAttribute("pedidos", pedidos);
        return "usuario/home";
    }

    @GetMapping("pedido/{status}")
    public String byStatus(@PathVariable("status") String status, Model model, Principal principal) {
        StatusPedido statusPedido = StatusPedido.valueOf(status.toUpperCase());
        String name = principal.getName();
        List<Pedido> pedidos = repository.findByStatusAndUser(statusPedido, name);
        model.addAttribute("pedidos", pedidos);
        model.addAttribute("status", status);
        return "usuario/home";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String onError() {
        return "redirect:/usuario/home";
    }

}
