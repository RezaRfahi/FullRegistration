package train.registeration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import train.registeration.repository.RoleRepository;

@Service
public class RoleService {
    RoleRepository roleRepository;
}
