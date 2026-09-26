package com.paymelavo.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    boolean existsByTin(String tin);
    List<Employee> findByEmploymentStatus(EmploymentStatus status);
}
