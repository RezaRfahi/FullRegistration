package train.registeration.service;

import com.nimbusds.openid.connect.sdk.assurance.evidences.attachment.Digest;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import train.registeration.entity.Role;
import train.registeration.entity.User;
import train.registeration.repository.UserRepository;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class UserService {
    UserRepository userRepository;

    public List<User> findAll() {
        assert userRepository != null;
        return userRepository.findAll();
    }

    public User findById(long id) {
        assert id > 0;
        return userRepository.findById(id).orElse(null);
    }

    public Page<User> findAllWithLimitation(int offset, int limit, String sortField, String sortDirection)
    {
        Sort sort = Sort.by(sortDirection, sortField);
        Pageable pageable = PageRequest.of(offset, limit);
        assert userRepository != null;
        return userRepository.findAllWithPagination(pageable, sort);
    }

    public User findByUsername(String username)
    {
        assert username != null;
        return userRepository.findByUsername(username);
    }

    public User save(String username, String password, String phone, byte birth_day, byte birth_month, byte birth_year, String email)
    {
        Date birthday = new Date(birth_year, birth_month, birth_day);
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setBirthday(birthday);
        user.setPhone(phone);
        return userRepository.save(user);
    }

    public int update(long id, String username, String password, String phone, String email, byte day, byte month, byte year)
    {
        User existsUser = findById(id);
        Date birthday = new Date(year, month, day);
        existsUser.setUsername(username);
        if (password != null){
            password = DigestUtils.md5DigestAsHex(password.getBytes());
            existsUser.setPassword(password);
        }
        existsUser.setPhone(phone);
        existsUser.setEmail(email);
        existsUser.setBirthday(birthday);
        return userRepository.updateUserById(id, existsUser);
    }

    public boolean delete(long id)
    {
        userRepository.deleteById(id);
        return findById(id) != null;
    }

    public Set<Role> findAllRoles(long id)
    {
        User user = userRepository.findById(id).orElse(null);
        assert user != null;
        return user.getRoles();
    }

}
