package co.com.jcga.appgate.calculator.usecase;

import co.com.jcga.appgate.calculator.entity.Session;

/**
 * Use case that defines the token generation process.
 *
 * @author <a href='camilo.gaspar10@gmail.com'>Juan Camilo Gaspar Arias</a>
 * @version 1.0.0
 */
public interface TokenUseCase {

	/**
	 * Generate a token for the current session.
	 *
	 * @return The session token.
	 */
	Session generate();
}
