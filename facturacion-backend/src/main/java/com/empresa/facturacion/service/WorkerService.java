package com.empresa.facturacion.service;

import com.empresa.facturacion.dto.WorkerRequest;
import com.empresa.facturacion.dto.WorkerResponse;
import com.empresa.facturacion.model.Department;
import com.empresa.facturacion.model.Role;
import com.empresa.facturacion.model.UserAccount;
import com.empresa.facturacion.model.Worker;
import com.empresa.facturacion.repository.DepartmentRepository;
import com.empresa.facturacion.repository.UserAccountRepository;
import com.empresa.facturacion.repository.WorkerRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class WorkerService {
    private final WorkerRepository workerRepository;
    private final DepartmentRepository departmentRepository;
    private final UserAccountRepository userAccountRepository;
    private final PasswordEncoder passwordEncoder;
    private final MapperService mapperService;

    public WorkerService(WorkerRepository workerRepository, DepartmentRepository departmentRepository, UserAccountRepository userAccountRepository, PasswordEncoder passwordEncoder, MapperService mapperService) {
        this.workerRepository = workerRepository;
        this.departmentRepository = departmentRepository;
        this.userAccountRepository = userAccountRepository;
        this.passwordEncoder = passwordEncoder;
        this.mapperService = mapperService;
    }

    public List<WorkerResponse> findAll() {
        return workerRepository.findAll()
                .stream()
                .map(mapperService::toWorkerResponse)
                .toList();
    }

    public WorkerResponse create(WorkerRequest request) {
        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Departamento no encontrado"));

        Worker worker = new Worker(request.getFullName(), request.getPosition(), request.getEmail(), department);
        Worker savedWorker = workerRepository.save(worker);

        if (request.getUsername() != null && !request.getUsername().isBlank()) {
            if (userAccountRepository.existsByUsername(request.getUsername())) {
                throw new RuntimeException("El nombre de usuario ya existe");
            }
            String rawPassword = request.getPassword() == null || request.getPassword().isBlank()
                    ? "trabajador123"
                    : request.getPassword();
            UserAccount account = new UserAccount(
                    request.getUsername(),
                    passwordEncoder.encode(rawPassword),
                    Role.WORKER,
                    savedWorker
            );
            userAccountRepository.save(account);
        }

        return mapperService.toWorkerResponse(savedWorker);
    }
}
