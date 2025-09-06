package com.assinatura.digital.core.application.service;
import com.assinatura.digital.core.domain.Assinatura;
import com.assinatura.digital.core.domain.Documento;
import com.assinatura.digital.core.infrastructure.repository.AssinaturaRepository;
import com.assinatura.digital.core.infrastructure.repository.DocumentoRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AssinaturaService {
    private final AssinaturaRepository assinaturaRepository;
    private final DocumentoRepository documentoRepository;

    public AssinaturaService(AssinaturaRepository assinaturaRepository, DocumentoRepository documentoRepository) {
        this.assinaturaRepository = assinaturaRepository;
        this.documentoRepository = documentoRepository;
    }

    public Assinatura assinarDocumento(Long idDocumento, String cpfAssinante) {
        Documento documento = documentoRepository.findById(idDocumento)
            .orElseThrow(() -> new RuntimeException("Documento não encontrado."));
        
        if (!"DISPONIVEL".equals(documento.getStatus())) {
            throw new RuntimeException("Documento não está disponível para assinatura.");
        }

        String hashAssinatura = new String("..."); // Lógica de hash...

        Assinatura novaAssinatura = new Assinatura();
        novaAssinatura.setIdDocumento(idDocumento);
        novaAssinatura.setCpfAssinante(cpfAssinante);
        novaAssinatura.setHashAssinatura(hashAssinatura);
        novaAssinatura.setDataAssinatura(LocalDateTime.now());
        
        documento.setStatus("ASSINADO");
        documentoRepository.save(documento);

        return assinaturaRepository.save(novaAssinatura);
    }

    // Método findAll() que estava faltando
    public List<Assinatura> findAll() {
        return assinaturaRepository.findAll();
    }
}