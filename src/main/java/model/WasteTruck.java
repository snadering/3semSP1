package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(mappedBy = "wasteTruck", cascade = CascadeType.ALL)
    private Set<Driver> drivers = new HashSet<>();

    public void addDriver(Driver driver){
        if (driver != null){
            drivers.add(driver);
            driver.setWasteTruck(this);
        }
    }

    public void removeDriver(Driver driver){
            drivers.remove(driver);
            driver.setWasteTruck(null);
    }



    public WasteTruck(String brand, int capacity, String registrationNumber) {
        this.brand = brand;
        this.capacity = capacity;
        this.registrationNumber = registrationNumber;
    }


}


