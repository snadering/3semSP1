package model;

import jakarta.persistence.*;
import lombok.*;

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
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @JoinColumn(name = "address")
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

    @Builder
    public User(String name, String surname, String phoneNumber, String email) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public void addHobby(Hobby hobby) {
        if (hobby != null) {
            this.hobbies.add(hobby);
            hobby.addUser(this);
        }
    }

    public void addEvent(Event event) {
        if (event != null) {
            this.events.add(event);
            event.addUser(this);
        }
    }

    @PrePersist
    public void prePersist() {
        validateName();
        validateSurname();
        validateEmail();
        validateNumber();
    }

    private void validateName() {
        if (name == null) {
            throw new IllegalArgumentException("Invalid Name");
        }
    }

    private void validateSurname() {
        if (surname == null) {
            throw new IllegalArgumentException("Invalid Surname");
        }
    }
    private void validateEmail(){
        if(email == null || !email.contains("@")){
            throw new IllegalArgumentException("Invalid Email");
        }
    }

    private void validateNumber(){
        if(phoneNumber==null){
            throw new IllegalArgumentException("Invalid phone number");
        }
    }

}
