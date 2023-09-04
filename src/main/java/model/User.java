package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    @ManyToOne()
    private Address address;

    @ManyToMany
    @JoinTable(
            name = "hobby_users",
            joinColumns = @JoinColumn(name = "users_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "hobby_id", nullable = false)
    )
    private Set<Hobby> hobbies = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "event_users",
            joinColumns = @JoinColumn(name = "users_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "event_id", nullable = false)
    )
    private Set<Event> events = new HashSet<>();
}
