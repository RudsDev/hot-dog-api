package ruds.desafio.alterdata.servicos.hotdog.domain.models.calculos;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

@Component
public interface Calculavel {

	default BigDecimal sum(Stream<BigDecimal> itens) {
		final BinaryOperator<BigDecimal> sum = (BigDecimal x, BigDecimal y) -> x.add(y);
		Optional<BigDecimal> soma = itens.reduce(sum);
		return soma.isPresent() ? soma.get() : BigDecimal.valueOf(0);
	}
}
