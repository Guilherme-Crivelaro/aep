package com.aep.doacaobooks.livro.service;

import com.aep.doacaobooks.livro.entity.Enum.StatusLivro;
import com.aep.doacaobooks.livro.entity.Livro;
import com.aep.doacaobooks.livro.exception.BookNotFoundException;
import com.aep.doacaobooks.livro.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository livroRepository;

    public Livro cadastrarLivro(Livro livro){
        return livroRepository.save(livro);
    }

    public List<Livro> todosLivros(){
        return livroRepository.findAll();
    }

    public List<Livro> livrosDisponiveis(){
        return livroRepository.findByStatus(StatusLivro.DISPONIVEL);
    }

    public Livro livroPorNome(String nome){
        return livroRepository.findByTitulo(nome);
    }

    public Livro atualizarStatus(Long id, StatusLivro novoStatus){
        Livro livro = livroRepository.findById(id)
                .orElseThrow(()-> new BookNotFoundException("Livro não encontrado"));

        livro.setStatus(novoStatus);
        return livroRepository.save(livro);
    }
}
