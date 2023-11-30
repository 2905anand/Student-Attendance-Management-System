package com.sams.attendancesystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sams.attendancesystem.Repository.BranchRepository;
import com.sams.attendancesystem.models.Branch;

@RestController
@RequestMapping(path = "/branch", method = {RequestMethod.DELETE, RequestMethod.GET,RequestMethod.PUT})
public class BranchController {
    
    @Autowired
    private BranchRepository branchRepository;

    // Adding New Branch 
    @PostMapping(path = "/add")
    public String addNewBranch(@RequestParam String branchId, @RequestParam String branchName){
        Branch n = new Branch();
        n.setBranchId(branchId);
        n.setBranchName(branchName);
        branchRepository.save(n);
        return "Saved";
    }


    // Display All branches
    @GetMapping(path = "/all")
    public Iterable<Branch> getAllBranch(){
        return branchRepository.findAll();
    }

    @DeleteMapping(path="/delete/{branchId}")
    public String deleteBranch(@PathVariable String branchId){
        branchRepository.deleteById(branchId);
        return "Branch Deleted";
    }
}
