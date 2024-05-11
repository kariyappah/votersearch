package com.kamp.votersearch;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Voter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double partNo;
    private String fullName;
    private String relationName;
    private String address;
    private String qualification;
    private String business;
    private Double age;
    private String sex;
    private String epicNumber;
    private String photo;
//    PartNo,
//    fullName
//    relationName
//    address
//    qualification
//    business
//    age
//    sex
//    epicNumber
//    photo
}
