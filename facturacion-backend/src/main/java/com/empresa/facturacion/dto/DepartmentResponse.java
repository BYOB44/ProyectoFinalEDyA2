package com.empresa.facturacion.dto;

public class DepartmentResponse {
    private Long id;
    private String name;
    private Long parentId;
    private String parentName;

    public DepartmentResponse(Long id, String name, Long parentId, String parentName) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.parentName = parentName;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public Long getParentId() { return parentId; }
    public String getParentName() { return parentName; }
}
