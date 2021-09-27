package co.com.jcga.appgate.calculator.infrastructure.port.out;

import java.util.UUID;

import co.com.jcga.appgate.calculator.infrastructure.port.out.database.model.SessionModel;
import co.com.jcga.appgate.calculator.infrastructure.port.out.database.repository.SessionRepository;
import co.com.jcga.appgate.calculator.usecase.provider.OperationResult;
import co.com.jcga.appgate.calculator.usecase.provider.SessionTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Defines CalculatorProvider implementation.
 *
 * @author <a href='camilo.gaspar10@gmail.com'>Juan Camilo Gaspar Arias</a>
 * @since 1.0.0
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class SessionTokenProviderImpl implements SessionTokenProvider {

	/**
	 * Session repository.
	 */
	private final SessionRepository sessionRepository;

	/**
	 * Save the session token for future calculation.
	 *
	 * @param sessionToken Token for a calculating session.
	 * @return Operation result.
	 */
	@Override
	public OperationResult saveSessionToken(final UUID sessionToken) {

		final var resultBuilder = OperationResult.builder();
		try {
			sessionRepository.save(SessionModel.builder().id(sessionToken).build());
		} catch (RuntimeException ex) {
			resultBuilder.errorMessages(ex.getMessage());
		}
		return resultBuilder.build();
	}
}
