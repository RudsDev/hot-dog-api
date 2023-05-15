package ruds.desafio.alterdata.servicos.hotdog.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ServidorException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    private final HttpStatus STATUS;
    
    public ServidorException(String msg, HttpStatus status) {
        super(msg);
        this.STATUS = status;
    }
    
    public ServidorException(String msg, Throwable causa, HttpStatus status) {
        super(msg, causa);
        this.STATUS = status;
    }
}
