package com.wagnerrmorais.mvc.mudi.api;

import com.wagnerrmorais.mvc.mudi.interceptor.InterceptadorAcessos;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("acessos")
@RestController
public class AcessosRest {

    @GetMapping
    public List<InterceptadorAcessos.Acesso> getAcessos() {
        return InterceptadorAcessos.acessos;
    }
}
