package com.aep.doacaobooks.doacao.service;

import com.aep.doacaobooks.doacao.entity.Doacao;
import com.aep.doacaobooks.doacao.entity.Enum.StatusDoacao;
import com.aep.doacaobooks.doacao.excpetion.IllegalStateException;
import com.aep.doacaobooks.doacao.repository.DoacaoRepository;
import com.aep.doacaobooks.livro.entity.Enum.StatusLivro;
import com.aep.doacaobooks.livro.entity.Livro;
import com.aep.doacaobooks.livro.repository.LivroRepository;
import com.aep.doacaobooks.usuario.entity.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class DoacaoService {

    private final DoacaoRepository doacaoRepository;
    private final LivroRepository livroRepository;

    public Doacao doarLivro(Livro livro, Usuario usuario){
        if(livro.getStatus()!= StatusLivro.DISPONIVEL){
            throw new IllegalStateException("Livro não disponivel");
        }
        livro.setStatus(StatusLivro.DOADO);
        livroRepository.save(livro);

        Doacao doacao = new Doacao();
        doacao.setLivro(livro);
        doacao.setBeneficiario(usuario);
        doacao.setDataAgendamento(LocalDate.now());
        doacao.setStatusDoacao(StatusDoacao.AGENDADA);

        return doacaoRepository.save(doacao);
    }

    public Doacao pegarLivro(Long doacaoId, Usuario usuario) {
        Doacao doacao = doacaoRepository.findById(doacaoId)
                .orElseThrow(() -> new RuntimeException("Doação não encontrada"));

        if (doacao.getStatusDoacao() != StatusDoacao.DISPONIVEL) {
            throw new RuntimeException("Esse livro já foi doado");
        }

        doacao.setStatusDoacao(StatusDoacao.CONCLUIDA);
        doacao.setBeneficiario(usuario);

        Livro livro = doacao.getLivro();
        livro.setStatus(StatusLivro.DOADO);
        livroRepository.save(livro);

        return doacaoRepository.save(doacao);
    }

    public Doacao atualizarStatus(Long id, StatusDoacao novoStatus){
        Doacao doacao = doacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doação não encontrada"));

        doacao.setStatusDoacao(novoStatus);
        return doacaoRepository.save(doacao);
    }
}
