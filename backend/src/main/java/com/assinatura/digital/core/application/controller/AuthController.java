package com.assinatura.digital.core.application.controller;
import com.assinatura.digital.core.application.dto.LoginRequest;
import com.assinatura.digital.core.application.dto.AuthResponse;
import com.assinatura.digital.security.JwtTokenUtil;
import com.assinatura.digital.core.application.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

      private final AuthenticationManager authenticationManager;
      private final JwtTokenUtil jwtTokenUtil;
      private final UsuarioService usuarioService;

      public AuthController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, UsuarioService usuarioService) {
           this.authenticationManager = authenticationManager;
           this.jwtTokenUtil = jwtTokenUtil;
           this.usuarioService = usuarioService;
      }

      @PostMapping("/login")
      public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
           final Authentication authentication = authenticationManager.authenticate(
                     new UsernamePasswordAuthenticationToken(
                               loginRequest.getEmail(),
                               loginRequest.getSenha()
                     )
           );

           SecurityContextHolder.getContext().setAuthentication(authentication);
           final UserDetails userDetails = (UserDetails) authentication.getPrincipal();
           final String token = jwtTokenUtil.generateToken(userDetails);

           return ResponseEntity.ok(new AuthResponse(token));
      }
}
