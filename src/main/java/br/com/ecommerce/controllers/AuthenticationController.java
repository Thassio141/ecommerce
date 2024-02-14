package br.com.ecommerce.controllers;

import br.com.ecommerce.domain.dto.AuthenticationDto;
import br.com.ecommerce.domain.dto.LoginResponseDto;
import br.com.ecommerce.domain.dto.UserCreateDto;
import br.com.ecommerce.domain.entity.User;
import br.com.ecommerce.domain.enumerations.ActivityStatus;
import br.com.ecommerce.repositories.UserRepository;
import br.com.ecommerce.services.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final TokenService tokenService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, UserRepository userRepository, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDto data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(),data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDto(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid UserCreateDto userCreateDto){
        if(this.userRepository.findByEmail(userCreateDto.email()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(userCreateDto.password());

        User user = new User(userCreateDto.name(),userCreateDto.email(),encryptedPassword, ActivityStatus.ACTIVE);

        userRepository.save(user);

        return ResponseEntity.ok().build();
    }
}