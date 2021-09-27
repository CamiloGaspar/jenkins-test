package co.com.jcga.appgate.calculator.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Class that starts the application
 *
 * @author <a href='camilo.gaspar10@gmail.com'>Juan Camilo Gaspar Arias</a>
 * @since 1.0.0
 */
@SpringBootApplication(scanBasePackages = "co.com.jcga.appgate.calculator")
@EntityScan("co.com.jcga.appgate.calculator.infrastructure.port.out.database.model")
@EnableJpaRepositories("co.com.jcga.appgate.calculator.infrastructure.port.out.database.repository")
public class AppgateCalculatorApplication {

	/**
	 * Application main class.
	 *
	 * @param args Application arguments.
	 */
	public static void main(String[] args) {

		SpringApplication.run(AppgateCalculatorApplication.class, args);
	}

}
