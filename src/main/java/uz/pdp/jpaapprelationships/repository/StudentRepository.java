package uz.pdp.jpaapprelationships.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.jpaapprelationships.entity.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Integer> {
        List<Student> findAllByGroupId(Integer group_id);
        List<Student> findAllByGroup_FacultyId_University_id(Integer group_faculty_university_id);
        List<Student> findAllByGroup_Faculty_id(Integer group_faculty_id);
        Page<Student> findAllByGroup_Faculty_UniversityId(Integer group_faculty_university_id, Pageable pageable);
}
