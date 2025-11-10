package com.example.projet_mairie.testData;

import com.example.projet_mairie.addresses.Address;
import com.example.projet_mairie.dtos.ProfileForUpdateDto;
import com.example.projet_mairie.profiles.Civilities;
import com.example.projet_mairie.profiles.Profile;

import java.util.ArrayList;
import java.util.List;

public class ProfileTestData {
    public static final Profile PROFILE_JEAN = createJeanProfile();
    public static final Profile PROFILE_MARIE = createMarieProfile();
    public static final Profile PROFILE_CANCELLED = createCancelledProfile();
    public static final List<Profile> PROFILE_LIST = getProfils();
    public static final Address ADDRESS_PARIS = createParisAddress();
    public static final Profile PROFILE_JEAN_UPDATED = updatedProfileJean();
    public static final Profile PROFILE_ACTIVE = createActiveProfile();

    private static Profile updatedProfileJean() {

        ProfileForUpdateDto profileForUpdateDto = new ProfileForUpdateDto("Jean","texDupon@gmail.com","011122233", ADDRESS_PARIS);
        PROFILE_JEAN.setEmail(profileForUpdateDto.email());
        PROFILE_JEAN.setLastname(profileForUpdateDto.lastName());
        PROFILE_JEAN.setPhone(profileForUpdateDto.phone());
        PROFILE_MARIE.setAddress(profileForUpdateDto.address());
        return PROFILE_JEAN;

    }

    static {
        PROFILE_JEAN.setId(100L);
        PROFILE_MARIE.setId(101L);
        PROFILE_CANCELLED.setId(102L);
    }

    private static Profile createJeanProfile() {
        Profile profile = new Profile();
        profile.setCivility(Civilities.Mr);
        profile.setLastname("Dupont");
        profile.setFirstName("Jean");
        profile.setPhone("0123456789");
        profile.setEmail("jean.dupont@mairie.fr");
        profile.setProfileCancelled(false);
        profile.setAddress(createParisAddress());
        return profile;
    }

    private static Profile createMarieProfile() {
        Profile profile = new Profile();
        profile.setCivility(Civilities.M);
        profile.setLastname("Martin");
        profile.setFirstName("Marie");
        profile.setPhone("0678912345");
        profile.setEmail("marie.martin@mairie.fr");
        profile.setProfileCancelled(false);
        return profile;
    }

    private static Profile createCancelledProfile() {
        Profile profile = new Profile();
        profile.setCivility(Civilities.Mr);
        profile.setLastname("Durand");
        profile.setFirstName("Pierre");
        profile.setPhone("0698765432");
        profile.setEmail("pierre.durand@mairie.fr");
        profile.setProfileCancelled(true);
        return profile;
    }

    private static Address createParisAddress() {
        Address address = new Address();
        address.setVia("1 Rue de la Mairie");
        address.setCity("Paris");
        address.setCap(75001);
        address.setPaese("France");
        return address;
    }
    private static List<Profile> getProfils(){
        List<Profile> listeProfiles = new ArrayList<>();
        listeProfiles.add(PROFILE_JEAN);
        listeProfiles.add(PROFILE_MARIE);
        return listeProfiles;
    }

    public static Profile createActiveProfile() {
        Profile profile = new Profile();
        profile.setCivility(Civilities.Mr);
        profile.setLastname("Durand");
        profile.setFirstName("Pierre");
        profile.setPhone("0698765432");
        profile.setEmail("pierre.durand@mairie.fr");
        profile.setProfileCancelled(false);
        return profile;
    }
}
