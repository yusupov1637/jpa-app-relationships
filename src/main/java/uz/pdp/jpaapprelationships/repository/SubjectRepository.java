package uz.pdp.jpaapprelationships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.jpaapprelationships.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject,Integer> {
    boolean existsByName(String name);
}
