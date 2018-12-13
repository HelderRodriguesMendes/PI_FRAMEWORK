/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.pi.services;

import com.example.pi.controller.ClienteController;
import com.example.pi.model.Carrinho;
import com.example.pi.model.Cliente;
import com.example.pi.repository.CarrinhoRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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

    public long carrinhoToken(HttpHeaders headers) throws Exception {
        long id = 0;

        String tokenCompleto = headers.get("Authorization").get(0);
        String token = tokenCompleto.substring(7);

        Claims c;
        try {
            c = Jwts.parser()
                    .setSigningKey(ClienteController.key)
                    .parseClaimsJws(token)
                    .getBody();

        } catch (JwtException e) {
            throw new Exception("Ocorreu um erro ao obter o id de carrinho");
        }
        String idCarrinho = "" + c.get("id_carrinho");

        try {
            id = Long.parseLong(idCarrinho);
        } catch (NumberFormatException e) {
            throw new Exception("Ocorreu um erro ao obter o id de carrinho");
        }

        return id;
    }
    
}
