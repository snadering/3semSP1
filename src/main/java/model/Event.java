package model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@ToString
@Table(name = "event")
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private float price;

    @ManyToOne
    @JoinColumn(name = "address")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "hobby")
    private Hobby hobby;

    @ManyToMany
    private Set<User> users = new HashSet<>();

    @Builder
    public Event(String name, float price, Address address) {
        this.name = name;
        this.price = price;
        this.address = address;
    }

    public void addUser(User user) {
        if (user != null) {
            users.add(user);
        }
    }
}
