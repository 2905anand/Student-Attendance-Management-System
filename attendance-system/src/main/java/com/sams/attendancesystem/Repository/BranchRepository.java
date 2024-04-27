package com.sams.attendancesystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sams.attendancesystem.models.Branch;
import java.util.List;


public interface BranchRepository extends JpaRepository<Branch, String> {

    List<Branch> findByBranchName(String branchName);
    
}
