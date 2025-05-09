package com.xeno.crm.miniCrm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private String email;
    private String phone;
    private String gender;
    private String address;
    private String password;

    private LocalDate registrationDate;
    private LocalDate lastPurchaseDate;

    private Integer totalOrders;
    private Double totalSpend;

    private Boolean isActive;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders;

    @PrePersist
    public void prePersist() {
        this.registrationDate = LocalDate.now();
    }




}
