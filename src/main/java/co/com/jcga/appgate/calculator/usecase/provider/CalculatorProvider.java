package co.com.jcga.appgate.calculator.usecase.provider;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Defines the operations that provide information to the use case.
 *
 * @author <a href='camilo.gaspar10@gmail.com'>Juan Camilo Gaspar Arias</a>
 * @version 1.0.0
 */
public interface CalculatorProvider {

	/**
	 * Add the giving operand to the giving session token.
	 *
	 * @param operand      Operation which will be added to calculate.
	 * @param sessionToken Token for a calculating session.
	 * @return Operation result
	 */
	OperationResult addOperand(BigDecimal operand, UUID sessionToken);

	/**
	 * Validate if the session token exist.
	 *
	 * @param sessionToken Session token.
	 * @return Operation result
	 */
	OperationResult getSession(UUID sessionToken);
}
