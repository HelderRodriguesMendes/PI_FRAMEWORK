/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.pi.controller;

import com.example.pi.model.Cliente;
import com.example.pi.model.Imagem;
import com.example.pi.model.Produto;
import com.example.pi.services.ImagemService;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author dp
 */
@RestController
@RequestMapping(value = "/imagem")
public class ImagemController {
    
    @Autowired
    ImagemService imagemService;

    @RequestMapping(method = RequestMethod.POST, value = "/admim/{id}")
    public ResponseEntity cadastrar(@PathVariable Long id, @RequestPart(value = "file", required = false)
    MultipartFile[] files) {
        
        Produto produto = new Produto();
        
        produto.setId(id);
        
        for (MultipartFile file : files) {
            
            try {
                imagemService.salvarImagem(file, produto);
            } catch (IOException e) {
                return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            
        }

        return new ResponseEntity(HttpStatus.CREATED);

    }

    @RequestMapping(method = RequestMethod.GET, 
            value = "/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<Resource> consultar(@PathVariable Long id) {
        
        Resource resource;
        
        try {
            resource = imagemService.consultarImagem(id);
        } catch (NoSuchElementException | FileNotFoundException e) {
            
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(resource, HttpStatus.OK);

    }
    
}
