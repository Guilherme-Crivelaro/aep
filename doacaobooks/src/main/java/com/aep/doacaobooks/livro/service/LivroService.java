package com.aep.doacaobooks.livro.service;

import com.aep.doacaobooks.livro.dto.LivroRequestDTO;
import com.aep.doacaobooks.livro.dto.LivroResponseDTO;
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

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository livroRepository;
    private final UsuarioRepository usuarioRepository;
    private final ModelMapper modelMapper;

    public LivroResponseDTO cadastrarLivro(LivroRequestDTO livroDTO) {
        Usuario doador = usuarioRepository.findById(livroDTO.getDoadorId())
                .orElseThrow(() -> new UserNotFoundException(""));

        Livro livro = new Livro();
        livro.setTitulo(livroDTO.getTitulo());
        livro.setAutor(livroDTO.getAutor());
        livro.setCurso(livroDTO.getCurso());
        livro.setDisciplina(livroDTO.getDisciplina());
        livro.setStatus(livroDTO.getStatus());
        livro.setDoador(doador);

        Livro savedLivro = livroRepository.save(livro);

        return modelMapper.map(savedLivro, LivroResponseDTO.class);
    }

    public List<LivroResponseDTO> todosLivros() {
        List<Livro> livros = livroRepository.findAll();
        List<LivroResponseDTO> livroResponseDTOS = new ArrayList<>();
        for (Livro livro : livros) {
            LivroResponseDTO dto = new LivroResponseDTO();
            livroResponseDTOS.add(modelMapper.map(livro, LivroResponseDTO.class));
        }
        return livroResponseDTOS;
    }

    public List<LivroResponseDTO> livrosDisponiveis() {
        List<Livro> livros = livroRepository.findByStatus(StatusLivro.DISPONIVEL);
        List<LivroResponseDTO> livroResponseDTOS = new ArrayList<>();
        for (Livro livro : livros) {
            LivroResponseDTO dto = new LivroResponseDTO();
            livroResponseDTOS.add(modelMapper.map(livro, LivroResponseDTO.class));
        }
        return livroResponseDTOS;
    }

    public LivroResponseDTO atualizarStatus(Long id, StatusLivro novoStatus) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Livro não encontrado"));

        livro.setStatus(novoStatus);
        Livro savedLivro = livroRepository.save(livro);

        return modelMapper.map(savedLivro, LivroResponseDTO.class);
    }
}
