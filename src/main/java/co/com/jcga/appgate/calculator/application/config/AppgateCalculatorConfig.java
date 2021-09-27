package co.com.jcga.appgate.calculator.application.config;

import co.com.jcga.appgate.calculator.usecase.CalculatorUseCase;
import co.com.jcga.appgate.calculator.usecase.CalculatorUseCaseImpl;
import co.com.jcga.appgate.calculator.usecase.TokenUseCase;
import co.com.jcga.appgate.calculator.usecase.TokenUseCaseImpl;
import co.com.jcga.appgate.calculator.usecase.provider.CalculatorProvider;
import co.com.jcga.appgate.calculator.usecase.provider.SessionTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The application configuration
 *
 * @author <a href='camilo.gaspar10@gmail.com'>Juan Camilo Gaspar Arias</a>
 * @version 1.0.0
 * @since 1.0.0
 */
@Configuration
@RequiredArgsConstructor
public class AppgateCalculatorConfig {

	/**
	 * The {@link TokenUseCase} bean used to generate the session token.
	 *
	 * @return TokenUseCase Bean
	 */
	@Bean
	public TokenUseCase getTokenUseCase(final SessionTokenProvider sessionTokenProvider) {

		return new TokenUseCaseImpl(sessionTokenProvider);
	}

	/**
	 * The {@link CalculatorUseCase} bean used to make the calculates.
	 *
	 * @return CalculatorUseCase Bean
	 */
	@Bean
	public CalculatorUseCase getCalculatorUseCase(final CalculatorProvider calculatorProvider) {

		return new CalculatorUseCaseImpl(calculatorProvider);
	}
}
