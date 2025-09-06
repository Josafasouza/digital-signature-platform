package com.assinatura.digital.core.application.controller;
import com.assinatura.digital.core.application.service.DocumentoService;
import com.assinatura.digital.core.domain.Documento;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/admin/documentos")
public class DocumentoController {
      private final DocumentoService documentoService;

      public DocumentoController(DocumentoService documentoService) {
           this.documentoService = documentoService;
      }

      @PostMapping("/upload")
      public ResponseEntity<Documento> uploadDocumento(@RequestParam("file") MultipartFile file) {
           Documento novoDocumento = documentoService.salvarDocumento(file);
           return ResponseEntity.ok(novoDocumento);
      }
}
