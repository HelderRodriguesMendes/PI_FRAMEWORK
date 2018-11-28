/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.pi.services;

import com.example.pi.model.Carrinho;
import com.example.pi.repository.CarrinhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author dp
 */
@Service
public class CarrinhoService {

    @Autowired
    CarrinhoRepository carrinhoRepository;

    public void cadastrarCarrinho(Carrinho carrinho) {
        carrinhoRepository.save(carrinho);
    }

    public void editarCarrinho(Carrinho carrinho) {
        carrinhoRepository.save(carrinho);
    }

    public void excluirCarrinho(Long id) {
        carrinhoRepository.deleteById(id);
    }
    
    public Carrinho buscaCrrinho(Long id) {
        return carrinhoRepository.findById(id).get();
    }

}
