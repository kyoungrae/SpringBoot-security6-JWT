package com.mbx.security.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="_user")
public class User implements UserDetails {
    @Id
    @GeneratedValue
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    /*
    * @Enumerated(EnumType.STRING)
    * spring에게 이것이 enum 이고 enum유형인지 여부에 관계없이 사용하고 싶다고 알리는 것.
    * */
    @Enumerated(EnumType.STRING)
    private Role role;
    /*
    * 권한을 가져오기 목록이 반환되거나 반환되어야 하는 부분
    * */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    /*
    * 만료가 되지 않음 확인 메소드; 만료가 되지 않았기 때문에 true 반환으로 변경
    * */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    /*
    * 만료가 되지 않았음으로 잠기지 않음 설정도 true로 변경
    * */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    /*
     * 자격증명이 만료 되지 않았는지 확인하는 메소드 이기 때문에 true로 변경
     * */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
