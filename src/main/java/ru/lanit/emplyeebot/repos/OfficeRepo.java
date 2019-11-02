package ru.lanit.emplyeebot.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.lanit.emplyeebot.entities.Office;

@Repository
public interface OfficeRepo extends JpaRepository<Office, Long> {
}
