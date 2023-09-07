package model;

import config.HibernateConfig;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
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
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private float price;

    @Temporal(TemporalType.DATE)
    @Column(name = "start_date")
    private LocalDate startDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "end_date")
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "address")
    @ToString.Exclude
    private Address address;

    @ManyToOne
    @JoinColumn(name = "hobby")
    @ToString.Exclude
    private Hobby hobby;

    @ManyToMany
    @ToString.Exclude
    private Set<User> users = new HashSet<>();

    @Builder
    public Event(String name, float price, Address address, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.price = price;
        this.address = address;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void addUser(User user) {
        if (user != null) {
            users.add(user);
        }
    }

    @PrePersist
    public void prePersist() {
        validatePrice();
        validateName();
        validateDate();
    }

    private void validateName() {
        if (name == null) {
            throw new IllegalArgumentException("Invalid Name");
        }
    }

    private void validatePrice() {
        if (price == 0) {
            throw new IllegalArgumentException("Invalid price");
        }
    }


    private void validateDate(){
        if (startDate==null || endDate==null){
            throw new IllegalArgumentException("Invalid date");
    @PreRemove
    public void removeEventUserLinks() {
        try (var em = HibernateConfig.getEntityManagerFactoryConfig().createEntityManager()) {
            em.getTransaction().begin();
            Query q = em.createNativeQuery("DELETE FROM event_users WHERE event_id = :id");
            q.setParameter("id", this.id);
            q.executeUpdate();
            em.getTransaction().commit();

        }
    }
}
