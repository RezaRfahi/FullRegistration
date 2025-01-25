package train.registeration.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;
import train.registeration.entity.Role;
import train.registeration.entity.User;

import java.util.Date;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    Page<User> findAllWithPagination(Pageable pageable, Sort sort);
    User findByUsername(String username);

    @Modifying
    @Transactional
    int updateUserById(long id, User user);

}