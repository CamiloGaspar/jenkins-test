package co.com.jcga.appgate.calculator.infrastructure.port.out.database.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

/**
 * Session model definition.
 *
 * @author <a href='camilo.gaspar10@gmail.com'>Juan Camilo Gaspar Arias</a>
 * @version 1.0.0
 */
@Entity
@Table(schema = "calculator", name = "operand")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OperandModel {

	/**
	 * Operation id.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false)
	private UUID id;

	/**
	 * Operation value.
	 */
	@Column(name = "value")
	private BigDecimal value;

	/**
	 * Operation date.
	 */
	@Column(name = "date", updatable = false)
	private Date date;

	/**
	 * Pricing profile for the account.
	 */
	@ManyToOne(fetch = FetchType.LAZY,
			cascade = CascadeType.ALL)
	@JoinColumn(name = "session_id", nullable = false)
	private SessionModel sessionModel;
}
