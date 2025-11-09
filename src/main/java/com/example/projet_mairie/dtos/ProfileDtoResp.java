package com.example.projet_mairie.dtos;

import com.example.projet_mairie.addresses.Address;
import com.example.projet_mairie.profiles.Civilities;
import lombok.Data;

@Data
public class ProfileDtoResp {
    private Civilities civilities;
    private String lastName;
    private String firstName;
    private String email;
    private Address address;
}
