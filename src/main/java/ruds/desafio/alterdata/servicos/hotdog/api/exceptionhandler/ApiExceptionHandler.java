package ruds.desafio.alterdata.servicos.hotdog.api.exceptionhandler;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;
import ruds.desafio.alterdata.servicos.hotdog.core.internationalization.Translator;
import ruds.desafio.alterdata.servicos.hotdog.domain.exception.EntidadeEmUsoException;
import ruds.desafio.alterdata.servicos.hotdog.domain.exception.EntidadeNaoEncontradaException;
import ruds.desafio.alterdata.servicos.hotdog.domain.exception.NegocioException;
import ruds.desafio.alterdata.servicos.hotdog.domain.exception.ServidorException;

@Slf4j
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * Trata exceções de BindException quando o cliente enviar parâmetros de filtro
	 * inválidos para relização de consultas.
	 */
	@Override
	protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		return handleValidationInternal(ex, headers, status, request, ex.getBindingResult());
	}
	
	/**
	 * Trata as exceções de MethodArgumentNotValidException, que são para erro de
	 * validação do Bean Validation.
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, 
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		return handleValidationInternal(ex, headers, status, request, ex.getBindingResult());
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleUncaught(Exception ex, WebRequest request) {
		
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;	
		String mensagem = Translator.toLocale("erro_de_sistema");

		log.error(ex.getMessage(), ex);
		
		ExceptionObject erro = createExceptionObjectBuilder(mensagem, status)
				.build();

		return handleExceptionInternal(ex, erro, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<?> handleNegocioExpection(NegocioException ex, WebRequest request) {
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String mensagem = ex.getMessage();
		
		ExceptionObject erro = createExceptionObjectBuilder(mensagem, status)
				.build();
		
		return super.handleExceptionInternal(ex, erro, new HttpHeaders(), status, request);
	}
        
        @ExceptionHandler(ServidorException.class)
        public ResponseEntity<?> handleServidorException(ServidorException ex, WebRequest request) {
            HttpStatus status = ex.getSTATUS();
            String mensagem = ex.getMessage();
            
            ExceptionObject erro = createExceptionObjectBuilder(mensagem, status)
                    .build();
            
            return super.handleExceptionInternal(ex, erro, new HttpHeaders(), status, request);
        }

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> handleEntidadeNaoEncontradaExpection(NegocioException ex, WebRequest request) {
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		String mensagem = ex.getMessage();
		
		ExceptionObject erro = createExceptionObjectBuilder(mensagem, status)
				.build();
		
		return super.handleExceptionInternal(ex, erro, new HttpHeaders(), status, request);
	}
	
	/**
	 * Trata EntidadeEmUsoExcpetion.
	 * Quando um recurso tenta ser removido, mas está associado a outro recurso cadastrado.
	 */
	@ExceptionHandler(EntidadeEmUsoException.class)
	public ResponseEntity<?> handleEntidadeEmUso(EntidadeEmUsoException ex, WebRequest request){
		
		HttpStatus status = HttpStatus.CONFLICT;
		String mensagem = ex.getMessage();
		
		ExceptionObject erro = createExceptionObjectBuilder(mensagem, status).build();
		
		return super.handleExceptionInternal(ex, erro, new HttpHeaders(), status, request);
	}
	
	private ResponseEntity<Object> handleValidationInternal(Exception ex, HttpHeaders headers, HttpStatus status, 
			WebRequest request, BindingResult bindingResult) {

		List<ExceptionObject.Campo> campos = bindingResult.getAllErrors().stream().map(error -> {
			String nome = ((FieldError) error).getField();
			String mensagem = Translator.toLocale(error);
			
			return ExceptionObject.Campo.builder()
					.nome(nome)
					.mensagem(mensagem)
					.build();
		}).collect(Collectors.toList());
		
		ExceptionObject erro = createExceptionObjectBuilder(Translator.toLocale("dados_invalidos"), status)
				.campos(campos)
				.build();

		return super.handleExceptionInternal(ex, erro, headers, status, request);
	}
	
	/**
	 * Este método recebe a mensagem de erro e o HTTPStatus, cria um builder para
	 * ExceptionObject, contendo as informações necessárias e retorna uma instância
	 * de ExceptionObjectBuilder.
	 * 
	 * Obs.: Builder criado através da anotação @Builder em ExceptionObject.
	 */
	private ExceptionObject.ExceptionObjectBuilder createExceptionObjectBuilder(String mensagem, HttpStatus status) {
		
		return ExceptionObject.builder()
				.status(status.value())
				.dataHora(Instant.now())
				.erro(mensagem);
	}
}
