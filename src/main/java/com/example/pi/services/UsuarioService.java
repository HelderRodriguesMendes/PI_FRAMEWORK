/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.pi.services;

import com.example.pi.model.Usuario;
import com.example.pi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author helde
 */
@Service
public class UsuarioService {
    
    @Autowired
    UsuarioRepository usuarioRepository;
    
    public void cadastrarUsuario(Usuario usu){
        usuarioRepository.save(usu);
    }
    
    public void excluirUsuario(Long id){
        usuarioRepository.deleteById(id);
    }
    
    public void editarUsuario(Usuario usu){
        usuarioRepository.save(usu);
    }
    
    public Usuario buscaUsuario(Long id){
        return usuarioRepository.findById(id).get();
    }
}
