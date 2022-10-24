package lt.codeacademy.callregistration.repositories;

import lt.codeacademy.callregistration.entities.CallRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CallRegistrationRepository extends JpaRepository <CallRegistration, Long> {
}
