package com.example.projet_mairie.service;

import com.example.projet_mairie.dtos.ProfileForUpdateDto;
import com.example.projet_mairie.profiles.Profile;

import java.util.List;

public interface ProfileService {
   Profile createProfile(Profile profile);
   List<Profile> ListeOfProfile();
   Profile getProfile(Long id);




    Profile upDateProfile(Long id, ProfileForUpdateDto profileUpdated);

    void deleteProfile(Long id);
}
