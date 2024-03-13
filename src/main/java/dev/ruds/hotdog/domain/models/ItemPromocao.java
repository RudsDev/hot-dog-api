package dev.ruds.hotdog.domain.models;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class ItemPromocao implements Vendavel {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    Lanche lanche;

    Integer quantidade;

    public ItemPromocao() {}

    public ItemPromocao(Long id, Integer quantidade) {
        this.id = id;
        this.quantidade = quantidade;
    }

    public ItemPromocao(Integer quantidade, Lanche lanche) {
        this.quantidade = quantidade;
        this.lanche = lanche;
    }

    public ItemPromocao(Long id, Integer quantidade, Lanche lanche) {
        this.id = id;
        this.quantidade = quantidade;
        this.lanche = lanche;
    }

    @Override
    public BigDecimal preco() {
        BigDecimal preco = lanche.preco();
        BigDecimal multiplicand = BigDecimal.valueOf(quantidade);
        return preco.multiply(multiplicand);
    }
}