package com.openclassrooms.mddapi.security.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

/**
 * UserDetails implementation.
 * @author tipikae
 * @version 1.0.0
 */
@Builder
@AllArgsConstructor
@Getter
public class UserDetailsImpl implements UserDetails {

    private Long id;
    private String username;
    @JsonIgnore
    private String password;

    /**
     * Get authorities.
     * @return Collection
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new HashSet<GrantedAuthority>();
    }

    /**
     * Check if account is expired.
     * @return boolean
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Check if account is locked.
     * @return boolean
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Check if credentials are expired.
     * @return boolean
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Check if user is enabled.
     * @return boolean
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * Check if user equals another object.
     * @param obj Object ot check.
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        UserDetailsImpl user = (UserDetailsImpl) obj;

        return Objects.equals(id, user.id);
    }
}
