package com.assinatura.digital.core.application.service;
import com.assinatura.digital.core.domain.Usuario;
import com.assinatura.digital.core.infrastructure.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import java.util.Collections;

@Service
public class UsuarioService implements UserDetailsService {
      private final UsuarioRepository usuarioRepository;

      public UsuarioService(UsuarioRepository usuarioRepository) {
           this.usuarioRepository = usuarioRepository;
      }

      @Override
      public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
           Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o email: " + email));

           return new User(
                usuario.getEmail(),
                usuario.getSenhaHash(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + usuario.getTipoPerfil()))
           );
      }
}
