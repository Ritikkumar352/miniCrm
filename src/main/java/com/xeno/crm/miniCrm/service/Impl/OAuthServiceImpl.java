package com.xeno.crm.miniCrm.service.Impl;

import com.xeno.crm.miniCrm.model.Admin;
import com.xeno.crm.miniCrm.repository.AdminRepo;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Optional;

public class OAuthServiceImpl implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final AdminRepo adminRepo;

    public OAuthServiceImpl(AdminRepo adminRepo) {
        this.adminRepo = adminRepo;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oauth2User = delegate.loadUser(userRequest);

        String email = oauth2User.getAttribute("email");
        Optional<Admin> existingAdmin = adminRepo.findByEmail(email);

        if (existingAdmin.isPresent()) {
            return existingAdmin.get(); // Admin implements OAuth2User
        }

        Admin admin = Admin.builder()
                .name(oauth2User.getAttribute("name"))
                .email(email)
                .googleId(oauth2User.getAttribute("sub"))
                .profilePicUrl(oauth2User.getAttribute("picture"))
                .build();



        System.out.println("saving admin");

        adminRepo.save(admin);

        // logs.. remove later
        System.out.println("admin saved");
        System.out.println(Optional.ofNullable(oauth2User.getAttribute("name")));
        System.out.println(admin.getName() + " <<- savedname ");

        return admin;
    }
}


