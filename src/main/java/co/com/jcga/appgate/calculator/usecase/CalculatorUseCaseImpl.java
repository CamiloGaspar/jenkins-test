package co.com.jcga.appgate.calculator.usecase;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import co.com.jcga.appgate.calculator.entity.Operand;
import co.com.jcga.appgate.calculator.entity.Operation;
import co.com.jcga.appgate.calculator.usecase.exception.OperationWithErrorException;
import co.com.jcga.appgate.calculator.usecase.provider.CalculatorProvider;

/**
 * Defines CalculatorUseCase implementation.
 *
 * @author <a href='camilo.gaspar10@gmail.com'>Juan Camilo Gaspar Arias</a>
 * @since 1.0.0
 */
public class CalculatorUseCaseImpl implements CalculatorUseCase {

	/**
	 * Calculator provider.
	 */
	private final CalculatorProvider calculatorProvider;

	/**
	 * Class constructor
	 *
	 * @param calculatorProvider calculator provider.
	 */
	public CalculatorUseCaseImpl(final CalculatorProvider calculatorProvider) {

		this.calculatorProvider = calculatorProvider;
	}

	/**
	 * Add the giving operand to the giving session token.
	 *
	 * @param operand      Operation which will be added to calculate.
	 * @param sessionToken Token for a calculating session.
	 */
	@Override
	public void addOperand(final BigDecimal operand, final UUID sessionToken) {

		validateSessionToken(sessionToken);
		calculatorProvider.addOperand(operand, sessionToken)
						  .ifHasErrorThrow();
	}

	/**
	 * Execute the operation with the operands added previously.
	 *
	 * @param operation    Operation type.
	 * @param sessionToken Session token.
	 * @return The operation result.
	 */
	@Override
	public BigDecimal calculate(final Operation operation, final UUID sessionToken) {

		final var operationResult = calculatorProvider.getSession(sessionToken);
		operationResult.ifHasErrorThrow();

		final var operands = operationResult.getSession().getOperands().stream()
											.sorted(Comparator.comparing(Operand::getDate))
											.map(Operand::getValue)
											.collect(Collectors.toList());
		return executeCalculate(operands, operation);
	}

	/**
	 * Execute to calculate to the current session.
	 *
	 * @param operands  The calculate operands.
	 * @param operation The calculate operation.
	 * @return Calculate result.
	 */
	private BigDecimal executeCalculate(final List<BigDecimal> operands,
										final Operation operation) {

		switch (operation) {
		case ADDITION:
			return executeAddition(operands);
		case SUBTRACTION:
			return executeSubtraction(operands);
		case MULTIPLICATION:
			return executeMultiplication(operands);
		case DIVISION:
			if (operands.stream().anyMatch(bigDecimal -> bigDecimal.intValue() == 0)) {
				throw new OperationWithErrorException(
						"The operand list contains a zero, please select an operation other than division.");
			}
			return executeDivision(operands);
		case POWER:
			return executePower(operands);
		default:
			throw new IllegalStateException(String.format("Invalid operation [%s]", operation));
		}
	}

	/**
	 * Execute power between all operands elements.
	 *
	 * @param operands Elements with which the calculations will be made.
	 * @return Operation result.
	 */
	private BigDecimal executePower(final List<BigDecimal> operands) {

		return operands.stream()
					   .reduce((bigDecimal, bigDecimal2) -> bigDecimal.pow(bigDecimal2.intValue()))
					   .orElse(BigDecimal.ZERO);
	}

	/**
	 * Execute division between all operands elements.
	 *
	 * @param operands Elements with which the calculations will be made.
	 * @return Operation result.
	 */
	private BigDecimal executeDivision(final List<BigDecimal> operands) {

		return operands.stream()
					   .reduce(BigDecimal::divide)
					   .orElse(BigDecimal.ZERO);
	}

	/**
	 * Execute multiplication between all operands elements.
	 *
	 * @param operands Elements with which the calculations will be made.
	 * @return Operation result.
	 */
	private BigDecimal executeMultiplication(final List<BigDecimal> operands) {

		return operands.stream()
					   .reduce(BigDecimal::multiply)
					   .orElse(BigDecimal.ZERO);
	}

	/**
	 * Execute subtraction between all operands elements.
	 *
	 * @param operands Elements with which the calculations will be made.
	 * @return Operation result.
	 */
	private BigDecimal executeSubtraction(final List<BigDecimal> operands) {

		return operands.stream()
					   .reduce(BigDecimal::subtract)
					   .orElse(BigDecimal.ZERO);
	}

	/**
	 * Execute addition between all operands elements.
	 *
	 * @param operands Elements with which the calculations will be made.
	 * @return Operation result.
	 */
	private BigDecimal executeAddition(final List<BigDecimal> operands) {

		return operands.stream()
					   .reduce(BigDecimal::add)
					   .orElse(BigDecimal.ZERO);
	}

	/**
	 * Validate if the session token exist.
	 *
	 * @param sessionToken Session token.
	 */
	private void validateSessionToken(final UUID sessionToken) {

		calculatorProvider.getSession(sessionToken)
						  .ifHasErrorThrow();
	}
}
