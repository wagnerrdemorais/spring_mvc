package com.wagnerrmorais.mvc.mudi.controller;

import com.wagnerrmorais.mvc.mudi.model.Pedido;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(Model model) {
        Pedido pedido = new Pedido();
        pedido.setNomeProduto("Batedeira");
        pedido.setDescricao("Que bate bate");
        pedido.setValorNegociado(new BigDecimal("200"));
        pedido.setUrlProduto("http://ae01.alicdn.com/kf/HTB1QRClQkvoK1RjSZPfq6xPKFXau.jpg?width=791&height=812&hash=1603");
        pedido.setUrlImagem("https://m.media-amazon.com/images/I/81S9qsHCFfL._AC_SY355_.jpg");

        List<Pedido> pedidos = Arrays.asList(pedido);

        model.addAttribute("pedidos",pedidos);
        return "home";
    }
}
