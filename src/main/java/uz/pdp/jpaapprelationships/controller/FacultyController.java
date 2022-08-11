package uz.pdp.jpaapprelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.jpaapprelationships.entity.Faculty;
import uz.pdp.jpaapprelationships.entity.University;
import uz.pdp.jpaapprelationships.payload.FacultyDto;
import uz.pdp.jpaapprelationships.repository.FacultyRepository;
import uz.pdp.jpaapprelationships.repository.UniversityRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/faculty")
public class FacultyController {
    @Autowired
    FacultyRepository facultyRepository;
    @Autowired
    UniversityRepository universityRepository;


   // @RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public List<Faculty> getFaculty(){
        List<Faculty> facultyList=facultyRepository.findAll();
        return facultyList;
    }

 //  @RequestMapping(method = RequestMethod.POST)
    @PostMapping
    public String addFaculty(@RequestBody FacultyDto facultyDto){
        boolean existsByNameAndUniversityId = facultyRepository.existsByNameAndUniversityId(facultyDto.getName(), facultyDto.getUniversityId());
        if (existsByNameAndUniversityId){
            return "such faculty exists in this university";
        }


        Faculty faculty=new Faculty();
       faculty.setName(facultyDto.getName());
        Optional<University> optionalUniversity = universityRepository.findById(facultyDto.getUniversityId());
        if (!optionalUniversity.isPresent()){
            return "Not found";
        }
       /* boolean existsByName = facultyRepository.existsByName(faculty.getName());
        if (existsByName==true){
            return "Alredy exists";
        }*/
        faculty.setUniversity(optionalUniversity.get());
        facultyRepository.save(faculty);
        return "faculty added";
    }

    //univeristy id orqali qaytaradi
    @GetMapping(value = "/byUniversityId/{universityId}")
    public List<Faculty> getFacultyByUniId(@PathVariable Integer universityId){
        List<Faculty> allByUniversityId = facultyRepository.findAllByUniversityId(universityId);
        return allByUniversityId;
    }
    @DeleteMapping("/{id}")
    public String deleteFaculty(@PathVariable Integer id){
       try{
           facultyRepository.deleteById(id);
           return "faculty deleted";
       }catch (Exception e){
           return "error";
       }
    }
    @PutMapping("/{id}")
    public String editFaculty(@PathVariable Integer id,@RequestBody FacultyDto facultyDto){
        Optional<Faculty> facultyOptional = facultyRepository.findById(id);
        if (facultyOptional.isPresent()){
            Faculty faculty = facultyOptional.get();
            faculty.setName(facultyDto.getName());
            Optional<University> universityOptional = universityRepository.findById(facultyDto.getUniversityId());
            if (!universityOptional.isPresent()){
                return "not found";
            }
            faculty.setUniversity(universityOptional.get());
            facultyRepository.save(faculty);
            return "edited";

        }
        return "faculty not found";

    }
}
