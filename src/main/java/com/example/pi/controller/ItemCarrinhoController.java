/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.pi.controller;

import com.example.pi.model.ItemCarrinho;
import com.example.pi.services.ItemCarrinhoService;
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
@RequestMapping(value = "itemCarrinhoController")
public class ItemCarrinhoController {
    
    @Autowired
    ItemCarrinhoService itemCarrinhoService;

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, value = "/admim" )
    ResponseEntity cadastrarItemCarrinho(@RequestBody ItemCarrinho itemCarrinho) {

        itemCarrinhoService.cadastrarItemCarrinho(itemCarrinho);

        return new ResponseEntity(HttpStatus.CREATED);

    }

    @RequestMapping(method = RequestMethod.DELETE,
            value = "/{id}/admim")
    ResponseEntity removerItemCarrinho(@PathVariable Long id) {

        itemCarrinhoService.excluirItemCarrinho(id);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/admim") 
    ResponseEntity editarItemCarrinho(@RequestBody ItemCarrinho itemCarrinho) {
        
        itemCarrinhoService.editarItemCarrinho(itemCarrinho);
        
        return new ResponseEntity(HttpStatus.OK);
        
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ItemCarrinho> mostraItemCarrinho(@PathVariable Long id) {
        
        ItemCarrinho pr = itemCarrinhoService.buscaItemCarrinho(id);
        
        return new ResponseEntity(pr, HttpStatus.OK);
    }
    
}
