package com.example.projet_mairie.service;

import com.example.projet_mairie.dtos.ProfileDtoResp;
import com.example.projet_mairie.profiles.Profile;
import org.springframework.stereotype.Service;

@Service
public class MappDto {
   // private ProfileDtoResp profileDtoResp;
    //private Profile profile;

    public ProfileDtoResp getProfileDtoResp(Profile profile){
        ProfileDtoResp profileDtoResp = new ProfileDtoResp();
        profileDtoResp.setCivilities(profile.getCivility());
        profileDtoResp.setEmail(profile.getEmail());
        profileDtoResp.setAddress(profile.getAddress());
        profileDtoResp.setLastName(profile.getLastname());
        profileDtoResp.setFirstName(profile.getFirstName());
        return profileDtoResp;
    }
    public Profile getProfileFromDto(ProfileDtoResp profileDtoResp){
        Profile profile = new Profile();
        profile.setCivility(profileDtoResp.getCivilities());
        profile.setEmail(profileDtoResp.getEmail());
        profile.setAddress(profileDtoResp.getAddress());
        profile.setLastname(profileDtoResp.getLastName());
        profile.setFirstName(profileDtoResp.getFirstName());
        return profile;
    }
}
