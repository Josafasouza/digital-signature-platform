package com.assinatura.digital.core.application.controller;
import com.assinatura.digital.core.application.service.AssinaturaService;
import com.assinatura.digital.core.domain.Assinatura;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AssinaturaController {
      private final AssinaturaService assinaturaService;

      public AssinaturaController(AssinaturaService assinaturaService) {
           this.assinaturaService = assinaturaService;
      }

      class AssinaturaRequest {
           public Long idDocumento;
           public String cpf;
      }

      @PostMapping("/assinar")
      public ResponseEntity<Assinatura> assinarDocumento(@RequestBody AssinaturaRequest request) {
           Assinatura novaAssinatura = assinaturaService.assinarDocumento(request.idDocumento, request.cpf);
           return ResponseEntity.ok(novaAssinatura);
      }

      @GetMapping("/admin/assinaturas")
      public ResponseEntity<List<Assinatura>> listarAssinaturas() {
           List<Assinatura> assinaturas = assinaturaService.findAll();
           return ResponseEntity.ok(assinaturas);
      }
}
