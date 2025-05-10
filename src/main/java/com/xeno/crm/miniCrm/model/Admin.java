package com.xeno.crm.miniCrm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "admin")
@Builder
public class Admin implements OAuth2User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    // I'll be getting all those 4 from oAuth login ... and +1 is emil verified and some more check response format
    private String name;
    private String email;
    private String googleId;
    private String profilePicUrl;


    @OneToMany(mappedBy = "createdBy")
    private List<Campaign> campaigns; // mapping check--OK

    // add seg mapping
    @OneToMany(mappedBy = "createdBy")
    private List<Segment> segments;  // mapping check--OK


    @Override
    public java.util.Map<String, Object> getAttributes() {
        return java.util.Map.of(
                "name", this.name,
                "email", this.email,
                "sub", this.googleId,
                "picture", this.profilePicUrl
        );
    }


    @Override
    public Object getAttribute(String name) {
        switch (name) {
            case "name":
                return this.name;
            case "email":
                return this.email;
            case "sub":
                return this.googleId;
            case "picture":
                return this.profilePicUrl;
            default:
                return null;
        }
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }
}
