    package com.project.product.service;

    import com.project.product.model.AuthenticationRequest;
    import com.project.product.model.ERole;
    import com.project.product.model.User;

    import com.project.product.model.RegisterRequest;
    import com.project.product.repository.UserRepository;
    import com.project.product.response.AuthenticationResponse;
    import com.project.product.security.jwt.JwtService;
    import lombok.RequiredArgsConstructor;
    import org.springframework.security.authentication.AuthenticationManager;
    import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.stereotype.Service;

    @Service
    @RequiredArgsConstructor
    public class AuthenticationService {

        private final UserRepository userRepository;
        private final PasswordEncoder passwordEncoder;
        private final JwtService jwtService;
        private final AuthenticationManager authenticationManager;

        public AuthenticationResponse register(RegisterRequest request) {
            var user = User.builder()
                    .username(request.getUsername())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .erole(ERole.USER)
                    .build();
            if(!userRepository.existsByEmail(request.getEmail())){
                userRepository.save(user);
                var jwtToken = jwtService.generateToken(user);
                return AuthenticationResponse.builder().token(jwtToken).build();
            }else{
                throw new RuntimeException("Email already exists");
            }

        }

        public AuthenticationResponse authenticate(AuthenticationRequest request) {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );//get user
            var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
            var jwtToken = jwtService.generateToken(user);//generate token
            return AuthenticationResponse.builder().token(jwtToken).build();
        }
    }
