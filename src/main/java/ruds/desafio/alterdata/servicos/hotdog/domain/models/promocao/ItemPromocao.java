package ruds.desafio.alterdata.servicos.hotdog.domain.models.promocao;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Getter;
import ruds.desafio.alterdata.servicos.hotdog.domain.models.Lanche;

@Entity
@Getter
public class ItemPromocao {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    Lanche lanche;

    Long quantidade;

    public ItemPromocao() {}

    public ItemPromocao(Long id, Long quantidade) {
        this.id = id;
        this.quantidade = quantidade;
    }

    public ItemPromocao(Long quantidade, Lanche lanche) {
        this.quantidade = quantidade;
        this.lanche = lanche;
    }

    public ItemPromocao(Long id, Long quantidade, Lanche lanche) {
        this.id = id;
        this.quantidade = quantidade;
        this.lanche = lanche;
    }

    public BigDecimal calculaPreco() {
        BigDecimal preco = lanche.calculaPreco();
        BigDecimal multiplicand = BigDecimal.valueOf(quantidade);
        return preco.multiply(multiplicand);
    }
}
