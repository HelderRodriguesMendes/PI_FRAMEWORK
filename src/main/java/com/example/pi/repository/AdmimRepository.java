/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.pi.repository;

import com.example.pi.model.Admim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author helde
 */
public interface AdmimRepository extends JpaRepository<Admim, Long>{
    
    public Admim findByEmailAndSenha(@Param("email")String nome,
                                      @Param("seha")String senha);
    
}
