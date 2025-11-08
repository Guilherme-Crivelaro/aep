package com.aep.doacaobooks.doacao.repository;

import com.aep.doacaobooks.doacao.entity.Doacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DoacaoRepository extends JpaRepository<Doacao, Long> {
}
