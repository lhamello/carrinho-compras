package br.com.geofusion.cart.config.exception;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.geofusion.cart.domain.shared.DomainException;

@ControllerAdvice
public class ExceptionHelper {

	@ExceptionHandler(value = { DomainException.class })
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ResponseEntity<Object> handleMethodArgumentNotValidException(final DomainException exception) {
		final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		
		final List<ErrorRestDTO> errors = exception.getValidationErrors()
				.stream()
				.map(error -> ErrorRestDTO.create(LocalDateTime.now(), httpStatus.value(), httpStatus.getReasonPhrase(), error.getMessage()))
				.collect(Collectors.toList());
		
		return ResponseEntity
				.badRequest()
				.body(errors);
	}
	
	@ExceptionHandler(value = { MethodArgumentNotValidException.class })
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ResponseEntity<Object> handleMethodArgumentNotValidException(final MethodArgumentNotValidException exception) {
		final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		
		final List<ErrorRestDTO> errors = exception.getBindingResult()
				.getFieldErrors()
				.stream()
				.map(error -> ErrorRestDTO.create(LocalDateTime.now(), httpStatus.value(), httpStatus.getReasonPhrase(), error.getDefaultMessage()))
				.collect(Collectors.toList());
		
		return ResponseEntity
				.badRequest()
				.body(errors);
	}

	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<Object> handleException(final Exception exception) {
		final HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		
		return ResponseEntity
				.internalServerError()
				.body(ErrorRestDTO.create(LocalDateTime.now(), httpStatus.value(), httpStatus.getReasonPhrase(), "Ocorreu um erro interno no servidor, contate a equipe de desenvolvimento."));
	}
}