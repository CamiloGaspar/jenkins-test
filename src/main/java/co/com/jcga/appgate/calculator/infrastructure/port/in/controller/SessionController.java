package co.com.jcga.appgate.calculator.infrastructure.port.in.controller;

import co.com.jcga.appgate.calculator.entity.Session;
import co.com.jcga.appgate.calculator.usecase.TokenUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller that contain the endpoints to process the token generation process.
 *
 * @author <a href='camilo.gaspar10@gmail.com'>Juan Camilo Gaspar Arias</a>
 */
@RestController
@RequestMapping("/v1.0/token")
@RequiredArgsConstructor
public class SessionController {

	/**
	 * Token generation use case.
	 */
	private final TokenUseCase tokenUseCase;

	/**
	 * Process the request for the token generation process.
	 *
	 * @return Session token.
	 */
	@GetMapping
	public ResponseEntity<Session> getToken() {

		return ResponseEntity.status(HttpStatus.CREATED)
							 .body(tokenUseCase.generate());
	}
}
