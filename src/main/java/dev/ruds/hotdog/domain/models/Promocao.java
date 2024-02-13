package dev.ruds.hotdog.domain.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import dev.ruds.hotdog.domain.models.calculo.CalculoPromocao;
import dev.ruds.hotdog.domain.models.calculo.TipoCalculoPromocao;

public class Promocao implements Vendavel{
    
    private Long id;
    private String nome;
    private Double baseCalculo;
    private TipoCalculoPromocao tipoCalculo;
    private CalculoPromocao calculo;
    private List<Lanche> itens = new ArrayList<Lanche>();

    public Promocao() {}

    public Promocao(Long id, String nome, List<Lanche> itens, Integer tipoCalculo, Double baseCalculo) {
        this.id = id;
        this.nome = nome;
        this.itens = itens;
        this.tipoCalculo = TipoCalculoPromocao.get(tipoCalculo);
        this.baseCalculo = baseCalculo;
    }

    public Promocao(Long id, String nome, Integer tipoCalculo, Double baseCalculo) {
        this.id = id;
        this.nome = nome;
        this.tipoCalculo = TipoCalculoPromocao.get(tipoCalculo);
        this.baseCalculo = baseCalculo;
    }

    public Promocao(String nome, List<Lanche> itens, TipoCalculoPromocao tipoCalculo, Double baseCalculo) {
        this.nome = nome;
        this.itens = itens;
        this.tipoCalculo = tipoCalculo;
        this.baseCalculo = baseCalculo;
    }

    public BigDecimal preco() {
        var value = itens.stream().map(i -> i.preco()).reduce(BigDecimal.ZERO, (c, n) -> c.add(n));
        this.calculo = new CalculoPromocao(tipoCalculo, baseCalculo, value);
        return this.calculo.calculaPromocao();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

}
