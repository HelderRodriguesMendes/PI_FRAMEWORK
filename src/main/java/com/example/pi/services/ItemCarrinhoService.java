/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.pi.services;

import com.example.pi.model.Carrinho;
import com.example.pi.model.ItemCarrinho;
import com.example.pi.repository.CarrinhoRepository;
import com.example.pi.repository.ItemCarrinhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author dp
 */
@Service
public class ItemCarrinhoService {

    @Autowired
    ItemCarrinhoRepository itemCarrinhoRepository;

    public void cadastrarItemCarrinho(ItemCarrinho itemCarrinho) {
        itemCarrinhoRepository.save(itemCarrinho);
    }

    public void editarItemCarrinho(ItemCarrinho itemCarrinho) {
        itemCarrinhoRepository.save(itemCarrinho);
    }

    public void excluirItemCarrinho(Long id) {
        itemCarrinhoRepository.deleteById(id);
    }
    
    public ItemCarrinho buscaItemCarrinho(Long id) {
        return itemCarrinhoRepository.findById(id).get();
    }
    
    public void limparCarrinho(Carrinho carri){
        itemCarrinhoRepository.deleteByCarrinho(carri);
    }

}
