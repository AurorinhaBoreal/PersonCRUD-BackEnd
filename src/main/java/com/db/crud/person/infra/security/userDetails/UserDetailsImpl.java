package com.db.crud.person.infra.security.userDetails;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.db.crud.person.entity.Authentication;
import com.db.crud.person.entity.Person;

import lombok.Getter;
import lombok.NonNull;

// Implementação do UserDetails
// Essa implementação nos permite puxarmos informações da autenticação
// Seja a senha, o email. ou mesmo as próprias autoridades da mesma
@Getter
public class UserDetailsImpl implements UserDetails {

    @NonNull
    private Authentication auth;
    @NonNull
    private Person person;

    public UserDetailsImpl(Authentication authentication) {
        this.auth = authentication;    
        this.person = authentication.getPerson();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<SimpleGrantedAuthority>();
    }

    @Override
    public String getPassword() {
        return auth.getPassword();
    }

    @Override
    public String getUsername() {
        return auth.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
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
