/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.pi.controller;

import com.example.pi.model.Cliente;
import com.example.pi.model.Imagem;
import com.example.pi.services.ImagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author dp
 */
@RestController
@RequestMapping(value = "/imagem/admimAut")
public class ImagemController {
    
    @Autowired
    ImagemService imagemService;

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, value = "/admim")
    ResponseEntity cadastrarImagem(@RequestBody Imagem imagem) {

        imagemService.cadastrarImagem(imagem);

        return new ResponseEntity(HttpStatus.CREATED);

    }

    @RequestMapping(method = RequestMethod.DELETE,
            value = "/{id}/admim")
    ResponseEntity removerImagem(@PathVariable Long id) {

        imagemService.excluirImagem(id);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/admim")
    ResponseEntity editarImagem(@RequestBody Imagem imagem) {
        
        imagemService.editarImagem(imagem);
        
        return new ResponseEntity(HttpStatus.OK);
        
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Imagem> mostraImagem(@PathVariable Long id) {
        
        Imagem im = imagemService.buscaImagem(id);
        
        return new ResponseEntity(im, HttpStatus.OK);
    }
    
}
