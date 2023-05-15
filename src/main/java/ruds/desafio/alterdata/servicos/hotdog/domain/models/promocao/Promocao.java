package ruds.desafio.alterdata.servicos.hotdog.domain.models.promocao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ruds.desafio.alterdata.servicos.hotdog.domain.models.calculos.Calculavel;

@Entity
public class Promocao implements Calculavel {

    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    private Integer tipoCalculo;
    private Double baseCalculo;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ItemPromocao> itens = new ArrayList<ItemPromocao>();

    @JsonIgnore
    @Transient
    private CalculoPromocao calculo;

    public Promocao() {}

    public Promocao(Long id, String nome, List<ItemPromocao> itens, Integer tipoCalculo, Double baseCalculo) {
        this.id = id;
        this.nome = nome;
        this.itens = itens;
        this.tipoCalculo = tipoCalculo;
        this.baseCalculo = baseCalculo;
    }

    public Promocao(Long id, String nome, Integer tipoCalculo, Double baseCalculo) {
        this.id = id;
        this.nome = nome;
        this.tipoCalculo = tipoCalculo;
        this.baseCalculo = baseCalculo;
    }

    public Promocao(String nome, List<ItemPromocao> itens, TipoCalculoPromocao tipoCalculo, Double baseCalculo) {
        this.nome = nome;
        this.itens = itens;
        this.tipoCalculo = tipoCalculo.getTipo();
        this.baseCalculo = baseCalculo;
    }

    public BigDecimal calculaPreco() {
        this.calculo = new CalculoPromocao(tipoCalculo, baseCalculo);
        Stream<BigDecimal> list = itens.stream().map((i) -> i.calculaPreco());
        return calculo.calculaPromocao(sum(list));
    }

    public Long getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public Integer getTipoCalculo() {
        return this.tipoCalculo;
    }

    public Double getBaseCalculo() {
        return this.baseCalculo;
    }

    public List<ItemPromocao> getItens() {
        return this.itens;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + this.id + "'" +
            ", nome='" + this.nome + "'" +
            ", itens='" + this.itens + "'" +
            ", preco='" + calculaPreco() + "'" +
            "}";
    }
}
