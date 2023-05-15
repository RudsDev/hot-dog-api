package ruds.desafio.alterdata.servicos.hotdog.api.exceptionhandler;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.time.Instant;

import lombok.Builder;
import lombok.Getter;

@JsonInclude(Include.NON_NULL)
@Getter
@Builder
public class ExceptionObject {

    private Integer status;
    private Instant dataHora;
    private String erro;
    private List<Campo> campos;

    @Getter
    @Builder
    public static class Campo {
        private String nome;
        private String mensagem;
    }

    public String getMessage() {
        return erro;
    }

    public int getError() {
        return status;
    }

}
