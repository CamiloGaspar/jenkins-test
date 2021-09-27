package co.com.jcga.appgate.calculator.usecase;

import java.math.BigDecimal;
import java.util.UUID;

import co.com.jcga.appgate.calculator.entity.Operation;

/**
 * Use case that defines the calculation process.
 *
 * @author <a href='camilo.gaspar10@gmail.com'>Juan Camilo Gaspar Arias</a>
 * @version 1.0.0
 */
public interface CalculatorUseCase {

	/**
	 * Add the giving operand to the giving session token.
	 *
	 * @param operand      Operation which will be added to calculate.
	 * @param sessionToken Token for a calculating session.
	 */
	void addOperand(BigDecimal operand, UUID sessionToken);

	/**
	 * Execute the operation with the operands added previously.
	 *
	 * @param operation      Operation type.
	 * @param sessionToken Session token.
	 * @return The operation result.
	 */
	BigDecimal calculate(Operation operation, UUID sessionToken);
}
