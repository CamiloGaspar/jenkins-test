package co.com.jcga.appgate.calculator.infrastructure.port.in.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

import co.com.jcga.appgate.calculator.infrastructure.port.in.controller.dto.CalculateRequest;
import co.com.jcga.appgate.calculator.infrastructure.port.in.controller.dto.CalculateResponse;
import co.com.jcga.appgate.calculator.infrastructure.port.in.controller.dto.OperandRequest;
import co.com.jcga.appgate.calculator.usecase.CalculatorUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller that contain the endpoints to process the token generation process.
 *
 * @author <a href='camilo.gaspar10@gmail.com'>Juan Camilo Gaspar Arias</a>
 */
@RestController
@RequestMapping("/v1.0/calculate")
@RequiredArgsConstructor
public class CalculatorController {

	/**
	 * Calculator use case.
	 */
	private final CalculatorUseCase calculatorUseCase;

	/**
	 * Add the operand to the current session.
	 *
	 * @param request      Operation value.
	 * @param sessionToken Session token.
	 * @return An HTTP response with a created status.
	 */
	@PostMapping("/operand")
	public ResponseEntity<Void> addOperand(@Valid @RequestBody final OperandRequest request,
										   @Valid @NotNull @RequestHeader("session-token") final UUID sessionToken) {

		calculatorUseCase.addOperand(request.getOperand(), sessionToken);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	/**
	 * Execute the operation with the operands added previously.
	 *
	 * @param request      Operation type.
	 * @param sessionToken Session token.
	 * @return The operation result.
	 */
	@PostMapping("/operate")
	public ResponseEntity<CalculateResponse> addOperand(@RequestBody final CalculateRequest request,
														@Valid @NotNull @RequestHeader("session-token")
														final UUID sessionToken) {

		final BigDecimal result = calculatorUseCase.calculate(request.getOperation(), sessionToken);
		return ResponseEntity.status(HttpStatus.OK).body(CalculateResponse.builder().result(result).build());
	}
}
