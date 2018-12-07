/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.pi.services;

import com.example.pi.model.ItemVenda;
import com.example.pi.model.Venda;
import com.example.pi.repository.ItemVendaRepository;
import com.example.pi.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author dp
 */
@Service
public class ItemVendaService {
    
    @Autowired
    ItemVendaRepository itemVendaRepository;
    
    public void cadastrarItemVenda(ItemVenda itemVenda) {
        itemVendaRepository.save(itemVenda);
    }
    
    public void alterarItemVenda(ItemVenda itemVenda) {
        itemVendaRepository.save(itemVenda);
    }
    
    public void excluirItemVenda(Long id) {
        itemVendaRepository.deleteById(id);
    }
    
    public ItemVenda buscaItemVenda(Long id) {
        return itemVendaRepository.findById(id).get();
    }
    
}
