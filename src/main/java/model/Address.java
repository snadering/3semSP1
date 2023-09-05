package model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "street")
    private String street;
    @Column(name = "number")
    private String number;
    @ManyToOne
    @Column(name = "zip")
    private ZipCode zip;
    @OneToMany(mappedBy = "address")
    private Set<User> users = new HashSet<>();


    public Address(String street, String number, ZipCode zip) {
        this.street = street;
        this.number = number;
        this.zip = zip;
    }
}
