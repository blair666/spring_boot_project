package com.blair.projectmgmtapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "vendors")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String category;

    @Column(nullable = true)
    private String zipCode;

    @Column(nullable = false)
    private double rating;

    @Column(nullable = false)
    private String reviews = "";

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String contactInfo;

    @OneToMany(mappedBy = "vendor")
    private List<Project> projects;

    @OneToMany(mappedBy = "vendor", cascade = CascadeType.ALL)
    private List<Quote> quotes;

    public Vendor(String electroPlus, String electrical, String number) {
    }

    //create mock data using Java POJOs (Plain Old Java Objects)
    @Override
    public String toString() {
        return "Vendor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}
