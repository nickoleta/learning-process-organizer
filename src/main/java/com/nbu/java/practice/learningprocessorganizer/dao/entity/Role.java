package com.nbu.java.practice.learningprocessorganizer.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
public class Role extends BaseEntity implements GrantedAuthority {

    @Column(name = "authority", unique = true, nullable = false)
    private String authority;

    @OneToMany(mappedBy = "authority", fetch = FetchType.EAGER, cascade = javax.persistence.CascadeType.ALL)
    private Set<UserIdentity> userIdentities;

}
