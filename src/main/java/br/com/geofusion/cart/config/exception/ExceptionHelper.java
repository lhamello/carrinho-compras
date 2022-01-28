package br.com.geofusion.cart.config.exception;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.geofusion.cart.domain.shared.DomainException;

@ControllerAdvice
public class ExceptionHelper {

	@ExceptionHandler(value = { DomainException.class })
	public ResponseEntity<List<ErrorRestDTO>> handleMethodArgumentNotValidException(final DomainException exception) {
		final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		
		return ResponseEntity.status(httpStatus)
				.body(exception.getValidationErrors()
						.stream()
						.map(error -> ErrorRestDTO.create(httpStatus, exception.getMessage()))
						.collect(Collectors.toList()));
	}
	
	@ExceptionHandler(value = { MethodArgumentNotValidException.class })
	public ResponseEntity<List<ErrorRestDTO>> handleMethodArgumentNotValidException(final MethodArgumentNotValidException exception) {
		final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		
		return ResponseEntity.status(httpStatus)
				.body(exception.getBindingResult()
						.getFieldErrors()
						.stream()
						.map(error -> ErrorRestDTO.create(httpStatus, error.getDefaultMessage()))
						.collect(Collectors.toList()));
	}

	@ExceptionHandler(value = { EntityNotFoundException.class })
	public ResponseEntity<ErrorRestDTO> handleEntityNotFoundException(final EntityNotFoundException exception) {
		final HttpStatus httpStatus = HttpStatus.NOT_FOUND;
		
		return ResponseEntity.status(httpStatus)
				.body(ErrorRestDTO.create(httpStatus, exception.getMessage()));
	}
	
	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<ErrorRestDTO> handleException(final Exception exception) {
		final HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		
		return ResponseEntity.status(httpStatus)
				.body(ErrorRestDTO.create(httpStatus, String.format("Erro inesperado: %s", exception.getMessage())));
	}
}