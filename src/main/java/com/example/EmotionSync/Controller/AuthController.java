package com.example.EmotionSync.Controller;

import com.example.EmotionSync.JWT.JwtHelper;
import com.example.EmotionSync.JWT.JwtRequestDTO;
import com.example.EmotionSync.JWT.JwtResponseDTO;
import com.example.EmotionSync.Service.JWTService.UserdetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserdetailsService userDetailsService; // typo in field name
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JwtHelper helper;

    public AuthController(UserdetailsService userDetailsService, AuthenticationManager manager, JwtHelper helper){
        this.userDetailsService = userDetailsService;
        this.helper = helper;
        this.manager = manager;
    }

    @PostMapping("/login")
    public JwtResponseDTO login(@RequestBody JwtRequestDTO request) throws BadCredentialsException {
        try {
            // Perform authentication
            this.doAuthenticate(request.getUsername(), request.getPassword());
            // Load user details
            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
            // Generate JWT token
            String token = helper.generateToken(userDetails);
            // Build response DTO
            JwtResponseDTO response = new JwtResponseDTO();
            response.setJwtToken(token);
            response.setAdmin_name(userDetails.getUsername()); // Assuming username holds the doctor's name
            return response;
        } catch (BadCredentialsException e) {
            // Handle authentication failure
          throw e;
        } catch (Exception e) {
            // Handle other exceptions
           throw e;
        }
    }

    private void doAuthenticate(String username, String password) {
        // You need to implement authentication logic here

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);
        manager.authenticate(authentication);
    }
}
