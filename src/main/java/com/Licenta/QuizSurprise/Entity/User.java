package com.Licenta.QuizSurprise.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private String password;
    private Integer userGroup;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL,  fetch = FetchType.LAZY, optional = true)
    @JsonManagedReference
    private UserPoints userPoints;
}
