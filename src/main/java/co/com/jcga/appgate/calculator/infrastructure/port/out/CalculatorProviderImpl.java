package co.com.jcga.appgate.calculator.infrastructure.port.out;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;

import co.com.jcga.appgate.calculator.infrastructure.port.out.database.model.OperandModel;
import co.com.jcga.appgate.calculator.infrastructure.port.out.database.model.SessionModel;
import co.com.jcga.appgate.calculator.infrastructure.port.out.database.repository.SessionRepository;
import co.com.jcga.appgate.calculator.infrastructure.port.out.mapper.SessionMapper;
import co.com.jcga.appgate.calculator.usecase.exception.OperationWithErrorException;
import co.com.jcga.appgate.calculator.usecase.provider.CalculatorProvider;
import co.com.jcga.appgate.calculator.usecase.provider.OperationResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Defines CalculatorProvider implementation.
 *
 * @author <a href='camilo.gaspar10@gmail.com'>Juan Camilo Gaspar Arias</a>
 * @since 1.0.0
 */
@Component
@RequiredArgsConstructor
public class CalculatorProviderImpl implements CalculatorProvider {

	/**
	 * Session mapper.
	 */
	private final SessionMapper sessionMapper;

	/**
	 * Session repository.
	 */
	private final SessionRepository sessionRepository;

	/**
	 * Add the giving operand to the giving session token.
	 *
	 * @param operand      Operation which will be added to calculate.
	 * @param sessionToken Token for a calculating session.
	 */
	@Override
	public OperationResult addOperand(final BigDecimal operand, final UUID sessionToken) {

		final var resultBuilder = OperationResult.builder();
		try {
			sessionRepository.save(buildOperand(operand, sessionToken));
		} catch (RuntimeException ex) {
			resultBuilder.errorMessages(ex.getMessage());
		}
		return resultBuilder.build();
	}

	/**
	 * Build a {@link SessionModel} object.
	 *
	 * @param operand      Operation value.
	 * @param sessionToken Session token
	 * @return {@link SessionModel} object with the giving data.
	 */
	private SessionModel buildOperand(final BigDecimal operand, final UUID sessionToken) {

		final var sessionModel = sessionRepository.findById(sessionToken)
												  .orElseThrow(() -> new OperationWithErrorException(
														  String.format("Session with id [%s] don't exist",
																		sessionToken)));
		sessionModel.getOperands().add(OperandModel.builder()
												   .value(operand)
												   .date(new Date(System.currentTimeMillis()))
												   .sessionModel(sessionModel)
												   .build());
		return sessionModel;
	}

	/**
	 * Validate if the session token exist.
	 *
	 * @param sessionToken Session token.
	 */
	@Override
	public OperationResult getSession(final UUID sessionToken) {

		final var resultBuilder = OperationResult.builder();
		final var optionalSessionModel = sessionRepository.findById(sessionToken);
		if (optionalSessionModel.isEmpty()) {
			return resultBuilder.errorMessages("The session token doesn't exist. Please generate it").build();
		}
		return resultBuilder.session(sessionMapper.toEntity(optionalSessionModel.get())).build();
	}
}
