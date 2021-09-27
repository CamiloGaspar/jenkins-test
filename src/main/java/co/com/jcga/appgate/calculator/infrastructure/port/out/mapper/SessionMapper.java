/*
 * PayU Latam - Copyright (c) 2013 - 2021
 * http://www.payu.com.co
 * Date:   05/01/2021
 */

package co.com.jcga.appgate.calculator.infrastructure.port.out.mapper;

import co.com.jcga.appgate.calculator.entity.Session;
import co.com.jcga.appgate.calculator.infrastructure.port.out.database.model.SessionModel;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Session mapper class between core layer and infrastructure layer.
 *
 * @author <a href='camilo.gaspar10@gmail.com'>Juan Camilo Gaspar Arias</a>
 * @version 1.0.0
 * @since 1.0.0
 */
@Mapper(componentModel = "spring",
		unmappedTargetPolicy = ReportingPolicy.IGNORE,
		injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface SessionMapper {

	/**
	 * Method which translate a session from the use case layer to infrastructure layer
	 *
	 * @param session from core.
	 * @return {@link SessionModel}
	 */
	SessionModel toModel(Session session);

	/**
	 * Method which translate a session from the infrastructure layer to use case layer
	 *
	 * @param sessionModel from infrastructure layer.
	 * @return {@link Session}
	 */
	Session toEntity(SessionModel sessionModel);
}
