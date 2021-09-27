package co.com.jcga.appgate.calculator.usecase.provider;

import java.util.List;
import java.util.stream.Collectors;

import co.com.jcga.appgate.calculator.entity.Session;
import co.com.jcga.appgate.calculator.usecase.exception.OperationWithErrorException;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

/**
 * Represents an operation response.
 *
 * @author <a href='camilo.gaspar10@gmail.com'>Juan Camilo Gaspar Arias</a>
 */
@Getter
@Builder
public class OperationResult {

	/**
	 * Session information
	 */
	private final Session session;

	/**
	 * Error messages.
	 */
	@Singular("errorMessages")
	private final List<String> errorMessages;

	/**
	 * Validate if the operation has errors.
	 */
	public void ifHasErrorThrow() {

		if (errorMessages != null && !errorMessages.isEmpty()) {
			final var errors = errorMessages.stream()
											.collect(Collectors.joining(".", "[", "]"));
			throw new OperationWithErrorException(errors);
		}
	}
}
