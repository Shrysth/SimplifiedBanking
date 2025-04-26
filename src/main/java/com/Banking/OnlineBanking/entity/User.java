package com.Banking.OnlineBanking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
    public User() {
        System.out.println("no para constructor");
    }
    public User(String username, String password, String fullname, String address, Long branchId) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.address = address;
        this.branchId = branchId;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String username;
    private String password;
    private String fullname;
    private String address;
    private Long branchId;
    private String pancard;
    
    public Long getuserId() {
        return userId;
    }
    public void setId(Long userId) {
        this.userId = userId;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getFullname() {
        return fullname;
    }
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public Long getBranchId() {
        return branchId;
    }
    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public String getPancard() {
        return pancard;
    }

    public void setPancard(String pancard) {
        this.pancard = pancard;
    }
}