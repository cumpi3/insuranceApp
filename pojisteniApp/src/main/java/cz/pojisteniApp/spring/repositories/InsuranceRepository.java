package cz.pojisteniApp.spring.repositories;

import cz.pojisteniApp.spring.models.UserDB;
import org.springframework.data.jpa.repository.JpaRepository;
import cz.pojisteniApp.spring.models.InsuranceDB;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface InsuranceRepository extends JpaRepository<InsuranceDB, Long> {
    List<InsuranceDB> findByUser(UserDB user);
}