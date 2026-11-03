package com.example.product.Exceptions;




import java.lang.module.ModuleDescriptor.Builder;
import java.time.Instant;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.CloseableThreadContext.Instance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.MethodArgumentBuilder;

@RestControllerAdvice
public class GlobelExceptionHandler {
	
	private static final Logger logger=LoggerFactory.getLogger(GlobelExceptionHandler.class);
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ApiError> handlerNotFound(ProductNotFoundException ex){
		return build(HttpStatus.NOT_FOUND, ex.getMessage(), Map.of());
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiError>handleValidation(MethodArgumentNotValidException
			e){
		Map<String, String>details=e.getBindingResult().getFieldErrors().stream().collect(Collectors.toMap(n->n.getField(), n->n.getDefaultMessage()));
		
		return build(HttpStatus.BAD_REQUEST, "Validation failed", details);
	}
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleUnexpected(Exception ex) {
        logger.error("Unexpected error", ex);
        return build(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error", Map.of());
    }
	private ResponseEntity<ApiError> build(HttpStatus status, String message, Map<String, String>details){
		return  ResponseEntity
				.status(status)
				.body(new ApiError(Instant.now(), status.value(), message, details));
	}

}
