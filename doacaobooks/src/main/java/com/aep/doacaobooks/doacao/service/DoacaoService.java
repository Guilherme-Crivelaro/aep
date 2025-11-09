package com.aep.doacaobooks.doacao.service;

import com.aep.doacaobooks.doacao.dto.DoacaoRequestDTO;
import com.aep.doacaobooks.doacao.dto.DoacaoResponseDTO;
import com.aep.doacaobooks.doacao.entity.Doacao;
import com.aep.doacaobooks.doacao.entity.Enum.StatusDoacao;
import com.aep.doacaobooks.doacao.exception.DonationNotFoundException;
import com.aep.doacaobooks.doacao.exception.DonationStatusException;
import com.aep.doacaobooks.doacao.repository.DoacaoRepository;
import com.aep.doacaobooks.livro.entity.Enum.StatusLivro;
import com.aep.doacaobooks.livro.entity.Livro;
import com.aep.doacaobooks.livro.exception.BookNotFoundException;
import com.aep.doacaobooks.livro.repository.LivroRepository;
import com.aep.doacaobooks.usuario.entity.Usuario;
import com.aep.doacaobooks.usuario.exception.UserNotFoundException;
import com.aep.doacaobooks.usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class DoacaoService {

    private final DoacaoRepository doacaoRepository;
    private final LivroRepository livroRepository;
    private final UsuarioRepository usuarioRepository;
    private final ModelMapper modelMapper;

    public DoacaoResponseDTO doarLivro(DoacaoRequestDTO dto){

        Livro livro = livroRepository.findByTitulo(dto.getTituloLivro())
                .orElseThrow(() -> new BookNotFoundException("Livro: " + dto.getTituloLivro()));

        Usuario usuario = usuarioRepository.findByEmail(dto.getEmailBeneficiario())
                .orElseThrow(() -> new UserNotFoundException("email: " + dto.getEmailBeneficiario()));

        if(livro.getStatus()!= StatusLivro.DISPONIVEL){
            throw new DonationStatusException(dto.getTituloLivro());
        }
        livro.setStatus(StatusLivro.DOADO);
        livroRepository.save(livro);

        Doacao doacao = new Doacao();
        doacao.setLivro(livro);
        doacao.setBeneficiario(usuario);
        doacao.setDataAgendamento(LocalDate.now());
        doacao.setStatusDoacao(StatusDoacao.AGENDADA);

        Doacao savedDoacao = doacaoRepository.save(doacao);

        return modelMapper.map(savedDoacao, DoacaoResponseDTO.class);
    }

    public DoacaoResponseDTO pegarLivro(Long doacaoId, Long usuarioId) {
        Doacao doacao = doacaoRepository.findById(doacaoId)
                .orElseThrow(() -> new DonationNotFoundException("doacaoId: " + doacaoId));

        if (doacao.getStatusDoacao() != StatusDoacao.DISPONIVEL) {
            throw new DonationStatusException("doacaoId: " + doacaoId);
        }

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new UserNotFoundException("usuarioId: " + usuarioId));

        doacao.setStatusDoacao(StatusDoacao.CONCLUIDA);
        doacao.setBeneficiario(usuario);

        Livro livro = doacao.getLivro();
        livro.setStatus(StatusLivro.DOADO);
        livroRepository.save(livro);

        Doacao savedDoacao = doacaoRepository.save(doacao);

        return modelMapper.map(savedDoacao, DoacaoResponseDTO.class);
    }

    public Doacao atualizarStatus(Long id, StatusDoacao novoStatus){
        Doacao doacao = doacaoRepository.findById(id)
                .orElseThrow(() -> new DonationStatusException("doacaoId: " + id));

        doacao.setStatusDoacao(novoStatus);
        return doacaoRepository.save(doacao);
    }
}
