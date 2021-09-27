package co.com.jcga.appgate.calculator.infrastructure.port.out.database.repository;

import java.util.UUID;

import co.com.jcga.appgate.calculator.infrastructure.port.out.database.model.SessionModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends CrudRepository<SessionModel, UUID> {

}
