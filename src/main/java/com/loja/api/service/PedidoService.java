package com.loja.api.service;

import com.loja.api.dto.PedidoRequest;
import com.loja.api.exception.RecursoNaoEncontradoException;
import com.loja.api.model.Cliente;
import com.loja.api.model.Pedido;
import com.loja.api.model.Produto;
import com.loja.api.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository repository;
    private final ClienteService clienteService;
    private final ProdutoService produtoService;

    public PedidoService(PedidoRepository repository,
                         ClienteService clienteService,
                         ProdutoService produtoService) {
        this.repository = repository;
        this.clienteService = clienteService;
        this.produtoService = produtoService;
    }

    public List<Pedido> listar() {
        return repository.findAll();
    }

    public Pedido buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Pedido nao encontrado: " + id));
    }

    public List<Pedido> buscarPorNomeCliente(String nome) {
        return repository.findByClienteNomeContainingIgnoreCase(nome);
    }

    public List<Pedido> buscarPorNomeProduto(String nome) {
        return repository.findByProdutoNomeContainingIgnoreCase(nome);
    }

    public long contar() {
        return repository.count();
    }

    public Pedido salvar(PedidoRequest request) {
        return repository.save(montarPedido(new Pedido(), request));
    }

    public Pedido atualizar(Long id, PedidoRequest request) {
        Pedido pedido = buscarPorId(id);
        return repository.save(montarPedido(pedido, request));
    }

    public void excluir(Long id) {
        Pedido pedido = buscarPorId(id);
        repository.delete(pedido);
    }

    private Pedido montarPedido(Pedido pedido, PedidoRequest request) {
        Cliente cliente = clienteService.buscarPorId(request.getClienteId());
        Produto produto = produtoService.buscarPorId(request.getProdutoId());
        pedido.setCliente(cliente);
        pedido.setProduto(produto);
        if (pedido.getData() == null) {
            pedido.setData(LocalDateTime.now());
        }
        return pedido;
    }
}
