package uz.pdp.jpaapprelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.jpaapprelationships.entity.Faculty;
import uz.pdp.jpaapprelationships.entity.Group;
import uz.pdp.jpaapprelationships.payload.GroupDto;
import uz.pdp.jpaapprelationships.repository.FacultyRepository;
import uz.pdp.jpaapprelationships.repository.GroupRepositorty;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/group")
public class GroupController {
    @Autowired
    GroupRepositorty groupRepositorty;
    @Autowired
    FacultyRepository facultyRepository;



    @GetMapping
    public List<Group> getGroups(){
        List<Group> groupList = groupRepositorty.findAll();
        return groupList;
    }
    @GetMapping(value = "/byUniversityId/{universityId}")
    public List<Group> getGroupByUni(@PathVariable Integer universityId){
        List<Group> allByFaculty_university_id = groupRepositorty.findAllByFaculty_University_Id(universityId);
        List<Group> groupsByUniversityId = groupRepositorty.getGroupsByUniversityId(universityId);
        List<Group> groupsByUniversityIdNative = groupRepositorty.getGroupsByUniversityIdNative(universityId);
        return allByFaculty_university_id;
    }
    @PostMapping
    public String addGroup(@RequestBody GroupDto groupDto){
        Group group=new Group();
        group.setName(groupDto.getName());
        Optional<Faculty> optionalFaculty = facultyRepository.findById(groupDto.getFacultyId());
        if(!optionalFaculty.isPresent()){
            return "not found";
        }
        group.setFaculty(optionalFaculty.get());
        groupRepositorty.save(group);
        return "Group added";
    }
}
