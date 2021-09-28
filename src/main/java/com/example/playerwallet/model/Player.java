package com.example.playerwallet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Data;
import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Player {

    @Id
    @Column(name = "player_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String fname;
    private String lname;
    private int age;

    @Column(nullable = false, unique = true)
    private String username;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "player_id")
    private Account account;


}
