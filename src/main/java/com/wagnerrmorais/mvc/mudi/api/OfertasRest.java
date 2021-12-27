package com.wagnerrmorais.mvc.mudi.api;

import com.wagnerrmorais.mvc.mudi.dto.RequisicaoNovaOferta;
import com.wagnerrmorais.mvc.mudi.model.Oferta;
import com.wagnerrmorais.mvc.mudi.model.Pedido;
import com.wagnerrmorais.mvc.mudi.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/ofertas")
public class OfertasRest {

    @Autowired
    private PedidoRepository pedidoRepository;

    @PostMapping
    public Oferta criaOferta(@RequestBody RequisicaoNovaOferta requisicaoNovaOferta) {
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(requisicaoNovaOferta.getPedidoId());
        if(!pedidoOptional.isPresent()){
            return null;
        }

        Pedido pedido = pedidoOptional.get();

        Oferta novaOferta = requisicaoNovaOferta.toOferta();
        novaOferta.setPedido(pedido);

        pedido.getOfertas().add(novaOferta);

        pedidoRepository.save(pedido);

        return novaOferta;
    }
}
