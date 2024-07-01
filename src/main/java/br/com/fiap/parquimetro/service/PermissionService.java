package br.com.fiap.parquimetro.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.parquimetro.exception.BusinessException;
import br.com.fiap.parquimetro.model.Permission;
import br.com.fiap.parquimetro.repository.PermissionRepository;

@Service
public class PermissionService {

    private final PermissionRepository permissionRepository;

    @Autowired
    public PermissionService(final PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    public Set<Permission> getPermissions(final List<String> permissionRequest) {
        final var permissions = new HashSet<Permission>();
        permissionRequest.forEach(p -> permissions.add(this.permissionRepository.getPermissionByDescription(p)));
        return permissions;
    }

    public boolean verifyPermissions(final Set<String> roles, final String requestRole) {
        for (var permission : roles) {
            if (permission.equals(requestRole)) {
                return true;
            }
        }
        throw new BusinessException("User don't have requireds permissions");
    }

}
