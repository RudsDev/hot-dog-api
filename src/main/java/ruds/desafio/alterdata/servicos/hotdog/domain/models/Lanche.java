package ruds.desafio.alterdata.servicos.hotdog.domain.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import ruds.desafio.alterdata.servicos.hotdog.domain.models.calculos.Calculavel;
import ruds.desafio.alterdata.servicos.hotdog.domain.models.promocao.Promocao;

@Entity
@Getter
public class Lanche implements Calculavel {

    @Id
    @GeneratedValue
    private Long id;

	private String nome;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Ingrediente> ingredientes = new ArrayList<Ingrediente>();

    @JsonIgnore
    @ManyToMany(mappedBy = "itens")
    private List<Promocao> promocoes;

    public Lanche() {}

    public Lanche(Long id, String nome, List<Ingrediente> ingredientes) {
        this.id = id;
        this.nome = nome;
        this.ingredientes = ingredientes;
    }

    public Lanche(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Lanche(String nome) {
        this.nome = nome;
    }

    public Lanche addIngrediente(Ingrediente ingrediente) {
        this.ingredientes.add(ingrediente);
        return this;
    }

    public void addIngredientes(List<Ingrediente> ingredientes) {
        this.ingredientes.addAll(ingredientes);
    }

    public BigDecimal calculaPreco() {
        Stream<BigDecimal> list = ingredientes.stream().map((i) -> i.getPreco());
        return sum(list);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + this.id + "'" +
            " nome='" + this.nome + "'" +
            ", ingredientes='" + this.ingredientes + "'" +
            "}";
    }

}
