package co.com.jcga.appgate.calculator.entity;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Represents a operand object.
 *
 * @author <a href='camilo.gaspar10@gmail.com'>Juan Camilo Gaspar Arias</a>
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Operand {

	/**
	 * Operation id.
	 */
	private UUID id;

	/**
	 * Operation value.
	 */
	private BigDecimal value;

	/**
	 * Operation date.
	 */
	private Date date;
}
