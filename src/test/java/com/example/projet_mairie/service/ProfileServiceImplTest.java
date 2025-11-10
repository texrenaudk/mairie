package com.example.projet_mairie.service;

import com.example.projet_mairie.dtos.ProfileForUpdateDto;
import com.example.projet_mairie.profiles.Civilities;
import com.example.projet_mairie.profiles.Profile;
import com.example.projet_mairie.repository.ProfileRepository;
import com.example.projet_mairie.testData.ProfileTestData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.example.projet_mairie.testData.ProfileTestData.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProfileServiceImplTest {

    @Mock
    private ProfileRepository profileRepository;

    @InjectMocks
    private ProfileServiceImpl profileService;

    @Test
    void createProfile() {
        Profile profileSaved = new Profile();
        profileSaved.setId(100L);
        profileSaved.setCivility(Civilities.Mr);
        profileSaved.setLastname("Dupont");
        profileSaved.setFirstName("Jean");
        profileSaved.setPhone("0123456789");
        profileSaved.setEmail("jean.dupont@mairie.fr");
        profileSaved.setProfileCancelled(false);
        profileSaved.setAddress(ProfileTestData.ADDRESS_PARIS);

        when(profileRepository.save(profileSaved))
                .thenReturn(profileSaved);
Profile result = profileService.createProfile(profileSaved);


        assertNotNull(result, "Le résultat ne devrait pas être null");
        assertEquals(100L, result.getId(), "L'ID devrait être 100");
        assertEquals("jean.dupont@mairie.fr", result.getEmail(), "L'email devrait correspondre");
        assertEquals("Dupont", result.getLastname(), "Le nom devrait être Dupont");
        assertEquals("Jean", result.getFirstName(), "Le prénom devrait être Jean");
        assertEquals(Civilities.Mr, result.getCivility(), "La civilité devrait être MONSIEUR");
        assertFalse(result.isProfileCancelled(), "Le profile ne devrait pas être annulé");
    }
    @Test
    void createProfile_ShouldThrowException_WhenRepositoryFails() {
        when(profileRepository.save(any(Profile.class)))
                .thenThrow(new RuntimeException("Database error"));

        assertThrows(RuntimeException.class, () -> {
            profileService.createProfile(ProfileTestData.PROFILE_JEAN);
        });
    }

    @Test
    void listeOfProfile() {
        List<Profile> expectedProfiles = List.of(
                ProfileTestData.PROFILE_JEAN,
                ProfileTestData.PROFILE_MARIE
        );
        when(profileRepository.findAll()).thenReturn(expectedProfiles);
        List<Profile> profiles = profileService.ListeOfProfile();
        assertNotNull(profiles, "la liste n devrait pas etre nulle");
        assertEquals(2,profiles.size(),"la liste doit etre constitue de 2 personnes");
        assertEquals(ProfileTestData.PROFILE_JEAN,profiles.getFirst(),"le premier profile doit etre celui de jean" );
        assertEquals(ProfileTestData.PROFILE_MARIE,profiles.getLast(),"le deuxieme profile doit etre celui de marie" );

    }

    @Test
    void getProfile_ShouldReturnProfile_WhenProfileExists() {
        Long profileId = 100L;
        Profile profileSaved = new Profile();
        profileSaved.setId(100L);
        profileSaved.setCivility(Civilities.Mr);
        profileSaved.setLastname("Dupont");
        profileSaved.setFirstName("Jean");
        profileSaved.setPhone("0123456789");
        profileSaved.setEmail("jean.dupont@mairie.fr");
        profileSaved.setProfileCancelled(false);
        profileSaved.setAddress(ProfileTestData.ADDRESS_PARIS);

        when(profileRepository.findById(profileId)).thenReturn(Optional.of(profileSaved));

        Profile result = profileService.getProfile(profileId);
        assertNotNull(result, "Le profile ne devrait pas être null");
        assertEquals(100L, result.getId(), "L'ID devrait être 100");
        assertEquals("jean.dupont@mairie.fr", result.getEmail());

    }

    @Test
    void getProfileUpdate_Should_verify_Updatedrofile() {
        Long profileId = 100L;
        ProfileForUpdateDto profileForUpdateDto = new ProfileForUpdateDto("Jean","texDupon@gmail.com","011122233", ProfileTestData.ADDRESS_PARIS);
        when(profileRepository.findById(profileId))
                .thenReturn(Optional.of(PROFILE_JEAN));
        when(profileRepository.save(any(Profile.class)))
                .thenReturn(ProfileTestData.PROFILE_JEAN_UPDATED);

    Profile jeanUpdated = profileService.upDateProfile(100L, profileForUpdateDto);
    assertNotNull(jeanUpdated, "le profil ajourné n'est pas nul");
    assertEquals(100L,jeanUpdated.getId(), "l'id de Jean doit etre le meme");
    assertEquals("Jean", jeanUpdated.getLastname(), "le nom de jean doit etre le meme");
    assertEquals("texDupon@gmail.com", jeanUpdated.getEmail(), " le mail de jean doit etre le meme");
    assertEquals("011122233", jeanUpdated.getPhone(), "le numero de telephone doit etre : 011122233");

    }

    @Test
    void deleteProfile() {
        Long profileId = 102L;

        when(profileRepository.findById(profileId)).thenReturn(Optional.of(ProfileTestData.PROFILE_ACTIVE)); // simulation d une fontion findbyId et avec son resultat
        when(profileRepository.save(any(Profile.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));
                //when(profileRepository.save(any(Profile.class))).thenReturn(ProfileTestData.PROFILE_CANCELLED);

              profileService.deleteProfile(profileId);

        // 1. Vérifiez que la méthode save a bien été appelée une fois
        verify(profileRepository, times(1)).save(ProfileTestData.PROFILE_ACTIVE);

        // 2. Vérifiez que la méthode findById a bien été appelée une fois avec le bon ID
        verify(profileRepository, times(1)).findById(profileId);

        // 3. Vérifiez l'état de l'objet local APRÈS l'appel
        // Même si la méthode est void, l'objet 'activeProfile' local a été modifié par le service.
        assertTrue(ProfileTestData.PROFILE_ACTIVE.isProfileCancelled(), "Le profil doit être annulé après l'appel au service.");

    }
}