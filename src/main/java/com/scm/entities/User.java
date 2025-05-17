package com.scm.entities;

import java.util.*;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class User {
    
    @Id
    private String userId;
    @Column(name = "user_name", nullable = false)
    private String name;
    private String password;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(unique = true, nullable = false)
    private String phoneNumber;
    @Column(columnDefinition = "TEXT")
    private String about;
    @Column(columnDefinition = "TEXT")
    private String profilePicture;
    //Information
    @Builder.Default
    private boolean enabled=false;
    @Builder.Default
    private boolean emailVerified=false;
    @Builder.Default
    private boolean phoneVerified=false;
    // SELF, GOOGLE, FACEBOOK, TWITTER
    @Builder.Default
    private Providers provider=Providers.SELF;
    private String providerId;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @Builder.Default
    private List<Contact> contacts = new ArrayList<>();



    // public User(String name, String password, String email, String phoneNumber) {
    //     this.name = name;
    //     this.password = password;
    //     this.email = email;
    //     this.phoneNumber = phoneNumber;
    // }

    // public String getName() {
    //     return name;
    // }

    // public void setName(String name) {
    //     this.name = name;
    // }

    // public String getPassword() {
    //     return password;
    // }

    // public void setPassword(String password) {
    //     this.password = password;
    // }

    // public String getEmail() {
    //     return email;
    // }

    // public void setEmail(String email) {
    //     this.email = email;
    // }

    // public String getPhoneNumber() {
    //     return phoneNumber;
    // }

    // public void setPhoneNumber(String phoneNumber) {
    //     this.phoneNumber = phoneNumber;
    // }

}
