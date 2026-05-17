package com.empresa.facturacion.dto;

public class WorkerResponse {
    private Long id;
    private String fullName;
    private String position;
    private String email;
    private Long departmentId;
    private String departmentName;

    public WorkerResponse(Long id, String fullName, String position, String email, Long departmentId, String departmentName) {
        this.id = id;
        this.fullName = fullName;
        this.position = position;
        this.email = email;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }

    public Long getId() { return id; }
    public String getFullName() { return fullName; }
    public String getPosition() { return position; }
    public String getEmail() { return email; }
    public Long getDepartmentId() { return departmentId; }
    public String getDepartmentName() { return departmentName; }
}
