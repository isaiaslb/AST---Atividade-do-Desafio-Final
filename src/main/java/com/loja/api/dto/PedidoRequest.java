package com.loja.api.dto;

import jakarta.validation.constraints.NotNull;

public class PedidoRequest {

    @NotNull(message = "clienteId e obrigatorio")
    private Long clienteId;

    @NotNull(message = "produtoId e obrigatorio")
    private Long produtoId;

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }
}
