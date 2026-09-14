package com.loja.api.controller;

import com.loja.api.dto.PedidoRequest;
import com.loja.api.model.Pedido;
import com.loja.api.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Pedido> listar() {
        return service.listar();
    }

    @GetMapping("/count")
    public Map<String, Long> contar() {
        return Map.of("count", service.contar());
    }

    @GetMapping("/cliente/{nome}")
    public List<Pedido> buscarPorNomeCliente(@PathVariable String nome) {
        return service.buscarPorNomeCliente(nome);
    }

    @GetMapping("/produto/{nome}")
    public List<Pedido> buscarPorNomeProduto(@PathVariable String nome) {
        return service.buscarPorNomeProduto(nome);
    }

    @GetMapping("/{id}")
    public Pedido buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<Pedido> criar(@Valid @RequestBody PedidoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(request));
    }

    @PutMapping("/{id}")
    public Pedido atualizar(@PathVariable Long id, @Valid @RequestBody PedidoRequest request) {
        return service.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
