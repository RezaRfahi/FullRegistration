package train.registeration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import train.registeration.entity.User;
import train.registeration.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    public Page<User> findAllWithLimitation(int offset, int limit, String sortField, String sortDirection)
    {
        Sort sort = Sort.by(sortDirection, sortField);
        Pageable pageable = PageRequest.of(offset, limit);
        return userRepository.findAllWithPagination(pageable, sort);
    }

    public User findByUsername(String username)
    {
        return userRepository.findByUsername(username);
    }

}
