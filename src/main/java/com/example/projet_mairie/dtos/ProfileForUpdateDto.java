package com.example.projet_mairie.dtos;

import com.example.projet_mairie.addresses.Address;

public record ProfileForUpdateDto(String lastName, String email, String phone, Address address ) {
}
