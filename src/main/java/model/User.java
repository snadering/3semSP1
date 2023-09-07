package model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
       if (!isValidEmail(email)) {
           throw new IllegalArgumentException("Invalid email address");
       }

        if (!isValidPhoneNumber(phoneNumber)) {
            throw new IllegalArgumentException("Invalid phone number");
        }
    }

    @PreUpdate
    public void preUpdate() {
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email address");
        }

        if (!isValidPhoneNumber(phoneNumber)) {
            throw new IllegalArgumentException("Invalid phone number");
        }
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,})$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


    private boolean isValidPhoneNumber(String phoneNumber) {
        String phoneRegex = "^(\\+45\\s?\\d{8}$)";
        Pattern pattern = Pattern.compile(phoneRegex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }


}
