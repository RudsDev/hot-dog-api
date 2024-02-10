package dev.ruds.hotdog.domain.models;

import java.math.BigDecimal;

public class Ingrediente {
    
    private Long id;
	private String nome;
    private BigDecimal preco;
    
    public Ingrediente() {}

    public Ingrediente(String nome, BigDecimal preco) {
        this.nome = nome;
        this.preco = preco;
    }
    public Long getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public BigDecimal getPreco() {
        return preco;
    }
}
