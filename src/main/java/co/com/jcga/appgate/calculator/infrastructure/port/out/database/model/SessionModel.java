package co.com.jcga.appgate.calculator.infrastructure.port.out.database.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Singular;
import lombok.With;

/**
 * Session model definition.
 *
 * @author <a href='camilo.gaspar10@gmail.com'>Juan Camilo Gaspar Arias</a>
 * @version 1.0.0
 */
@Entity
@Table(schema = "calculator", name = "session")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SessionModel {

	/**
	 * Session token.
	 */
	@Id
	@Column(name = "id", updatable = false)
	private UUID id;

	/**
	 * Session operands.
	 */
	@Singular("operands")
	@With
	@OneToMany(fetch = FetchType.LAZY,
			cascade = CascadeType.ALL,
			mappedBy = "sessionModel")
	private List<OperandModel> operands;
}
