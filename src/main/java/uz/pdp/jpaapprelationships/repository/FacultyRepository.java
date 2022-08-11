package uz.pdp.jpaapprelationships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.jpaapprelationships.entity.Faculty;

import java.util.List;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Integer> {
    /*boolean existsByName(String name);*/
    boolean existsByNameAndUniversityId(String name, Integer university_id);
    List<Faculty> findAllByUniversityId(Integer university_id);
}
