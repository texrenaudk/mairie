package com.example.projet_mairie.testData;

import com.example.projet_mairie.addresses.Address;
import com.example.projet_mairie.profiles.Civilities;
import com.example.projet_mairie.profiles.Profile;

public class ProfileTetData {
    public static final Profile PROFILE_JEAN = createJeanProfile();
    public static final Profile PROFILE_MARIE = createMarieProfile();
    public static final Profile PROFILE_CANCELLED = createCancelledProfile();
    public static final Address ADDRESS_PARIS = createParisAddress();

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
}
