package co.com.jcga.appgate.calculator.entity;

import java.util.List;
import java.util.UUID;

import co.com.jcga.appgate.calculator.infrastructure.port.out.database.model.OperandModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Singular;

/**
 * Represents a session object.
 *
 * @author <a href='camilo.gaspar10@gmail.com'>Juan Camilo Gaspar Arias</a>
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Session {

	/**
	 * Session token.
	 */
	private UUID id;

	/**
	 * Session operands.
	 */
	@Singular("operands")
	private List<Operand> operands;
}
