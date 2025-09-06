package com.assinatura.digital.core.domain;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Usuario {
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;
      private String nome;
      private String cpf;
      private String email;
      private String senhaHash;
      private String tipoPerfil; // "ADMIN" ou "FINAL"
}
