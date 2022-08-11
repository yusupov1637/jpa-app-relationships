package uz.pdp.jpaapprelationships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.jpaapprelationships.entity.University;

@Repository
public interface UniversityRepository extends JpaRepository<University,Integer> {

}
