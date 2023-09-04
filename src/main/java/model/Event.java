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
@Table(name = "event")
@Entity
public class Event {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private float price;

    @ManyToOne
    @Column(name = "address")
    private Address address;

    @ManyToOne
    @Column(name = "hobby")
    private Hobby hobby;

    @ManyToMany
    private Set<User> users = new HashSet<>();
}
