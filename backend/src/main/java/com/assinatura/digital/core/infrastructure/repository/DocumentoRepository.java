package com.assinatura.digital.core.infrastructure.repository;
import com.assinatura.digital.core.domain.Documento;
import org.springframework.data.jpa.repository.JpaRepository;
public interface DocumentoRepository extends JpaRepository<Documento, Long> {}
