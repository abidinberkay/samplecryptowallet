package com.example.samplecryptowallet.model.Entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
@Entity
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "username", unique = true)
    private String username;

}
