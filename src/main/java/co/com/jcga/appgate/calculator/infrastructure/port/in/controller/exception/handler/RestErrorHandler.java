/*
 * PayU Latam - Copyright (c) 2013 - 2021
 * http://www.payu.com.co
 * Date:   24/09/2021
 */
package co.com.jcga.appgate.calculator.infrastructure.port.in.controller.exception.handler;

import co.com.jcga.appgate.calculator.infrastructure.port.in.controller.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.NonNull;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Exception handler for the REST controllers.
 *
 * @author <a href='camilo.gaspar10@gmail.com'>Juan Camilo Gaspar Arias</a>
 * @version 1.0.0
 * @since 1.0.0
 */
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class RestErrorHandler extends ResponseEntityExceptionHandler {

	/**
	 * Customize the response for MethodArgumentNotValidException.
	 * <p>This method delegates to {@link #handleExceptionInternal}.
	 *
	 * @param ex      the exception
	 * @param headers the headers to be written to the response
	 * @param status  the selected response status
	 * @param request the current request
	 * @return a {@code ResponseEntity} instance
	 */
	@Override
	@NonNull
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
																  @NonNull HttpHeaders headers,
																  @NonNull HttpStatus status,
																  @NonNull WebRequest request) {

		log.info(ex.getMessage());
		final StringBuilder messageBuilder = new StringBuilder("Check Request: ");
		ex.getBindingResult().getAllErrors().forEach(objectError -> messageBuilder.append("[")
																				  .append(objectError.getDefaultMessage())
																				  .append("]"));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
							 .body(ErrorResponse.builder()
												.errorMessage(messageBuilder.toString())
												.build());
	}

	/**
	 * Handle an {@link MissingRequestHeaderException}.
	 *
	 * @param ex MissingRequestHeaderException.
	 */
	@ResponseBody
	@ExceptionHandler(MissingRequestHeaderException.class)
	public ResponseEntity<ErrorResponse> missingRequestHeaderExceptionHandler(final MissingRequestHeaderException ex) {

		log.info(ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
							 .body(ErrorResponse.builder()
												.errorMessage(ex.getMessage())
												.build());
	}

	/**
	 * Customize the response for HttpMessageNotReadableException.
	 * <p>This method delegates to {@link #handleExceptionInternal}.
	 *
	 * @param ex      the exception
	 * @param headers the headers to be written to the response
	 * @param status  the selected response status
	 * @param request the current request
	 * @return a {@code ResponseEntity} instance
	 */
	@Override
	@NonNull
	protected ResponseEntity<Object> handleHttpMessageNotReadable(@NonNull HttpMessageNotReadableException ex,
																  @NonNull HttpHeaders headers,
																  @NonNull HttpStatus status,
																  @NonNull WebRequest request) {

		log.info(ex.getMessage(), ex);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
							 .body(ErrorResponse.builder()
												.errorMessage(ex.getMessage())
												.build());

	}

	/**
	 * Customize the response for BindException.
	 * <p>This method delegates to {@link #handleExceptionInternal}.
	 *
	 * @param ex      the exception
	 * @param headers the headers to be written to the response
	 * @param status  the selected response status
	 * @param request the current request
	 * @return a {@code ResponseEntity} instance
	 */
	@Override
	@NonNull
	protected ResponseEntity<Object> handleBindException(@NonNull BindException ex, @NonNull HttpHeaders headers,
														 @NonNull HttpStatus status, @NonNull WebRequest request) {

		log.info("handleBindException: {}", ex.getMessage());
		final StringBuilder messageBuilder = new StringBuilder("Check Request: ");
		ex.getAllErrors().forEach(objectError -> messageBuilder.append("[")
															   .append(objectError.getDefaultMessage())
															   .append("]"));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
							 .body(ErrorResponse.builder()
												.errorMessage(messageBuilder.toString())
												.build());
	}

	/**
	 * Handle an {@link RuntimeException}.
	 *
	 * @param ex Exception.
	 */
	@ResponseBody
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorResponse> internalErrorHandler(final RuntimeException ex) {

		log.warn("An unexpected error occurred while processing the request:[{}]", ex.getMessage(), ex);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
							 .body(ErrorResponse.builder()
												.errorMessage(ex.getMessage())
												.build());
	}

}
