package com.example.projet_mairie.controller;

import com.example.projet_mairie.dtos.ApiResponse;
import com.example.projet_mairie.dtos.ProfileDtoResp;
import com.example.projet_mairie.dtos.ProfileForUpdateDto;
import com.example.projet_mairie.profiles.Profile;
import com.example.projet_mairie.service.MappDto;
import com.example.projet_mairie.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/v1/api/")
@RequiredArgsConstructor
public class ProfileController {

private final ProfileService profileService;
private final MappDto mappDto;

    @PostMapping("create")
    public ResponseEntity<ApiResponse<ProfileDtoResp>> creationProfile(@Valid @RequestBody Profile profile){

        Profile newProfileCreated = profileService.createProfile(profile);
        ProfileDtoResp profileDtoResp = mappDto.getProfileDtoResp(newProfileCreated);


        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<ProfileDtoResp>(true, "Utilisateur ajouté avec success", profileDtoResp));
    }
    @GetMapping("profiles")
    public ResponseEntity<ApiResponse<List<ProfileDtoResp>>> listeProfiles(){
        List<Profile> profiles = profileService.ListeOfProfile();
        List<ProfileDtoResp> listeProfilesDto = profiles.stream()
                .map(mappDto::getProfileDtoResp)
                .toList();
        return ResponseEntity.ok().body(new ApiResponse<>(true,"profils fournis", listeProfilesDto));

    }
    @GetMapping("profile/{id}")
    public ResponseEntity<ApiResponse<Profile>> getProfile(@PathVariable Long id){
       Profile profile = profileService.getProfile(id);
       return ResponseEntity.ok().body(new ApiResponse<>(true, "profile trouvé", profile));
    }
    @PutMapping("profile-update/{id}")
    public ResponseEntity<ApiResponse<Profile>> update(@PathVariable Long id, @RequestBody ProfileForUpdateDto profileToUpdate){
        Profile profile = profileService.upDateProfile(id, profileToUpdate);
        return ResponseEntity.ok().body(new ApiResponse<>(true, "Utilisateur ajourné avec succes", profile));
    }
    @DeleteMapping("profile/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> delelingProfile(@PathVariable Long id){
        profileService.deleteProfile(id);
        return ResponseEntity.noContent().build();
    }
}
