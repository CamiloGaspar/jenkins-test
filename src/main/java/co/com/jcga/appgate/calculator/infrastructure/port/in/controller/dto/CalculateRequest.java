package co.com.jcga.appgate.calculator.infrastructure.port.in.controller.dto;

import co.com.jcga.appgate.calculator.entity.Operation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Represents a calculate request.
 *
 * @author <a href='camilo.gaspar10@gmail.com'>Juan Camilo Gaspar Arias</a>
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CalculateRequest {

	/**
	 * operation value.
	 */
	private Operation operation;
}
