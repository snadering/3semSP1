package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
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
    @JoinColumn(name = "zip")
    private ZipCode zip;
    @OneToMany(mappedBy = "address")
    private Set<User> users = new HashSet<>();


    public Address(String street, String number, ZipCode zip) {
        this.street = street;
        this.number = number;
        this.zip = zip;
    }

    @PrePersist
    public void prePersist() {
    validateNumber();
    validateStreet();
    validateZip();
    }

    private void validateStreet() {
        if (street == null) {
            throw new IllegalArgumentException("Invalid street name");
        }
    }

    private void validateNumber() {
        if (number == null) {
            throw new IllegalArgumentException("Invalid number");
        }
    }
    private void validateZip(){
        if(zip == null){
            throw new IllegalArgumentException("Invalid zipcode");
        }
    }
}
