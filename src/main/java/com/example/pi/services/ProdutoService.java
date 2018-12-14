/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.pi.services;

import com.example.pi.model.Produto;
import com.example.pi.repository.ProdutoRepository;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author dp
 */
@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

    public void cadastrarProduto(Produto produto) {
        produtoRepository.save(produto);
    }

    public void editarProduto(Produto produto) {
        produtoRepository.save(produto);
    }

    public void excluirProduto(Long id) {
        produtoRepository.deleteById(id);
    }

    public Produto buscaProduto(Long id) {
        return produtoRepository.findById(id).get();
    }
    
    public List<Produto> buscaTodosProduto() {
        return produtoRepository.findAll();
    }

}
