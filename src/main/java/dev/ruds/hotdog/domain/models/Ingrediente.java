package dev.ruds.hotdog.domain.models;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "INGREDIENTES")
public class Ingrediente {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
    private String nome;
    private BigDecimal preco;
    
    public Ingrediente() {}

    public Ingrediente(Long id) {
        this.id = id;
    }

    public Ingrediente(String nome, BigDecimal preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public Ingrediente(Long id, String nome, BigDecimal preco) {
        this.id = id;
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
