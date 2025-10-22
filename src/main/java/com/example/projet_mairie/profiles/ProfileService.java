package com.example.projet_mairie.profiles;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service


public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;




    public Profile createProfile(Profile profile){
        return profileRepository.save(profile);
    }
    public List<Profile> profiles{
        return  Profile;
    }

}
