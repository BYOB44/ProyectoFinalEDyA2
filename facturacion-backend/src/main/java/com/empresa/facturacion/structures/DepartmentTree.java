package com.empresa.facturacion.structures;

import com.empresa.facturacion.dto.TreeNodeResponse;
import com.empresa.facturacion.model.Department;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DepartmentTree {
    public List<TreeNodeResponse> buildTree(List<Department> departments) {
        Map<Long, TreeNodeResponse> nodes = new HashMap<>();
        List<TreeNodeResponse> roots = new ArrayList<>();

        for (Department department : departments) {
            nodes.put(department.getId(), new TreeNodeResponse(department.getId(), department.getName()));
        }

        for (Department department : departments) {
            TreeNodeResponse current = nodes.get(department.getId());
            if (department.getParent() == null) {
                roots.add(current);
            } else {
                TreeNodeResponse parent = nodes.get(department.getParent().getId());
                if (parent != null) {
                    parent.addChild(current);
                }
            }
        }
        return roots;
    }
}
