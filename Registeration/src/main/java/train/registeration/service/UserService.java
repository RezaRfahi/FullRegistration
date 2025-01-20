package train.registeration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import train.registeration.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
}
