package com.loja.api.service;

import com.loja.api.exception.RecursoNaoEncontradoException;
import com.loja.api.model.Cliente;
import com.loja.api.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public List<Cliente> listar() {
        return repository.findAll();
    }

    public Cliente buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente nao encontrado: " + id));
    }

    public List<Cliente> buscarPorNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome);
    }

    public long contar() {
        return repository.count();
    }

    public Cliente salvar(Cliente cliente) {
        return repository.save(cliente);
    }

    public Cliente atualizar(Long id, Cliente dados) {
        Cliente cliente = buscarPorId(id);
        cliente.setNome(dados.getNome());
        cliente.setEmail(dados.getEmail());
        return repository.save(cliente);
    }

    public void excluir(Long id) {
        Cliente cliente = buscarPorId(id);
        repository.delete(cliente);
    }
}
