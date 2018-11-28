/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.pi.services;

import com.example.pi.model.Imagem;
import com.example.pi.repository.ImagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author dp
 */
@Service
public class ImagemService {
    
    @Autowired
    ImagemRepository imagemRepository;

    public void cadastrarImagem(Imagem imagem) {
        imagemRepository.save(imagem);
    }

    public void editarImagem(Imagem imagem) {
        imagemRepository.save(imagem);
    }

    public void excluirImagem(Long id) {
        imagemRepository.deleteById(id);
    }

    public Imagem buscaImagem(Long id) {
        return imagemRepository.findById(id).get();
    }
    
}
