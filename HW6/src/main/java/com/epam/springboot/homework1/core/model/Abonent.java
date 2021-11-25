package com.epam.springboot.homework1.core.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
//@Builder
@NoArgsConstructor
@Entity
@Table(name = "Abonents")
public class Abonent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "abonent_id")
    public Long abonentId;
    @Column(nullable = false, name = "first_name")
    public String firstName;
    @Column(nullable = false, name = "last_name")
    public String lastName;
    @Column(unique = true)
    public String email;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "abonent_service",
            joinColumns = @JoinColumn(name = "abonent_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    @EqualsAndHashCode.Exclude @ToString.Exclude
    public Set<Service> serviceList = new HashSet<>();


    public void subscribe(Service service) {
        serviceList.add(service);
    }

    public void unsubscribe(Service service) {
        serviceList.remove(service);
    }
}
