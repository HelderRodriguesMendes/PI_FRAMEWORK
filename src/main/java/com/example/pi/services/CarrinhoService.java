/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.pi.services;

import com.example.pi.model.Carrinho;
import com.example.pi.model.Cliente;
import com.example.pi.repository.CarrinhoRepository;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author dp
 */
@Service
public class CarrinhoService {

    final long data = TimeUnit.DAYS.toMillis(20);
    
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
    
    public Carrinho verificarCarrinhoId_Cli(Cliente cli){
        return carrinhoRepository.findByCli(cli);
    }
    public Carrinho casdastrarNovoCarrinho(Carrinho carrinho){
        return carrinhoRepository.save(carrinho);
         
    }
    
    public void restaurarTempoExpiração(Carrinho carri){
        carri.setExpireTime(new Date(System.currentTimeMillis() + data));
    }

}
