package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "truck")
public class WasteTruck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "is_available")
    private boolean isAvailable;
    private String brand;
    private int capacity;
    @Column(name = "registration_number")
    private String registrationNumber;

    @OneToMany(mappedBy = "truck", cascade = CascadeType.ALL)
    Driver driver;

    public WasteTruck(String brand, int capacity, String registrationNumber) {
        this.brand = brand;
        this.capacity = capacity;
        this.registrationNumber = registrationNumber;
    }

}


