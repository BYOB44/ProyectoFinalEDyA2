package com.empresa.facturacion.service;

import com.empresa.facturacion.dto.DepartmentRequest;
import com.empresa.facturacion.dto.DepartmentResponse;
import com.empresa.facturacion.dto.TreeNodeResponse;
import com.empresa.facturacion.model.Department;
import com.empresa.facturacion.repository.DepartmentRepository;
import com.empresa.facturacion.structures.DepartmentTree;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final MapperService mapperService;

    public DepartmentService(DepartmentRepository departmentRepository, MapperService mapperService) {
        this.departmentRepository = departmentRepository;
        this.mapperService = mapperService;
    }

    public List<DepartmentResponse> findAll() {
        return departmentRepository.findAll()
                .stream()
                .map(mapperService::toDepartmentResponse)
                .toList();
    }

    public DepartmentResponse create(DepartmentRequest request) {
        Department parent = null;
        if (request.getParentId() != null) {
            parent = departmentRepository.findById(request.getParentId())
                    .orElseThrow(() -> new RuntimeException("Departamento padre no encontrado"));
        }
        Department department = new Department(request.getName(), parent);
        return mapperService.toDepartmentResponse(departmentRepository.save(department));
    }

    public List<TreeNodeResponse> getTree() {
        DepartmentTree tree = new DepartmentTree();
        return tree.buildTree(departmentRepository.findAll());
    }
}
