package co.com.jcga.appgate.calculator.usecase;

import java.util.UUID;

import co.com.jcga.appgate.calculator.entity.Session;
import co.com.jcga.appgate.calculator.usecase.provider.SessionTokenProvider;

/**
 * Defines TokenUseCase implementation.
 *
 * @author <a href='camilo.gaspar10@gmail.com'>Juan Camilo Gaspar Arias</a>
 * @since 1.0.0
 */
public class TokenUseCaseImpl implements TokenUseCase {

	/**
	 * Calculator provider.
	 */
	private final SessionTokenProvider sessionTokenProvider;

	/**
	 * Class constructor
	 *
	 * @param sessionTokenProvider session token provider.
	 */
	public TokenUseCaseImpl(SessionTokenProvider sessionTokenProvider) {

		this.sessionTokenProvider = sessionTokenProvider;
	}

	/**
	 * Generate a token for the current session.
	 *
	 * @return The session token.
	 */
	@Override
	public Session generate() {

		final var sessionToken = UUID.randomUUID();
		sessionTokenProvider.saveSessionToken(sessionToken).ifHasErrorThrow();
		return Session.builder().id(sessionToken).build();
	}
}
