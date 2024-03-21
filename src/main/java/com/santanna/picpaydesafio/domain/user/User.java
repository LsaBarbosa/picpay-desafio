package com.santanna.picpaydesafio.domain.user;

import com.santanna.picpaydesafio.domain.dto.UserDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name = "users")
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String document;

    @Column(unique = true)
    private String email;

    private String password;
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    public User(UserDTO userData) {
        this.firstName = userData.firstName();
        this.lastName = userData.lastName();
        this.document = userData.document();
        this.balance = userData.balance();
        this.userType = userData.userType();
        this.email = userData.email();
        this.password = userData.password();

    }
}
