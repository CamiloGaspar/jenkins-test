package co.com.jcga.appgate.calculator.infrastructure.port.in.controller.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Represents a calculate response.
 *
 * @author <a href='camilo.gaspar10@gmail.com'>Juan Camilo Gaspar Arias</a>
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CalculateResponse {

	/**
	 * Calculation result.
	 */
	private BigDecimal result;
}
