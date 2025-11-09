package com.example.projet_mairie.service;

import com.example.projet_mairie.addresses.AddressRepository;
import com.example.projet_mairie.dtos.ProfileForUpdateDto;
import com.example.projet_mairie.profiles.Profile;
import com.example.projet_mairie.repository.ProfileRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    private ProfileRepository profileRepository;
    private AddressRepository addressRepository;


    @Override
    public Profile createProfile(Profile profile) {
        log.info("le profile avec email {}, a ete crée", profile.getEmail());

        return profileRepository.save(profile);

    }

    @Override
    public List<Profile> ListeOfProfile() {
        return profileRepository.findAll()
                .stream().filter(profile-> !profile.isProfileCancelled()).toList();
    }

    @Override
    public Profile getProfile(Long id) {
        Optional<Profile> profileOptional = profileRepository.findById(id);

            return profileOptional.orElseThrow(()->new EntityNotFoundException("Aucun profil trouvé"));

    }

    @Override
    public Profile upDateProfile(Long id, ProfileForUpdateDto profileUpdated) {
        Profile profileToUpdate = this.getProfile(id);
        profileToUpdate.setAddress(profileUpdated.address());
        profileToUpdate.setEmail(profileUpdated.email());
        profileToUpdate.setFirstName(profileUpdated.lastName());
        profileToUpdate.setPhone(profileUpdated.phone());
        log.info("le profil ayant cet id : {}c a ete ajourné", profileToUpdate.getId());
        return profileRepository.save(profileToUpdate);
    }

    @Override
    @Transactional
    public void deleteProfile(Long id) {
        Profile profileToCancelled = this.getProfile(id);
        profileToCancelled.setProfileCancelled(true);
        log.info("le profil ayant cet id : {}c a ete effacé", profileToCancelled.getId());

        profileRepository.save(profileToCancelled);

    }
}
