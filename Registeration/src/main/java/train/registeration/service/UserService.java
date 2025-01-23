package train.registeration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
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

    public User save(User user)
    {
        return userRepository.save(user);
    }

    public User update(User user, long id)
    {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null)
        {
            existingUser.setUsername(user.getUsername());
            existingUser.setPassword(user.getPassword());
            existingUser.setEmail(user.getEmail());
            existingUser.setPhone(user.getPhone());
            existingUser.setBirthday(user.getBirthday());
        }
        assert existingUser != null;
        return userRepository.save(existingUser);
    }

    public boolean delete(long id)
    {
        userRepository.deleteById(id);
        return findById(id) != null;
    }

}
