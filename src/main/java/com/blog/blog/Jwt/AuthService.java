//package com.blog.blog.Jwt;
//
//import com.blog.blog.entity.User;
//import com.blog.blog.repository.UserRepo;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class AuthService {
//    private  final UserRepo userRepository;
//    private  final JwtService jwtService;
//    private final PasswordEncoder passwordEncoder;
//    private final AuthenticationManager authenticationManager;
//
//    public AuthenticationResponse register(User registerRequest) {
//        User user = User.builder()
//                .name(registerRequest.getName())
//                .address(registerRequest.getAddress())
//                .phoneNumber(registerRequest.getPhoneNumber())
//                .email(registerRequest.getEmail())
//                .website(registerRequest.getWebsite())
//                .password(passwordEncoder.encode(registerRequest.getPassword()))
//                .image(registerRequest.getImage())
//                .build();
//        User savedUser = userRepository.save(user);
//        String jwtToken = jwtService.generateToken(savedUser);
//        return AuthenticationResponse.builder().accessToken(jwtToken).build();
//    }
//
//    public AuthenticationResponse authenticate(AuthenticationRequest request) {
//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            request.getEmail(),
//                            request.getPassword()
//                    )
//            );
//        } catch (BadCredentialsException e) {
//            // Handle bad credentials exception
//            throw new RuntimeException("Invalid credentials");
//        }
//
//        // Retrieve the authenticated user from the database
//        var user = userRepository.findByEmail(request.getEmail())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        // Generate and return the JWT token
//        String jwtToken = jwtService.generateToken(user);
//        return AuthenticationResponse.builder().accessToken(jwtToken).build();
//    }
//
//}
//



package com.blog.blog.Jwt;

import com.blog.blog.entity.User;
import com.blog.blog.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepo userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(User registerRequest) {
        User user = User.builder()
                .name(registerRequest.getName())
                .address(registerRequest.getAddress())
                .phoneNumber(registerRequest.getPhoneNumber())
                .email(registerRequest.getEmail())
                .website(registerRequest.getWebsite())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .image(registerRequest.getImage())
                .build();
        User savedUser = userRepository.save(user);
        String jwtToken = jwtService.generateToken(savedUser);
        return AuthenticationResponse.builder().accessToken(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new RuntimeException("Invalid credentials");
        }

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().accessToken(jwtToken).build();
    }
}
