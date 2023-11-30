package com.sams.attendancesystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sams.attendancesystem.models.Branch;

public interface BranchRepository extends JpaRepository<Branch, String> {
    
}
