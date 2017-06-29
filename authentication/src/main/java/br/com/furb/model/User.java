package br.com.furb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.furb.enumeration.Role;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name", unique = true, nullable = false, length = 32)
    private String name;
    @Column(name = "role", nullable = false, length = 2)
    private Role role;
    @Column(name = "salt", nullable = false, length = 32)
    private String salt;
    @Column(name = "password", nullable = false, length = 64)
    private String password;
    @Column(name = "essence", nullable = true, length = 64)
    private String essence;
    @Column(name = "card", nullable = true, length = 256)
    private String card;

    public User() {

    }

    public User(Long id, String name, Role role, String salt, String password, String essence) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.salt = salt;
        this.password = password;
        this.essence = essence;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEssence() {
        return essence;
    }

    public void setEssence(String essence) {
        this.essence = essence;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", role=" + role + ", salt=" + salt + ", password=" + password + ", card=" + card + "]";
    }

}
