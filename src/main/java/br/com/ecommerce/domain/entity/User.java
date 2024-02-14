package br.com.ecommerce.domain.entity;

import br.com.ecommerce.domain.enumerations.ActivityStatus;
import br.com.ecommerce.domain.enumerations.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String email;

    private String password;

    private Role userRole;

    private ActivityStatus userStatus;

    @Lob
    private byte[] profilePicture;

    public User(String name,String email, String password, ActivityStatus userStatus) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.userStatus = userStatus;
    }

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Address> addressList = new ArrayList<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Phone> phoneList = new ArrayList<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
    private List<Order> orderList = new ArrayList<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private WishList wishList;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private ShopCart shopCart;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        switch (this.userRole) {

            case ADMIN -> {
                return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"),
                        new SimpleGrantedAuthority("ROLE_CUSTOMER"),
                        new SimpleGrantedAuthority("ROLE_MANAGER"));
            }

            case MANAGER -> {
                return List.of(new SimpleGrantedAuthority("ROLE_MANAGER"),
                        new SimpleGrantedAuthority("ROLE_CUSTOMER"));
            }

            default -> {
                return List.of(new SimpleGrantedAuthority("ROLE_CUSTOMER"));
            }
        }
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword(){
        return password;
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
