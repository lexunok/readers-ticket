package com.coursework.readersticket.services;


import com.coursework.readersticket.models.dto.UserDTO;
import com.coursework.readersticket.models.entity.User;
import com.coursework.readersticket.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    @Value("${admin.username}")
    String adminUsername;
    @Value("${admin.password}")
    String adminPassword;
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper mapper = new ModelMapper();
    private final JwtEncoder encoder;

    public String login(Authentication authentication) {
        Instant now = Instant.now();
        long expiry = 36000L;
        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(authentication.getName())
                .claim("scope", scope)
                .build();
        return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public void register(UserDTO userDTO) {
        User user = mapper.map(userDTO, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);
    }

    public UserDTO getProfile(String username) {
        return mapper.map(repository.findByUsername(username), UserDTO.class);
    }

    public List<UserDTO> getAllUsers() {
        return repository.findAll().stream().map(u -> mapper.map(u, UserDTO.class)).toList();
    }

    public void deleteUser(Long id) {
        repository.deleteById(id);
    }

    @PostConstruct
    private void registerAdminOnInit() {
        if (!repository.existsByUsername(adminUsername)) {
            repository.save(new User(adminUsername,
                    passwordEncoder.encode(adminPassword), User.Role.ADMIN));
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username);
    }
}
