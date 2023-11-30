package com.sams.attendancesystem.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sams.attendancesystem.Repository.SubjectRepository;
import com.sams.attendancesystem.models.Subject;

@RestController
@RequestMapping(path="/subject",method = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.PUT})
public class SubjectController {
    
    @Autowired
    private SubjectRepository subjectRepository;

    // Used to add New Subjects
    @PostMapping(path = "/add")
    public String addNewSubject(@RequestParam Integer subjectId, @RequestParam String subjectCode, @RequestParam String subjectName){
        Subject n=new Subject();
        n.setsubjectId(subjectId);
        n.setsubjectCode(subjectCode);
        n.setsubjectName(subjectName);
        subjectRepository.save(n);
        return "Saved";
    }

    // To Retrieve All Subjects
    @GetMapping(path = "/all")
    public Iterable<Subject> getAllUsers(){
        return subjectRepository.findAll();

    }

    // To retrieve subject using it's name
    @GetMapping(path = "/{subjectName}")
    public List<Subject> getSubject(@PathVariable String subjectName){
        List<Subject> subjects=subjectRepository.findBySubjectName(subjectName);
        return subjects;
    }

    // to delete any Subject using its id
    @DeleteMapping(path = "/delete/{subjectId}")
    public String delete(@PathVariable Integer subjectId){
        subjectRepository.deleteById(subjectId);
        return "Subject Deleted";
    }
}
