package com.ajt.ems.repositories;

import com.ajt.ems.models.db.ems.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employees, Integer> {
}
