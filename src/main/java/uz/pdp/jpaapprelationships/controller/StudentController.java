package uz.pdp.jpaapprelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.pdp.jpaapprelationships.entity.Adress;
import uz.pdp.jpaapprelationships.entity.Group;
import uz.pdp.jpaapprelationships.entity.Student;
import uz.pdp.jpaapprelationships.payload.StudentDto;
import uz.pdp.jpaapprelationships.repository.AdressRepositiry;
import uz.pdp.jpaapprelationships.repository.GroupRepositorty;
import uz.pdp.jpaapprelationships.repository.StudentRepository;

import javax.xml.ws.Action;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/student")
public class StudentController {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    GroupRepositorty groupRepositorty;
    @Autowired
    AdressRepositiry adressRepositiry;

    @PostMapping
    public String addStudent(@RequestBody StudentDto studentDto){
        Adress adress=new Adress();
        adress.setCity(studentDto.getCity());
        adress.setDitrict(studentDto.getDistrict());
        adress.setStreet(studentDto.getStreet());
        Adress savedAdress = adressRepositiry.save(adress);
        Optional<Group> optionalGroup = groupRepositorty.findById(studentDto.getGroupId());
        if (!optionalGroup.isPresent()){
            return "Not found group";
        }
        Student student=new Student();
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setAdress(savedAdress);
        student.setGroup(optionalGroup.get());
        studentRepository.save(student);
        return "Student saved";
    }
    @GetMapping("/allinCountry")
    public Page<Student> getStudent(@RequestParam int page){
        //select * from student limit 10 offset 0
        Pageable pageable= PageRequest.of(page,10);
        Page<Student> all = studentRepository.findAll(pageable);
        return all;

        /*List<Student> studentList = studentRepository.findAll();
        return studentList;*/
    }
    @GetMapping(value = "/studentsByUniversityId/{universityId}")
    public Page<Student> getStudentsByUniversityId(@PathVariable Integer universityId,@RequestParam int page){
        Pageable pageable= PageRequest.of(page,10);
        Page<Student> studentPage = studentRepository.findAllByGroup_Faculty_UniversityId(universityId, pageable);
        return studentPage;
        // List<Student> allByGroup_facultyId_university_id = studentRepository.findAllByGroup_FacultyId_University_id(universityId);
      //  return allByGroup_facultyId_university_id;
    }
    @GetMapping(value = "/groupIdStudents/{groupId}")
    public List<Student> getStudentByGroupId(@PathVariable Integer groupId){
        List<Student> allByGroupId = studentRepository.findAllByGroupId(groupId);
        return allByGroupId;

    }
    @GetMapping(value = "/studentsByFacultyId/{facultyId}")
    public List<Student> getStudentsByFacultyId(@PathVariable Integer facultyId){
        List<Student> allByGroup_faculty_id = studentRepository.findAllByGroup_Faculty_id(facultyId);
        return allByGroup_faculty_id;
    }
}
