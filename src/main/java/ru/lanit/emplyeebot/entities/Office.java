package ru.lanit.emplyeebot.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "offices")
public class Office {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String descriptionl;

    @Column(name = "date_of_creation")
    private LocalDate dateOfCreation;

    @Column(name = "address")
    private String address;
}
