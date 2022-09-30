package com.tripath.trifood.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name="students")
@NoArgsConstructor
@Getter
@Setter
public class Student implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_name", nullable = false, length = 100)
    private String name;

    @Column(name = "password", nullable = false, length = 500)
    private String password;

    @Column(name = "student_fullname")
    private String fullname;

    @Column(name = "student_class")
    private String stclass;

    @Column(name = "student_email")
    private String email;

    @Column(name = "student_phone")
    private String phone;

//    -------------------------TRIFOOD-ADD----------------------------------
    @ManyToOne
    private EClass eClass;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    @JsonIgnore
    Set<StudentOrder> studentOrders;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    @JsonIgnore
    Set<StudentPayment> studentPayments;

//   -------------------------TRIFOOD-ADD-----------------------------------

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinTable(name = "student_role",
            joinColumns =@JoinColumn(name = "student", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name= "role", referencedColumnName = "id"))
    private Set<Role> roles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = this.roles.stream().map((role)-> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
        return authorities;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
