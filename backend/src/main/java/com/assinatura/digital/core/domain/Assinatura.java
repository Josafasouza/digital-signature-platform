package com.assinatura.digital.core.domain;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Assinatura {
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;
      private Long idDocumento;
      private String cpfAssinante;
      private String hashAssinatura;
      private LocalDateTime dataAssinatura;
}
