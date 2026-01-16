package cb.dam.secureapibackend.controller;

import cb.dam.secureapibackend.dto.JwtResponse;
import cb.dam.secureapibackend.dto.LoginRequest;
import cb.dam.secureapibackend.dto.MessageResponse;
import cb.dam.secureapibackend.model.User;
import cb.dam.secureapibackend.repository.UserRepository;
import cb.dam.secureapibackend.security.JwtUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Request;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateToken(authentication);

        return ResponseEntity.ok(new JwtResponse(jwt, loginRequest.getUsername()));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser( @RequestBody LoginRequest signUpRequest){

        System.out.println("--> HE ENTRADO AL METODO SIGNUP CON: " + signUpRequest.getUsername());

        if(userRepository.existsByUsername(signUpRequest.getUsername())){
            return ResponseEntity.badRequest().body("Error: El username ya existe");
        }

        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        userRepository.save(user);
        System.out.println("Usuario guardado. Total en DB: " + userRepository.count());

        return ResponseEntity.ok(new MessageResponse("Usuario registrado correctamente"));
    }

    @GetMapping("/test")
    public String test() {
        return "Hola, el controlador funciona";
    }
}
