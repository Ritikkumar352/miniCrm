package com.xeno.crm.miniCrm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "admin")
@Builder
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private String email;
    private String googleId;
    private String profilePicUrl;

    @OneToMany(mappedBy = "createdBy")
    private List<Campaign> campaigns;

    @OneToMany(mappedBy = "createdBy")
    private List<Segment> segments;
}
