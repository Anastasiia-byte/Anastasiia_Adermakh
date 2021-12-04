package com.epam.springboot.homework1.core.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Tariffs")
@Data
@NoArgsConstructor
public class Tariff {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "tariff_id", columnDefinition = "int default 1")
    public Long tariffId;
    public String name;
    public int price;

    @ManyToOne()
    @JoinColumn(name = "service_id", nullable = false)
    @EqualsAndHashCode.Exclude @ToString.Exclude
    public Service service;

}
