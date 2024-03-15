package cz.pojisteniApp.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import cz.pojisteniApp.spring.models.AccidentDB;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AccidentRepository extends JpaRepository<AccidentDB, Long> {
}