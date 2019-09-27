package com.junior.university.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.junior.university.model.Degree;

@Repository
public interface DegreeRepository extends JpaRepository<Degree, Long> {

    Optional<Degree> findByName(final String name);
}
