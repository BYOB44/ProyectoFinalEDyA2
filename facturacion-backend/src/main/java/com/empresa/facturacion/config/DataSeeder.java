package com.empresa.facturacion.config;

import com.empresa.facturacion.model.*;
import com.empresa.facturacion.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Configuration
public class DataSeeder {
    @Bean
    CommandLineRunner seedData(
            DepartmentRepository departmentRepository,
            WorkerRepository workerRepository,
            UserAccountRepository userAccountRepository,
            InvoiceRepository invoiceRepository,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {
            Department admin = departmentRepository.save(new Department("Administración", null));
            Department human = departmentRepository.save(new Department("Recursos Humanos", admin));
            Department finance = departmentRepository.save(new Department("Tesorería", admin));
            Department systems = departmentRepository.save(new Department("Sistemas", admin));
            Department sales = departmentRepository.save(new Department("Ventas", admin));

            Worker worker1 = workerRepository.save(new Worker("Laura Gómez", "Analista de nómina", "laura@empresa.com", human));
            Worker worker2 = workerRepository.save(new Worker("Carlos Pérez", "Auxiliar contable", "carlos@empresa.com", finance));
            Worker worker3 = workerRepository.save(new Worker("Aquiles Bailo", "Desarrollador junior", "Aquiles@empresa.com", systems));
            Worker worker4 = workerRepository.save(new Worker("Ana Torres", "Ejecutiva comercial", "ana@empresa.com", sales));

            userAccountRepository.save(new UserAccount("admin", passwordEncoder.encode("admin123"), Role.ADMIN, null));
            userAccountRepository.save(new UserAccount("tesorero", passwordEncoder.encode("tesorero123"), Role.TREASURER, null));
            userAccountRepository.save(new UserAccount("trabajador", passwordEncoder.encode("trabajador123"), Role.WORKER, worker3));

            invoiceRepository.save(new Invoice("FAC-1001", "Pago de nómina mensual", new BigDecimal("2500000"), InvoiceType.NOMINA, Priority.HIGH, InvoiceStatus.PENDING, LocalDate.now().minusDays(4), worker1));
            invoiceRepository.save(new Invoice("FAC-1002", "Viáticos visita cliente", new BigDecimal("320000"), InvoiceType.VIATICOS, Priority.LOW, InvoiceStatus.PENDING, LocalDate.now().minusDays(3), worker4));
            invoiceRepository.save(new Invoice("FAC-1003", "Comisión por ventas", new BigDecimal("850000"), InvoiceType.COMISION, Priority.MEDIUM, InvoiceStatus.PENDING, LocalDate.now().minusDays(2), worker4));
            invoiceRepository.save(new Invoice("FAC-1004", "Pago soporte técnico interno", new BigDecimal("1200000"), InvoiceType.SERVICIO, Priority.MEDIUM, InvoiceStatus.PENDING, LocalDate.now().minusDays(1), worker3));
            invoiceRepository.save(new Invoice("FAC-1005", "Nómina auxiliar contable", new BigDecimal("1800000"), InvoiceType.NOMINA, Priority.HIGH, InvoiceStatus.PENDING, LocalDate.now(), worker2));
        };
    }
}
