package com.assinatura.digital.core.application.service;
import com.assinatura.digital.core.domain.Documento;
import com.assinatura.digital.core.infrastructure.repository.DocumentoRepository;
import com.assinatura.digital.core.infrastructure.storage.FileStorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Service
public class DocumentoService {
      private final DocumentoRepository documentoRepository;
      private final FileStorageService fileStorageService;

      public DocumentoService(DocumentoRepository documentoRepository, FileStorageService fileStorageService) {
           this.documentoRepository = documentoRepository;
           this.fileStorageService = fileStorageService;
      }

      public Documento salvarDocumento(MultipartFile file) {
           String caminho = fileStorageService.storeFile(file);
           String hash = fileStorageService.getFileHash(file);

           Documento novoDocumento = new Documento();
           novoDocumento.setNome(file.getOriginalFilename());
           novoDocumento.setCaminhoArquivo(caminho);
           novoDocumento.setHashOriginal(hash);
           novoDocumento.setStatus("DISPONIVEL");
           return documentoRepository.save(novoDocumento);
      }
}
