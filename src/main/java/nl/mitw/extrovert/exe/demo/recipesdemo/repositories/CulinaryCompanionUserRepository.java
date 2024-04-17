package nl.mitw.extrovert.exe.demo.recipesdemo.repositories;

import nl.mitw.extrovert.exe.demo.recipesdemo.model.CulinaryCompanionUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CulinaryCompanionUserRepository extends JpaRepository<CulinaryCompanionUser, Long> {

    Optional<CulinaryCompanionUser> findByUsername(String username);
}
