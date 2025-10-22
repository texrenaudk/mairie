package com.example.projet_mairie.profiles;

import com.example.projet_mairie.addresses.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor
@Slf4j
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "profile_seq_generator")
    @SequenceGenerator(name = "profile_seq_generator", sequenceName = "profile_id_seq", allocationSize = 20)
    private long id;
    private Civilities civility;
    private String lastname;
    private String firstName;
    private String phone;
    private  String email;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "address_id")
    private Address address;



}
