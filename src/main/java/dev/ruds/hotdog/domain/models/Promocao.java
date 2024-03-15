package dev.ruds.hotdog.domain.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import dev.ruds.hotdog.domain.models.calculo.CalculoPromocao;
import dev.ruds.hotdog.domain.models.calculo.tipo.TipoCalculoPromocao;
import dev.ruds.hotdog.domain.records.PromocaoRecord;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

@Entity
public class Promocao implements Vendavel {
    
    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    private Double baseCalculo;

    @Enumerated(EnumType.ORDINAL)
    private TipoCalculoPromocao tipoCalculo;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "promocao_id")
    private List<ItemPromocao> itens = new ArrayList<ItemPromocao>();
    
    @JsonIgnore
    @Transient
    private CalculoPromocao calculo;

    public Promocao() {}

    public Promocao(PromocaoRecord record, List<ItemPromocao> itens) {
        this.nome = record.nome();
        this.tipoCalculo = TipoCalculoPromocao.get(record.tipoCalculo());
        this.baseCalculo = record.baseCalculo();
        this.itens = itens;
    }

    public Promocao(Long id, String nome, List<ItemPromocao> itens, Integer tipoCalculo, Double baseCalculo) {
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

    public Promocao(String nome, List<ItemPromocao> itens, TipoCalculoPromocao tipoCalculo, Double baseCalculo) {
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

    public Double getBaseCalculo() {
        return baseCalculo;
    }

    public int getTipoCalculo() {
        return tipoCalculo.getTipo();
    }

    public List<ItemPromocao> getItens() {
        return itens;
    }
}
