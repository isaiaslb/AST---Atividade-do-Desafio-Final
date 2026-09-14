package com.loja.api.repository;

import com.loja.api.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByClienteNomeContainingIgnoreCase(String nome);

    List<Pedido> findByProdutoNomeContainingIgnoreCase(String nome);
}
