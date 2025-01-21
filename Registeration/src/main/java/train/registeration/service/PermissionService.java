package train.registeration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import train.registeration.repository.PermissionRepository;

@Service
public class PermissionService {
    PermissionRepository permissionRepository;
}
