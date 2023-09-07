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
@Table(name = "hobby")
@Entity
public class Hobby {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "category")
    private String category;

    @ManyToMany
    private Set<User> users = new HashSet<>();

    public Hobby(String name, String category) {
        this.name = name;
        this.category = category;
    }

    public void addUser(User user) {
        if (user != null) {
            users.add(user);
        }
    }

    @PrePersist
    public void prePersist() {
        validateName();
        validateCategory();
    }

    private void validateName() {
        if (name == null) {
            throw new IllegalArgumentException("Invalid Name");
        }
    }

    private void validateCategory() {
        if (category == null) {
            throw new IllegalArgumentException("Invalid Category");
        }
    }

}
