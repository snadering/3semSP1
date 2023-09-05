package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
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

    public ZipCode(int zip, String cityName, String regionName, String municipalityName) {
        this.zip = zip;
        this.cityName = cityName;
        this.regionName = regionName;
        this.municipalityName = municipalityName;
    }
}
