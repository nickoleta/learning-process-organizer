package com.nbu.java.practice.learningprocessorganizer.dao.entity.users;

import com.nbu.java.practice.learningprocessorganizer.dao.entity.BaseEntity;
import com.nbu.java.practice.learningprocessorganizer.dao.entity.Lecturer;
import com.nbu.java.practice.learningprocessorganizer.dao.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Collection;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
public class UserIdentity extends BaseEntity implements UserDetails {

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column
    private boolean isAccountNonExpired;

    @Column
    private boolean isAccountNonLocked;

    @Column
    private boolean isCredentialsNonExpired;

    @Column
    private boolean isEnabled;

    @ManyToOne(fetch = FetchType.EAGER)
    private Role authority;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lecturer_id", unique = true)
    private Lecturer lecturer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id", unique = true)
    private Student student;

    public UserIdentity(String username, String password, boolean isAccountNonExpired, boolean isAccountNonLocked, boolean isCredentialsNonExpired, boolean isEnabled,
                        Role authority) {
        this.username = username;
        this.password = password;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
        this.authority = authority;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Set.of(this.authority);
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        isAccountNonExpired = accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        isAccountNonLocked = accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        isCredentialsNonExpired = credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public void setAuthority(Role authority) {
        this.authority = authority;
    }
}
