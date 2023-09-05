package model;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Table(name = "zipcode")
public class ZipCode {

    @Id
    private int zip;
    @Column(name = "city_name")
    private String cityName;
    @Column(name = "region_name")
    private String regionName;
    @Column(name = "municipality_name")
    private String municipalityName;
    @OneToMany(mappedBy = "zip")
    private Set<Address> addresses = new HashSet<>();

}
