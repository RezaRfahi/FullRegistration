package train.registeration.entity;

import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    private String username;

    @Column()
    private String password;

    @Column(unique = true, length = 50)
    private String email;

    @Column(unique = true, length = 13)
    private String phone;

    private Date birthday;

    private Date registrationDate;
}