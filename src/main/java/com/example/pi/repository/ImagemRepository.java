/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.pi.repository;

import com.example.pi.model.Imagem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author dp
 */
public interface ImagemRepository extends JpaRepository<Imagem, Long> {
    
}
