package com.assinatura.digital.core.infrastructure.repository;
import com.assinatura.digital.core.domain.Assinatura;
import org.springframework.data.jpa.repository.JpaRepository;
public interface AssinaturaRepository extends JpaRepository<Assinatura, Long> {}
