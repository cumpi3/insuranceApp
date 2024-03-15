package cz.pojisteniApp.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import cz.pojisteniApp.spring.models.UserDB;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
    public interface UserRepository extends JpaRepository<UserDB, Long> {
        Optional<UserDB> findByEmail(String Email);
    }
