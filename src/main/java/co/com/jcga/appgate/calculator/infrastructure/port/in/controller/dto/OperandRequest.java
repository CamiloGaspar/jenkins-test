package co.com.jcga.appgate.calculator.infrastructure.port.in.controller.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Represents an operand request.
 *
 * @author <a href='camilo.gaspar10@gmail.com'>Juan Camilo Gaspar Arias</a>
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OperandRequest {

	/**
	 * operand value.
	 */
	@NotNull
	private BigDecimal operand;
}
