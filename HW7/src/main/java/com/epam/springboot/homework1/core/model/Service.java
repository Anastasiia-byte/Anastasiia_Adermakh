package com.epam.springboot.homework1.core.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Services")
@Data
@NoArgsConstructor
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "service_id", columnDefinition = "int default 1")
    public Long serviceId;
    public String name;

    @ManyToMany(mappedBy = "serviceList", cascade = CascadeType.MERGE)
    @EqualsAndHashCode.Exclude @ToString.Exclude
    public Set<Abonent> abonentList = new HashSet<>();

    @OneToMany(mappedBy = "service", fetch = FetchType.EAGER,
            cascade = CascadeType.MERGE)
    public Set<Tariff> tariffList = new HashSet<>();


    public void enroll(Abonent abonent) {
        abonentList.add(abonent);
    }

    public void extract(Abonent abonent) {
        abonentList.remove(abonent);
    }

    public void add(Tariff tariff) {
        tariffList.add(tariff);
    }

    public void remove(Tariff tariff) {
        tariffList.remove(tariff);
    }
}
