/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.pi.services;

import com.example.pi.model.Admim;
import com.example.pi.repository.AdmimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author helde
 */
@Service
public class AdmimService {
    @Autowired
    AdmimRepository admimRepository;
    
    public void cadastraAdmim(Admim adm){
        admimRepository.save(adm);
    }
    
    public void excluirAdmim(Long id){
        admimRepository.deleteById(id);
    }
    
    public void editarAdmim(Admim adm){
        admimRepository.save(adm);
    }
    
    public Admim bauscarAdmim(Long id){
        return admimRepository.findById(id).get();
    }
    
    public Admim validarAdmim(Admim adm){
        return admimRepository.findByEmailAndSenha(adm.getEmail(), adm.getSenha());
    }
}
