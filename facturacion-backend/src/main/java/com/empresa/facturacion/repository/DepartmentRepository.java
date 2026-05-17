package com.empresa.facturacion.repository;

import com.empresa.facturacion.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
