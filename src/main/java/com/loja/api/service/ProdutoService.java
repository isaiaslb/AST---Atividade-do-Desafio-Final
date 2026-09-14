package com.loja.api.service;

import com.loja.api.exception.RecursoNaoEncontradoException;
import com.loja.api.model.Produto;
import com.loja.api.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public List<Produto> listar() {
        return repository.findAll();
    }

    public Produto buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto nao encontrado: " + id));
    }

    public List<Produto> buscarPorNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome);
    }

    public long contar() {
        return repository.count();
    }

    public Produto salvar(Produto produto) {
        return repository.save(produto);
    }

    public Produto atualizar(Long id, Produto dados) {
        Produto produto = buscarPorId(id);
        produto.setNome(dados.getNome());
        produto.setPreco(dados.getPreco());
        return repository.save(produto);
    }

    public void excluir(Long id) {
        Produto produto = buscarPorId(id);
        repository.delete(produto);
    }
}
