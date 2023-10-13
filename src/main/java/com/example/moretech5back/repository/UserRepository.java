package online.mdfactory.backend.repository;

import online.mdfactory.backend.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByLogin(String login);

    List<Employee> findByLoginIn(List<String> logins);

    void deleteByLogin(String login);

}
