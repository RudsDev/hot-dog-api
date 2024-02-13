package dev.ruds.hotdog.domain.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "LANCHES")
public class Lanche implements Vendavel {
    
    @Id
    @GeneratedValue
    private Long id;

	private String nome;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Ingrediente> ingredientes = new ArrayList<Ingrediente>();

    public Lanche(Long id, String nome, List<Ingrediente> ingredientes) {
        this.id = id;
        this.nome = nome;
        this.ingredientes = ingredientes;
    }

    public Lanche(String nome, List<Ingrediente> ingredientes) {
        this.nome = nome;
        this.ingredientes = ingredientes;
    }

    public Lanche() {}

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    @Override
    public BigDecimal preco() {
        return ingredientes
            .stream()
            .map(i -> i.getPreco())
            .reduce(BigDecimal.ZERO, (c, n) -> c.add(n));
    }
}
