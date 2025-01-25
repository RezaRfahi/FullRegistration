package train.registeration.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Getter
@Setter
@Entity
@AllArgsConstructor
@Table(name = "role")
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "permission_role", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Permission> permissions = new HashSet<>();

    @ManyToMany(mappedBy = "role_user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<User> users = new HashSet<>();

}