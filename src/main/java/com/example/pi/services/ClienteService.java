/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.pi.services;

import com.example.pi.model.Cliente;
import com.example.pi.repository.ClienteRepository;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author dp
 */
@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    
    public void cadastrarCliente(Cliente cli) {
        clienteRepository.save(cli);
    }

    public void editarCliente(Cliente cli) {
        clienteRepository.save(cli);
    }

    public void excluirCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    public Cliente buscaCliente(Long id) {
        return clienteRepository.findById(id).get();
    }
    
    public Cliente validarCliente(Cliente c){
        return clienteRepository.findByEmailAndSenha(c.getEmail(), c.getSenha());
    }

}
