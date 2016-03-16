package com.monkeyhorse.blog.models;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;

/**
 *  @author uoor<i@monkeyhorse.cn thx raysmood>
 */
@Entity
@Table(name = "users")
@Getter @Setter
public class User extends BaseModel{
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER  = "ROLE_USER";

    @Column(unique = true)
    private String email;

    public static String getRoleAdmin() {
        return ROLE_ADMIN;
    }

    public static String getRoleUser() {
        return ROLE_USER;
    }

    @JsonIgnore
    private String password;

    private String role = ROLE_USER;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.REMOVE)
    private Collection<Post> posts = new ArrayList<>();

//
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.REMOVE)
//    private Collection<Book> books = new ArrayList<>();

    public User() {

    }

    public User(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Collection<Post> getPosts() {
        return posts;
    }

    public void setPosts(Collection<Post> posts) {
        this.posts = posts;
    }
}
