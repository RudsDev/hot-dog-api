package ruds.desafio.alterdata.servicos.hotdog.domain.models.promocao;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

import lombok.Getter;
import ruds.desafio.alterdata.servicos.hotdog.core.internationalization.Translator;
import ruds.desafio.alterdata.servicos.hotdog.domain.exception.NegocioException;

@Getter
public enum TipoCalculoPromocao {
    
    ACRESCIMO_VALOR(1),
    DESCONTO_VALOR(2),
    ACRESCIMO_PORCENTAGEM(3),
    DESCONTO_PORCENTAGEM(4);

    private int tipo;

    TipoCalculoPromocao(int tipo) {
		  this.tipo = tipo;
    }

    public static TipoCalculoPromocao get(Integer value) {
      Stream<TipoCalculoPromocao> stream = Arrays.stream(TipoCalculoPromocao.values());
      Predicate<TipoCalculoPromocao> predicate = (t) -> t.tipo == value;
      Optional<TipoCalculoPromocao> optional = stream.filter(predicate).findFirst();
      if (optional.isEmpty()) throw new NegocioException(Translator.toLocale("tipo_promocao_desconhecido"));
      return optional.get();
    }
}
