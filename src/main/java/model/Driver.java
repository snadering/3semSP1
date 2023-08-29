package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "driver")
public class Driver {

    @Id
    private String id;
    @Temporal(TemporalType.DATE)
    @Column(name = "employment_date")
    private Date employmentDate;
    private String name;
    private String surname;
    private BigDecimal salary;
    @Column(name = "truck_id")
    private Integer truckId;

    @ManyToOne
    private WasteTruck wasteTruck;


    public Driver(String name, String surname, BigDecimal salary) {
        this.name = name;
        this.surname = surname;
        this.salary = salary;
        this.employmentDate = new Date();
    }

    @PrePersist
    public void prePersist(){
        if (id == null){
            id = generateId();
        }
        if (employmentDate == null){
            employmentDate = new Date();
        }
    }

    public String generateId(){
        if(id == null){

            /*YearMonthDate*/
            //Year
           int size = ((employmentDate.getYear() + 1900) % 1000);
            if (size < 10){
                id = "0";
            } else {
                id = String.valueOf(size);
            }

            //Month
            size = employmentDate.getMonth()+1;
            if (size < 10){
                id += "0";
            }
                id += size;

            //Date
            size = employmentDate.getDate();
            if (size < 10){
                id += "0";
            }
                id += String.valueOf(size);





        id += "-";
        /*Name and Surname*/
        id += name.toUpperCase().charAt(0);
        id += surname.toUpperCase().charAt(0);

        //Random number between 100-999
        id += "-";
        Random rnd = new Random();
        int num = rnd.nextInt(100, 1000);
        id += num;

        //Last letter of surname
        id += surname.toUpperCase().charAt(surname.length()-1);
        }

        return id;
    }

    public Boolean validateDriverId(String driverId) {
        return driverId.matches("[0-9][0-9][0-9][0-9][0-9][0-9]-[A-Z][A-Z]-[0-9][0-9][0-9][A-Z]");
    }

}
