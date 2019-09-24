package az.pashabank.ls.msstudent.repository;

import az.pashabank.ls.msstudent.repository.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
}
