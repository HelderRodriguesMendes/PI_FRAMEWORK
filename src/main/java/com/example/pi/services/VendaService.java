/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.pi.services;

import com.example.pi.model.Venda;
import com.example.pi.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author dp
 */
@Service
public class VendaService {
    
    @Autowired
    VendaRepository vendaRepository;
    
    public void cadastrarVenda(Venda venda) {
        vendaRepository.save(venda);
    }
    
    public void alterarVenda(Venda venda) {
        vendaRepository.save(venda);
    }
    
    public void excluirVenda(Long id) {
        vendaRepository.deleteById(id);
    }
    
    public Venda buscaVenda(Long id) {
        return vendaRepository.findById(id).get();
    }
    
}
