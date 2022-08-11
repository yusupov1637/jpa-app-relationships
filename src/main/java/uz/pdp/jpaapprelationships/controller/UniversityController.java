package uz.pdp.jpaapprelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.jpaapprelationships.entity.Adress;
import uz.pdp.jpaapprelationships.entity.Faculty;
import uz.pdp.jpaapprelationships.entity.University;
import uz.pdp.jpaapprelationships.payload.FacultyDto;
import uz.pdp.jpaapprelationships.payload.UniversityDto;
import uz.pdp.jpaapprelationships.repository.AdressRepositiry;
import uz.pdp.jpaapprelationships.repository.FacultyRepository;
import uz.pdp.jpaapprelationships.repository.UniversityRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class UniversityController {

    @Autowired
    UniversityRepository universityRepository;

    @Autowired
    AdressRepositiry adressRepositiry;


    //Read all university
    @RequestMapping(value = "/university", method = RequestMethod.GET)
    public List<University> getUniversities() {
        List<University> universityList = universityRepository.findAll();
        return universityList;
    }

    @RequestMapping(value = "/university", method = RequestMethod.POST)
    public String addUniversity(@RequestBody UniversityDto universityDto) {
        Adress adress = new Adress();
        adress.setCity(universityDto.getCity());
        adress.setDitrict(universityDto.getDistrict());
        adress.setStreet(universityDto.getStreet());
        Adress saveAdress = adressRepositiry.save(adress);
        University university = new University();
        university.setName(universityDto.getName());
        university.setAdress(saveAdress);
        universityRepository.save(university);
        return "University Added";
    }


    @RequestMapping(value = "/university/{id}", method = RequestMethod.PUT)
    public String editUniversity(@PathVariable Integer id, @RequestBody UniversityDto universityDto) {
        Optional<University> byId = universityRepository.findById(id);
        if (byId.isPresent()) {
            University university = byId.get();
            university.setName(universityDto.getName());
            ///university adress
            Adress adress = university.getAdress();
            adress.setCity(universityDto.getCity());
            adress.setDitrict(universityDto.getDistrict());
            adress.setStreet(universityDto.getStreet());
            adressRepositiry.save(adress);
            universityRepository.save(university);
            return "University edited";
        }
        return "Not found";
    }


    @RequestMapping(value = "/university/{id}", method = RequestMethod.DELETE)
    public String deleteUniversity(@PathVariable Integer id) {
        universityRepository.deleteById(id);
        return "University deleted";
    }


}
