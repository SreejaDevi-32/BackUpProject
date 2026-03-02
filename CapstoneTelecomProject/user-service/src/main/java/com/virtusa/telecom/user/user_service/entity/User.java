package com.virtusa.telecom.user.user_service.entity;

import java.util.HashSet;
import java.util.Set;

import com.virtusa.telecom.user.user_service.systemconstants.Role;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users") 
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class User extends BaseEntity{ 
    @Id
    @GeneratedValue(strategy = GenerationType.UUID) 
    private String userId; 

    @Column(name = "first_name") 
    private String firstName; 

    @Column(name = "last_name") 
    private String lastName; 

    @Column(unique = true,nullable = false) 
    private String email; 
    
    @Column(name = "email_hash", nullable = false)
    private String emailHash;

    @Column(unique = true) 
    private String phoneNumber; 
    
    @Column(name = "phone_hash")
    private String phoneHash;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;
    
    @Builder.Default
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Set<Role> roles = new HashSet<>();
    

    private boolean active=false; 

}
