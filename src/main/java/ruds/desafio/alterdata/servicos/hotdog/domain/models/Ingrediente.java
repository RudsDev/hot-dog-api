package ruds.desafio.alterdata.servicos.hotdog.domain.models;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Ingrediente {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    protected Long id;
	private String nome;
    private BigDecimal preco;

    @JsonIgnore
    @ManyToMany(mappedBy = "ingredientes")
    private List<Lanche> lanches;

    public Ingrediente(){}

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
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public BigDecimal getPreco() {
        return this.preco;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + this.id + "'" +
            " nome='" + this.nome + "'" +
            ", preco='" + this.preco + "'" +
            "}";
    }

}