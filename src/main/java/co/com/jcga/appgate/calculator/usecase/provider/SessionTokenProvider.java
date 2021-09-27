package co.com.jcga.appgate.calculator.usecase.provider;

import java.util.UUID;

import co.com.jcga.appgate.calculator.usecase.TokenUseCase;

/**
 * Defines the operations that provide information to the {@link TokenUseCase} use case.
 *
 * @author <a href='camilo.gaspar10@gmail.com'>Juan Camilo Gaspar Arias</a>
 * @version 1.0.0
 */
public interface SessionTokenProvider {

	/**
	 * Save the session token for future calculation.
	 *
	 * @param sessionToken Token for a calculating session.
	 * @return Operation result.
	 */
	OperationResult saveSessionToken(UUID sessionToken);
}
