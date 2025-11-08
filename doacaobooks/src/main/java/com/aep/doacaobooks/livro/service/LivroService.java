package com.aep.doacaobooks.livro.service;

import com.aep.doacaobooks.livro.dto.LivroCreateDTO;
import com.aep.doacaobooks.livro.entity.Enum.StatusLivro;
import com.aep.doacaobooks.livro.entity.Livro;
import com.aep.doacaobooks.livro.exception.BookNotFoundException;
import com.aep.doacaobooks.livro.repository.LivroRepository;
import com.aep.doacaobooks.usuario.entity.Usuario;
import com.aep.doacaobooks.usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository livroRepository;
    private final UsuarioRepository usuarioRepository;

    public Livro cadastrarLivro(LivroCreateDTO livroDTO){
        Usuario doador = usuarioRepository.findById(livroDTO.getDoadorId())
                .orElseThrow(() -> new RuntimeException("Doador não encontrado"));

        Livro livro = new Livro();
        livro.setTitulo(livroDTO.getTitulo());
        livro.setAutor(livroDTO.getAutor());
        livro.setCurso(livroDTO.getCurso());
        livro.setDisciplina(livroDTO.getDisciplina());
        livro.setStatus(livroDTO.getStatus());
        livro.setDoador(doador);

        return livroRepository.save(livro);
    }

    public List<Livro> todosLivros(){
        return livroRepository.findAll();
    }

    public List<Livro> livrosDisponiveis(){
        return livroRepository.findByStatus(StatusLivro.DISPONIVEL);
    }

    public Livro atualizarStatus(Long id, StatusLivro novoStatus){
        Livro livro = livroRepository.findById(id)
                .orElseThrow(()-> new BookNotFoundException("Livro não encontrado"));

        livro.setStatus(novoStatus);
        return livroRepository.save(livro);
    }
}
