package com.junior.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.junior.university.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
