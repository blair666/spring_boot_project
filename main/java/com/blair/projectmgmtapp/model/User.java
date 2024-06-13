//package com.blair.projectmgmtapp.model;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@AllArgsConstructor
//@Data
//@Entity
//@NoArgsConstructor
//
//@Table(name = "users")
//public class User {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    @Column(name = "first_name")
//    private String firstName;
//
//    @Column(name = "last_name")
//    private String lastName;
//
//    @Column
//    private String email;
//
//    @Column(nullable = true, unique = true)
//    private Long accountNo;
//
//
////    @Column(nullable = false)
////    private String password;
//
//    @Column(nullable = false)
//    private String zipcode;
//
//
//}